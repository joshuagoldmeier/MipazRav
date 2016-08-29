package com.example.steve.mipazrav;

import android.content.Context;
import android.content.Intent;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Steve on 8/26/2016.
 */
class ParseXML {
    String url;

    public ParseXML(String url) {
        this.url = url;
    }

//    getJSON obj = new getJSON(context, url);





}



//    public static void parse (Context context) throws JSONException {
//        List<String> items = new ArrayList<>();
//
//        JSONArray jArray = new JSONArray(XMLfile.file);
//        String oneObjectsItem="";
//        String oneObjectsItem2="";
//        String oneObjectsItem3="";
//        for (int i=0; i < jArray.length(); i++)
//        {
//            try {
//                JSONObject oneObject = jArray.getJSONObject(i);
//                // Pulling items from the array
//                oneObjectsItem = oneObject.getString("AudioName");
//                oneObjectsItem2 = oneObject.getString("RecID");
//                oneObjectsItem3 = oneObject.getString("StreamingFile");
//                items.add(oneObjectsItem3);
//            } catch (JSONException e) {
//                // Oops
//            }
//        }
//        XMLfile.items=items;
//        System.out.println(items);
//        Intent i = new Intent(context,ItemsList.class);
//        context.startActivity(i);
