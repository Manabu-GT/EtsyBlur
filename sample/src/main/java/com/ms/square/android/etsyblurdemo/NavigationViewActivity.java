package com.ms.square.android.etsyblurdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ms_square.etsyblur.BlurSupport;

public class NavigationViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        PlaceholderFragment.OnSectionListener {

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (savedInstanceState == null) {
            title = getString(R.string.title_section1);
            navigationView.setCheckedItem(R.id.nav_menu1);
            replaceFragment(1);
        }

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerVisible(navigationView)) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.dialogs, menu);
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
            CreateViewDialogFragment fragment = CreateViewDialogFragment.newInstance();
            fragment.show(getSupportFragmentManager(), "dialog1");
            return true;
        } else if (id == R.id.action_dialog2) {
            CreateDialogDialogFragment fragment = CreateDialogDialogFragment.newInstance();
            fragment.show(getSupportFragmentManager(), "dialog2");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_menu1) {
            replaceFragment(1);
        } else if(id == R.id.nav_menu2) {
            replaceFragment(2);
        } else {
            replaceFragment(3);
        }
        return true;
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
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    private void initView() {
        // apply blur effect
        BlurSupport.addTo(drawerLayout);

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                supportInvalidateOptionsMenu();
            }
        });

        // Set the custom scrim color (the overlay color) for the etsy like effect
        drawerLayout.setScrimColor(ContextCompat.getColor(this, R.color.bg_glass));

        if (title != null) {
            toolbar.setTitle(title);
        }
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void replaceFragment(int sectionNumber) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_view, PlaceholderFragment.newInstance(sectionNumber, R.layout.fragment_place_holder))
                .commit();
    }
}
