package com.example.dime.quizz;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Dime on 08-Apr-17.
 */


public class JsonParser {

    private static InputStream is = null;
    private static JSONObject jObj = null;
    private static String json = "";
    private HttpURLConnection urlConnection;

    JsonParser(){}

    public JSONObject getJSONFromUrl(String link) {


        try {
            URL url = new URL(link);
            urlConnection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(urlConnection.getInputStream());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Error", "greska :" + e.toString());
        }

        try {
            //Log.d("Debug", json.toString());
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("Error", "Error vo parsin json" + e.toString());
        }

        return jObj;

    }

}
