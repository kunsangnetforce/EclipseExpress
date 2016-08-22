package com.netforceinfotech.eclipseexpress.ProductCategory;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.netforceinfotech.eclipseexpress.R;
import com.netforceinfotech.eclipseexpress.dashboard.dashboardcontentnew.CommomAdapter;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;

public class Productlist_category_activity extends AppCompatActivity {
   private StikkyHeaderBuilder stikkyHeader;

    private ScrollView ScrollView_Commom,scrollView;
    private RelativeLayout layoutcontainer;
    private Toolbar toolbar;
    RecyclerView recycleview_subcat_list;
    private Product_category_grid_adapter category_adapter;
    private LinearLayoutManager llManagerAll_subcategory;

    GridLayoutManager gridLayoutManager;
    String Sub_cat_names[]={"Top & Tees","Coat & Jackets","Shirts","Jeans","Pants","Shorts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist_category_activity);
        llManagerAll_subcategory = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        gridLayoutManager=new GridLayoutManager(this,3);
        category_adapter = new Product_category_grid_adapter(this);
        initilaizeview();
        setupToolBar();
        addstikyheader();
        setupsubcategory();

        
        
    }


    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_subcategory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "MEN CLOTHING";
        getSupportActionBar().setTitle(teams);

    }
    private void initilaizeview() {
        ScrollView_Commom = (ScrollView) findViewById(R.id.scroll_down4);
        layoutcontainer=(RelativeLayout)findViewById(R.id.cat_layout_holder);


    }
    private void setuprecyclers() {
       setupsubcategory();
//        setupBestSelling();
//        setupEclipseCollection();
//        setupTrendingStyles();
//        setupAllSale();
    }

    private void setupsubcategory() {
        recycleview_subcat_list = (RecyclerView)findViewById(R.id.RecyclerView_subcategory);
        recycleview_subcat_list.setLayoutManager(gridLayoutManager);
        recycleview_subcat_list.setAdapter(category_adapter);

    }
    private void addstikyheader() {
        stikkyHeader = StikkyHeaderBuilder.stickTo(ScrollView_Commom);
        stikkyHeader.setHeader(R.id.header4,layoutcontainer)
                .minHeightHeaderDim(R.dimen.min_height_header)
                .animator(new ParallaxStikkyAnimator())
                .build();
    }



    private class ParallaxStikkyAnimator extends HeaderStikkyAnimator {
        @Override
        public AnimatorBuilder getAnimatorBuilder() {
            View mHeader_image = getHeader().findViewById(R.id.relativeLayout_main_subcat);
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

}
