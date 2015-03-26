package com.ms.square.android.etsyblur;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Util.java
 *
 * @author Manabu-GT on 6/12/14.
 */
public class Util {

    public static Bitmap drawViewToBitmap(View view, int width, int height, int downSampling) {
        return drawViewToBitmap(view, width, height, 0f, 0f, downSampling);
    }

    public static Bitmap drawViewToBitmap(View view, int width, int height, float translateX,
                                          float translateY, int downSampling) {
        float scale = 1f / downSampling;
        int bmpWidth = (int) (width * scale - translateX / downSampling);
        int bmpHeight = (int) (height * scale - translateY / downSampling);
        Bitmap dest = Bitmap.createBitmap(bmpWidth, bmpHeight, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(dest);
        c.translate(-translateX / downSampling, -translateY / downSampling);
        if (downSampling > 1) {
            c.scale(scale, scale);
        }
        view.draw(c);
        return dest;
    }

    public static boolean isPostHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public static void setAlpha(View view, float alpha) {
        if (isPostHoneycomb()) {
            view.setAlpha(alpha);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(alpha, alpha);
            // make it instant
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public static void animateAlpha(final View view, float fromAlpha, float toAlpha, int duration, final Runnable endAction) {
        if (isPostHoneycomb()) {
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
                        Handler handler = new Handler();
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