package com.example.courtneyw.tasklist.task.details;

import android.util.Log;

import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by courtney.w on 11/15/17.
 */


public class LoadTaskFeature implements TaskDetailFeature {
    private final String id;

    private final TaskListModel taskListModel;

    private final TaskDetailView view;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LoadTaskFeature(String id, TaskListModel taskListModel, TaskDetailView view) {
        this.id = id;
        this.taskListModel = taskListModel;
        this.view = view;
    }


    @Override
    public void start() {
        registerSubscription(taskListModel.listen().subscribe(data -> getTaskFromListModel(data),
                e-> Log.e("ecobee ", "can't get task list", e)));
    }

    @Override
    public void stop() {
        compositeDisposable.dispose();
    }

    private void registerSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private void getTaskFromListModel (List<TaskEntity> taskList){
        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).getId().equals(id)){
                TaskEntity taskEntity = taskList.get(i);
                view.prefillTaskInformation(taskEntity.getTitle(), taskEntity.getDate(), taskEntity.getDescription());
            }
        }

    }
}