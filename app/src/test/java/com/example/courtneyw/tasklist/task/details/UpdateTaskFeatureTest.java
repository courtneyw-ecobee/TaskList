package com.example.courtneyw.tasklist.task.details;

import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by courtney.w on 11/17/17.
 */
public class UpdateTaskFeatureTest {

    private UpdateTaskFeature updateTaskFeature;
    private TaskDetailView view;
    private TaskListModel taskListModel;
    private String taskId = "1";

    @Before
    public void setup(){
        view = mock(TaskDetailView.class);
        taskListModel = mock(TaskListModel.class);
        when(view.listenToSaveClick()).thenReturn(Observable.empty());
        when(view.getTitleText()).thenReturn("a");
        when(view.getDescriptionText()).thenReturn("b");
        when(view.getDateText()).thenReturn("");
        updateTaskFeature = new UpdateTaskFeature(taskId, taskListModel, view);
    }

    @Test
    public void task_update_success (){
        when(view.listenToSaveClick()).thenReturn(Observable.just(true));
        when(view.getDateText()).thenReturn("c");

        TaskEntity taskEntity = new TaskEntity(view.getTitleText(), view.getDescriptionText(), view.getDateText(), taskId);
        taskListModel.updateTaskToList(taskEntity);

        updateTaskFeature.start();
        verify(view).navigateToList();
    }

    @Test
    public void task_update_fail() {
        when(view.listenToSaveClick()).thenReturn(Observable.just(true));
        when(view.getDateText()).thenReturn("Date");

        updateTaskFeature.start();
        verify(view).showErrorMessage();
    }
}