package com.jaek.widgit.Activities;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaek.widgit.Adapters.TodoAdapter;
import com.jaek.widgit.Models.Todo;
import com.jaek.widgit.MyApplication;
import com.jaek.widgit.R;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference ref;
    private FirebaseAuth auth;
    private EditText todoTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        ref = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        todoTitle = (EditText) findViewById(R.id.todo_text);
        recyclerView = (RecyclerView) findViewById(R.id.todo_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(MyApplication.user.getTodos()!= null) {
            System.out.println("TODO COUNT: "+MyApplication.user.getTodos().size());
            adapter = new TodoAdapter(new ArrayList<>(MyApplication.user.getTodos().values()));


            recyclerView.setAdapter(adapter);


            ref.child("users/"+auth.getCurrentUser().getUid()+"/todos").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    adapter.notifyDataSetChanged();
                    new CountDownTimer(1000, 1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            adapter = new TodoAdapter(new ArrayList<>(MyApplication.user.getTodos().values()));
                            recyclerView.setAdapter(adapter);
                            System.out.println("JAKE: VIEW UPDATED!!!");
                        }
                    }.start();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

    public void addTodo(View v){
        Todo todo = new Todo(todoTitle.getText().toString());
        ref.child("users/"+auth.getCurrentUser().getUid()+"/todos").push().setValue(todo);
    }
}
