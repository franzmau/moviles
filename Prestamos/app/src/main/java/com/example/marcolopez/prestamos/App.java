package com.example.marcolopez.prestamos;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by MarcoLopez on 11/7/16.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "tzJOgpbzaGmT3RjU8LKjtyGrPlKVdiAAJKSZwZh9", "9b8ljbopzuz6JELOaDe2V58PSINt28Wg4o3HQ6Kt");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
