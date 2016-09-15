package com.netforceinfotech.eclipseexpress.Helpcenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.netforceinfotech.eclipseexpress.Add_to_cart.Add_to_card_activity;
import com.netforceinfotech.eclipseexpress.R;

public class Help_center extends AppCompatActivity {
    Toolbar toolbar;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        setupToolBar();
        context=this;

    }
    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_help_center);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Help Center";
        getSupportActionBar().setTitle(teams);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);


                break;

            case  R.id.add_to_cart:
                //finish();
                Intent i =new Intent(Help_center.this, Add_to_card_activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;






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
}
