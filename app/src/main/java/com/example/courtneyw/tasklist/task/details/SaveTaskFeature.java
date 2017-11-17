package com.example.courtneyw.tasklist.task.details;

import android.annotation.SuppressLint;
import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;
import com.example.courtneyw.tasklist.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by courtney.w on 11/10/17.
 */

@ActivityScope
public class SaveTaskFeature implements TaskDetailFeature {

    private final TaskDetailView view;
    private final TaskListModel taskListModel;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    SaveTaskFeature(TaskDetailView view, TaskListModel taskListModel) {
        this.view = view;
        this.taskListModel = taskListModel;
    }

    @Override
    public void start() {
        view.start();
        registerSubscription(view.listenToSaveClick().subscribe(ignore -> getTaskText(), Log::e));
    }

    @Override
    public void stop() {
        compositeDisposable.dispose();
        view.stop();
    }

    private void registerSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @SuppressLint("SimpleDateFormat")
    private void getTaskText() {
        String titleText = view.getTitleText();
        String descriptionText = view.getDescriptionText();
        String dateText = view.getDateText();

        boolean isDateValueValid = !dateText.isEmpty() && !dateText.equalsIgnoreCase("date");

        if (!titleText.isEmpty() && !descriptionText.isEmpty() && isDateValueValid) {
            TaskEntity task = new TaskEntity(titleText, descriptionText, dateText, UUID.randomUUID().toString());

            taskListModel.updateTaskToList(task);
            view.navigateToList();
        } else {
            view.showErrorMessage();
        }
    }
}
