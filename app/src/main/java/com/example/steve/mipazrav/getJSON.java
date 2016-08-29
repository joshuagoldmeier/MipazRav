package com.example.steve.mipazrav;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Steve on 8/25/2016.
 */
class getJSON{

    private final Context context;
    private final String url;

    public getJSON(Context context, String url)
    {
        this.context = context;
        this.url = url;
    }

    public String executeTheWhatever(){
        RetrieveFeedTask rtf = new RetrieveFeedTask();
        rtf.execute();
        return rtf.thePage;
    }


    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
        String thePage;

        private Exception exception;

        protected String doInBackground(Void... urls) {

            //TODO: Do some validation here
            queryAPI();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
//            try {
//                ParseXML.parse(getJSON.this.context);
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }

        public String queryAPI() {

            try {
                URL url = new URL(getJSON.this.url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    thePage = stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);

            }

            return thePage;
        }
    }}