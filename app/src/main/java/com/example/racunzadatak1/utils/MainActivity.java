package com.example.racunzadatak1.utils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
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
    private MyRecyclerViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton ibAdd = findViewById(R.id.ibAdd);
        Toolbar mainToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);

        recyclerViewInit();


        // Adds new item in list on button click
        ibAdd.setOnClickListener(v -> {
            ListItem defaultItem = new ListItem("Default", false, false, 20);
            adapter.getItems().add(defaultItem); //add it to top of list
            adapter.notifyDataSetChanged();
        });


        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_DATA)) {
            adapter.setItems((ArrayList<ListItem>) savedInstanceState.getSerializable(KEY_DATA));
        } else {
            adapter.setItems(dataPopulation());
        }


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(findViewById(R.id.rvData));
    }


    /**
     * Initialize adapter
     */
    public void recyclerViewInit() {
        adapter = new MyRecyclerViewAdapter(this);
        RecyclerView rvData = findViewById(R.id.rvData);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider)));
        rvData.addItemDecoration(divider);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(adapter);
    }


    /**
     * Populates data
     *
     * @return
     */
    public ArrayList<ListItem> dataPopulation() {
        ArrayList<ListItem> data = new ArrayList<>();
        data.add(new ListItem("Receipt", true, false, 24));
        data.add(new ListItem("Company name", false, false, 16));
        data.add(new ListItem("VAT", true, true, 18));
        data.add(new ListItem("TAX ID", false, true, 12));
        data.add(new ListItem("User info", false, true, 12));
        data.add(new ListItem("Print line 1", true, true, 12));
        data.add(new ListItem("Print line 2", true, true, 13));
        data.add(new ListItem("Print line 3", true, true, 14));
        data.add(new ListItem("Print line 4", true, true, 15));
        return data;
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_DATA, (Serializable) adapter.getItems());
    }


    //Drag and drop
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            adapter.getItems().get(fromPosition).setPosition(toPosition);
            adapter.getItems().get(toPosition).setPosition(fromPosition);
            Collections.swap(adapter.getItems(), fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }


        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


}