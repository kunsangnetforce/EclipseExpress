package com.netforceinfotech.eclipseexpress.ProductCategory.Show_subcategory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.eclipseexpress.ProductCategory.Show_subcategory.CommomHolder_subcategory;
import com.netforceinfotech.eclipseexpress.R;
import com.netforceinfotech.eclipseexpress.category.CategoryActivity;
import com.netforceinfotech.eclipseexpress.dashboard.dashboardcontentnew.CommomData;

import java.util.ArrayList;

/**
 * Created by Ritesh on 22-Aug-16.
 */
public class Product_category_grid_adapter extends RecyclerView.Adapter<CommomHolder_subcategory> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public Product_category_grid_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommomHolder_subcategory onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_cat_grid_row, parent, false);
        CommomHolder_subcategory viewHolder = new CommomHolder_subcategory(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommomHolder_subcategory holder, int position) {
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
        return 6;
    }
}
