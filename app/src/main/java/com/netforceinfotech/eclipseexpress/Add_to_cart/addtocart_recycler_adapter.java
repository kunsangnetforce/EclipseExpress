package com.netforceinfotech.eclipseexpress.Add_to_cart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.netforceinfotech.eclipseexpress.Account.My_orders.CommonHolder_my_orders;
import com.netforceinfotech.eclipseexpress.R;
import com.netforceinfotech.eclipseexpress.dashboard.dashboardcontentnew.CommomData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abcd on 8/31/2016.
 */
public class addtocart_recycler_adapter extends RecyclerView.Adapter<CommonHolder_addtocard> {
    Context context;
    ArrayList<CommomData> commomDatas;
    String count_item[]={"1","2","3","4","5","6","7","8","9","More"};

    public addtocart_recycler_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommonHolder_addtocard onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addtocard_single_row, parent, false);
        CommonHolder_addtocard viewHolder = new CommonHolder_addtocard(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder_addtocard holder, int position) {

        List<String> count = new ArrayList<String>();
        count.add("1");
        count.add("2");
        count.add("3");
        count.add("4");
        count.add("5");
        count.add("6");
        count.add("7");
        count.add("8");
        count.add("9");
        count.add("10");
        count.add("More");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,R.layout.spinner_layout,count );

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        holder.spinner.setAdapter(dataAdapter);
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