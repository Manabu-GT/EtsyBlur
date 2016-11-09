package com.ms.square.android.etsyblurdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.ms.square.android.etsyblur.BlurDrawable;

public class BitmapDrawableActivity extends AppCompatActivity {

    private BlurDrawable drawable;
    private SeekBar.OnSeekBarChangeListener listener1 = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            drawable.setBlurRadius(seekBar.getProgress() + 1);
        }
    };
    private SeekBar.OnSeekBarChangeListener listener2 = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            drawable.setBlur(((float) i) / 100f);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_drawable);
        ImageView mBlurView = (ImageView) findViewById(R.id.blur_view);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(listener1);
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seek_bar2);
        seekBar2.setOnSeekBarChangeListener(listener2);
        Resources resources = getResources();
        Bitmap bitmap = ((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.lolipop_bg)).getBitmap();
        drawable = new BlurDrawable(this, resources, bitmap);
        drawable.setBlur(0f);
        drawable.setBlurRadius(1);
        mBlurView.setImageDrawable(drawable);
    }
}
