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

public class playerAdapter2 extends ArrayAdapter<ArrayList<String>> {


    ArrayList<ArrayList<String>> players;
    Context context;


    public playerAdapter2(Context context, ArrayList<ArrayList<String>> players){
        super(context,0,players);
        this.context = context;
        this.players = players;
    }

    public View getView(final int position, View convertView, ViewGroup parent){

        final ArrayList<String> player = players.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listlayout2, parent, false);
        }

        //ImageView image = (ImageView)convertView.findViewById(R.id.image);
        TextView playername = (TextView)convertView.findViewById(R.id.playername2);
        //Button removePlayer = (Button)convertView.findViewById(R.id.btnRemove);
        //removePlayer.setTag(position);
        final View finalConvertView = convertView;
//        removePlayer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                player.remove(position); //or some other task
//                notifyDataSetChanged();
//
//                //int positionToRemove = (int)v.getTag(); //get the position of the view to delete stored in the tag
//                //player.remove(position); //remove the item
//
//            }
//        });

        ImageView image = (ImageView)convertView.findViewById(R.id.icon2);

        playername.setText(player.get(0));

        int id = getContext().getResources().getIdentifier("drawable/"+player.get(1), null, getContext().getPackageName());
        image.setImageResource(id);


        return convertView;
    }


}
