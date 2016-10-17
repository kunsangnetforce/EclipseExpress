package com.netforceinfotech.eclipseexpress.dashboard.dashboardcontentnew;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.netforceinfotech.eclipseexpress.R;
import com.netforceinfotech.eclipseexpress.category.CategoryActivity;
import com.netforceinfotech.eclipseexpress.productdetail.ProductDetailActivity;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;

/**
 * Created by Netforce on 7/25/2016.
 */
public class CommomAdapter extends RecyclerView.Adapter<CommomHolder> {
    Context context;
    ArrayList<CommomData> commomDatas;

    CommomAdapter(Context context, ArrayList<CommomData> commomDatas) {
        this.context = context;
        this.commomDatas = commomDatas;
    }

    @Override
    public CommomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dashboard, parent, false);
        CommomHolder viewHolder = new CommomHolder(view);


        //RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());




                View v = inflater.inflate(R.layout.row_dashboard, parent, false);
                viewHolder = new CommomHolder(v);






        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommomHolder holder, int position) {

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ProductDetailActivity.class);
                context.startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });


    }

    @Override
    public int getItemCount() {
        return commomDatas.size();
    }








}
