package com.ms.square.android.etsyblur;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

/**
 * EtsyActionBarDrawerToggle.java
 *
 * @author Manabu-GT on 6/12/14.
 */
public class EtsyActionBarDrawerToggle extends ActionBarDrawerToggle {

    private static final int DEFAULT_RADIUS = 10;
    private static final int DEFAULT_DOWN_SAMPLE_FACTOR = 4;

    private Activity activity;

    private View container;
    private ImageView blurImage;

    private int blurRadius;
    private int downSampleFactor;

    public EtsyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout,
                                     int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        init(activity);
    }

    public EtsyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar,
                                     int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        init(activity);
    }

    public void setBlurImage() {
        blurImage.setImageBitmap(null);
        blurImage.setVisibility(View.VISIBLE);
        // do the downscaling for faster processing
        Bitmap downScaled = ViewUtil.drawViewToBitmap(container,
                container.getWidth(), container.getHeight(), downSampleFactor);
        // apply the blur
        Bitmap blurred = Blur.apply(activity, downScaled, blurRadius);
        blurImage.setImageBitmap(blurred);
    }

    public void clearBlurImage() {
        blurImage.setVisibility(View.GONE);
        blurImage.setImageBitmap(null);
    }

    public void setBlurRadius(int blurRadius) {
        if (0 < blurRadius && blurRadius <= 25) {
            this.blurRadius = blurRadius;
        }
    }

    public void setDownSampleFactor(int downSampleFactor) {
        this.downSampleFactor = downSampleFactor;
    }

    private void init(Activity activity) {
        this.activity = activity;
        this.blurRadius = DEFAULT_RADIUS;
        this.downSampleFactor = DEFAULT_DOWN_SAMPLE_FACTOR;

        this.container = activity.findViewById(R.id.container);
        this.blurImage = (ImageView) activity.findViewById(R.id.blur_view);
    }

    private void setBlurAlpha(float slideOffset) {
        if (blurImage.getVisibility() != View.VISIBLE) {
            setBlurImage();
        }
        blurImage.setAlpha(slideOffset);
    }

    @Override
    public void onDrawerSlide(final View drawerView, final float slideOffset) {
        super.onDrawerSlide(drawerView, slideOffset);
        if (slideOffset > 0f) {
            setBlurAlpha(slideOffset);
        } else {
            clearBlurImage();
        }
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        clearBlurImage();
    }
}
