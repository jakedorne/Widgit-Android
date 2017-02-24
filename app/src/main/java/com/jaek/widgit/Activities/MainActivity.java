package com.jaek.widgit.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaek.widgit.Fragments.AboutFragment;
import com.jaek.widgit.Fragments.CatalogueFragment;
import com.jaek.widgit.Fragments.ContactFragment;
import com.jaek.widgit.Fragments.DashboardFragment;
import com.jaek.widgit.Fragments.PrivacyFragment;
import com.jaek.widgit.Fragments.ProfileFragment;
import com.jaek.widgit.Models.User;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new DashboardFragment();

        fragmentTransaction.add(R.id.dashboard_fragment_container, fragment);
        fragmentTransaction.commit();

        FirebaseUser authUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = authUser.getUid();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        if (id == R.id.nav_about) {
            fragment = new AboutFragment();
        } else if (id == R.id.nav_contact) {
            fragment = new ContactFragment();
        } else if (id == R.id.nav_privacy) {
            fragment = new PrivacyFragment();
        }

        transaction.replace(R.id.dashboard_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


    // dashboard buttons

    public void todoButtonPressed(View v) {
        Intent intent = new Intent(getApplicationContext(), TodoActivity.class);
        startActivity(intent);
    }

    public void weatherButtonPressed(View v) {
        Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
        startActivity(intent);
    }

    public void timerButtonPressed(View v) {
        Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
        startActivity(intent);
    }

}
