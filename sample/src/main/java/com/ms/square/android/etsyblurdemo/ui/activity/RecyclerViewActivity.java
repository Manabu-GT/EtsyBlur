package com.ms.square.android.etsyblurdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ms.square.android.etsyblurdemo.R;
import com.ms.square.android.etsyblurdemo.data.ListItemDataProvider;
import com.ms.square.android.etsyblurdemo.ui.adapter.ListItemRecyclerViewAdapter;
import com.ms_square.etsyblur.BlurringView;

public class RecyclerViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private BlurringView blurringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new ListItemRecyclerViewAdapter(ListItemDataProvider.generateSample(this, 5)));

        blurringView = (BlurringView) findViewById(R.id.blurring_view);
        blurringView.blurredView(recyclerView);
    }
}
