package com.example.todo2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todo2.R;
import com.example.todo2.model.TaskData;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TasksRecyclerView extends RecyclerView.Adapter<TasksRecyclerView.ViewHolder>{

    private List<TaskData> mTaskDatas;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public  TasksRecyclerView(Context context, List<TaskData> taskDatas) {
        this.mInflater = LayoutInflater.from(context);
        this.mTaskDatas = taskDatas;
    }

    // inflates the row layout from xml when needed

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskData taskData = mTaskDatas.get(position);
        holder.myTextView.setText(taskData.getName());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mTaskDatas.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.nameView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    TaskData getItem(int id) {
        return mTaskDatas.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}