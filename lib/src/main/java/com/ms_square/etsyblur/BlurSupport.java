package com.ms_square.etsyblur;

import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

/**
 * BlurSupport.java
 *
 * @author Manabu-GT on 3/17/17.
 */
public class BlurSupport {

    public static void addTo(@NonNull final DrawerLayout drawerLayout) {
        View viewToBlur = drawerLayout.getChildAt(0);
        final View blurringView = drawerLayout.findViewById(R.id.blurring_view);

        if (viewToBlur == null) {
            throw new IllegalStateException("There's no view to blur. DrawerLayout does not have the first child view.");
        }

        if (blurringView == null) {
            throw new IllegalStateException("There's no blurringView. Include BlurringView with id set to 'blurring_view'");
        }

        if (!(blurringView instanceof BlurringView)) {
            throw new IllegalStateException("blurring_view must be type BlurringView");
        }

        ((BlurringView) blurringView).blurredView(viewToBlur);

        drawerLayout.addDrawerListener(new BlurDrawerListener((BlurringView) blurringView));

        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                if (drawerLayout.isDrawerVisible(GravityCompat.START) || drawerLayout.isDrawerVisible(GravityCompat.END)) {
                    // makes sure to set it to be visible if the drawer is visible at start
                    blurringView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}