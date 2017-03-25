package com.ms.square.android.etsyblurdemo.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ms.square.android.etsyblurdemo.R;

public class ViewPagerIndicator extends RadioGroup {

    private int pageCount;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
        removeAllViews();
        for (int i = 0; i < pageCount; i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setFocusable(false);
            rb.setClickable(false);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.indicator);
                rb.setButtonDrawable(d);
                LinearLayout.LayoutParams params = generateDefaultLayoutParams();
                params.width = d.getIntrinsicWidth();
                params.height = d.getIntrinsicHeight();
                rb.setLayoutParams(params);
            } else {
                rb.setButtonDrawable(R.drawable.indicator);
            }
            addView(rb);
        }
        setCurrentPosition(-1);
    }

    public void setCurrentPosition(int position) {
        if (position >= pageCount) {
            position = pageCount - 1;
        }
        if (position < 0) {
            position = pageCount > 0 ? 0 : -1;
        }

        if (position >= 0 && position < pageCount) {
            // Update the radio button status
            RadioButton rb = (RadioButton) getChildAt(position);
            rb.setChecked(true);
        }
    }
}