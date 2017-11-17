package com.example.courtneyw.tasklist.dagger;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by courtney.w on 11/7/17.
 */

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity activity(){
        return activity;
    }
}
