package com.ms.square.android.etsyblur;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;

/**
 * EtsyActionBarDrawerToggle.java
 *
 * @author Manabu-GT on 6/12/14.
 */
public class EtsyActionBarDrawerToggle extends ActionBarDrawerToggle {

    private static final String TAG = EtsyActionBarDrawerToggle.class.getSimpleName();

    private static final int DEFAULT_RADIUS = 10;
    private static final int DEFAULT_DOWN_SAMPLING = 3;

    private Activity mActivity;

    private View mContainer;
    private ImageView mBlurImage;

    private int mBlurRadius;
    private int mDownSampling;

    /**
     * Construct a new ActionBarDrawerToggle.
     * <p/>
     * <p>The given {@link android.app.Activity} will be linked to the specified {@link android.support.v4.widget.DrawerLayout}.
     * The provided drawer indicator drawable will animate slightly off-screen as the drawer
     * is opened, indicating that in the open state the drawer will move off-screen when pressed
     * and in the closed state the drawer will move on-screen when pressed.</p>
     * <p/>
     * <p>String resources must be provided to describe the open/close drawer actions for
     * accessibility services.</p>
     *
     * @param activity                  The Activity hosting the drawer
     * @param drawerLayout              The DrawerLayout to link to the given Activity's ActionBar
     * @param drawerImageRes            A Drawable resource to use as the drawer indicator
     * @param openDrawerContentDescRes  A String resource to describe the "open drawer" action
     *                                  for accessibility
     * @param closeDrawerContentDescRes A String resource to describe the "close drawer" action
     */
    public EtsyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int drawerImageRes, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
        mActivity = activity;
        mBlurRadius = DEFAULT_RADIUS;
        mDownSampling = DEFAULT_DOWN_SAMPLING;

        mContainer = activity.findViewById(R.id.container);
        mBlurImage = (ImageView) activity.findViewById(R.id.blur_view);
    }

    public void setBlurRadius(int blurRadius) {
        if (0 < blurRadius && blurRadius <= 25) {
            mBlurRadius = blurRadius;
        }
    }

    public void setDownSampling(int downSampling) {
        mDownSampling = downSampling;
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

    private void setBlurAlpha(float slideOffset) {
        if (mBlurImage.getVisibility() != View.VISIBLE) {
            setBlurImage();
        }
        Util.setAlpha(mBlurImage, slideOffset);
    }

    public void setBlurImage() {
        mBlurImage.setImageBitmap(null);
        mBlurImage.setVisibility(View.VISIBLE);
        // do the downscaling for faster processing
        Bitmap downScaled = Util.drawViewToBitmap(mContainer,
                mContainer.getWidth(), mContainer.getHeight(), mDownSampling);
        // apply the blur using the renderscript
        Bitmap blurred = Blur.apply(mActivity, downScaled, mBlurRadius);
        mBlurImage.setImageBitmap(blurred);
        downScaled.recycle();
    }

    public void clearBlurImage() {
        mBlurImage.setVisibility(View.GONE);
        mBlurImage.setImageBitmap(null);
    }
}