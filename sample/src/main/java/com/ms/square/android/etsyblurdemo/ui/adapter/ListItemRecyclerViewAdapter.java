package com.ms.square.android.etsyblurdemo.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.etsyblurdemo.R;
import com.ms.square.android.etsyblurdemo.data.ListItemData;

import java.util.List;

public class ListItemRecyclerViewAdapter extends RecyclerView.Adapter<ListItemRecyclerViewAdapter.ViewHolder> {

    private final List<ListItemData> listItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView description;

        public ViewHolder(View root) {
            super(root);
            image = (ImageView) root.findViewById(R.id.image);
            title = (TextView) root.findViewById(R.id.title);
            description = (TextView) root.findViewById(R.id.description);
        }
    }

    public ListItemRecyclerViewAdapter(@NonNull List<ListItemData> listItems) {
        this.listItems = listItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.image.setImageResource(listItems.get(position).imageRes);
        viewHolder.title.setText(listItems.get(position).title);
        viewHolder.description.setText(listItems.get(position).description);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}