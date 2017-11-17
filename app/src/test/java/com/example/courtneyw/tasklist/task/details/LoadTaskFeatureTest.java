package com.example.courtneyw.tasklist.task.details;

import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by courtney.w on 11/17/17.
 */
public class LoadTaskFeatureTest {

    private TaskDetailView view;
    private LoadTaskFeature loadTaskFeature;
    private TaskListModel taskListModel;
    private String id = "123";

    @Before
    public void setup(){
        view = mock(TaskDetailView.class);
        taskListModel = mock(TaskListModel.class);
        loadTaskFeature = new LoadTaskFeature( id, taskListModel, view);
    }

    @Test
    public void task_is_loaded_success(){
        List<TaskEntity> list = new ArrayList<>();
        TaskEntity taskEntity = new TaskEntity("a", "b", "c", id);
        list.add(taskEntity);

        when(taskListModel.listen()).thenReturn(Observable.just(list));

        loadTaskFeature.start();
        verify(view).preFillTaskInformation(taskEntity.getTitle(), taskEntity.getDate(), taskEntity.getDescription());
    }

    @Test
    public void task_is_not_successful(){
        List<TaskEntity> list = new ArrayList<>();
        TaskEntity taskEntity = new TaskEntity("a", "b", "c", "d");
        list.add(taskEntity);

        when(taskListModel.listen()).thenReturn(Observable.just(list));

        loadTaskFeature.start();
        verifyZeroInteractions(view);
    }

}