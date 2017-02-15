package com.jaek.widgit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaek.widgit.Adapters.TodoAdapter;
import com.jaek.widgit.Models.Todo;
import com.jaek.widgit.R;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        recyclerView = (RecyclerView) findViewById(R.id.todo_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Todo> todos = new ArrayList<Todo>();
        // TODO: need to get todos from firebase here

         adapter = new TodoAdapter(todos);
         recyclerView.setAdapter(adapter);

    }
}
