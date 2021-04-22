package com.example.racunzadatak1.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.racunzadatak1.R;
import com.example.racunzadatak1.entity.ListItem;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<ListItem> items;
    private final LayoutInflater mInflater;


    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public List<ListItem> getItems() {
        return items;
    }

    public void setItems(List<ListItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_row, parent, false);
        return new ViewHolder(view);
    }



    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem item = items.get(position);
        holder.etItemText.setText(item.getItemText());
        holder.etFontSize.setText(String.valueOf(item.getFontSize()));
        holder.cbIsPrintable.setChecked(item.isPrintable());
        holder.cbIsBold.setChecked(item.isBold());

        holder.ibDelete.setOnClickListener(v -> {
            // remove your item from data base
            items.remove(item);  // remove the item from list
            notifyDataSetChanged();
        });


//        holder.etItemText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                items.get(position).setItemText(s.toString());
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


        if (holder.cbIsBold.isChecked()) {
            holder.etItemText.setTypeface(holder.etItemText.getTypeface(), Typeface.BOLD);

        } else {
            holder.etItemText.setTypeface(null, Typeface.NORMAL);

        }


        //bolds text in etItemText when checkbox cbIsBold is checked
        CompoundButton.OnCheckedChangeListener listener = (buttonView, isChecked) -> {
            items.get(position).setBold(holder.cbIsBold.isChecked());

            if (holder.cbIsBold.isChecked()) {
                holder.etItemText.setTypeface(holder.etItemText.getTypeface(), Typeface.BOLD);

            } else {
                holder.etItemText.setTypeface(null, Typeface.NORMAL);
            }
        };
        holder.cbIsBold.setOnCheckedChangeListener(listener);


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return items.size();
    }

    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText etItemText;
        EditText etFontSize;
        CheckBox cbIsPrintable;
        CheckBox cbIsBold;
        ImageButton ibDelete;


        ViewHolder(View itemView) {
            super(itemView);
            etItemText = itemView.findViewById(R.id.etItemText);
            etFontSize = itemView.findViewById(R.id.etFontSize);
            cbIsPrintable = itemView.findViewById(R.id.cbIsPrintable);
            cbIsBold = itemView.findViewById(R.id.cbIsBold);
            ibDelete = itemView.findViewById(R.id.ibDelete);

        }
    }
}

