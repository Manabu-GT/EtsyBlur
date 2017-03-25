package com.ms.square.android.etsyblurdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ms.square.android.etsyblurdemo.R;
import com.ms.square.android.etsyblurdemo.ui.fragment.CreateDialogDialogFragment;
import com.ms.square.android.etsyblurdemo.ui.fragment.CreateViewDialogFragment;

public class SampleLaunchActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_launch);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.list_view);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, getResources().getStringArray(R.array.sample_titles));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: {
                startActivity(new Intent(this, NavigationDrawerActivity.class));
                break;
            }
            case 1: {
                startActivity(new Intent(this, NavigationViewActivity.class));
                break;
            }
            case 2: {
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            }
            case 3: {
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            }
            case 4: {
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            }
            case 5: {
                CreateDialogDialogFragment fragment = CreateDialogDialogFragment.newInstance();
                fragment.show(getSupportFragmentManager(), "dialog1");
                break;
            }
            case 6: {
                CreateViewDialogFragment fragment = CreateViewDialogFragment.newInstance();
                fragment.show(getSupportFragmentManager(), "dialog2");
                break;
            }
        }
    }
}
