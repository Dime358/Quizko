package com.example.dime.quizz;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Dime on 26-Mar-17.
 */

public class tv_fragment extends Fragment {

    Integer br;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tv_fragment, container, false);


        questions_database dt = new questions_database(getActivity());
        Cursor questions = dt.getAnsweredQUestions("television",1,1);
        br = questions.getCount();

        TextView progres = (TextView)v.findViewById(R.id.progres);
        progres.setText("Ниво 1:   "+br+ "/10");


        questions = dt.getAnsweredQUestions("television",2,1);
        br = questions.getCount();

        TextView progres2 = (TextView)v.findViewById(R.id.progres2);
        progres2.setText("Ниво 2:   "+br+ "/10");

        questions = dt.getAnsweredQUestions("television",3,1);
        br = questions.getCount();

        TextView progres3 = (TextView)v.findViewById(R.id.progres3);
        progres3.setText("Ниво 3:   "+br+ "/10");


        Button sport_btn = (Button) v.findViewById(R.id.btn_tv);
        sport_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),playerScreen.class);
                intent.putExtra("categoryName","television");
                startActivity(intent);
            }
        });

        return v;
    }

}
