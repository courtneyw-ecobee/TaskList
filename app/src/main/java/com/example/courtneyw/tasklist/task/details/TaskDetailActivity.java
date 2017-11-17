package com.example.courtneyw.tasklist.task.details;

import android.os.Bundle;
import com.example.courtneyw.tasklist.R;
import com.example.courtneyw.tasklist.common.EcobeeActivity;

import javax.inject.Inject;

/**
 * Created by courtney.w on 11/8/17.
 */

public class TaskDetailActivity extends EcobeeActivity {

    @Inject
    TaskDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        getComponent()
                .withDetailComponentBuilder()
                .activity(this)
                .taskDetailFeature(new TaskDetailFeatureModule(getTaskIdFromIntent()))
                .build()
                .injectMembers(this);
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }


    private String getTaskIdFromIntent() {
        if (getIntent().getExtras() != null) {
            return getIntent().getStringExtra("taskId");
        }
        return null;
    }

}
