package com.ms.square.android.etsyblurdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ms.square.android.etsyblurdemo.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final String ARG_LAYOUT_RESOURCE = "layout_resource";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, @LayoutRes int layoutRes) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt(ARG_LAYOUT_RESOURCE, layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getArguments().getInt(ARG_LAYOUT_RESOURCE), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        View numberText = view.findViewById(R.id.number_text);
        if (numberText != null && numberText instanceof TextView) {
            ((TextView) numberText).setText(String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof  OnSectionListener) {
            ((OnSectionListener) context).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public interface OnSectionListener {
        void onSectionAttached(int sectionNumber);
    }
}
