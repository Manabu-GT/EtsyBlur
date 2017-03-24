package com.ms.square.android.etsyblurdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ms_square.etsyblur.BlurConfig;
import com.ms_square.etsyblur.BlurDialogFragment;

/**
 * CreateViewDialogFragment.java
 *
 * @author Manabu-GT on 6/12/14.
 */
public class CreateViewDialogFragment extends BlurDialogFragment {

    public static CreateViewDialogFragment newInstance() {
        CreateViewDialogFragment fragment = new CreateViewDialogFragment();
        fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.EtsyBlurDialogTheme);
        return fragment;
    }

    // implement either onCreateView or onCreateDialog
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_demo, container, false);

        ListView listView = (ListView) v.findViewById(R.id.dialog_content);
        listView.setAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new String[]{
                        "Enable History",
                        "Clear History",
                        "Search History",
                        "Select Currency",
                        "About"
                }
        ));
        return v;
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