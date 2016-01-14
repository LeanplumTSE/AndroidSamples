package com.fede.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fede on 1/13/16.
 */
class RestAPIactivity extends AsyncTask<String, String, String> {

    InputStream in = null;

    @Override
    protected String doInBackground(String... params) {

        String urlString=params[0]; // URL to call
        String resultToDisplay = "";
        InputStream in = null;

        // HTTP Get
        try {

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());

        } catch (Exception e ) {

            System.out.println(e.getMessage());
            return e.getMessage();

        }

        return resultToDisplay;
    }

    protected void onPostExecute(String result) {

    }
}
