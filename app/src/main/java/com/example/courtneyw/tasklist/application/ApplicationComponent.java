package com.example.courtneyw.tasklist.application;

import com.example.courtneyw.tasklist.dagger.ActivityModule;
import com.example.courtneyw.tasklist.dagger.AppScope;
import com.example.courtneyw.tasklist.task.details.DetailComponent;
import com.example.courtneyw.tasklist.task.list.ListComponent;

import dagger.Component;

/**
 * Created by courtney.w on 11/7/17.
 */

@Component
@AppScope
public interface ApplicationComponent {
    ListComponent withListComponent(ActivityModule module);
    DetailComponent.Builder withDetailComponentBuilder();
}
