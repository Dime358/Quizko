package com.example.dime.quizz;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Dime on 08-Apr-17.
 */

public class UpdateDatabaseService extends IntentService {

    private static String url = "https://dl.dropboxusercontent.com/s/wt3h0r50d0zabxw/test_json.json?dl=0";

    //Json table names
    private static final String ACTION_UPDATE = "update";
    private static final String ACTION_ADD = "add";
    private static final String COL_1 = "CATEGORY";
    private static final String COL_2 = "ID";
    private static final String COL_3 = "QUESTION";
    private static final String COL_4 = "OPTION_1";
    private static final String COL_5 = "OPTION_2";
    private static final String COL_6 = "OPTION_3";
    private static final String COL_7 = "CORRECT";
    private static final String COL_8 = "LEVEL";

    questions_database dt;
    Cursor oldQuestions;
    JSONObject jsonAction = null;
    Integer oldVersionUpdate = null;
    Integer oldVersionAdd = null;
    JSONArray versions = null;

    public UpdateDatabaseService() {
        super("UpdateDatabaseService");
    }

    //saving vo preferences verzijata na bazata i zimanje na starata verzija
    public void SaveInt(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public int LoadInt(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getInt("version", 0);
    }



    @Override
    protected void onHandleIntent(Intent intent) {

        dt = new questions_database(UpdateDatabaseService.this);

        //proverka ako prv pat se pusta aplikacijata da ne javi error
        if(LoadInt()==0)
            SaveInt("version",1);

        oldVersionUpdate = LoadInt();
        oldVersionAdd = LoadInt();

try{
    JsonParser jParser = new JsonParser();
    JSONObject json = jParser.getJSONFromUrl(url);

    try {
        jsonAction = json.getJSONObject("ACTION_UPDATE");
        int tmp = oldVersionUpdate;
        tmp++;
        for(int i = tmp; i <= jsonAction.length(); i++) {
            versions = jsonAction.getJSONArray(Integer.toString(i));
            for(int j = 0; j < versions.length(); j++) {
                JSONObject prasanje = versions.getJSONObject(j);
                String category = prasanje.getString(COL_1);
                Integer id = Integer.valueOf(prasanje.getString(COL_2));
                String question = prasanje.getString(COL_3);
                String option_1 = prasanje.getString(COL_4);
                String option_2 = prasanje.getString(COL_5);
                String option_3 = prasanje.getString(COL_6);
                Integer correct = Integer.valueOf(prasanje.getString(COL_7));
                Integer level = Integer.valueOf(prasanje.getString(COL_8));

                oldQuestions = dt.getQuestion(category, id);
                oldQuestions.moveToPosition(0);
                dt.updateQuestion(category, id, question, option_1, option_2, option_3, correct, level, oldQuestions.getInt(7));

            }
            oldVersionUpdate++;
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }

    //add section
    try {
        jsonAction = json.getJSONObject("ACTION_ADD");
        int tmp = oldVersionAdd;
        tmp++;

        for(int i = tmp; i <= jsonAction.length(); i++) {
            versions = jsonAction.getJSONArray(Integer.toString(i));
            for(int j = 0; j < versions.length(); j++) {
                JSONObject prasanje = versions.getJSONObject(j);
                String category = prasanje.getString(COL_1);
                String question = prasanje.getString(COL_3);
                String option_1 = prasanje.getString(COL_4);
                String option_2 = prasanje.getString(COL_5);
                String option_3 = prasanje.getString(COL_6);
                Integer correct = Integer.valueOf(prasanje.getString(COL_7));
                Integer level = Integer.valueOf(prasanje.getString(COL_8));
                dt.insertQuestion(category, question, option_1, option_2, option_3, correct, level, 0);
            }
            oldVersionAdd++;
        }


    } catch (JSONException e)
    {
        e.printStackTrace();
    }

    if(oldVersionAdd>oldVersionUpdate)
        SaveInt("version",oldVersionAdd);
    else if(oldVersionAdd<oldVersionUpdate)
        SaveInt("version",oldVersionUpdate);
    else
        SaveInt("version",oldVersionAdd);

} catch (Exception e)
    {
        e.printStackTrace();
    }

    }
}
