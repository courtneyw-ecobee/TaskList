package com.example.courtneyw.tasklist.task.list;

import android.util.Log;

import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.task.TaskListModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by courtney.w on 11/14/17.
 */
@ActivityScope
public class PopulateListData implements TaskFeature {

    private final TaskListView view;
    private final TaskListModel taskListModel;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    PopulateListData(TaskListView view, TaskListModel taskListModel){
        this.view = view;
        this.taskListModel = taskListModel;
    }

    @Override
    public void start() {
        registerSubscription(taskListModel.listen().subscribe(data -> view.setTaskList(data),
                e-> Log.e("ecobee ", "can't get task data", e)));
    }

    @Override
    public void stop() {
        compositeDisposable.dispose();
    }

    private void registerSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

}
