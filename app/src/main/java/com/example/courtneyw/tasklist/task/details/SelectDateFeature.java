package com.example.courtneyw.tasklist.task.details;

import android.util.Log;

import com.example.courtneyw.tasklist.dagger.ActivityScope;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by courtney.w on 11/10/17.
 */

@ActivityScope
public class SelectDateFeature implements TaskDetailFeature {
    private final TaskDetailView view;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    SelectDateFeature(TaskDetailView view){
        this.view=view;
    }

    @Override
    public void start() {
        view.start();
        registerSubscription(view.listenToDateSelectClick().subscribe(ignore -> view.openDatePicker(),
                e ->Log.e("ecobee ", "can't open date calendar", e)));
//        registerSubscription(view.listenToDateSelected()
//                .map(DateEntity::convertDateToString)
//                .subscribe(view::updateDisplayDate,
//                        e-> Log.e("ecobee ", "can't convert date value", e)));
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
