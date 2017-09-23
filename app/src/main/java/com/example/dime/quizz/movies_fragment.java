package com.example.dime.quizz;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Dime on 25-Mar-17.
 */

public class movies_fragment extends Fragment {


    Integer br;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_fragment, container, false);


        questions_database dt = new questions_database(getActivity());
        Cursor questions = dt.getAnsweredQUestions("movie",1,1);
        br = questions.getCount();

        TextView progres = (TextView)v.findViewById(R.id.progres);
        progres.setText("Ниво 1:   "+br+ "/10");


        questions = dt.getAnsweredQUestions("movie",2,1);
        br = questions.getCount();

        TextView progres2 = (TextView)v.findViewById(R.id.progres2);
        progres2.setText("Ниво 2:   "+br+ "/10");

        questions = dt.getAnsweredQUestions("movie",3,1);
        br = questions.getCount();

        TextView progres3 = (TextView)v.findViewById(R.id.progres3);
        progres3.setText("Ниво 3:   "+br+ "/10");


        Button sport_btn = (Button) v.findViewById(R.id.btn_movies);
        sport_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),playerScreen.class);
                intent.putExtra("categoryName","movie");
                startActivity(intent);
            }
        });



        return v;
    }

}
