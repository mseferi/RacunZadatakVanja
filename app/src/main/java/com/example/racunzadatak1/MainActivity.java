package com.example.racunzadatak1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import static android.widget.LinearLayout.HORIZONTAL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ListItem> data = new ArrayList<>();
        data.add(new ListItem("Receipt", true, false, 24));
        data.add(new ListItem("Company name", false, false, 16));
        data.add(new ListItem("VAT", true, true, 18));
        data.add(new ListItem("TAX ID", false, true, 12));

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, data);
        RecyclerView rvData = findViewById(R.id.rvData);
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(this));


    }

}