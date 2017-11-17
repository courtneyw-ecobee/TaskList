package com.example.courtneyw.tasklist.task.details;

import android.util.Log;

import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by courtney.w on 11/16/17.
 */

public class UpdateTaskFeature implements TaskDetailFeature {
    private final String taskId;

    private final TaskListModel taskListModel;

    private final TaskDetailView view;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public UpdateTaskFeature(String taskId, TaskListModel taskListModel, TaskDetailView view) {
        this.taskId = taskId;
        this.taskListModel = taskListModel;
        this.view = view;
    }

    @Override
    public void start() {
        view.start();
        registerSubscription(view.listenToSaveClick().subscribe(ignore -> getTaskText(),
                e-> Log.e("ecobee ", "can't update task", e)));
    }

    @Override
    public void stop() {
        compositeDisposable.dispose();
        view.stop();
    }

    private void registerSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private void getTaskText(){
        if (!view.getTitleText().equals("") && !view.getDescriptionText().equals("") && !view.getDateText().equals("Date")) {
            TaskEntity task = new TaskEntity(view.getTitleText(), view.getDescriptionText(), view.getDateText(), taskId);
            taskListModel.updateTaskToList(task);
            view.navigateToList();
        } else {
            view.showErrorMessage();
        }
    }

}
