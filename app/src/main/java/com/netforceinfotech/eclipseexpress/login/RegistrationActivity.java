package com.netforceinfotech.eclipseexpress.login;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.eclipseexpress.R;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    Button registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Window window = getWindow();


// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        }
        initview();
    }

    private void initview() {

        registration=(Button)findViewById(R.id.buttonGetstarted);
        registration.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.buttonGetstarted:
                Ion.with(this)
                        .load("https://netforcesales.com/eclipseexpress/web_api.php?type=get_category")
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                Log.e("result", result.toString());

                                // do stuff with the result or error
                            }
                        });



        }
    }
}
