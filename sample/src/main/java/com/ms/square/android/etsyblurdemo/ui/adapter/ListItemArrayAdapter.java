package com.ms.square.android.etsyblurdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.etsyblurdemo.R;
import com.ms.square.android.etsyblurdemo.data.ListItemData;

import java.util.List;

public class ListItemArrayAdapter extends ArrayAdapter<ListItemData> {

    public ListItemArrayAdapter(Context context, int resource, List<ListItemData> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView description = (TextView) convertView.findViewById(R.id.description);

            holder = new ViewHolder();
            holder.image = image;
            holder.title = title;
            holder.description = description;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListItemData data = getItem(position);

        holder.image.setImageResource(data.imageRes);
        holder.title.setText(data.title);
        holder.description.setText(data.description);

        return convertView;
    }

    private static class ViewHolder {
        ImageView image;
        TextView title;
        TextView description;
    }
}
