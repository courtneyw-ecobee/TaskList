package com.example.courtneyw.tasklist.task;

import com.example.courtneyw.tasklist.dagger.AppScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by courtney.w on 11/13/17.
 */

@AppScope
public class TaskListModel {

    //people will subscribe to this task
    private final BehaviorSubject<List<TaskEntity>> task = BehaviorSubject.createDefault(Collections.emptyList());
    private final List<TaskEntity> taskList = new ArrayList<>();

    @Inject
    TaskListModel() {
    }

    //for "updating" find one by the ID itself, remove it, then add it with the same id
    public void updateTaskToList(TaskEntity task) {
        for (TaskEntity taskEntity : taskList) {
            if (taskEntity.getId().equals(task.getId())) {
                taskList.remove(taskEntity);
            }
        }
        taskList.add(task);
        report();
    }

    private void report() {
        //this is unmodifiable so that other people can't change it, only this class should be in charge of this list
        //unmodifiable helps with concurrency
        task.onNext(Collections.unmodifiableList(taskList)); //this will push out the new list
    }

    public Observable<List<TaskEntity>> listen() {
        return task;
    }
}
