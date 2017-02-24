package com.jaek.widgit.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;

public class ProfileFragment extends Fragment {

    EditText firstname;
    EditText lastname;
    EditText country;
    EditText city;
    TextView currency;

    FirebaseAuth auth;
    DatabaseReference dbRef;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        currency = (TextView) view.findViewById(R.id.profile_currency);
        currency.setText("Points: "+MyApplication.user.getCurrency());
        firstname = (EditText) view.findViewById(R.id.profile_firstname);
        lastname = (EditText) view.findViewById(R.id.profile_lastname);
        country = (EditText) view.findViewById(R.id.profile_country);
        city = (EditText) view.findViewById(R.id.profile_city);

        while(MyApplication.user == null) {
            // this is bad
        }

        firstname.setText(MyApplication.user.getFirstname());
        lastname.setText(MyApplication.user.getLastname());
        country.setText(MyApplication.user.getCountry());
        city.setText(MyApplication.user.getCity());

        Button submitButton = (Button) view.findViewById(R.id.profile_update_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile(v);
            }
        });

    }

    public void updateProfile(View view) {
        String fn = firstname.getText().toString();
        String ln = lastname.getText().toString();
        String cn = country.getText().toString();
        String ct = city.getText().toString();

        if(fn.equals(null) || ln.equals(null) || cn.equals(null) || ct.equals(null)) {
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference ref = dbRef.child("users").child(auth.getCurrentUser().getUid());
        ref.child("firstname").setValue(fn);
        ref.child("lastname").setValue(ln);
        ref.child("country").setValue(cn);
        ref.child("city").setValue(ct);

    }
}
