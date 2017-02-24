package com.jaek.widgit.Activities;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jaek.widgit.R;

public class TimerActivity extends AppCompatActivity {

    CountDownTimer timer;

    TextView timerText;

    EditText hoursInput;
    EditText minutesInput;
    EditText secondsInput;

    long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerText = (TextView) findViewById(R.id.timer_text);

        hoursInput = (EditText) findViewById(R.id.timer_hrs);
        minutesInput = (EditText) findViewById(R.id.timer_mins);
        secondsInput = (EditText) findViewById(R.id.timer_secs);

    }

    public void startPressed(View view) {
        if(timer!=null) {timer.cancel();}

        long hrs = Long.parseLong(hoursInput.getText().toString()) * 3600 * 1000;
        long mins = Long.parseLong(minutesInput.getText().toString()) * 60 * 1000;
        long secs = Long.parseLong(secondsInput.getText().toString()) * 1000;

        timer = new CountDownTimer(hrs + mins + secs, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secsUntilFinished = millisUntilFinished / 1000;

                int hrs = (int) Math.floor(secsUntilFinished / 3600);
                int mins = (int) Math.floor((secsUntilFinished - hrs * 3600) / 60);
                int secs = (int) Math.floor(secsUntilFinished - (hrs * 3600) - (mins * 60));

                String text = "";

                text += (hrs < 10) ? "0"+hrs+":" : hrs+":" ;
                text += (mins < 10) ? "0"+mins+":" : mins+":" ;
                text += (secs < 10) ? "0"+secs : secs ;

                timerText.setText(text);
            }

            @Override
            public void onFinish() {
                timerText.setText("00:00:00");
            }
        }.start();

    }

    public void pausePressed(View view) {

    }

    public void resetPressed(View view) {
        if(timer!=null) {timer.cancel();}

        String text = "";
        int hrs = Integer.parseInt(hoursInput.getText().toString());
        int mins = Integer.parseInt(minutesInput.getText().toString());
        int secs = Integer.parseInt(secondsInput.getText().toString());;

        text += (hrs < 10) ? "0"+hrs+":" : hrs+":" ;
        text += (mins < 10) ? "0"+mins+":" : mins+":" ;
        text += (secs < 10) ? "0"+secs : secs ;

        timerText.setText(text);

    }

}
