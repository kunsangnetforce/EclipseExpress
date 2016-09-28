package com.netforceinfotech.eclipseexpress.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.eclipseexpress.R;
import com.netforceinfotech.eclipseexpress.general.Validation;
import com.netforceinfotech.eclipseexpress.dashboard.DashboardActivity;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

     Context context;
     TextView textViewRegistration,textViewForgotPassword;
     private Intent intent;
     TwitterLoginButton loginButton;
     Button twitter_button;
     EditText email_edittext,password_edittext;
    ProgressDialog _progressDialog;
    public  static  String MyPREFERENCES="Ecllipse";
    SharedPreferences sharedpreferences;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        }
        textViewRegistration = (TextView) findViewById(R.id.textViewRegistration);
        textViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);

        twitter_button=(Button) findViewById(R.id.buttonTwitter);

        email_edittext = (EditText) findViewById(R.id.email);
        password_edittext=(EditText)findViewById(R.id.password_edittext);

        twitter_button.setOnClickListener(this);
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null)
        {

            twitter_button.setText("Logout");
        }
        else
        {

            twitter_button.setText("twitter");

        }

        setupTwitter();
        textViewRegistration.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
        _progressDialog = new ProgressDialog(this);

        setupSharedpreference();


        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    private void setupSharedpreference() {
    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);




    }

    private void setupTwitter()
    {
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
             /*   TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                System.out.println("msg==========================="+ msg);


                loginButton.setVisibility(View.INVISIBLE);*/


                login(result);


            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }


    public void login(Result<TwitterSession> result) {

//Creating a twitter session with result's data
        TwitterSession session = result.data;

        //Getting the username from session
        final String username = session.getUserName();

        //This code will fetch the profile image URL
        //Getting the account service of the user logged in
        Twitter.getApiClient(session).getAccountService()
                .verifyCredentials(true, false, new Callback<User>() {
                    @Override
                    public void failure(TwitterException e) {
                        //If any error occurs handle it here
                    }

                    @Override
                    public void success(Result<User> userResult) {
                        //If it succeeds creating a User object from userResult.data
                        twitter_button.setText("Logout");
                        User user = userResult.data;

                        //Getting the profile image url
                        String profileImage = user.profileImageUrl.replace("_normal", "");

                        Log.d("done", "name-->" + username + "url-->" + profileImage);
                        // Toast.makeText(this,"name-->"+username + "url-->"+profileImage,Toast.LENGTH_LONG).show();

                    }
                });
    }

    public void logoutTwitter() {
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null)
        {
            ClearCookies(getApplicationContext());
            Twitter.getSessionManager().clearActiveSession();
            Twitter.logOut();
            twitter_button.setText("twitter");
        }
        else
        {

            loginButton.performClick();
        }
    }

    public static void ClearCookies(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1)
        {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        }
        else
        {
            CookieSyncManager cookieSyncMngr= CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager= CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
// Make sure that the loginButton hears the result from any
// Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.textViewRegistration:

                intent = new Intent(context, RegistrationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.buttonLogin:

                String email_data = email_edittext.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if (Validation.isEmailAddress(email_edittext,false))

                {
                    if(email_data.length()!=0|| password_edittext.getText().length()!=0) {

                        _progressDialog.show();

                        String url="https://netforcesales.com/eclipseexpress/web_api.php?type=login&email="+email_edittext.getText().toString()+"&password="+password_edittext.getText().toString();


                        Log.e("url", url);
                        setupSelfSSLCert();
                        Ion.with(this)
                                .load(url)
                                .progressDialog(_progressDialog)
                                .asJsonObject()

                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {
                                        if (result != null)

                                        {


                                            String status = result.get("status").toString();
                                            if(status.contains("sussess"))
                                            {
                                                JsonObject userData = (JsonObject) result.get("userData");
                                                String message = userData.get("customer_id").toString();
                                                 Log.e("customer_id",message);
                                                ShowMessage("Sucessfully login");
                                                intent = new Intent(context, DashboardActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.enter, R.anim.exit);
                                            }
                                            else{
                                                String message = result.get("message").toString();
                                                ShowMessage(message);
                                            }


                                            //String message = result.get("message").toString();

                                            Log.e("status", "st" + status);

                                            _progressDialog.dismiss();


                                        } else {
                                            Log.e("error", e.toString());
                                        }

//                                        JsonObject js =result;
//
//                                        String status=result.get("status").toString();
//                                        String customer_id=result.get("customer_id").toString();
//                                        String message=result.get("message").toString();
//                                        Log.e("status","st"+status+"cust"+customer_id+"mes"+message);


                                        // do stuff with the result or error
                                    }
                                });



                    }
                    else{
                        ShowMessage("can't leave blank");
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.buttonTwitter:
                logoutTwitter();

                break;
            case R.id.textViewForgotPassword:
                intent = new Intent(context, Forgot_password.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

        }

    }

    private void ShowMessage(String s) {

        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();

    }



    private static class Trust implements X509TrustManager {

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

    public  void setupSelfSSLCert() {
        final Trust trust = new Trust();
        final TrustManager[] trustmanagers = new TrustManager[]{trust};
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustmanagers, new SecureRandom());
            Ion.getInstance(getApplicationContext(), "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(getApplicationContext(), "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }
    }

}




