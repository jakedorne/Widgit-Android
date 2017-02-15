package com.jaek.widgit.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaek.widgit.R;

public class WeatherActivity extends AppCompatActivity {

    protected ProgressDialog progressDialog;
    protected Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                setLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // TODO: need to check permissions
//        // Register the listener with the Location Manager to receive location updates
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    public void setLocation(Location l) {
        this.location = l;
    }

    protected class WeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(WeatherActivity.this);
            progressDialog.setMessage("Retrieving highscores...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(progressDialog!=null){
                progressDialog.dismiss();
            }

            // do something with result here?
        }
    }
}
