package com.netforceinfotech.eclipseexpress.login;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
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

import com.netforceinfotech.eclipseexpress.R;
import com.netforceinfotech.eclipseexpress.com.netforceinfotech.eclipseexpress.common.Validation;
import com.netforceinfotech.eclipseexpress.dashboard.DashboardActivity;
import com.netforceinfotech.eclipseexpress.json.ManActivity;
import com.netforceinfotech.eclipseexpress.jsonclass.Woman;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

     Context context;
     TextView textViewRegistration;
     private Intent intent;
     TwitterLoginButton loginButton;
     Button twitter_button;
     EditText email_edittext;

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
        twitter_button=(Button) findViewById(R.id.buttonTwitter);

        email_edittext = (EditText) findViewById(R.id.email);

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

        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    private void setupTwitter()
    {
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>()
        {
            @Override
            public void success(Result<TwitterSession> result)
            {
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


                if (Validation.isEmailAddress(email_edittext,true))
                {
                    intent = new Intent(context, DashboardActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.buttonTwitter:
                logoutTwitter();

                break;
        }

    }
}
