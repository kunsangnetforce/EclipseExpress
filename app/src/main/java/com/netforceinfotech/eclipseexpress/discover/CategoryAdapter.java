package com.netforceinfotech.eclipseexpress.discover;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.eclipseexpress.R;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    public static int position = 0;
    private List<CategoryData> itemList;
    private Context context;
    private String imagePath;


    public CategoryAdapter(Context context, List<CategoryData> itemList) {
        this.itemList = itemList;
        this.context = context;
        this.imagePath = imagePath;

    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dashboard, parent, false);
        CategoryHolder viewHolder = new CategoryHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, final int position) {

    }


    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {

        return 15;
        //  return itemList.size();
    }


}
