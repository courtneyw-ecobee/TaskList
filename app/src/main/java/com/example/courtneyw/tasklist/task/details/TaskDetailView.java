package com.example.courtneyw.tasklist.task.details;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.courtneyw.tasklist.R;
import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.task.DateEntity;
import com.example.courtneyw.tasklist.task.list.TaskListActivity;

import java.util.Calendar;

import io.reactivex.Observable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by courtney.w on 11/8/17.
 */

@ActivityScope
public class TaskDetailView implements DatePickerDialog.OnDateSetListener{
    @BindView(R.id.task_title) EditText title;
    @BindView(R.id.task_description) EditText description;
    @BindView(R.id.task_date_picker) TextView datePicker;
    @BindView(R.id.task_save) Button save;
    @BindView(R.id.error_message) TextView errorMessage;

    private final Activity activity;
    private final PublishSubject<Boolean> saveClicks = PublishSubject.create();
    private final PublishSubject<Boolean> dateSelectClicks = PublishSubject.create();
    private PublishSubject<DateEntity> dateSelected = PublishSubject.create();
    private final MyBroadcastReceiver receiver = new MyBroadcastReceiver();

    private IntentFilter intentFilter = new IntentFilter("broadcastDate");

    @Inject
    TaskDetailView(Activity activity) {
        this.activity = activity;
    }

    Observable<Boolean> listenToSaveClick() {
        return saveClicks;
    }

    Observable<Boolean> listenToDateSelectClick() {
        return dateSelectClicks;
    }

    void start() {
        ButterKnife.bind(this, activity);
        setClickListeners();
        activity.registerReceiver(receiver, intentFilter);
    }

    void stop(){
        try {
            activity.unregisterReceiver(receiver);
        }
        catch (Exception e){
            Log.e("receiver ", "receiver already unregistered");
        }
    }



    private void setClickListeners(){
        datePicker.setOnClickListener(v -> dateSelectClicks.onNext(true));
        save.setOnClickListener(v ->saveClicks.onNext(true));
    }

    String getTitleText(){
        return title.getText().toString();
    }

    public String getDescriptionText() {
        return description.getText().toString();
    }

    public String getDateText(){
        return datePicker.getText().toString();
    }

    public void openDatePicker() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(activity.getFragmentManager(), "datePicker");
    }

    public void updateDisplayDate(int month, int day, int year){
        datePicker.setText(month + "/" + day + "/" + year);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        DateEntity dateEntity = new DateEntity(day, month, year);
        dateSelected.onNext(dateEntity); //pushes it to subscribed
    }

    public void prefillTaskInformation(String name, String date, String descriptionText) {
        title.setText(name);
        datePicker.setText(date);
        description.setText(descriptionText);
    }

    //have a method to receive broadcast receiver then give to onDateSet,
    public class MyBroadcastReceiver extends BroadcastReceiver {
        private static final String TAG = "MyBroadcastReceiver";
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("broadcastDate")) {
                int yearReceived = intent.getIntExtra("year", -1);
                int dateReceived = intent.getIntExtra("day", -1);
                int monthReceived = intent.getIntExtra("month", -1);
                updateDisplayDate(monthReceived, dateReceived, yearReceived);
            }
        }
    }


    //put this in a generic package
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            //read id

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Intent broadcastIntent = new Intent("broadcastDate");
            broadcastIntent.putExtra("year", year);
            broadcastIntent.putExtra("month", month);
            broadcastIntent.putExtra("day", day);
            getActivity().sendBroadcast(broadcastIntent);

        }

    }

    public void showErrorMessage(){
        errorMessage.setText("Ensure the title, description and date are all filled in.");
    }

    public void navigateToList(){
        activity.startActivity(new Intent(activity, TaskListActivity.class));
        activity.finish();

    }

}
