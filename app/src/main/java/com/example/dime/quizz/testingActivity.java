package com.example.dime.quizz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class testingActivity extends AppCompatActivity {


    private Integer br = 1;

    private void add() {
        Button ins = (Button) findViewById(R.id.btnAdd);
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //questions_database dt = new questions_database(testingActivity.this);
                //dt.promeniVersion(5);
                //Log.d("Version", " " + dt.staraVersion());


                //dt.insertPlayer("Player 1",0);

                //level 1
//                dt.insertQuestion("movie","Кој од James Bond филмовите прв доби оскар?","The Spy Who Loved Me (1977)","From Russia with Love (1963)","Goldfinger (1964)",3,1,0);
//                dt.insertQuestion("movie","Кој филм има добиено оскари за:Најдобар филм,најдобар актер,најдобра актерка,најдобар режисер и најдобро сценарио?","Ben-Hur (1959)","One Flew Over the Cuckoo\'s Nest (1975)","Titanic (1997)",2,1,0);
//                dt.insertQuestion("movie","Кој актер го глумеше капетанот Jack Sparrow  во филмот Pirates of Caribbean?","Johnny Depp","Orlando Bloom","Hugh Jackman",1,1,0);
//                dt.insertQuestion("movie","Кој актер го глумеше Neo во филмот Matrix?","Keanu Reeves","Laurence Fishburne","Hugo Weaving",1,1,0);
//                dt.insertQuestion("movie","Од која планета е Superman?","Krypton","Ashara","Mars",1,1,0);
//                dt.insertQuestion("movie","Кој е режисер на филмот Avatar?","Steven Spielberg","James Cameron","Peter Jackson",2,1,0);
//                dt.insertQuestion("movie","Кој е режисер на филмовите Lord of the Rings и King Kong(2005)?","Steven Spielberg","James Cameron","Peter Jackson",3,1,0);
//                dt.insertQuestion("movie","Кој е режисер на филмовите Jaws(1975),E.T.(1982) и Saving Private Ryan?","Steven Spielberg","James Cameron","Peter Jackson",1,1,0);
//                dt.insertQuestion("movie","Кое е главното оружје на главните ликови во филмот Star Wars?","Guns","Lightsaber","AK-47",2,1,0);
//                dt.insertQuestion("movie","Кој е режисер на филмовите Jaws(1975),E.T.(1982) и Saving Private Ryan?","Steven Spielberg","James Cameron","Peter Jackson",1,1,0);
                //level 2
//                dt.insertQuestion("movie","Која година е испаднат филмот Jurassic park?","1989","1997","1993",3,2,0);
//                dt.insertQuestion("movie","Како се вика убиецот од филмовите Friday the 13th?","Jason ","Freddy","Miller",1,2,0);
//                dt.insertQuestion("movie","Од кој серијал е филмот The battle of five armies?","The Hobbit","Hunger games","Star wars",1,2,0);
//                dt.insertQuestion("movie","Кој актер ја глумеше улогата на снајперистот Chris Kyle во филмот American Sniper?","Bradley Cooper","Christian Bale","Casey Affleck",1,2,0);
//                dt.insertQuestion("movie","Во кој од филмовите глумеше Morgan Freeman?","Blood Diamond","Amistad","Sahara",2,2,0);
//                dt.insertQuestion("movie","Kој го глуми Noah во филмот The Notebook?","Ryan Phillipe","Colin Farrell","Ryan Gossling",3,2,0);
//                dt.insertQuestion("movie","Кој од следните филмови е режиран од Tim Burton?","Charlie And The Chocolate Factory","Constantine","Kingdom Of Heaven",1,2,0);
//                dt.insertQuestion("movie","Кој од следните филмови е режиран од Steven Spielberg ?","The Brothers Grimm","War Of The Worlds","The Devil's Rejects",2,2,0);
//                dt.insertQuestion("movie","Во кој од следните филмови глумеа Michael Caine и Christine Adams?","Batman Begins","Kingdom Of Heaven","The Skeleton Key",1,2,0);

//                //level 3




                JsonParser jParser = new JsonParser();
                JSONObject json = jParser.getJSONFromUrl("https://www.dropbox.com/s/wfnun2xa2g2u0lx/The%20Web%20as%20a%20Global%20Dataspace-.pdf?dl=0");


                Log.d("prasanje", " " + json);
                try {
                    JSONObject c = json.getJSONObject("ACTION_ADD");
                    for(int i = 0; i < c.length(); i++) {
                        JSONArray versions = c.getJSONArray(Integer.toString(i));

                        for(int j = 0; i < c.length(); i++) {
                            JSONObject test = versions.getJSONObject(j);
                            String id =  test.getString("id");
                            String question =  test.getString("question");
                            String option_1 = c.getString("option_1");
                            String option_2 = c.getString("option_2");
                            String option_3 = c.getString("option_3");

                            Log.d("prasanje", question + " " + option_1 +" "+ option_2 + " " + option_3);
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void show() {
        Button sho = (Button) findViewById(R.id.btnShow);
        sho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                questions_database dt = new questions_database(testingActivity.this);
                Cursor questions;
                questions = dt.getQuestion("movie", 8);
                questions.moveToPosition(0);
                Log.d("question so K", " " + questions.getString(1));

                questions = dt.getQuestion("movie", 9);
                questions.moveToPosition(0);
                Log.d("question so star", " " + questions.getString(2));


                questions = dt.getDataQuestions("geography");
//                questions.moveToPosition(0);
                Integer i = 0;
//                Log.d("question geo", i + " " + questions.getString(2));

                while(questions.moveToNext()){
                    questions.moveToPosition(i);
                    Log.d("question geo",i + " " + questions.getString(2));
                    i++;
                }


//                TextView test = (TextView)findViewById(R.id.testtxt);
//                questions.moveToPosition(0);
//                test.setText(questions.getString(1));
//





//                questions.moveToPosition(1);
//                Log.d("test1", " " + questions.getString(1));



//                Cursor player = dt.getDataPlayer();
//                player.moveToPosition(0);
//                Log.d("Player", " " + player.getString(1));
//                Log.d("Score", " " + player.getInt(2));

            }
        });
    }

    private void update(){
        Button up = (Button)findViewById(R.id.btnUpdate);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                questions_database dt = new questions_database(testingActivity.this);
//                Cursor player = dt.getDataPlayer();
//                player.moveToPosition(0);
//                Log.d("Player", " " + player.getString(1));
//                Log.d("Score", " " + player.getInt(2));
//
//                if (player.getInt(2)<4){
//                    dt.updatePlayer("1","dime",3);
//                    //Log.d("update vo if", " " + res);
//                }
                //dt.updateQuestion("movie",21,"Како се вика лидерот на бандата Ravagers во филмот Guardians of the Galaxy(2014)?","Ronan The Accuser","Korath","Yondu Udonta",3,3,0);


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
//        if (android.os.Build.VERSION.SDK_INT > 9)
//        {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }

        Intent msgIntent = new Intent(testingActivity.this, UpdateDatabaseService.class);
        startService(msgIntent);

        add();
        update();
        show();


    }

}
