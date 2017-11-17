package com.example.courtneyw.tasklist.task.list;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by courtney.w on 11/9/17.
 */

public class TaskListPresenterTest {

    private TaskListPresenter presenter;
    private TaskFeature taskFeature;

    @Before
    public void setup(){
        taskFeature = mock(TaskFeature.class);
        Set<TaskFeature> taskFeatures = new HashSet<>();
        taskFeatures.add(taskFeature);
        presenter = new TaskListPresenter(taskFeatures);
    }

    @Test
    public void presenter_starts(){
        presenter.start();

        verify(taskFeature).start();
    }

    @Test
    public void presenter_stops(){
        presenter.stop();
        verify(taskFeature).stop();
    }
}
