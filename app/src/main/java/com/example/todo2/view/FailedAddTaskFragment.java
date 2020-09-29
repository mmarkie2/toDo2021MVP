package com.example.todo2.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.todo2.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


public class FailedAddTaskFragment extends AppCompatDialogFragment {

    private FailedAddTaskFragmentListener listener;
//shows dialog when user inputs invalid dada
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_failed_add_task, null);
        builder.setView(view)

                .setMessage(this.getTag() + " Czy chcesz powtórzyć?")
                .setNegativeButton("nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onRetry(false);

                    }
                })
                .setPositiveButton("tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onRetry(true);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (FailedAddTaskFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement FailedAddTaskFragmentListener");
        }
    }


    public interface FailedAddTaskFragmentListener {
        void onRetry(boolean isRetry);
    }
}
