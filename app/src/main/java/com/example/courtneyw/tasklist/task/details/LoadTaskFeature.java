package com.example.courtneyw.tasklist.task.details;

import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;
import com.example.courtneyw.tasklist.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.List;

/**
 * Created by courtney.w on 11/15/17.
 */


class LoadTaskFeature implements TaskDetailFeature {

    private final String id;
    private final TaskListModel taskListModel;
    private final TaskDetailView view;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    LoadTaskFeature(String id, TaskListModel taskListModel, TaskDetailView view) {
        this.id = id;
        this.taskListModel = taskListModel;
        this.view = view;
    }

    @Override
    public void start() {
        registerSubscription(taskListModel.listen().subscribe(this::getTaskFromListModel, Log::e));
    }

    @Override
    public void stop() {
        compositeDisposable.dispose();
    }

    private void registerSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private void getTaskFromListModel (List<TaskEntity> taskList){
        for (TaskEntity task : taskList) {
            if (task.getId().equals(id)) {
                view.preFillTaskInformation(task.getTitle(), task.getDate(), task.getDescription());
            }
        }

    }
}