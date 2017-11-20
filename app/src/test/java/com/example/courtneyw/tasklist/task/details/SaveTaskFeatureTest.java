package com.example.courtneyw.tasklist.task.details;

import com.example.courtneyw.tasklist.task.TaskEntity;
import com.example.courtneyw.tasklist.task.TaskListModel;
import io.reactivex.Observable;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

/**
 * Created by courtney.w on 11/17/17.
 */
public class SaveTaskFeatureTest {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Mock TaskDetailView view;
    @Mock TaskListModel taskListModel;

    @InjectMocks SaveTaskFeature saveTaskFeature;

    @Before
    public void setup() {
        stubViewTextFields("", "", "");
        stubSaveClicks(Observable.empty());
    }

    @Test
    public void task_saved_success() {
        stubViewTextFields("a", "b", "c");
        stubSaveClicks(Observable.just(true));

        saveTaskFeature.start();

        verify(taskListModel).updateTaskToList(argThat(new TaskMatcher("a", "b", "c")));
    }

    @Test
    public void when_task_saved_user_is_taken_to_list() {
        stubViewTextFields("a", "b", "c");
        stubSaveClicks(Observable.just(true));

        saveTaskFeature.start();

        verify(view).navigateToList();
    }

    @Test
    public void task_saved_failed_when_title_is_empty() {
        stubViewTextFields("", "b", "c");
        stubSaveClicks(Observable.just(true));

        saveTaskFeature.start();

        verify(view).showErrorMessage();
    }

    @Test
    public void task_saved_failed_when_description_is_empty() {
        stubViewTextFields("a", "", "c");
        stubSaveClicks(Observable.just(true));

        saveTaskFeature.start();

        verify(view).showErrorMessage();
    }

    @Test
    public void task_saved_fail_when_date_is_empty() {
        stubViewTextFields("a", "b", "");
        stubSaveClicks(Observable.just(true));

        saveTaskFeature.start();

        verify(view).showErrorMessage();
    }

    @Test
    public void task_saved_fail_when_date_is_date() {
        stubViewTextFields("a", "b", "date");
        stubSaveClicks(Observable.just(true));

        saveTaskFeature.start();

        verify(view).showErrorMessage();
    }

    private void stubViewTextFields(String title, String description, String date) {
        when(view.getTitleText()).thenReturn(title);
        when(view.getDescriptionText()).thenReturn(description);
        when(view.getDateText()).thenReturn(date);
    }

    private void stubSaveClicks(Observable<Boolean> clicks) {
        when(view.listenToSaveClick()).thenReturn(clicks);
    }

    private static class TaskMatcher extends TypeSafeMatcher<TaskEntity> {

        private final String title;
        private final String description;
        private final String date;

        private TaskMatcher(String title, String description, String date) {
            this.title = title;
            this.description = description;
            this.date = date;
        }


        @Override
        protected boolean matchesSafely(TaskEntity item) {
            return item.getTitle().equals(title)
                    && item.getDescription().equals(description)
                    && item.getDate().equals(date);
        }

        @Override
        public void describeTo(Description description) {
        }
    }

}