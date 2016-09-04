package com.example.steve.mipazrav;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Steve on 9/4/2016.
 */
public class DownloadFromWebservice {

  private QueryType mQueryType;
  private Context context;
  private String apiArg;
  private boolean mPlayAudio;
  private boolean nothingFound;

  public enum QueryType {SHIUR, AUDIO, PDF}

  public DownloadFromWebservice(Context c, QueryType q, String apiArg) {
    mQueryType = q;
    context = c;
    this.apiArg = apiArg;
    new getDetails().execute();
  }

  public void shiurimQuery() throws JSONException {
    String results = establishConnection("http://www.mipazrav.com/webservice/api/Classes/subCategories?Token=a643ec87-a511-41ee-ae69-61068a4be758&" + apiArg);
    parseShiurimJSON(results);
  }

  private String establishConnection(String u) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      URL url = new URL(u);
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
      try {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
          stringBuilder.append(line);
        }
        bufferedReader.close();
      } finally {
        urlConnection.disconnect();
      }
    } catch (Exception e) {
      Log.e("ERROR", e.getMessage(), e);

    }
    return stringBuilder.toString();
  }

  private void parseShiurimJSON(String json) throws JSONException {
    ArrayList items = new ArrayList<shiurObject>();
    JSONArray jArray = new JSONArray(json);
    for (int i = 0; i < jArray.length(); i++) {
      try {
        JSONObject oneObject = jArray.getJSONObject(i);
        // Pulling items from the array

        items.add(new shiurObject(oneObject.getString("Name"), oneObject.getString("Description"), oneObject.getString("RecID")));

      } catch (JSONException e) {
        // Oops
      }
    }
    if (items.size() <= 0) {
      audioQuery();//if there's no result from shiurim then try checking audio file names with the same apiArgs

      return;
    }
    Intent i = new Intent(context, ShiurimList.class);
    Result.r = items;
    context.startActivity(i);
  }

  private void parseAudioNamesJSON(String json) throws JSONException {
    JSONArray jArray = new JSONArray(json);
    ArrayList items = new ArrayList<shiurObject>();
    for (int i = 0; i < jArray.length(); i++) {
      try {
        JSONObject oneObject = jArray.getJSONObject(i);
        // Pulling items from the array
        items.add(new shiurObject(oneObject.getString("AudioName"), oneObject.getString("DownloadFile"), oneObject.getString("RecID")));
      } catch (JSONException e) {
        // Oops
      }
    }
    if (items.size() <= 0) {
      nothingFound = true;
      return;
    }
    Intent i = new Intent(context, ShiurimList.class);
    Result.r = items;

    i.putExtra("playAudio", true);
    context.startActivity(i);
  }

  private class getDetails extends AsyncTask<Void, Void, Void> {
    ProgressDialog progBar;


    @Override
    protected void onPreExecute() {

      progBar = new ProgressDialog(context);
      progBar.setCancelable(false);
      progBar.setMessage("Working....");
      showDialog();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
      hideDialog();

      showErrorMessage();
    }


    @Override
    protected Void doInBackground(Void... voids) {


      try {
        checkQueryType();
      } catch (JSONException e) {
        e.printStackTrace();
      }


      return null;
    }

    private void showDialog() {
      if (!progBar.isShowing()) {
        progBar.show();
      }
    }

    private void hideDialog() {
      if (progBar.isShowing()) {
        progBar.dismiss();
      }
    }
  }

  private void showErrorMessage() {
    if (nothingFound) {
      Toast.makeText(context, "Nothing Here", Toast.LENGTH_SHORT).show();
      nothingFound = false;
    }
  }


  private void checkQueryType() throws JSONException {

    switch (mQueryType) {
      case SHIUR:
        shiurimQuery();
        break;
      case AUDIO:
        audioQuery();
      case PDF:
        pdfQuery();
    }
  }

  private void pdfQuery() throws JSONException {
    String results = establishConnection("http://www.mipazrav.com/webservice/api/Classes/AudioFileNames?Token=a643ec87-a511-41ee-ae69-61068a4be758&categoryId=126");
    parsePDFNames(results);
  }

  private void parsePDFNames(String results) throws JSONException {
    JSONArray jArray = new JSONArray(results);
    ArrayList items = new ArrayList<shiurObject>();
    for (int i = 0; i < jArray.length(); i++) {
      try {
        JSONObject oneObject = jArray.getJSONObject(i);
        // Pulling items from the array
        items.add(new shiurObject(oneObject.getString("AudioName"), oneObject.getString("DownloadFile"), oneObject.getString("RecID")));
      } catch (JSONException e) {
        // Oops
      }
    }
    if (items.size() <= 0) {
      nothingFound = true;
      return;
    }
    Intent i = new Intent(context, ShiurimList.class);
    Result.r = items;

    i.putExtra("loadPDF", true);
    context.startActivity(i);
  }


  private void audioQuery() throws JSONException {
    String results = establishConnection("http://www.mipazrav.com/webservice/api/Classes/AudioFileNames?Token=a643ec87-a511-41ee-ae69-61068a4be758&" + apiArg.substring(3));//substring to remove the 'sub' and just leave 'category' kludge
    parseAudioNamesJSON(results);
  }

}