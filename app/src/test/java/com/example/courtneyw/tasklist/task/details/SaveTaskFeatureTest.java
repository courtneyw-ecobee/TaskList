package com.example.courtneyw.tasklist.task.details;

import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by courtney.w on 11/17/17.
 */
public class SaveTaskFeatureTest {

    private TaskDetailView view;
    private TaskListModel taskListModel;
    private SaveTaskFeature saveTaskFeature;

    @Before
    public void setup(){
        view = mock(TaskDetailView.class);
        taskListModel = mock(TaskListModel.class);
        when(view.getTitleText()).thenReturn("a");
        when(view.getDescriptionText()).thenReturn("b");
        when(view.getDateText()).thenReturn("");
        when(view.listenToSaveClick()).thenReturn(Observable.empty());
        saveTaskFeature = new SaveTaskFeature(view, taskListModel);
    }

    @Test
    public void task_saved_success(){
        when(view.listenToSaveClick()).thenReturn(Observable.just(true));
        when(view.getDateText()).thenReturn("c");

        TaskEntity taskEntity = new TaskEntity(view.getTitleText(), view.getDescriptionText(), view.getDateText(), "d");
        taskListModel.updateTaskToList(taskEntity);

        saveTaskFeature.start();

        verify(view).navigateToList();
    }

    @Test
    public void task_saved_fail() {
        when(view.listenToSaveClick()).thenReturn(Observable.just(true));
        when(view.getDateText()).thenReturn("Date");

        saveTaskFeature.start();
        verify(view).showErrorMessage();

    }

}