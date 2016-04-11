package com.example.avitansh.servicemycar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by avitansh on 3/21/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "carservice.db";
    public static final String LOGIN_TABLE = "login_table";
    public static final String CAR_TABLE_NAME = "car_table";
    public static final String SERVICE_TABLE_NAME = "service_table";
    public static final String GAS_TABLE = "gas_table";

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    public static final String CAR = "CAR";
    public static final String MODEL ="MODEL";
    public static final String PLATE = "PLATE";

    public static final String CAR_MODEL = "CAR_MODEL";
    public static final String DATE = "DATE";
    public static final String SERVICE_TYPE = "SERVICE_TYPE";
    public static final String NOTES = "NOTES";

    public static final String GAS_AMOUNT = "GAS_AMOUNT";
    public static final String GAS_COST = "GAS_COST";
    public static final String GAS_DATE = "GAS_DATE";
    public static final String GAS_CAR = "GAS_CAR";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 8);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LOGIN_TABLE +
                "(USERNAME TEXT PRIMARY KEY, PASSWORD TEXT)");
        db.execSQL("create table " + CAR_TABLE_NAME +
                "(PLATE TEXT PRIMARY KEY NOT NULL, CAR TEXT, MODEL TEXT)");
        db.execSQL("create table " + SERVICE_TABLE_NAME +
                "(CAR_MODEL TEXT, DATE TEXT, SERVICE_TYPE, NOTES TEXT)");
        db.execSQL("create table " + GAS_TABLE +
                "(GAS_AMOUNT INTEGER, GAS_COST INTEGER, GAS_DATE TEXT, GAS_CAR TEXT)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CAR_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SERVICE_TABLE_NAME);
        onCreate(db);
    }
    public boolean insertCarService(String carData, String date, String serviceType, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAR_MODEL,carData);
        contentValues.put(DATE,date);
        contentValues.put(SERVICE_TYPE, serviceType);
        contentValues.put(NOTES, notes);
        long result = db.insert(SERVICE_TABLE_NAME, null, contentValues);
        if(result==-1){

            return false;
        }
        db.close();
        return true;
    }
    public boolean insertLoginData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,username);
        contentValues.put(PASSWORD, password);
        long result = db.insert(LOGIN_TABLE, null, contentValues);
        if(result==-1){

            return false;
        }
        db.close();
        return true;
    }
    public boolean insertCarData(String car, String model, String license){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLATE, license);
        contentValues.put(CAR, car);
        contentValues.put(MODEL,model);
        long result = db.insert(CAR_TABLE_NAME, null, contentValues);
        if(result==-1){

            return false;
        }
        db.close();
        return true;


    }
    public ArrayList<String> returnCars(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select CAR_MODEL,DATE,SERVICE_TYPE,NOTES from service_table", null);
        ArrayList<String> s= new ArrayList<>();
        while(cursor.moveToNext()){
            s.add("Car: " + cursor.getString(0) +"\n" + "Date: "+ cursor.getString(1) +"\n" + "Service Type: " + cursor.getString(2) + "\n" + "Notes: " + cursor.getString(3));
        }
        return s;
    }
    public ArrayList<String> returnAllCarAndModel(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select CAR,MODEL,PLATE from car_table", null);
        ArrayList<String> s= new ArrayList<>();
        while(cursor.moveToNext()){
            s.add(cursor.getString(0) +" " + cursor.getString(1) + " " + cursor.getString(2));
        }
        return s;
    }
    public boolean onlyOnePlate(String plate){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select PLATE from car_table", null);
        while(cursor.moveToNext()){
            if(plate.equals(cursor.getString(0))){
                return false;
            }
        }
        return true;

    }

    public boolean checkPassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select PASSWORD from login_table WHERE USERNAME = ? ", new String[]{username}, null);
        while (cursor.moveToNext()) {

            if(password.equals(cursor.getString(0))){
                return true;
            }
        }
        return false;

    }
}
