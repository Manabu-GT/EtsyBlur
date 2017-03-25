package com.ms.square.android.etsyblurdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ms.square.android.etsyblurdemo.R;
import com.ms.square.android.etsyblurdemo.data.ListItemDataProvider;
import com.ms.square.android.etsyblurdemo.ui.adapter.ListItemArrayAdapter;
import com.ms_square.etsyblur.BlurringView;

public class ListViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private BlurringView blurringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.list_view);
        ListAdapter adapter = new ListItemArrayAdapter(this, 0, ListItemDataProvider.generateSample(this, 5));
        listView.setAdapter(adapter);

        blurringView = (BlurringView) findViewById(R.id.blurring_view);
        blurringView.blurredView(listView);
    }
}
