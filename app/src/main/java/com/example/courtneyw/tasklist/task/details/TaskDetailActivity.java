package com.example.courtneyw.tasklist.task.details;

import android.app.Activity;
import android.os.Bundle;

import com.example.courtneyw.tasklist.R;
import com.example.courtneyw.tasklist.application.MyApplication;

import javax.inject.Inject;

/**
 * Created by courtney.w on 11/8/17.
 */

public class TaskDetailActivity extends Activity {

    @Inject
    TaskDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        String taskId = null;
        if (getIntent().getExtras()!= null) {
            taskId = getIntent().getStringExtra("taskId");
        }
        ((MyApplication) getApplication()).getApplicationComponent().withDetailComponentBuilder()
        .activity(this).taskDetailFeature(new TaskDetailFeatureModule(taskId)).build().injectMembers(this);
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }


}
