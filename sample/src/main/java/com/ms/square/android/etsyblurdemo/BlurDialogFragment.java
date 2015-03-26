package com.ms.square.android.etsyblurdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ms.square.android.etsyblur.BlurDialogFragmentHelper;

/**
 * BlurDialogFragment.java
 *
 * @author Manabu-GT on 6/12/14.
 */
public class BlurDialogFragment extends DialogFragment {

    private BlurDialogFragmentHelper mHelper;

    public static BlurDialogFragment newInstance() {
        BlurDialogFragment fragment = new BlurDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new BlurDialogFragmentHelper(this);
        mHelper.onCreate();
    }

    // implement either onCreateView or onCreateDialog
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            AlertDialog dialog = new AlertDialog.Builder(getActivity(), getTheme())
//                    .setIcon(R.drawable.ic_launcher)
//                    .setTitle("Hello")
//                    .setPositiveButton("OK", null)
//                    .create();
//            dialog.getWindow().getAttributes().height = WindowManager.LayoutParams.WRAP_CONTENT;
//            return dialog;
//        }
//        // customize the content of the dialog by overriding onCreateView
//        return super.onCreateDialog(savedInstanceState);
//    }

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

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHelper.onActivityCreated();
    }

    @Override
    public void onStart() {
        super.onStart();
        mHelper.onStart();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mHelper.onDismiss();
        super.onDismiss(dialog);
    }
}