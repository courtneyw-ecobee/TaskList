package com.example.courtneyw.tasklist.task.list;

import com.example.courtneyw.tasklist.dagger.ActivityScope;

import java.util.Set;

import javax.inject.Inject;

/**
 * Created by courtney.w on 11/7/17.
 */

@ActivityScope
public class TaskListPresenter {

    private final Set<TaskFeature> taskFeatures;

    @Inject
    TaskListPresenter(Set<TaskFeature> taskFeatures){
        this.taskFeatures = taskFeatures;
    }

    void start(){
        for (TaskFeature taskFeature : taskFeatures) {
            taskFeature.start();
        }
    }

    void stop(){
        for (TaskFeature taskFeature : taskFeatures) {
            taskFeature.stop();
        }

    }
}
