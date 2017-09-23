package com.example.dime.quizz;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class playerScreen extends AppCompatActivity {

    private ListView list;
    private int id = 1;
    private playerAdapter2 adapter;
    ArrayList<ArrayList<String>> player_data;
    private Integer scoreNumber = 5;

    public void changeName(View view,final int position) {

        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.custom_dialog);
        d.setTitle("Add player");
        d.setCancelable(true);
        final EditText edit = (EditText) d.findViewById(R.id.edit_dialog);
        ArrayList<String> player = player_data.get(position);
        edit.setText(player.get(0));
        Button b = (Button) d.findViewById(R.id.btn_dialog);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String playerName = edit.getText().toString();
                ArrayList<String> player = player_data.get(position);
                player.set(0,playerName);
                playerScreen.this.adapter.notifyDataSetChanged();
                d.dismiss();
            }

        });

        d.show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_screen);

        Intent intent = getIntent();
        final String category = intent.getStringExtra("categoryName");

        Button back = (Button)findViewById(R.id.btnback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button startGame = (Button)findViewById(R.id.startGame);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extra = new Bundle();
                extra.putSerializable("player_data", player_data);
                Intent intent = new Intent(playerScreen.this,questionsActivity.class);
                intent.putExtra("extra",extra);

                    //da se smeni
                final TextView targetScore = (TextView)findViewById(R.id.targetScore);
                final String targetNum = targetScore.getText().toString();

                intent.putExtra("categoryName",category);

                intent.putExtra("targetScore",targetNum);

                startActivity(intent);
            }
        });

        TextView text = (TextView)findViewById(R.id.categoryLabel);

        switch(category) {
            case "movie":
                text.setText(R.string.movie_naslov);
                break;
            case "sport":
                text.setText(R.string.sport_naslov);
                break;
            case "history":
                text.setText(R.string.history_naslov);
                break;
            case "geography":
                text.setText(R.string.geography_naslov);
                break;
            case "television":
                text.setText(R.string.tv_naslov);
                break;
        }


        TextView text2 = (TextView)findViewById(R.id.playerCount);
        text2.setText(String.valueOf(id));

        //list items del

        player_data = new ArrayList<ArrayList<String>>();
        ArrayList<String> player = new ArrayList<String>();
        player.add("Играч 1");
        player.add("icon1");
        player.add("0");

        player_data.add(player);



        adapter = new playerAdapter2(this, player_data);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


        Button upCounter = (Button)findViewById(R.id.button3);
        Button downCounter = (Button)findViewById(R.id.button2);
        Button upScore = (Button)findViewById(R.id.btnScoreHiger);
        Button downScore = (Button)findViewById(R.id.btnScoreLower);




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeName(view,position);
            }


        });

        upCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerScreen.this.id++;

                ArrayList<String> player = new ArrayList<String>();
                player.add("Играч " + playerScreen.this.id);
                player.add("icon" +playerScreen.this.id);
                player.add("0");
                player_data.add(player);


                TextView text2 = (TextView)findViewById(R.id.playerCount);
                text2.setText(String.valueOf(id));

                playerScreen.this.adapter.notifyDataSetChanged();
            }
        });

        downCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerScreen.this.id > 1) {
                    int test = playerScreen.this.id - 1;
                    player_data.remove(test);
                    playerScreen.this.id--;

                    TextView text2 = (TextView) findViewById(R.id.playerCount);
                    text2.setText(String.valueOf(id));

                    adapter.notifyDataSetChanged();
                }
            }
        });

        upScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreNumber++;
                TextView score = (TextView)findViewById(R.id.targetScore);
                score.setText(String.valueOf(scoreNumber));

            }
        });

        downScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scoreNumber > 5) {
                    scoreNumber--;
                    TextView score = (TextView) findViewById(R.id.targetScore);
                    score.setText(String.valueOf(scoreNumber));
                }
            }
        });


    }
}
