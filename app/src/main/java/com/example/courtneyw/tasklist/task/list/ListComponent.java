package com.example.courtneyw.tasklist.task.list;

import com.example.courtneyw.tasklist.dagger.ActivityModule;
import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.task.details.TaskDetailActivity;

import dagger.Subcomponent;

/**
 * Created by courtney.w on 11/7/17.
 */

@ActivityScope
@Subcomponent(modules = {ActivityModule.class, TaskFeatureModule.class})
public interface ListComponent {
    void injectMembers(TaskListActivity target);
}
