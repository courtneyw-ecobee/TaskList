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
 * Created by courtney.w on 11/17/17.
 */
public class PopulateListDataTest {

    private TaskListView view;
    private PopulateListData populateListData;
    private TaskListModel taskListModel;

    @Before
    public void setup (){
        view = mock(TaskListView.class);
        taskListModel = mock(TaskListModel.class);
        populateListData = new PopulateListData(view, taskListModel);
    }

    @Test
    public void task_data_set(){
        TaskEntity taskEntity = new TaskEntity("a", "b", "c", "d");
        List<TaskEntity> taskList = new ArrayList<>();
        taskList.add(taskEntity);

        when(taskListModel.listen()).thenReturn(Observable.just(taskList));

        populateListData.start();
        verify(view).setTaskList(taskList);
    }

}