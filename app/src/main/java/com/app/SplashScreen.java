package com.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Utils.ServiceHandler;

/**
 * Created by Ean on 3/18/14.
 */
public class SplashScreen extends Activity {


    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String trophiesUrl = "http://10.0.2.2:3000/trophies"; // (the ip is for use with the web API on the emulator)

    // JSON Node names
    private static final String PK_TROPHY = "pkTrophy";
    private static final String TROPHY_NAME = "Name";
    private static final String TROPHY_URL = "TrophyUrl";


    // contacts JSONArray
    JSONArray trophies = null;


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    ActionBar actionBar;
    SharedPreferences mySharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        actionBar = getActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_splash);

        //Get request Test - doesn't use service handler class yet
        new GetTrophys().execute();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetTrophys extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SplashScreen.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(trophiesUrl, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                     trophies = new JSONArray(jsonStr);

                    // looping through All trophies
                    for (int i = 0; i < trophies.length(); i++) {
                        JSONObject c = trophies.getJSONObject(i);

                        Log.i("JSON", c.toString());
                        Log.i("JSON", c.getString(TROPHY_NAME));
                        Log.i("JSON", c.getString(PK_TROPHY));
                        Log.i("JSON", c.getString(TROPHY_URL));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Toast.makeText(getApplicationContext(), "JSON Trophy Test Post Execute, check LogCat!!! =)",
                    Toast.LENGTH_LONG).show();
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

        }

    }


}



