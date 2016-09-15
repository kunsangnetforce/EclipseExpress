package com.netforceinfotech.eclipseexpress.Account.Reviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.netforceinfotech.eclipseexpress.Account.Mywishlist.My_wishlist_adapter;
import com.netforceinfotech.eclipseexpress.R;

public class Review extends AppCompatActivity {

    private LinearLayout layoutcontainer,ll_myorders,ll_mywishlist,ll_myaddress;
    private Toolbar toolbar;
    RecyclerView rl_review;
    LinearLayoutManager my_reviews_linearlayout_manager;
    My_review_adapter review_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        my_reviews_linearlayout_manager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        review_adapter=new My_review_adapter(this);
        initializerecycle();
        setupToolBar();
    }

    private void initializerecycle() {
        rl_review = (RecyclerView)findViewById(R.id.recycler_my_review);
        rl_review.setLayoutManager(my_reviews_linearlayout_manager);
        rl_review.setAdapter(review_adapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editprofile_menu, menu);
//        ActionItemBadge.update(((AppCompatActivity) this), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(this, R.drawable.ic_cart_black)
//                , ActionItemBadge.BadgeStyles.GREY, 3);
        return true;
    }



    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "My Review";
        getSupportActionBar().setTitle(teams);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
