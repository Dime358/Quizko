package com.example.dime.quizz;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * Created by Dime on 03-Mar-17.
 */

public class highScore_adapter extends ArrayAdapter<ArrayList<String>> {


    ArrayList<ArrayList<String>> players;
    Context context;


    public highScore_adapter(Context context, ArrayList<ArrayList<String>> players){
        super(context,0,players);
        this.context = context;
        this.players = players;
    }

    public View getView(final int position, View convertView, ViewGroup parent){

        final ArrayList<String> player = players.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.high_score_layout, parent, false);
        }

        TextView playername = (TextView)convertView.findViewById(R.id.nameid);
        TextView playerscore = (TextView)convertView.findViewById(R.id.scoreid);

        final View finalConvertView = convertView;

        playername.setText(player.get(0));
        playerscore.setText(player.get(1));


        return convertView;
    }


}
