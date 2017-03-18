package com.ms.square.android.etsyblur;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * BlurDialogFragmentHelper.java
 *
 * @author Manabu-GT on 6/12/14.
 */
public class BlurDialogFragmentHelper {

    private static final int DEFAULT_DOWN_SAMPLE_FACTOR = 4;

    private final DialogFragment fragment;

    private ViewGroup root;

    private ViewGroup blurContainer;

    private View blurBgView;

    private ImageView blurImgView;

    private int animDuration;

    private int windowAnimStyle;

    private int bgColorResId;

    private int downSampleFactor;

    public BlurDialogFragmentHelper(@NonNull DialogFragment fragment) {
        this.fragment = fragment;
        this.animDuration = fragment.getActivity().getResources().getInteger(android.R.integer.config_mediumAnimTime);
        this.windowAnimStyle = R.style.DialogSlideAnimation;
        this.bgColorResId = R.color.bg_glass;
        this.downSampleFactor = DEFAULT_DOWN_SAMPLE_FACTOR;
    }

    /**
     * Duration of the alpha animation.
     *
     * @param animDuration The length of ensuing property animations, in milliseconds.
     *                     The value cannot be negative.
     */
    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
    }

    public void setWindowAnimStyle(@StyleRes int windowAnimStyle) {
        this.windowAnimStyle = windowAnimStyle;
    }

    public void setBgColorResId(@ColorRes int bgColorResId) {
        this.bgColorResId = bgColorResId;
    }

    public void setDownSampleFactor(int downSampleFactor) {
        this.downSampleFactor = downSampleFactor;
    }

    public void onCreate() {
        fragment.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public void onActivityCreated() {
        Activity activity = fragment.getActivity();
        Window window = fragment.getDialog().getWindow();
        window.setWindowAnimations(windowAnimStyle);

        Rect visibleFrame = new Rect();
        root = (ViewGroup) fragment.getActivity().getWindow().getDecorView();
        root.getWindowVisibleDisplayFrame(visibleFrame);

        blurContainer = new FrameLayout(activity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(visibleFrame.right - visibleFrame.left,
                visibleFrame.bottom - visibleFrame.top);
        params.setMargins(visibleFrame.left, visibleFrame.top, 0, 0);
        blurContainer.setLayoutParams(params);

        blurBgView = new View(activity);
        blurBgView.setBackgroundColor(ContextCompat.getColor(activity, bgColorResId));
        blurBgView.setAlpha(0f);

        blurImgView = new ImageView(activity);
        blurImgView.setAlpha(0f);

        blurContainer.addView(blurImgView);
        blurContainer.addView(blurBgView);

        root.addView(blurContainer);

        // apply blur effect
        Bitmap bitmap = ViewUtil.drawViewToBitmap(root, visibleFrame.right,
                visibleFrame.bottom, visibleFrame.left, visibleFrame.top, downSampleFactor);
        Bitmap blurred = Blur.apply(activity, bitmap);
        blurImgView.setImageBitmap(blurred);

        View view = fragment.getView();
        if (view != null) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    fragment.dismiss();
                    return true;
                }
            });
        }
    }

    public void onStart() {
        startEnterAnimation();
    }

    public void onDismiss() {
        startExitAnimation();
    }

    private void startEnterAnimation() {
        ViewUtil.animateAlpha(blurBgView, 0f, 1f, animDuration, null);
        ViewUtil.animateAlpha(blurImgView, 0f, 1f, animDuration, null);
    }

    private void startExitAnimation() {
        ViewUtil.animateAlpha(blurBgView, 1f, 0f, animDuration, null);
        ViewUtil.animateAlpha(blurImgView, 1f, 0f, animDuration, new Runnable() {
            @Override
            public void run() {
                root.removeView(blurContainer);
            }
        });
    }
}
