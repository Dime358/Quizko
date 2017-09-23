package com.example.dime.quizz;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainScreen extends AppCompatActivity {

    private Button btn;

    private void sleden(){
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,categories.class);
                startActivity(intent);
            }
        });
    }
//    private void testBaza(){
//
//        Button btnTest = (Button)findViewById(R.id.btntest);
//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intent = new Intent(MainScreen.this,testingActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        sleden();
        //testBaza();

        questions_database dt = new questions_database(this);
        try {
            dt.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            dt.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }

        Intent msgIntent = new Intent(MainScreen.this, UpdateDatabaseService.class);
        startService(msgIntent);


    }


}
