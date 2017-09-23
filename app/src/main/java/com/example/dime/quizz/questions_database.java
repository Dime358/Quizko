package com.example.dime.quizz;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Dime on 25-Mar-17.
 */

public class questions_database extends SQLiteOpenHelper {

    //private static final String DATABASE_NAME = "questions.db";
    private static final String DATABASE_NAME = "questions.sqlite";
    private static int DATABASE_VERSION = 1;
    //table names
    private static final String TABLE_MOVIES = "movie";
    private static final String TABLE_SPORT = "sport";
    private static final String TABLE_MUSIC = "music";
    private static final String TABLE_TV = "television";
    private static final String TABLE_HISTORY = "history";
    private static final String TABLE_GEOGRAPHY = "geography";
    private static final String TABLE_PLAYER = "player";
    //table columns
    private static final String COL_1 = "ID";
    private static final String COL_2 = "QUESTION";
    private static final String COL_3 = "OPTION_1";
    private static final String COL_4 = "OPTION_2";
    private static final String COL_5 = "OPTION_3";
    private static final String COL_6 = "CORRECT";
    private static final String COL_7 = "LEVEL";
    private static final String COL_8 = "STATE";
    //player columns
    private static final String PLAYER_COL_2 = "NAME";
    private static final String PLAYER_COL_3 = "SCORE";

    private static final String CREATE_MOVIES = "CREATE TABLE " + TABLE_MOVIES + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + COL_6 + " INTEGER," + COL_7 + " INTEGER," + COL_8 + " INTEGER)";
    private static final String CREATE_SPORT = "CREATE TABLE " + TABLE_SPORT + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + COL_6 + " INTEGER," + COL_7 + " INTEGER," + COL_8 + " INTEGER)";
    private static final String CREATE_MUSIC = "CREATE TABLE " + TABLE_MUSIC + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + COL_6 + " INTEGER," + COL_7 + " INTEGER," + COL_8 + " INTEGER)";
    private static final String CREATE_TV = "CREATE TABLE " + TABLE_TV + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + COL_6 + " INTEGER," + COL_7 + " INTEGER," + COL_8 + " INTEGER)";
    private static final String CREATE_HISTORY = "CREATE TABLE " + TABLE_HISTORY + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + COL_6 + " INTEGER," + COL_7 + " INTEGER," + COL_8 + " INTEGER)";
    private static final String CREATE_GEOGRAPHY = "CREATE TABLE " + TABLE_GEOGRAPHY + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + COL_6 + " INTEGER," + COL_7 + " INTEGER," + COL_8 + " INTEGER)";
    private static final String CREATE_PLAYER = "CREATE TABLE " + TABLE_PLAYER + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + PLAYER_COL_2 + " TEXT," + PLAYER_COL_3 + " INTEGER)";

    //external
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static String DATABASE_PATH = "/data/data/com.example.dime.quizz/databases/";




    public void test(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.getVersion();
    }


    public questions_database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_MOVIES);
//        db.execSQL(CREATE_MUSIC);
//        db.execSQL(CREATE_HISTORY);
//        db.execSQL(CREATE_GEOGRAPHY);
//        db.execSQL(CREATE_SPORT);
//        db.execSQL(CREATE_TV);
//        db.execSQL(CREATE_PLAYER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+ CREATE_GEOGRAPHY);
//        db.execSQL("DROP TABLE IF EXISTS "+ CREATE_HISTORY);
//        db.execSQL("DROP TABLE IF EXISTS "+ CREATE_SPORT);
//        db.execSQL("DROP TABLE IF EXISTS "+ CREATE_MOVIES);
//        db.execSQL("DROP TABLE IF EXISTS "+ CREATE_MUSIC);
//        db.execSQL("DROP TABLE IF EXISTS "+ CREATE_TV);
//        db.execSQL("DROP TABLE IF EXISTS "+ CREATE_PLAYER);
//        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion<newVersion)
            DATABASE_VERSION = newVersion;
    }

    public void insertQuestion(String TABLE_NAME,String question,String opt1,String opt2,String opt3,Integer correct,Integer level,Integer state){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,question);
        contentValues.put(COL_3,opt1);
        contentValues.put(COL_4,opt2);
        contentValues.put(COL_5,opt3);
        contentValues.put(COL_6,correct);
        contentValues.put(COL_7,level);
        contentValues.put(COL_8,state);
        db.insert(TABLE_NAME,null,contentValues);

    }

    public void insertPlayer(String name,Integer score){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLAYER_COL_2,name);
        contentValues.put(PLAYER_COL_3,score);

        db.insert(TABLE_PLAYER,null,contentValues);

    }

    public void updateQuestion(String TABLE_NAME,Integer id,String question,String opt1,String opt2,String opt3,Integer correct,Integer level,Integer state){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,question);
        contentValues.put(COL_3,opt1);
        contentValues.put(COL_4,opt2);
        contentValues.put(COL_5,opt3);
        contentValues.put(COL_6,correct);
        contentValues.put(COL_7,level);
        contentValues.put(COL_8,state);
        db.update(TABLE_NAME,contentValues,"ID = " + id ,null);
    }


    public void updatePlayer(String id,String name,Integer score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(PLAYER_COL_2,name);
        contentValues.put(PLAYER_COL_3,score);
        long i = db.update(TABLE_PLAYER,contentValues,COL_1 + "=?", new String[]{id});
    }

    public Cursor getQuestion(String TABLE_NAME,Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor_data = db.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE ID = "+ id, null);
        return cursor_data;

    }

    public Cursor getDataQuestions(String TABLE_NAME){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor_data = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cursor_data;
    }

    public Cursor getDataLevel(String TABLE_NAME,Integer level){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor_data = db.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE LEVEL = "+ level, null);
        return cursor_data;
    }

    public Cursor getDataPlayer(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor_data = db.rawQuery("SELECT * FROM "+TABLE_PLAYER, null);
        return cursor_data;
    }

    public Cursor getHighScore(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor_data = db.rawQuery("SELECT * FROM " + TABLE_PLAYER +" WHERE SCORE > 0 ORDER BY SCORE DESC LIMIT 5",null);
        return cursor_data;
    }

    public Cursor getAnsweredQUestions(String TABLE_NAME,Integer level,Integer state){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor_data = db.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE LEVEL = "+ level + " AND STATE = "+state, null);
        return cursor_data;

    }

    ///highscore funkcii

    public void highScoreManager(String name,Integer score) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLAYER_COL_2, name);
        contentValues.put(PLAYER_COL_3, score);

        db.insert(TABLE_PLAYER,null,contentValues);
        db.execSQL("DELETE FROM " + TABLE_PLAYER +" WHERE ID NOT IN"+
                        " (SELECT ID FROM " + TABLE_PLAYER +" ORDER BY SCORE DESC LIMIT 5)");
    }

    public int staraVersion(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.getVersion();
    }

    public void promeniVersion(Integer NEW_VERSION){
        SQLiteDatabase db = this.getReadableDatabase();
        db.setVersion(NEW_VERSION);
    }



    // kopiranje na baza od assets

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;
        try{
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){

        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{


        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();
        super.close();

    }

}
