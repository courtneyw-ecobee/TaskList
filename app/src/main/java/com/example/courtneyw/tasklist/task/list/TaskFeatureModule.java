package com.example.courtneyw.tasklist.task.list;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

/**
 * Created by courtney.w on 11/8/17.
 */

@Module
public interface TaskFeatureModule {
    @Binds
    @IntoSet
    TaskFeature addTaskNavigator(AddTaskNavigator addTaskNavigator);
    @Binds
    @IntoSet
    TaskFeature populateListData(PopulateListData populateListData);
}
