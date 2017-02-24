package com.jaek.widgit.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jaek.widgit.Activities.TodoActivity;
import com.jaek.widgit.Activities.WeatherActivity;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DashboardFragment extends Fragment {

    private Button[] buttons;

    public DashboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttons = new Button[MyApplication.widgets.length];

        Button b0 = (Button) view.findViewById(R.id.dashboard_button_weather);
        Button b1 = (Button) view.findViewById(R.id.dashboard_button_notekeeper);
        Button b2 = (Button) view.findViewById(R.id.dashboard_button_todolist);
        Button b3 = (Button) view.findViewById(R.id.dashboard_button_timer);
        Button b4 = (Button) view.findViewById(R.id.dashboard_button_calendar);

        buttons[0] = b0;
        buttons[1] = b1;
        buttons[2] = b2;
        buttons[3] = b3;
        buttons[4] = b4;

        if(MyApplication.user != null && MyApplication.user.getWidgets() != null) {
            for(int i = 0; i < buttons.length; i++) {
                if(!MyApplication.user.getWidgets().contains(Integer.toString(i))){
                    buttons[i].setVisibility(View.GONE);
                }
            }
        } else {
            for(Button b : buttons) {
                b.setVisibility(View.GONE);
            }
        }
    }




}
