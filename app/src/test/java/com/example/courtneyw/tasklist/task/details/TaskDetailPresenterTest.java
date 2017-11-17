package com.example.courtneyw.tasklist.task.details;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by courtney.w on 11/10/17.
 */
public class TaskDetailPresenterTest {

    private TaskDetailPresenter presenter;
    private TaskDetailFeature taskDetailFeature;

    @Before
    public void setup(){
        taskDetailFeature = mock(TaskDetailFeature.class);
        Set<TaskDetailFeature> taskDetailFeatures = new HashSet<>();
        taskDetailFeatures.add(taskDetailFeature);
        presenter = new TaskDetailPresenter(taskDetailFeatures);
    }

    @Test
    public void presenter_starts(){
        presenter.start();

        verify(taskDetailFeature).start();
    }

    @Test
    public void presenter_stops(){
        presenter.stop();

        verify(taskDetailFeature).stop();
    }

}