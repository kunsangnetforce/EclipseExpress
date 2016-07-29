package com.netforceinfotech.eclipseexpress.checkout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.netforceinfotech.eclipseexpress.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CheckoutActivity2 extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    RecyclerView recyclerView;
    private Toolbar toolbar;
    Context context;
    ArrayList<Data> datas = new ArrayList<>();
    RadioButton radioButtonPayPal, radioButtonCheck, radioButtonBankTransfer;
    CircleImageView circleImageViewpaypal, circleImageViewcheck, circleImageViewbanktransfer;

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
        radioButtonBankTransfer = (RadioButton) findViewById(R.id.radioDirectBankTransfer);
        radioButtonCheck = (RadioButton) findViewById(R.id.radioCheckPayment);
        radioButtonPayPal = (RadioButton) findViewById(R.id.radioPaypal);
        circleImageViewpaypal = (CircleImageView) findViewById(R.id.imageviewPaypal);
        circleImageViewcheck = (CircleImageView) findViewById(R.id.imageViewCheck);
        circleImageViewbanktransfer = (CircleImageView) findViewById(R.id.imageViewBankTransfer);
        radioButtonBankTransfer.setOnCheckedChangeListener(this);
        radioButtonPayPal.setOnCheckedChangeListener(this);
        radioButtonCheck.setOnCheckedChangeListener(this);
        circleImageViewbanktransfer.setOnClickListener(this);
        circleImageViewcheck.setOnClickListener(this);
        circleImageViewpaypal.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        Adapter adapter = new Adapter(context, datas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.radioCheckPayment:
                if(b){
                    circleImageViewpaypal.setImageResource(R.drawable.ic_radio_custom);
                }
                else {
                    circleImageViewpaypal.setImageResource(R.drawable.ic_circle);
                }
                break;
            case R.id.radioDirectBankTransfer:
                if(b){
                    circleImageViewpaypal.setImageResource(R.drawable.ic_radio_custom);
                }
                else {
                    circleImageViewpaypal.setImageResource(R.drawable.ic_circle);
                }

                break;
            case R.id.radioPaypal:
                if(b){
                    circleImageViewpaypal.setImageResource(R.drawable.ic_radio_custom);
                }
                else {
                    circleImageViewpaypal.setImageResource(R.drawable.ic_circle);
                }

                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageviewPaypal:
                radioButtonPayPal.setChecked(true);
                break;
            case R.id.imageViewCheck:
                radioButtonCheck.setChecked(true);
                break;
            case R.id.imageViewBankTransfer:
                radioButtonBankTransfer.setChecked(true);
                break;
        }
    }
}
