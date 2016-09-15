package com.netforceinfotech.eclipseexpress.Add_to_cart;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;

import com.netforceinfotech.eclipseexpress.R;

/**
 * Created by abcd on 9/1/2016.
 */
public class CommonHolder_addtocard extends RecyclerView.ViewHolder {
    CardView cardview;
    Spinner spinner;
    public CommonHolder_addtocard(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_addtocard);
        spinner=(Spinner)itemView.findViewById(R.id.spinner_addto_card);
    }
}