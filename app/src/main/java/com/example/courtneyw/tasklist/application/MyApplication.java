package com.example.courtneyw.tasklist.application;

import android.app.Application;

/**
 * Created by courtney.w on 11/7/17.
 */

public class MyApplication extends Application{
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.create();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
