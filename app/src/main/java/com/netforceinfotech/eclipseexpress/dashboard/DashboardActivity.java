package com.netforceinfotech.eclipseexpress.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.WindowManager;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.netforceinfotech.eclipseexpress.R;
import com.netforceinfotech.eclipseexpress.dashboard.dashboardcontentnew.DashboardFragment;
import com.netforceinfotech.eclipseexpress.dashboard.navigation.NavigationFragment;


public class DashboardActivity extends AppCompatActivity {

    private NavigationFragment drawer;
    private Toolbar toolbar;
    private DashboardFragment homeFragment;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        context = this;
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setupToolBar();
        //setupNavigation();
        setupNavigationCustom();
        setupDashFragment();
    }

    private void setupNavigationCustom() {
        drawer = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        drawer.setup(R.id.fragment, (DrawerLayout) findViewById(R.id.drawer), toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);
        ActionItemBadge.update(((AppCompatActivity) context), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
                , ActionItemBadge.BadgeStyles.GREY, 3);
        return true;
    }


    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Home";
        getSupportActionBar().setTitle(teams);

    }

    private void replaceFragment(Fragment newFragment, String tag) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, newFragment, tag);
        transaction.commit();
    }

    private void setupDashFragment() {
        if (homeFragment == null) {
            homeFragment = new DashboardFragment();
        }
        String tagName = homeFragment.getClass().getName();
        replaceFragment(homeFragment, tagName);
    }
}
