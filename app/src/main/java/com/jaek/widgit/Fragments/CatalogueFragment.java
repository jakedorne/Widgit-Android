package com.jaek.widgit.Fragments;

import android.app.Application;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jaek.widgit.Activities.LoginActivity;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;


public class CatalogueFragment extends Fragment {

    private Button[] buttons;

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
        for (int index = 0; index < buttons.length; index++) {
            buttons[index].setOnClickListener(new View.OnClickListener() {

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
