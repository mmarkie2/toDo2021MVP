package com.example.todo2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.todo2.R;
import com.example.todo2.model.RoomTask;
import com.example.todo2.model.TaskData;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder> {

    private final  List<RoomTask> roomTasks;
    private final LayoutInflater inflater;
    private final RecyclerDeleteButtonClickListener recyclerDeleteButtonClickListener;


    public TasksRecyclerViewAdapter(Context context, List<RoomTask> roomTasks, RecyclerDeleteButtonClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.roomTasks = roomTasks;
        this.recyclerDeleteButtonClickListener = listener;
    }

    // inflates the row layout from xml when needed

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view, this.recyclerDeleteButtonClickListener);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RoomTask roomTask = roomTasks.get(position);



        holder.nameView.setText(roomTask.getName());
        holder.categoryView.setText(roomTask.getType());
        holder.dateView.setText(roomTask.date.toString());

    }


    @Override
    public int getItemCount() {
        return roomTasks.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
          final TextView nameView;
         final TextView categoryView;
         final TextView dateView;
          final Button deleteButton;
        private final WeakReference<RecyclerDeleteButtonClickListener> listenerRef;

        ViewHolder(final View itemView, RecyclerDeleteButtonClickListener listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);

            nameView = itemView.findViewById(R.id.nameView);
            categoryView = itemView.findViewById(R.id.categoryView);
            dateView = itemView.findViewById(R.id.dateView);


            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
// detects if delete button was clicked
            if (v.getId() == deleteButton.getId()) {
                listenerRef.get().onPositionClicked(getAdapterPosition());
            }


        }
    }


}