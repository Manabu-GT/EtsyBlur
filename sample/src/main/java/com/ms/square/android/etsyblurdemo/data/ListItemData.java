package com.ms.square.android.etsyblurdemo.data;

import android.support.annotation.DrawableRes;

public class ListItemData {
    @DrawableRes
    public final int imageRes;
    public final String title;
    public final String description;

    public ListItemData(@DrawableRes int imageRes, String title, String description) {
        this.imageRes = imageRes;
        this.title = title;
        this.description = description;
    }
}
