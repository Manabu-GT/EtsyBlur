package com.ms.square.android.etsyblurdemo.data;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;

import com.ms.square.android.etsyblurdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ListItemDataProvider {

    public static List<ListItemData> generateSample(@NonNull Context context, int repeatCount) {
        List<ListItemData> list = new ArrayList<>();
        TypedArray images = context.getResources().obtainTypedArray(R.array.list_item_images);
        String[] titles = context.getResources().getStringArray(R.array.list_item_titles);
        String[] descriptions = context.getResources().getStringArray(R.array.list_item_descriptions);

        int imagesCount = images.length();
        if (imagesCount== titles.length && imagesCount == descriptions.length) {
            // add 5 times to make the list longer
            for (int repeat = 0; repeat < repeatCount; repeat++) {
                for (int i = 0; i < imagesCount; i++) {
                    list.add(new ListItemData(images.getResourceId(i, 0), titles[i], descriptions[i]));
                }
            }
        }

        images.recycle();

        return list;
    }
}
