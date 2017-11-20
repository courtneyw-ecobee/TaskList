package com.example.courtneyw.tasklist.task.details;

import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

/**
 * Created by courtney.w on 11/10/17.
 */

@ActivityScope
public class SelectDateFeature implements TaskDetailFeature {

    private final TaskDetailView view;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    SelectDateFeature(TaskDetailView view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.start();
        registerSubscription(view.listenToDateSelectClick().subscribe(ignore -> view.openDatePicker(), Log::e));
    }

    @Override
    public void stop() {
        compositeDisposable.dispose();
        view.stop();
    }

    private void registerSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
