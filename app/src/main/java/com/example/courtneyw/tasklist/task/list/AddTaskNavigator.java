package com.example.courtneyw.tasklist.task.list;

import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;

import javax.inject.Inject;

import com.example.courtneyw.tasklist.util.Log;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by courtney.w on 11/8/17.
 */

@ActivityScope
public class AddTaskNavigator implements TaskFeature {

    private final TaskListView view;
    private final TaskListModel taskListModel;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    AddTaskNavigator(TaskListView view, TaskListModel taskListModel) {
        this.view = view;
        this.taskListModel = taskListModel;
    }

    @Override
    public void start() {
        view.start();
        registerSubscription(view.listenToFabClicks().subscribe(ignore -> view.openTaskDetails(), Log::e));
        registerSubscription(view.listenToCardClick()
                .flatMap(this::getTaskToUpdate)
                .subscribe(view::updateTaskNavigation, Log::e));
    }

    @Override
    public void stop() {
        compositeDisposable.dispose();
    }

    private void registerSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private Observable<TaskEntity> getTaskToUpdate(int position) {
        return taskListModel.listen().map(list -> list.get(position));

    }
}
