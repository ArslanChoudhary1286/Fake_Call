package com.myapp.fake_call;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class caller extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Params> arrayList;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller);
        setTitle("Add your Contacts");

        //Recyclerview initialization
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new DataBase(caller.this);
        arrayList = new ArrayList<>();

        String query = "SELECT * FROM char_data";
        ArrayList<Params> list = db.getAllData(query);

        for (Params p : list){
            arrayList.add(p);
        }

        //        Use your recyclerView
        recyclerViewAdapter = new RecyclerViewAdapter(caller.this, arrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.addButton:
                Intent intent = new Intent(caller.this, AddData.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}