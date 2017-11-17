package com.example.courtneyw.tasklist.common;


import android.app.Activity;
import com.example.courtneyw.tasklist.application.ApplicationComponent;
import com.example.courtneyw.tasklist.application.MyApplication;

public class EcobeeActivity extends Activity {

    protected ApplicationComponent getComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

}
