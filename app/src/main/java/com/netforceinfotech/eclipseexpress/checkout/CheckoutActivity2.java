package com.netforceinfotech.eclipseexpress.checkout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.netforceinfotech.eclipseexpress.R;

import java.util.ArrayList;

public class CheckoutActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    private Toolbar toolbar;
    Context context;
    ArrayList<Data> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout2);
        context = this;
        setupToolBar("Checkout");
        initview();
    }

    private void setupToolBar(String s) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = s;
        getSupportActionBar().setTitle(teams);

    }

    private void initview() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        Adapter adapter = new Adapter(context, datas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
