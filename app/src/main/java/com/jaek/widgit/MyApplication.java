package com.jaek.widgit;

import android.app.Application;

import com.jaek.widgit.Models.User;
import com.jaek.widgit.Models.Widget;

/**
 * Created by jaek on 14/02/17.
 */

public class MyApplication extends Application{

    public static User user;
    public static Widget[] widgets = {
            new Widget("Weather", "Weather desc"),
            new Widget("Note Keeper", "nfids"),
            new Widget("Todo List", ""),
            new Widget("Countdown Timer", ""),
            new Widget("Calendar", "")
    };

}
