package com.jaek.widgit.Fragments;

import android.app.Application;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.jaek.widgit.Activities.LoginActivity;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CatalogueFragment extends Fragment {

    private Button[] buttons;

    protected int index;

    protected DatabaseReference db;
    protected FirebaseAuth auth;

    private SearchView search;

    public CatalogueFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_catalogue, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
        search = (SearchView) view.findViewById(R.id.catalogue_search);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                for (Button b: buttons) {
                    if (b.getText().toString().toLowerCase().contains(newText.toLowerCase())) {
                        b.setVisibility(View.VISIBLE);
                    } else {
                        b.setVisibility(View.GONE);
                    }
                }
                return false;
            }
        });
        buttons = new Button[MyApplication.widgets.length];

        Button b0 = (Button) view.findViewById(R.id.catalogue_button_weather);
        Button b1 = (Button) view.findViewById(R.id.catalogue_button_notekeeper);
        Button b2 = (Button) view.findViewById(R.id.catalogue_button_todolist);
        Button b3 = (Button) view.findViewById(R.id.catalogue_button_timer);
        Button b4 = (Button) view.findViewById(R.id.catalogue_button_calendar);

        buttons[0] = b0;
        buttons[1] = b1;
        buttons[2] = b2;
        buttons[3] = b3;
        buttons[4] = b4;

        configureButtons();

    }

    public void configureButtons() {
        for (index = 0; index < buttons.length; index++) {
            buttons[index].setOnClickListener(new View.OnClickListener() {

                int widgetIndex = index;

                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Purchase?")
                            .setMessage("Cost: 200 points")
                            .setCancelable(true)
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    // use index to add widget to user widgets
                                    String uid = auth.getCurrentUser().getUid();
                                    System.out.println("JAKE: "+ uid);

                                    if(MyApplication.user.getCurrency() >= 200) {
                                        List<String> widgets = (MyApplication.user.getWidgets()!=null) ? MyApplication.user.getWidgets() : new ArrayList<String>();
                                        if(widgets.contains(Integer.toString(widgetIndex))) {
                                            Toast.makeText(getActivity(), "You already own this widget!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        widgets.add(Integer.toString(widgetIndex));
                                        Collections.sort(widgets);
                                        db.child("users/"+uid+"/widgets").setValue(widgets);
                                        db.child("users/"+uid+"/currency").setValue(MyApplication.user.getCurrency() - 200);
                                    } else {
                                        Toast.makeText(getActivity(), "You can't afford this widget!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }


}
