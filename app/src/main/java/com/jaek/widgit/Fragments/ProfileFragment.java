package com.jaek.widgit.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;

public class ProfileFragment extends Fragment {

    EditText firstname;
    EditText lastname;

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

        firstname = (EditText) view.findViewById(R.id.profile_firstname);
        lastname = (EditText) view.findViewById(R.id.profile_lastname);
        firstname.setText(MyApplication.user.getFirstname());
        lastname.setText(MyApplication.user.getLastname());

    }

    public void updateProfile(View view) {
        DatabaseReference ref = dbRef.child("users").child(auth.getCurrentUser().getUid());
        ref.child("firstname").setValue(firstname.getText().toString());
        ref.child("lastname").setValue(lastname.getText().toString());

    }
}
