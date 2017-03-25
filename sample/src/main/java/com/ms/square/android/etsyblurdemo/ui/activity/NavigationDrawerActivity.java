package com.ms.square.android.etsyblurdemo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ms.square.android.etsyblurdemo.R;
import com.ms.square.android.etsyblurdemo.ui.fragment.CreateDialogDialogFragment;
import com.ms.square.android.etsyblurdemo.ui.fragment.CreateViewDialogFragment;
import com.ms.square.android.etsyblurdemo.ui.fragment.NavigationDrawerFragment;
import com.ms.square.android.etsyblurdemo.ui.fragment.PlaceholderFragment;
import com.ms_square.etsyblur.BlurSupport;


public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, PlaceholderFragment.OnSectionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment navigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        title = getTitle();

        // Set up the drawer.
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);
        BlurSupport.addTo(drawerLayout);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_view, PlaceholderFragment.newInstance(position + 1,
                        R.layout.fragment_place_holder))
                .commit();
    }

    @Override
    public void onSectionAttached(int sectionNumber) {
        switch (sectionNumber) {
            case 1:
                title = getString(R.string.title_section1);
                break;
            case 2:
                title = getString(R.string.title_section2);
                break;
            case 3:
                title = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.dialogs, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_dialog1) {
            CreateDialogDialogFragment fragment = CreateDialogDialogFragment.newInstance();
            fragment.show(getSupportFragmentManager(), "dialog1");
            return true;
        } else if (id == R.id.action_dialog2) {
            CreateViewDialogFragment fragment = CreateViewDialogFragment.newInstance();
            fragment.show(getSupportFragmentManager(), "dialog2");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
