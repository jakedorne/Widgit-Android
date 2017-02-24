package com.jaek.widgit.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaek.widgit.Models.Todo;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;

import java.util.HashMap;

/**
 * Created by Jaek on 15/02/2017.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder {

    private TextView text;

    public TodoViewHolder(View itemView) {
        super(itemView);
        this.text = (TextView) itemView.findViewById(R.id.todo_text);
    }

    public void updateUI(Todo todo) {
        text.setText(todo.getTitle());
    }

}
