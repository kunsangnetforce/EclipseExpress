package com.netforceinfotech.eclipseexpress.ProductCategory.sub_category_brands_offers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.eclipseexpress.ProductCategory.Top_brands.CommomHolder_Topbrands;
import com.netforceinfotech.eclipseexpress.R;
import com.netforceinfotech.eclipseexpress.category.CategoryActivity;
import com.netforceinfotech.eclipseexpress.dashboard.dashboardcontentnew.CommomData;

import java.util.ArrayList;

/**
 * Created by Ritesh on 23-Aug-16.
 */
public class Brands_offers_adapter extends RecyclerView.Adapter<CommomHolder_offers_brands> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public Brands_offers_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommomHolder_offers_brands onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_brands_categories_row, parent, false);
        CommomHolder_offers_brands viewHolder = new CommomHolder_offers_brands(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommomHolder_offers_brands holder, int position) {
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, CategoryActivity.class);
//                context.startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
