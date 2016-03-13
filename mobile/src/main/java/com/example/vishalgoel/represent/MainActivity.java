   package com.example.vishalgoel.represent;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


   public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "2qNWCFEdS65cOLL90fcZg1IK0";
    private static final String TWITTER_SECRET = "	3ht2URYr93EpKv3SeUtHHDCxnTpn8FD0lrUMVQghIZwX8vTpeP ";

       public final static String EXTRA_MESSAGE = "com.example.vishalgoel.represent.MESSAGE";
       String code;
       EditText zip;
       Button go;
       GoogleApiClient mGoogleApiClient;
       Location mLastLocation;
       double mLatitudeText;
       double mLongitudeText;
       String county;

       protected void onStart() {
           mGoogleApiClient.connect();
           super.onStart();
       }

       protected void onStop() {
           mGoogleApiClient.disconnect();
           super.onStop();
       }
       @Override
       public void onConnected(Bundle connectionHint) {
//           Log.d("tag", "are you getting here 2");

//           mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                   mGoogleApiClient);
//           if (mLastLocation != null) {
               //mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
               //mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
//               mLatitudeText = mLastLocation.getLatitude();
//               mLongitudeText = mLastLocation.getLongitude();
          // }
       }
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
           Log.d("tag", "are you getting here 1");

           // Create an instance of GoogleAPIClient.
           if (mGoogleApiClient == null) {
               mGoogleApiClient = new GoogleApiClient.Builder(this)
                       .addConnectionCallbacks(this)
                       .addOnConnectionFailedListener(this)
                       .addApi(LocationServices.API)
                       .build();
           }
        //this.onStart();
        //this.onConnected(savedInstanceState);

           mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
       if (mLastLocation != null) {
           mLatitudeText = mLastLocation.getLatitude();
           mLongitudeText = mLastLocation.getLongitude();
           //county = getCountyNameFromLatLong(getApplicationContext(), mLatitudeText, mLongitudeText);
           }
           //county = getCountyNameFromLatLong(getApplicationContext(), 36.1215, -115.1739);
        //double myLatitude = Double.parseDouble(mLatitudeText.getText().toString());
        Log.d("tag", "are you getting here");
        //Log.d("tag1", mLongitudeText);
        //double myLongitude = Double.parseDouble(mLongitudeText.getText().toString());
        //county = getCountyNameFromLatLong(getApplicationContext(), 37.257447, -122.021958);


       }

       public static String GET(String url){
           Log.i("URL", url);
           HttpURLConnection urlConnection = null;
           try {
               URL url2 = new URL(url);
               urlConnection = (HttpURLConnection) url2.openConnection();
               InputStream in = new BufferedInputStream(urlConnection.getInputStream());
               return convertInputStreamToString(in);
           }catch(Exception e){
               Log.i("Exception", e.toString());
               return "It broke ";
           }
           finally {
               if (urlConnection != null){
                   urlConnection.disconnect();
               }
           }
       }

       // convert inputstream to String
       private static String convertInputStreamToString(InputStream inputStream) throws IOException{
           BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
           String line = "";
           String result = "";
           while((line = bufferedReader.readLine()) != null)
               result += line;

           inputStream.close();
           return result;

       }

       private class HttpAsyncTask extends AsyncTask<String, Void, String> {
           @Override
           protected String doInBackground(String... urls) {

               return GET(urls[0]);
           }
           // onPostExecute displays the results of the AsyncTask.
           @Override
           protected void onPostExecute(String result) {
               Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
           }
       }

       @Override
       public void onConnectionSuspended(int i) {

       }

    /*
       LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
       Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
       double longitude = location.getLongitude();
       double latitude = location.getLatitude();

       private final LocationListener locationListener = new LocationListener() {
           public void onLocationChanged(Location location) {
               longitude = location.getLongitude();
               latitude = location.getLatitude();
           }
       }
       long two_thousand = 2000;
       float ten = 10;
       lm.requestLocationUpdates(lm.GPS_PROVIDER, two_thousand, ten, locationListener);

       private class MyLocationListener implements LocationListener {

           public void onLocationChanged(Location location) {
               String format = String.format("New Location \n Longitude: %1$s \n Latitude: %2$s", location.getLongitude(), location.getLatitude());
               String message = format;
               Toast.makeText(getApplicationContext(), "message", Toast.LENGTH_LONG).show();
           }

           public void onStatusChanged(String s, int i, Bundle b) {
               Toast.makeText(getApplicationContext(), "Provider status changed", Toast.LENGTH_LONG).show();
           }

           public void onProviderDisabled(String s) {
               Toast.makeText(getApplicationContext(), "Provider disabled by the user. GPS turned off", Toast.LENGTH_LONG).show();
           }

           public void onProviderEnabled(String s) {
               Toast.makeText(getApplicationContext(), "Provider enabled by the user. GPS turned on", Toast.LENGTH_LONG).show();
           }
       }
     */

       public static String getCountyNameFromLatLong(Context context, double latitude, double longitude) {
           Geocoder geocoder = new Geocoder(context, Locale.getDefault());
           List<Address> addresses = null;
           try {
               addresses = geocoder.getFromLocation(latitude, longitude, 1);
               Address result;
               if (addresses != null && !addresses.isEmpty()) {
                   return addresses.get(0).getLocality();
               }
               return null;
           } catch (IOException ignored) {
               //do something
               return "FAILURE";
           }
       }

       public void sendMessage(View view){
           Intent intent = new Intent(this, Congressional.class);

           zip = (EditText)findViewById(R.id.ZipCode);
           go = (Button)findViewById(R.id.GoButton);
           code = zip.getText().toString();
           intent.putExtra(EXTRA_MESSAGE, code);
           Bundle bund = makeCongressionalAPICall(code);
           intent.putExtra("Bundle of Congressional Information", bund);
           startActivity(intent);

//           Intent intent_wear = new Intent(this, MainActivity.class);
//           zip = (EditText)findViewById(R.id.ZipCode);
//           go = (Button)findViewById(R.id.GoButton);
//           code = Double.parseDouble(zip.getText().toString());
//           intent_wear.putExtra(EXTRA_MESSAGE, code);
//           startActivity(intent_wear);
       }


       @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


       @Override
       public void onConnectionFailed(ConnectionResult connectionResult) {

       }

       public Bundle makeCongressionalAPICall(String Zip){
           //TODO: make this work zipcode

           String SUNLIGHT = "http://congress.api.sunlightfoundation.com";
           String KEY = "apikey=77d34e2a099548f9a5f888228a26b7f7";
            Log.i("Zip", Zip);
           String theString = null;
           JSONObject jsonObj;
           JSONArray arr = null;
           try {
               theString = new HttpAsyncTask().execute(SUNLIGHT + "/legislators/locate?zip=" + Zip + "&" + KEY).get();
               Log.i("vishal", theString);
               jsonObj = new JSONObject(theString);
               arr = jsonObj.getJSONArray("results");
           }catch(Exception e){
               Log.i("vineet", "it broke " + e.toString());
           }
           try {
               Bundle bund = new Bundle();
               for (int i = 0; i < arr.length(); i++) {
                   String first_name = arr.getJSONObject(i).getString("first_name");
                   String last_name = arr.getJSONObject(i).getString("last_name");
                   String party = arr.getJSONObject(i).getString("party");
                   String email = arr.getJSONObject(i).getString("oc_email");
                   String website = arr.getJSONObject(i).getString("website");
                   String rep_or_sen = arr.getJSONObject(i).getString("title");
                   String twitter_id = arr.getJSONObject(i).getString("twitter_id");
                   String term_end = arr.getJSONObject(i).getString("term_end");
                   String bioguide_id = arr.getJSONObject(i).getString("bioguide_id");


                   bund.putString("first_name" + i, first_name);
                   bund.putString("last_name" + i, last_name);
                   bund.putString("party" + i, party);
                   bund.putString("email" + i, email);
                   bund.putString("website" + i, website);
                   bund.putString("rep_or_sen" + i, rep_or_sen);
                   bund.putString("twitter_id" + i, twitter_id);
                   bund.putString("term_end" + i, term_end);
                   bund.putString("bioguide_id" + i, bioguide_id);
               }
               return bund;
           }catch(Exception e){
               Log.i("uh oh", "It errored out");
               Log.i("Exception", e.toString());
           }
           return null;
       }
   }
