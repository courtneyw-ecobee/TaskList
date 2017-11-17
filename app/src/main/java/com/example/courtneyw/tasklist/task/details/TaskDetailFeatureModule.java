package com.example.courtneyw.tasklist.task.details;

import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.task.TaskListModel;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

/**
 * Created by courtney.w on 11/10/17.
 */

@ActivityScope
@Module
public class TaskDetailFeatureModule {

    private final String taskId;

    public TaskDetailFeatureModule(@Nullable String taskId) {
        this.taskId = taskId;
    }

    @Provides
    public Set<TaskDetailFeature> featuresSet(Lazy<SaveTaskFeature> savetaskFeature,
                                              Lazy<SelectDateFeature> selectDateFeature,
                                              Lazy<LoadTaskFeature> loadTaskFeatureLazy,
                                              Lazy<UpdateTaskFeature> updateTaskFeatureLazy) {
        Set<TaskDetailFeature> featuresSet = new HashSet<>();

        if (taskId != null) {
            featuresSet.add(loadTaskFeatureLazy.get());
            featuresSet.add(updateTaskFeatureLazy.get());
        } else {
            featuresSet.add(savetaskFeature.get());
        }
        featuresSet.add(selectDateFeature.get());

        return featuresSet;
    }

    @ActivityScope
    @Provides
    LoadTaskFeature loadTaskFeature(TaskListModel taskListModel, TaskDetailView view) {
        return new LoadTaskFeature(taskId,taskListModel, view);
    }

    @ActivityScope
    @Provides
    UpdateTaskFeature updateTaskFeature(TaskListModel taskListModel, TaskDetailView view) {
        return new UpdateTaskFeature(taskId, taskListModel, view);
    }

}
