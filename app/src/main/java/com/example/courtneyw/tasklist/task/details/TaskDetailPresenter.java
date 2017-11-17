package com.example.courtneyw.tasklist.task.details;

import com.example.courtneyw.tasklist.dagger.ActivityScope;

import java.util.Set;

import javax.inject.Inject;

/**
 * Created by courtney.w on 11/8/17.
 */

@ActivityScope
public class TaskDetailPresenter {

    private final Set<TaskDetailFeature> taskDetailFeatures;

    @Inject
    TaskDetailPresenter(Set<TaskDetailFeature> taskDetailFeatures) {
        this.taskDetailFeatures = taskDetailFeatures;
    }

    void start(){
        for (TaskDetailFeature taskDetailFeature : taskDetailFeatures) {
            taskDetailFeature.start();
        }
    }

    void stop(){
        for (TaskDetailFeature taskDetailFeature : taskDetailFeatures) {
            taskDetailFeature.stop();
        }
    }
}
