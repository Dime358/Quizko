package com.example.dime.quizz;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;

import static java.security.AccessController.getContext;

public class questionsActivity extends AppCompatActivity implements View.OnClickListener {


    private ListView list;
    private playerAdapter adapter;
    ArrayList<ArrayList<String>> player_data;
    ArrayList<Integer> passed_questions = new ArrayList<Integer>();
    Button btn1;
    Button btn2;
    Button btn3;
    int playerID = 0;
    String category;
    int randomNumber;
    int brojNaPrasanja=10;
    int brojNaPrasanjaMulti = 30;
    Integer targetScore;
    int zivoti = 3;
    int singleScore = 0;
    boolean singlePlayer = false;
    private DrawerLayout drawLayout;

    //SQLite
    questions_database dt = new questions_database(questionsActivity.this);
    Cursor questions;
    Integer level = 1;
    Integer levelScore = 0;

    public void getLevel(String category,Integer level){
        questions = dt.getDataLevel(category,level);
        TextView texlevel = (TextView)findViewById(R.id.level);
        texlevel.setText("Level: "+level);
    }

    public void getQuestions(String category){
        questions = dt.getDataQuestions(category);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);


        //zimanje na data od array prenesenata
        Bundle extra = getIntent().getBundleExtra("extra");
        player_data = (ArrayList<ArrayList<String>>) extra.getSerializable("player_data");
        ArrayList<String> player = player_data.get(0);
        TextView question = (TextView)findViewById(R.id.question);
        question.setText(player.get(1));

        //zimanje na kategorija
        Intent intent = getIntent();
        String categoryIntent = intent.getStringExtra("categoryName");
        category = categoryIntent;
        //category = "movie";
        String targetSc= intent.getStringExtra("targetScore");
        int finalValue=Integer.parseInt(targetSc);
        targetScore = finalValue;

        drawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        adapter = new playerAdapter(this, player_data);
        list = (ListView)findViewById(R.id.questionList);
        list.setAdapter(adapter);

        //kopcinja na odgovori
        Button op = (Button) findViewById(R.id.open);
        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawLayout.openDrawer(Gravity.LEFT);
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener((View.OnClickListener) this);

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener((View.OnClickListener) this);

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener((View.OnClickListener) this);

        //test za single player
        if(player_data.size()==1){
            //da se vidi mozno e i ova scenario !!!!
            this.targetScore = 100;
            updateLives();
            this.singlePlayer = true;
        }

        if(singlePlayer){
            getLevel(category,level);
        }
        else {
            getQuestions(category);
        }


