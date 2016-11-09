package com.ms.square.android.etsyblur;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

/**
 * BlurDrawable.java
 *
 * @author JurgisG on 2016-11-09.
 */
public class BlurDrawable extends BitmapDrawable {

    private final Context mContext;
    private Bitmap mBitmap;
    private Bitmap mBlurredBitmap;

    private int mBlurRadius = 5;
    private float mBlur = 1f;

    private int mAlpha = 255;
    private ColorFilter mColorFilter;

    public BlurDrawable(Context context, Resources res, Bitmap bitmap) {
        super(res, bitmap);
        this.mContext = context;
        setBitmap(bitmap);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mBitmap != null) {
            Paint paint = new Paint();
            paint.setAlpha(mAlpha);
            if (mColorFilter != null) {
                paint.setColorFilter(mColorFilter);
            }
            canvas.drawBitmap(mBitmap, 0, 0, paint);
            paint.setAlpha((int) (mAlpha * mBlur));
            canvas.drawBitmap(mBlurredBitmap, 0, 0, paint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mAlpha = alpha;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mColorFilter = colorFilter;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    public void setBitmap(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            if (mBitmap != null && mBitmap.sameAs(bitmap)) {
            } else {
                mBitmap = bitmap;
                updateBlurredBitmap();
            }
        } else {
            mBitmap = bitmap;
            updateBlurredBitmap();
        }
    }

    private void updateBlurredBitmap() {
        if (mBlurredBitmap != null) {
            mBlurredBitmap.recycle();
            mBlurredBitmap = null;
        }
        mBlurredBitmap = Blur.apply(mContext, mBitmap, mBlurRadius);
        invalidateSelf();
    }

    public int getBlurRadius() {
        return mBlurRadius;
    }

    public void setBlurRadius(int blurRadius) {
        blurRadius = Math.min(25, Math.max(1, blurRadius));
        if (mBlurRadius != blurRadius) {
            this.mBlurRadius = blurRadius;
            updateBlurredBitmap();
        }
    }

    public void setBlur(float blur) {
        mBlur = Math.min(1f, Math.max(0f, blur));
        invalidateSelf();
    }

}
