package com.ms.square.android.etsyblurdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms_square.etsyblur.BlurringView;

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
        View fab = view.findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), NavigationViewActivity.class);
                    startActivity(intent);
                }
            });
        }

        View blurringView = view.findViewById(R.id.blurring_view);
        if (blurringView != null) {
            ((BlurringView) blurringView).blurredView(view.findViewById(R.id.container));
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
