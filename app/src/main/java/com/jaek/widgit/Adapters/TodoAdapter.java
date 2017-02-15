package com.jaek.widgit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaek.widgit.Holders.TodoViewHolder;
import com.jaek.widgit.Models.Todo;
import com.jaek.widgit.R;

import java.util.ArrayList;

/**
 * Created by Jaek on 15/02/2017.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder>{


    private ArrayList<Todo> todos;

    public TodoAdapter(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todo = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_view, parent, false);
        return new TodoViewHolder(todo);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        final Todo todo = todos.get(position);
        holder.updateUI(todo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // idk if they even need an on click?
            }
        });

    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

}
