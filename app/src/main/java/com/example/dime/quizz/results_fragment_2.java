package com.example.dime.quizz;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dime on 26-Mar-17.
 */

public class results_fragment_2 extends Fragment {

    ArrayList<ArrayList<String>> player_data;
    questions_database dtb;
    private highScore_adapter score_adapter;
    ArrayList<String> player;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.result_fragment_2, container, false);

        dtb = new questions_database(getActivity());
        Cursor highscores = dtb.getHighScore();
        Integer br = highscores.getCount();

        //StringBuilder scores = new StringBuilder();
        //TextView tmp = (TextView)v.findViewById(R.id.highscore);
        //tmp.setText(highscores.getString(1) + " so rezultat:  " + highscores.getInt(2));
        //tmp.setText(scores);

        player_data = new ArrayList<ArrayList<String>>();

        Integer i = 0;
        while(i<br) {
            highscores.moveToPosition(i);
            player = new ArrayList<String>();
            player.add(highscores.getString(1));
            player.add(Integer.toString(highscores.getInt(2)));
            player_data.add(player);
            i++;
        }



        score_adapter = new highScore_adapter(getActivity(), player_data);
        ListView list = (ListView)v.findViewById(R.id.high_score_list);
        list.setAdapter(score_adapter);


        return v;
    }
}
