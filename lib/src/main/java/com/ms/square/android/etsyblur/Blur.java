package com.ms.square.android.etsyblur;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

/**
 * Blur.java
 *
 * @author Manabu-GT on 6/12/14.
 */
public class Blur {

    private static final int DEFAULT_BLUR_RADIUS = 10;

    public static Bitmap apply(@NonNull Context context, @NonNull Bitmap sentBitmap) {
        return apply(context, sentBitmap, DEFAULT_BLUR_RADIUS);
    }

    public static Bitmap apply(@NonNull Context context, @NonNull Bitmap bitmap, int radius) {

        RenderScript rs = null;
        Allocation input = null;
        Allocation output = null;
        ScriptIntrinsicBlur scriptBlur = null;

        try {
            rs = RenderScript.create(context);
            input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            output = Allocation.createTyped(rs, input.getType());
            scriptBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

            scriptBlur.setRadius(radius);
            scriptBlur.setInput(input);
            scriptBlur.forEach(output);
            output.copyTo(bitmap);
        } finally {
            // clean up renderscript resources
            if (rs != null) {
                rs.destroy();
            }
            if (input != null) {
                input.destroy();
            }
            if (output != null) {
                output.destroy();
            }
            if (scriptBlur != null) {
                scriptBlur.destroy();
            }
        }

        return bitmap;
    }
}
