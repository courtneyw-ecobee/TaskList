package com.example.courtneyw.tasklist.task.details;

import android.util.Log;

import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by courtney.w on 11/10/17.
 */

@ActivityScope
public class SaveTaskFeature implements TaskDetailFeature {

    private final TaskDetailView view;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final TaskListModel taskListModel;

    @Inject
    SaveTaskFeature(TaskDetailView view, TaskListModel taskListModel) {
        this.view = view;
        this.taskListModel = taskListModel;
    }
    @Override
    public void start() {
        view.start();
        registerSubscription(view.listenToSaveClick().subscribe(ignore -> getTaskText(),
                e-> Log.e("ecobee ", "can't save task", e)));
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
            SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
            String format = s.format(new Date());
            TaskEntity task = new TaskEntity(view.getTitleText(), view.getDescriptionText(), view.getDateText(), format);
            taskListModel.updateTaskToList(task);
            view.navigateToList();
        } else {
            view.showErrorMessage();
        }
    }
}
