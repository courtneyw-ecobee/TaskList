package com.example.courtneyw.tasklist.task.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.courtneyw.tasklist.R;
import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.task.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by courtney.w on 11/14/17.
 */

@ActivityScope
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    private List<TaskEntity> taskList = new ArrayList<>();
    private final PublishSubject<Integer> cardItemClick = PublishSubject.create();

    public PublishSubject<Integer> listenToCardClick() {
        return cardItemClick;
    }

    @Inject
    TaskListAdapter() {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_name)
        TextView nameText;
        @BindView(R.id.task_date)
        TextView dateText;
        @BindView(R.id.task_description)
        TextView descText;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_task, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameText.setText(taskList.get(position).getTitle());
        holder.dateText.setText(taskList.get(position).getDate());
        holder.descText.setText(taskList.get(position).getDescription());

        holder.itemView.setOnClickListener(v -> cardItemClick.onNext(position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void setData(List<TaskEntity> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }
}
