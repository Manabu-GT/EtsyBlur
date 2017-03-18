package com.ms.square.android.etsyblur;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * ViewUtil.java
 *
 * @author Manabu-GT on 6/12/14.
 */
public class ViewUtil {

    public static final boolean IS_POST_HONEYCOMB_MR1 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;

    public static Bitmap drawViewToBitmap(View view, int width, int height, int downSampleFactor) {
        return drawViewToBitmap(view, width, height, 0f, 0f, downSampleFactor);
    }

    public static Bitmap drawViewToBitmap(View view, int width, int height, float translateX,
                                          float translateY, int downSampleFactor) {
        if (downSampleFactor <= 0) {
            throw new IllegalArgumentException("downSampleFactor must be greater than 0.");
        }

        // check whether valid width/height is given to create a bitmap
        if (width <= 0 || height <= 0) {
            return null;
        }

        int bmpWidth = (int) ((width - translateX) / downSampleFactor);
        int bmpHeight = (int) ((height - translateY) / downSampleFactor);

        Bitmap dest = Bitmap.createBitmap(bmpWidth, bmpHeight, Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(dest);
        c.translate(-translateX / downSampleFactor, -translateY / downSampleFactor);
        c.scale(1f / downSampleFactor, 1f / downSampleFactor);
        view.draw(c);

        return dest;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public static void animateAlpha(@NonNull View view, float fromAlpha, float toAlpha, int duration,
                                    @NonNull final Runnable endAction) {
        if (IS_POST_HONEYCOMB_MR1) {
            ViewPropertyAnimator animator = view.animate().alpha(toAlpha).setDuration(duration);
            if (endAction != null) {
                animator.setListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        endAction.run();
                    }
                });
            }
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
            alphaAnimation.setDuration(duration);
            alphaAnimation.setFillAfter(true);
            if (endAction != null) {
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // fixes the crash bug while removing views
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(endAction);
                    }
                    @Override
                    public void onAnimationStart(Animation animation) { }
                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
            }
            view.startAnimation(alphaAnimation);
        }
    }
}