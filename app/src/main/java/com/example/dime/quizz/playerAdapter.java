package com.example.dime.quizz;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * Created by Dime on 03-Mar-17.
 */

public class playerAdapter extends ArrayAdapter<ArrayList<String>> {


    ArrayList<ArrayList<String>> players;
    Context context;

    public playerAdapter(Context context, ArrayList<ArrayList<String>> players){
        super(context,0,players);
        this.context = context;
        this.players = players;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        ArrayList<String> player = players.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listlayout, parent, false);
        }

        //ImageView image = (ImageView)convertView.findViewById(R.id.image);
        TextView playername = (TextView)convertView.findViewById(R.id.playername);
        TextView playerscore = (TextView)convertView.findViewById(R.id.playerscore);
        ImageView image = (ImageView)convertView.findViewById(R.id.icon);

        playername.setText(player.get(0));
        playerscore.setText(String.valueOf(player.get(2)));


        int id = getContext().getResources().getIdentifier("drawable/"+player.get(1), null, getContext().getPackageName());
        image.setImageResource(id);


        return convertView;
    }


}
