package com.example.racunzadatak1.utils;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.racunzadatak1.R;
import com.example.racunzadatak1.adapter.MyRecyclerViewAdapter;
import com.example.racunzadatak1.entity.ListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // add commentes where needed -
    // separete recycleview init in separate method +
    // separate data population in separate method +
    // private, publix..
    // unused...
    // fromat code, num. of new lines
    // all in english, extract strings
    // fix layout warnings
    // add theme and colors like in last project
    // beautify rv row
    // pdv styles

    // mato styles
    // mato x btn middlie vertical
    // mato shared perferences use KEY psfs


    public static final String KEY_DATA = "KEY_DATA";
    MyRecyclerViewAdapter adapter;
    ArrayList<ListItem> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);


        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_DATA)) {
            data = (ArrayList<ListItem>) savedInstanceState.getSerializable(KEY_DATA);
        } else {
            dataPopulation();
        }


        recyclerViewInit();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(findViewById(R.id.rvData));
    }


    /**
     * Initialize adapter
     */
    public void recyclerViewInit() {
        adapter = new MyRecyclerViewAdapter(this);
        adapter.setItems(data);
        RecyclerView rvData = findViewById(R.id.rvData);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider)));
        rvData.addItemDecoration(divider);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(adapter);
    }

    /**
     * Populates data
     */
    public void dataPopulation() {
        data.add(new ListItem("Receipt", true, false, 24));
        data.add(new ListItem("Company name", false, false, 16));
        data.add(new ListItem("VAT", true, true, 18));
        data.add(new ListItem("TAX ID", false, true, 12));
        data.add(new ListItem("User info", false, true, 12));
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_DATA, (Serializable) adapter.getItems());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0 ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(data, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
}