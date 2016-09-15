package com.netforceinfotech.eclipseexpress.Account;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.netforceinfotech.eclipseexpress.Account.Address.My_address_activity;
import com.netforceinfotech.eclipseexpress.Account.My_orders.My_orders;
import com.netforceinfotech.eclipseexpress.Account.Mywishlist.MyWishlist_activity;
import com.netforceinfotech.eclipseexpress.Account.Reviews.My_review_adapter;
import com.netforceinfotech.eclipseexpress.Account.Reviews.Review;
import com.netforceinfotech.eclipseexpress.Editprofile.Edit_profile_activity;
import com.netforceinfotech.eclipseexpress.R;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;

public class User_account extends AppCompatActivity implements View.OnClickListener {
    private StikkyHeaderBuilder.ScrollViewBuilder stikkyHeader;
    private SwipyRefreshLayout mSwipyRefreshLayout;
    private ScrollView ScrollView_Commom;
    private LinearLayout layoutcontainer,ll_myorders,ll_mywishlist,ll_myaddress,ll_myreviews;
    private Toolbar toolbar;
    RelativeLayout Edit_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        setupToolBar();
        initilaizeview();
        addstikyheader();
    }

    private void initilaizeview() {
//        mSwipyRefreshLayout = (SwipyRefreshLayout)findViewById(R.id.swipyrefreshlayout);
        ScrollView_Commom = (ScrollView) findViewById(R.id.scroll_down);
        Edit_profile=(RelativeLayout) findViewById(R.id.relativeLayout2);
        layoutcontainer=(LinearLayout)findViewById(R.id.l_container);
        ll_mywishlist =(LinearLayout)findViewById(R.id.ll_mywishlist);
        ll_myorders=(LinearLayout)findViewById(R.id.ll_myorders);
        ll_myaddress=(LinearLayout)findViewById(R.id.ll_my_address);
        ll_myreviews=(LinearLayout)findViewById(R.id.ll_myreview);
        ll_myorders.setOnClickListener(this);
        ll_myaddress.setOnClickListener(this);
        ll_mywishlist.setOnClickListener(this);
        ll_myreviews.setOnClickListener(this);
        Edit_profile.setOnClickListener(this);
//        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh(SwipyRefreshLayoutDirection direction) {
//                refreshItem();
//            }
//        });


    }
    private void refreshItem()
    {
        try
        {
            Thread.sleep(2000);
            //mSwipyRefreshLayout.setRefreshing(false);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void addstikyheader() {
        stikkyHeader = StikkyHeaderBuilder.stickTo(ScrollView_Commom);
        stikkyHeader.setHeader(R.id.header,layoutcontainer)
                .minHeightHeaderDim(R.dimen.min_height_header3)
                .animator(new ParallaxStikkyAnimator())
                .build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {case R.id.ll_myorders:
            Intent i =new Intent(User_account.this, My_orders.class);
            startActivity(i);
            break;
            case R.id.ll_mywishlist:
                Intent i1 =new Intent(User_account.this, MyWishlist_activity.class);
                startActivity(i1);
                break;

            case  R.id.ll_my_address:

                    Intent i2 =new Intent(User_account.this, My_address_activity.class);
                    startActivity(i2);
                    break;
            case  R.id.ll_myreview:

                Intent i3 =new Intent(User_account.this, Review.class);
                startActivity(i3);
                break;
            case R.id.relativeLayout2:
                Intent i4 =new Intent(User_account.this, Edit_profile_activity.class);
                startActivity(i4);
                break;


        }

    }


    private class ParallaxStikkyAnimator extends HeaderStikkyAnimator {
        @Override
        public AnimatorBuilder getAnimatorBuilder() {
            View mHeader_image = getHeader().findViewById(R.id.linearlayout_container);
            return AnimatorBuilder.create().applyVerticalParallax(mHeader_image);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editprofile_menu, menu);
        ActionItemBadge.update(((AppCompatActivity) this), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(this, R.drawable.ic_cart_black)
                , ActionItemBadge.BadgeStyles.GREY, 3);
        return true;
    }



    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Logout";
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