        randomGenerator();
        try {
            checkQuestion();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public void updateLives() {
        TextView test = (TextView)findViewById(R.id.zivoti);
        test.setText(this.zivoti +" живот/и");
    }

    public void randomGenerator(){
        Random randomID = new Random();
        if(singlePlayer){
            int rand = randomID.nextInt(this.brojNaPrasanja);
            this.randomNumber = rand;
        }
        else {
            int rand = randomID.nextInt(this.brojNaPrasanjaMulti);
            this.randomNumber=rand;
        }
    }

    public void checkAnswer(Integer id,int btn) throws NoSuchFieldException, IllegalAccessException {

        questions.moveToPosition(this.randomNumber);
        Integer correctAnswer = questions.getInt(5);
        boolean res = id.equals(correctAnswer);

        if (res){ scoreChanger(1,btn); }
        else { scoreChanger(2,btn); }
    }

    public void changePlayer(){
        int dolzina = this.player_data.size();
        if(this.playerID<dolzina-1){
            this.playerID++;
        }
        else {
            this.playerID = 0;
        }
    }

    //1 e tocno :: 2 e netocno
    public void scoreChanger(int state,final int btn) throws NoSuchFieldException, IllegalAccessException {

        ArrayList<String> player = this.player_data.get(this.playerID);
        String old = player.get(2);
        int oldScore = Integer.valueOf(old);

        if(state==1){
            if(btn==1) {
                this.btn1.setBackgroundColor(Color.parseColor("#5cac5b"));
                this.btn2.setClickable(false);
                this.btn3.setClickable(false);
            }
            if(btn==2) {
                this.btn2.setBackgroundColor(Color.parseColor("#5cac5b"));
                this.btn1.setClickable(false);
                this.btn3.setClickable(false);
            }
            if(btn==3) {
                this.btn3.setBackgroundColor(Color.parseColor("#5cac5b"));
                this.btn1.setClickable(false);
                this.btn2.setClickable(false);
            }
            if (this.singlePlayer){
                oldScore++;
                this.singleScore++;
                this.levelScore++;
                dt.updateQuestion(category,questions.getInt(0),questions.getString(1),questions.getString(2),questions.getString(3),questions.getString(4),questions.getInt(5),questions.getInt(6),1);
            }
            else { oldScore++; }
        }

        if(state==2){
            if(btn==1) {
                this.btn1.setBackgroundColor(Color.parseColor("#c02026"));
                this.btn2.setClickable(false);
                this.btn3.setClickable(false);
            }
            if(btn==2) {
                this.btn2.setBackgroundColor(Color.parseColor("#c02026"));
                this.btn1.setClickable(false);
                this.btn3.setClickable(false);
            }
            if(btn==3) {
                this.btn3.setBackgroundColor(Color.parseColor("#c02026"));
                this.btn1.setClickable(false);
                this.btn2.setClickable(false);
            }
        }
        if(state==2 && this.singlePlayer){
            this.zivoti--;
            updateLives();
        }
        if(this.zivoti<1){
            Bundle extra = new Bundle();
            extra.putSerializable("player_data", player_data);
            Intent intent = new Intent(questionsActivity.this,gameResults.class);
            intent.putExtra("extra",extra);
            intent.putExtra("singleWinner",Integer.toString(this.playerID));
            intent.putExtra("score",Integer.toString(this.singleScore));
            startActivity(intent);
        }

        String newScore = Integer.toString(oldScore);
        player.set(2,newScore);
        if(oldScore==this.targetScore){
            Bundle extra = new Bundle();
            extra.putSerializable("player_data", player_data);
            Intent intent = new Intent(questionsActivity.this,gameResults.class);
            intent.putExtra("extra",extra);
            intent.putExtra("multiWinner",Integer.toString(this.playerID));
            startActivity(intent);
        }
        questionsActivity.this.adapter.notifyDataSetChanged();
        changePlayer();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(btn==1) {
                    btn1.setBackgroundColor(Color.parseColor("#198b6c"));
                    btn2.setClickable(true);
                    btn3.setClickable(true);
                }
                if(btn==2) {
                    btn2.setBackgroundColor(Color.parseColor("#198b6c"));
                    btn1.setClickable(true);
                    btn3.setClickable(true);
                }
                if(btn==3) {
                    btn3.setBackgroundColor(Color.parseColor("#198b6c"));
                    btn1.setClickable(true);
                    btn2.setClickable(true);
                }

                try {
                    checkQuestion();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);

    }



    public void checkQuestion() throws NoSuchFieldException, IllegalAccessException {
        Integer br = 0;
        if(singlePlayer)
            br = this.brojNaPrasanja;
        else
            br = this.brojNaPrasanjaMulti;
        if(this.passed_questions.size()==br){
            this.passed_questions.clear();
            int star = this.randomNumber;
            randomGenerator();
            while (this.randomNumber==star)
                randomGenerator();
            this.passed_questions.add(this.randomNumber);
            newQuestion();
        }
        else {
            if (this.passed_questions.size() > 0) {
                while (this.passed_questions.contains(this.randomNumber)) {
                    randomGenerator();
                }
            }
            else{
                int star = this.randomNumber;
                randomGenerator();
                while (this.randomNumber==star)
                    randomGenerator();
            }
            this.passed_questions.add(this.randomNumber);
            newQuestion();
        }
    }

    public void newQuestion() throws NoSuchFieldException, IllegalAccessException {

        //menuvanje na score za next level
        if(singlePlayer && levelScore>6){
            if(level<3){
                level++;
                this.passed_questions.clear();
                levelScore = 0;
                getLevel(category,level);
            }
        }
        questions.moveToPosition(randomNumber);
        ArrayList<String> player = this.player_data.get(this.playerID);
        String playerActive = player.get(0);
        TextView playerField = (TextView)findViewById(R.id.activePlayer);
        playerField.setText("Одговара: "+ playerActive);

        TextView questionField = (TextView)findViewById(R.id.question);
        questionField.setText(questions.getString(1));

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setText(questions.getString(2));
        btn1.setBackgroundColor(Color.CYAN);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkAnswer(1,1);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setText(questions.getString(3));
        btn2.setBackgroundColor(Color.CYAN);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkAnswer(2,2);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setText(questions.getString(4));
        btn3.setBackgroundColor(Color.CYAN);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkAnswer(3,3);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        //nema nazad
    }

}
