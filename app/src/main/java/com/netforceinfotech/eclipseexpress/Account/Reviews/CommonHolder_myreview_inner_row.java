package com.netforceinfotech.eclipseexpress.Account.Reviews;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.netforceinfotech.eclipseexpress.R;

/**
 * Created by abcd on 8/29/2016.
 */
public class CommonHolder_myreview_inner_row extends RecyclerView.ViewHolder {
    CardView cardview;

    public CommonHolder_myreview_inner_row(View itemView) {
        super(itemView);
        cardview = (CardView) itemView.findViewById(R.id.cardview_myreviews_inner);

    }
}