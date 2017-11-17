package com.example.courtneyw.tasklist.task.list;

import android.app.Activity;
import android.os.Bundle;

import com.example.courtneyw.tasklist.R;
import com.example.courtneyw.tasklist.application.MyApplication;
import com.example.courtneyw.tasklist.dagger.ActivityModule;

import javax.inject.Inject;


/**
 * Created by courtney.w on 11/7/17.
 */

public class TaskListActivity extends Activity {

    @Inject TaskListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getApplicationComponent().withListComponent(new ActivityModule(this)).injectMembers(this);
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }
}
