package com.example.courtneyw.tasklist.task.list;

import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by courtney.w on 11/8/17.
 */

public class AddTaskNavigatorTest {

    private TaskListView view;
    private AddTaskNavigator navigator;
    private TaskListModel taskListModel;

    @Before
    public void setup(){
        view = mock(TaskListView.class);
        taskListModel = mock(TaskListModel.class);
        when(view.listenToFabClicks()).thenReturn(Observable.empty());
        when(view.listenToCardClick()).thenReturn(Observable.empty());
        navigator = new AddTaskNavigator(view, taskListModel);
    }

    @Test
    public void login_click_reported(){
        //stubbing/mocking
        when(view.listenToFabClicks()).thenReturn(Observable.just(true));
        //dostuff
        navigator.start();
        //verifying
        verify(view).openTaskDetails();
    }

    @Test
    public void start_called_then_view_started() {
        //stubbing

        //dostuff
        navigator.start();

        //verifying
        verify(view).start();
    }

    @Test
    public void card_click_reported(){
        //make sure it's clicked
        when(view.listenToCardClick()).thenReturn(Observable.just(1));

        TaskEntity taskEntity = new TaskEntity("a", "b", "c", "d");
        List<TaskEntity> entityList = new ArrayList<>();
        entityList.add(null);
        entityList.add(taskEntity);

        when(taskListModel.listen()).thenReturn(Observable.just(entityList));

        navigator.start();
        verify(view).updateTaskNavigation(taskEntity);
        //make sure that the task is passed
    }


}
