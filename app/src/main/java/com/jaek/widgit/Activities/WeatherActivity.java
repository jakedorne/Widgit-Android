package com.jaek.widgit.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.vision.text.Text;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    protected ProgressDialog progressDialog;
    protected double lat = -41.2864603;
    protected double lon = 174.776236;

    protected String key = "64c7db9ec8944acc21fafe6560cbb68e";
    protected String baseURL = "http://api.openweathermap.org/data/2.5/weather?";

    protected TextView title;
    protected TextView description;
    protected TextView degrees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        title = (TextView) findViewById(R.id.weather_title);
        description = (TextView) findViewById(R.id.weather_description);
        degrees = (TextView) findViewById(R.id.weather_degrees);

        new WeatherTask().execute("");



        GoogleApiClient gac = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // ask permission i guess
        } else {
            // do shit idk
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    protected class WeatherTask extends AsyncTask<String, Void, JSONObject> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(WeatherActivity.this);
            progressDialog.setMessage("Retrieving weather data...");
            progressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {

            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(baseURL+"&q="+MyApplication.user.getCity()+"&units=metric&APPID="+key);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                connection.setRequestProperty("Content-Type", "application/json");
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while((line = bufferedReader.readLine()) !=null){
                    stringBuilder.append(line);
                }

                try {
                    JSONObject json = new JSONObject(stringBuilder.toString());
                    return (json);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.out.println("GOT A MOTHA FUCKIN ERROR: "+e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);
            if(progressDialog!=null){
                progressDialog.dismiss();
            }

            try {
                title.setText(json.getString("name"));
                description.setText(json.getJSONArray("weather").getJSONObject(0).getString("description"));
                degrees.setText(json.getJSONObject("main").getString("temp")+"C°");
            } catch (JSONException e) {
                System.out.println("well fuck: " +e);
            }


            // do something with result here?
        }
    }
}
