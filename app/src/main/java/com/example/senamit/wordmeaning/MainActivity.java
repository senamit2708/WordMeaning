package com.example.senamit.wordmeaning;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;




public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    RecyclerAdapter adapter;
    List<Words> list;
    FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabButton = findViewById(R.id.floatingbtnSave);

        list = new ArrayList<Words>();
        list.add(new Words("amit","sen"));
        list.add(new Words("ashok", "sah"));
        list.add(new Words("vikash","kumar"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new RecyclerAdapter(list);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);



        adapter.setItem(list);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabButtonAddWords();
            }
        });



    }

    private void fabButtonAddWords() {

        Intent intent = new Intent(MainActivity.this, WordNewEntry.class);
        startActivity(intent);
    }
}
