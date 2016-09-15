package com.netforceinfotech.eclipseexpress.ChangePassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.netforceinfotech.eclipseexpress.Add_to_cart.Add_to_card_activity;
import com.netforceinfotech.eclipseexpress.R;

public class change_password extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setupToolBar();
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_change_password);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Change Password";
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
            Intent i =new Intent(change_password.this, Add_to_card_activity.class);
            startActivity(i);
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

               break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }




}
