package com.example.courtneyw.tasklist.task.details;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by courtney.w on 11/17/17.
 */
public class SelectDateFeatureTest {

    private SelectDateFeature selectDateFeature;
    private TaskDetailView view;

    @Before
    public void setup(){
        view = mock(TaskDetailView.class);
        when(view.listenToDateSelectClick()).thenReturn(Observable.empty());
        selectDateFeature = new SelectDateFeature(view);
    }

    @Test
    public void date_picker_opens(){
        when(view.listenToDateSelectClick()).thenReturn(Observable.just(true));

        selectDateFeature.start();

        verify(view).openDatePicker();
    }

}