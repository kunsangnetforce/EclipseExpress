package com.netforceinfotech.eclipseexpress.Account.My_orders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.netforceinfotech.eclipseexpress.R;

/**
 * Created by abcd on 8/26/2016.
 */
public class CommonHolder_my_orders extends RecyclerView.ViewHolder {
    CardView cardview;
    public CommonHolder_my_orders(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_myreviews);
    }
}