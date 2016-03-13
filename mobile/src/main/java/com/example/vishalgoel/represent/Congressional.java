package com.example.vishalgoel.represent;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.GuestCallback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.List;

import io.fabric.sdk.android.Fabric;

public class Congressional extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_congressional);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Bundle bund = intent.getBundleExtra("Bundle of Congressional Information");

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        LinearLayout layout = (LinearLayout) findViewById(R.id.content);
        layout.addView(textView);

        TextView view1 = (TextView) findViewById(R.id.Congressional1);
        view1.setText(bund.getString("rep_or_sen0") + " " + bund.getString("first_name0") + " " + bund.getString("last_name0") + " " + "(" + bund.getString("party0") + ")\n" + bund.getString("email0") + "\n" + bund.getString("website0") + "\n");

        TextView view2 = (TextView) findViewById(R.id.Congressional2);
        view2.setText(bund.getString("rep_or_sen1") + " " + bund.getString("first_name1") + " " + bund.getString("last_name1") + " " + "(" + bund.getString("party1") + ")\n" + bund.getString("email1") + "\n" + bund.getString("website1") + "\n");

        TextView view3 = (TextView) findViewById(R.id.Congressional3);
        view3.setText(bund.getString("rep_or_sen2") + " " + bund.getString("first_name2") + " " + bund.getString("last_name2") + " " + "(" + bund.getString("party2") + ")\n" + bund.getString("email2") + "\n" + bund.getString("website2") + "\n");

        ImageView pic1 = (ImageView) findViewById(R.id.Picture1);
        String str1 = bund.getString("bioguide_id0");
        Picasso.with(this).load("https://theunitedstates.io/images/congress/225x275/" + str1 + ".jpg").into(pic1);

        ImageView pic2 = (ImageView) findViewById(R.id.Picture2);
        String str2 = bund.getString("bioguide_id1");
        Picasso.with(this).load("https://theunitedstates.io/images/congress/225x275/" + str2 + ".jpg").into(pic2);

        ImageView pic3 = (ImageView) findViewById(R.id.Picture3);
        String str3 = bund.getString("bioguide_id2");
        Picasso.with(this).load("https://theunitedstates.io/images/congress/225x275/" + str3 + ".jpg").into(pic3);


        String tweetId = bund.getString("twitter_id0");

         // TODO: Use a more specific parent
         //final ViewGroup parentView = (ViewGroup) getWindow().getDecorView().getRootView();
        // final LinearLayout myLayout = (LinearLayout) findViewById(R.id.bike_tweet);
//         // TODO: Base this Tweet ID on some data from elsewhere in your app
//         long tweetId = 631879971628183552L;
//         TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
//             @Override
//             public void success(Result<Tweet> result) {
//                 TweetView tweetView = new TweetView(Congressional.this, result.data);
//                 parentView.addView(tweetView);
//                 myLayout.addView(tweetView);
//             }
//
//             @Override
//             public void failure(TwitterException exception) {
//                 Log.d("TwitterKit", "Load Tweet failure", exception);
//             }
//         });
        Log.d("Congressional", "Trying to build timeline");
        UserTimeline userTimeline = new UserTimeline.Builder().screenName(tweetId).build();
        Log.d("Congressional", "Trying to built the timeline");



        /*
        TwitterLoginButton loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // Pass the activity result to the fragment, which will then pass the result to the login
            // button.
            Fragment fragment = getFragmentManager().findFragmentById(R.id.your_fragment_id);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }

        TwitterSession session = Twitter.getSessionManager().getActiveSession();
        TwitterAuthToken authToken = session.getAuthToken();
        String token = authToken.token;
        String secret = authToken.secret;

        TwitterApiClient twitterApiClient =  TwitterCore.getInstance().getApiClient(guestAppSession);
        twitterApiClient.getSearchService().tweets("#fabric", null, null, null, null, 50, null, null, null, true, new GuestCallback<>(new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                // use result tweets
            }

            @Override
            public void failure(TwitterException exception) {
                // handle exceptions
            }
        }));

         // TODO: Use a more specific parent
         final ViewGroup parentView = (ViewGroup) getWindow().getDecorView().getRootView();
         // TODO: Base this Tweet ID on some data from elsewhere in your app
         long tweetId = 631879971628183552L;
         TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
             @Override
             public void success(Result<Tweet> result) {
                 TweetView tweetView = new TweetView(Congressional.this, result.data);
                 parentView.addView(tweetView);
             }

             @Override
             public void failure(TwitterException exception) {
                 Log.d("TwitterKit", "Load Tweet failure", exception);
             }
         });
        */


    }

}
