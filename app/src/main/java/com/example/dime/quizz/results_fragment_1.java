package com.example.dime.quizz;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dime on 26-Mar-17.
 */


public class results_fragment_1 extends Fragment {

    questions_database dt;
    ArrayList<ArrayList<String>> player_data;

    public void update(Integer newScore,String name){

        dt = new questions_database(getActivity());
//        Cursor playerData = dt.getDataPlayer();
//        playerData.moveToPosition(0);
//        if (playerData.getInt(2)< newScore){
//            dt.updatePlayer("1",name,newScore);
//        }

        dt.highScoreManager(name,newScore);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        View v = inflater.inflate(R.layout.result_fragment_1, container, false);

        if (intent.hasExtra("multiWinner")) {
            Bundle extra = getActivity().getIntent().getBundleExtra("extra");
            player_data = (ArrayList<ArrayList<String>>) extra.getSerializable("player_data");
            String gameWinner = intent.getStringExtra("multiWinner");
            int id = Integer.valueOf(gameWinner);
            ArrayList<String> player = this.player_data.get(id);
            String name = player.get(0);
            TextView pobednik = (TextView)v.findViewById(R.id.pobednik);
            pobednik.setText(name);
        }

        if (intent.hasExtra("singleWinner")) {
            Bundle extra = getActivity().getIntent().getBundleExtra("extra");
            player_data = (ArrayList<ArrayList<String>>) extra.getSerializable("player_data");
            String gameWinner = intent.getStringExtra("singleWinner");
            int id = Integer.valueOf(gameWinner);
            ArrayList<String> player = this.player_data.get(id);
            String name = player.get(0);
            String scoreString = intent.getStringExtra("score");
            int score = Integer.valueOf(scoreString);
            TextView pobednik = (TextView)v.findViewById(R.id.pobednik);
            pobednik.setText(name);
            TextView scoreTxt = (TextView)v.findViewById(R.id.scoreLabel);
            scoreTxt.setText(score+"");
            update(score,name);
        }


        Button btn = (Button)v.findViewById(R.id.newgame);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), categories.class);
                startActivity(intent);
            }
        });

        return v;
    }


}
