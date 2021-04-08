package com.example.racunzadatak1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_DATA = "KEY_DATA";
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ListItem> data = new ArrayList<>();
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_DATA)) {
            data = (ArrayList<ListItem>) savedInstanceState.getSerializable(KEY_DATA);
        } else {
            data.add(new ListItem("Receipt", true, false, 24));
            data.add(new ListItem("Company name", false, false, 16));
            data.add(new ListItem("VAT", true, true, 18));
            data.add(new ListItem("TAX ID", false, true, 12));
            data.add(new ListItem("User info", false, true, 12));
        }

        adapter = new MyRecyclerViewAdapter(this);
        adapter.setmData(data);
        RecyclerView rvData = findViewById(R.id.rvData);
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_DATA, (Serializable) adapter.getmData());
    }

}