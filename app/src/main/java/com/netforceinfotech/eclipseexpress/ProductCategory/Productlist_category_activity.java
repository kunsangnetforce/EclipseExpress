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
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.netforceinfotech.eclipseexpress.ProductCategory.Flat_offers.Flat_offer_adapter;
import com.netforceinfotech.eclipseexpress.ProductCategory.Show_subcategory.Product_category_grid_adapter;

import com.netforceinfotech.eclipseexpress.ProductCategory.Top_selling.Top_selling_adapter;
import com.netforceinfotech.eclipseexpress.ProductCategory.sub_category_brands_offers.Brands_offers_adapter;
import com.netforceinfotech.eclipseexpress.R;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;

public class Productlist_category_activity extends AppCompatActivity {
   private StikkyHeaderBuilder stikkyHeader;

    private ScrollView ScrollView_Commom,scrollView;
    private RelativeLayout layoutcontainer;
    private Toolbar toolbar;
    RecyclerView recycleview_subcat_list,recycleview_top_brands,recyclerView_brands_offers,recyclerView_flat_offers,
            recyclerView_top_selling,recyclerView_top_rated;
    private Product_category_grid_adapter category_adapter;
    private Topbrands_adapter topbrands_adapter;
    private Flat_offer_adapter flat_offer_adapter;


    private Brands_offers_adapter brands_offers_adapter;
    private Top_selling_adapter top_selling_adapter;

    private LinearLayoutManager subcat_brands_offers__linearlayout_manager,
    flat_offers_linearlayout_manager,top_selling_linearlayout_manager,toprated_linearlayout_manager,topbrands_linearlayout_manager;

    GridLayoutManager gridLayoutManager;
    String Sub_cat_names[]={"Top & Tees","Coat & Jackets","Shirts","Jeans","Pants","Shorts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist_category_activity);
        topbrands_linearlayout_manager =  new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);;
        subcat_brands_offers__linearlayout_manager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        top_selling_linearlayout_manager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        toprated_linearlayout_manager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        flat_offers_linearlayout_manager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);



        gridLayoutManager=new GridLayoutManager(this,3);
        topbrands_adapter= new Topbrands_adapter(this);
        category_adapter = new Product_category_grid_adapter(this);
        top_selling_adapter=new Top_selling_adapter(this);
        brands_offers_adapter=new Brands_offers_adapter(this);
        flat_offer_adapter=new Flat_offer_adapter(this);
        initilaizeview();
        setupToolBar();
        addstikyheader();
        setuprecyclers();
//        setupsubcategory();
//        setup_subcat_brands_offers();

        
        
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
        setuptopbrands();
        setup_subcat_brands_offers();
       setup_flat_offers();
        setup_top_selling();
       setuptoprated();
//        setupAllSale();
    }

    private void setuptoprated() {
        recyclerView_top_rated = (RecyclerView)findViewById(R.id.recycyle_top_rated);
        recyclerView_top_rated.setLayoutManager(toprated_linearlayout_manager);
        recyclerView_top_rated.setAdapter(top_selling_adapter);

    }

    private void setup_top_selling() {
        recyclerView_top_selling = (RecyclerView)findViewById(R.id.recycler_top_selling);
        recyclerView_top_selling.setLayoutManager(top_selling_linearlayout_manager);
        recyclerView_top_selling.setAdapter(top_selling_adapter);
    }

    private void setup_flat_offers() {
        recyclerView_flat_offers = (RecyclerView)findViewById(R.id.recycler_flat_offers);
        recyclerView_flat_offers.setLayoutManager(flat_offers_linearlayout_manager);
        recyclerView_flat_offers.setAdapter(flat_offer_adapter);

    }


    //initilaize top brands
    private void setuptopbrands()
    {
        recycleview_top_brands = (RecyclerView)findViewById(R.id.recycler_top_brands);
        recycleview_top_brands.setLayoutManager(topbrands_linearlayout_manager);
        recycleview_top_brands.setAdapter(topbrands_adapter);

    }
    //initilaize_subcategory
    private void setupsubcategory() {
        recycleview_subcat_list = (RecyclerView)findViewById(R.id.RecyclerView_subcategory);
        recycleview_subcat_list.setHasFixedSize(true);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return (position % 2 == 0 ? 2 : 1);
//            }
//        });
        recycleview_subcat_list.setLayoutManager(gridLayoutManager);
        recycleview_subcat_list.setAdapter(category_adapter);

    }
    //initilaize offers according to brands
    private void setup_subcat_brands_offers() {
        recyclerView_brands_offers = (RecyclerView)findViewById(R.id.recycler_offers);
        recyclerView_brands_offers.setLayoutManager(subcat_brands_offers__linearlayout_manager);
        recyclerView_brands_offers.setAdapter(brands_offers_adapter);

    }
    private void addstikyheader() {
        stikkyHeader = StikkyHeaderBuilder.stickTo(ScrollView_Commom);
        stikkyHeader.setHeader(R.id.header4,layoutcontainer)
                .minHeightHeaderDim(R.dimen.min_height_header3)
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
