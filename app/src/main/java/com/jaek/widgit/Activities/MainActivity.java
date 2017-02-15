package com.jaek.widgit.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaek.widgit.Fragments.CatalogueFragment;
import com.jaek.widgit.Fragments.DashboardFragment;
import com.jaek.widgit.Fragments.ProfileFragment;
import com.jaek.widgit.Models.User;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new DashboardFragment();

        fragmentTransaction.add(R.id.dashboard_fragment_container, fragment);
        fragmentTransaction.commit();

        FirebaseUser authUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = authUser.getUid();

        DatabaseReference dbUser = FirebaseDatabase.getInstance().getReference("users/"+uid);

        dbUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MyApplication.user = dataSnapshot.getValue(User.class);
                // TODO: POPULATE FRAGMENT / POSSIBLE RECYCLER VIEW WITH USER WIDGETS??
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_logout:
                logoutPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logoutPressed() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void dashboardPressed(View view) {
        Fragment fragment = new DashboardFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dashboard_fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void cataloguePressed(View view) {
        Fragment fragment = new CatalogueFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dashboard_fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void profilePressed(View view) {
        Fragment fragment = new ProfileFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dashboard_fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
