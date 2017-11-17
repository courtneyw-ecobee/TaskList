package com.example.courtneyw.tasklist.task.list;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.courtneyw.tasklist.R;
import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.details.TaskDetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@ActivityScope
class TaskListView {
    @BindView(R.id.task_list_recyclerview) RecyclerView recyclerView;
    @BindView(R.id.floating_action_button) FloatingActionButton fabBtn;

    private final Activity activity;
    private final PublishSubject<Boolean> fabClicks = PublishSubject.create();
    private final  TaskListAdapter adapter;

    @Inject
    TaskListView(Activity activity, TaskListAdapter adapter) {
        this.activity = activity;
        this.adapter = adapter;
    }

    void start () {
        ButterKnife.bind(this, activity);
        setClickListeners();

        //setup recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    Observable<Boolean> listenToFabClicks(){
        return fabClicks;
    }

    private void setClickListeners() {
        fabBtn.setOnClickListener(v -> fabClicks.onNext(true));
    }


    public void openTaskDetails() {
        activity.startActivity(new Intent(activity, TaskDetailActivity.class));
        activity.finish();
    }

    public void updateTaskNavigation(TaskEntity taskEntity){
        Intent intent = new Intent(activity, TaskDetailActivity.class);
        intent.putExtra("taskId", taskEntity.getId());
        activity.startActivity(intent);
        activity.finish();
    }

    public void setTaskList(List<TaskEntity> taskList){
        adapter.setData(taskList);
    }

    public Observable<Integer> listenToCardClick() {
        return adapter.listenToCardClick();
    }
}
