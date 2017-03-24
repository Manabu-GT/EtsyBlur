package com.ms.square.android.etsyblurdemo;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.ms_square.etsyblur.BlurConfig;
import com.ms_square.etsyblur.BlurDialogFragment;

/**
 * CreateDialogDialogFragment.java
 *
 * @author Manabu-GT on 6/12/14.
 */
public class CreateDialogDialogFragment extends BlurDialogFragment {

    public static CreateDialogDialogFragment newInstance() {
        CreateDialogDialogFragment fragment = new CreateDialogDialogFragment();
        return fragment;
    }

    // implement either onCreateView or onCreateDialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity(), R.style.EtsyBlurAlertDialogTheme)
                .setIcon(R.drawable.ic_launcher)
                .setTitle("Hello")
                .setPositiveButton("OK", null)
                .create();
        return dialog;
    }

    @NonNull
    protected BlurConfig blurConfig() {
        return new BlurConfig.Builder()
                .asyncPolicy(SmartAsyncPolicyHolder.INSTANCE.smartAsyncPolicy())
                .debug(true)
                .build();
    }

    @ColorInt
    protected int overlayColor() {
        // semi-transparent white color
        return Color.argb(136, 255, 255, 255);
    }
}