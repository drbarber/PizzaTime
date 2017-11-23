package com.example.sam.pizza;

import android.arch.persistence.room.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 11/11/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "toppingManager";
    private static final String TABLE_TOPPINGS = "toppings";
    private static final String TABLE_PIZZA =  "pizza";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TOPPINGS_LIST = "list";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create tables
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TOPPINGS_TABLE = "CREATE TABLE " + TABLE_TOPPINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT"
                +  ")";
        db.execSQL(CREATE_TOPPINGS_TABLE);
        String CREATE_PIZZA_TABLE = "CREATE TABLE " + TABLE_PIZZA + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TOPPINGS_LIST + " TEXT"
                + ")";
        db.execSQL(CREATE_PIZZA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPPINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIZZA);
        onCreate(db);
    }

    void addTopping(Topping topping) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, topping.getName());

        db.insert(TABLE_TOPPINGS,null,values);
        db.close();
    }

    void addPizza(SavedPizza savedPizza){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, savedPizza.getToppings());

        db.insert(TABLE_PIZZA, null, values);
        db.close();
    }

    public Topping getTopping(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TOPPINGS, new String[] { KEY_ID,
        KEY_NAME}, KEY_ID + " = ?",
                new String[]{String.valueOf(id)}, null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();

        Topping topping = new Topping(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));

        return topping;
    }

    public Topping getToppingByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TOPPINGS, new String[] {KEY_ID,
        KEY_NAME} , KEY_NAME + " = ?",
                new String[] {name}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Topping topping = new Topping(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        return topping;
    }

    public SavedPizza getPizza(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PIZZA, new String[] { KEY_ID, KEY_TOPPINGS_LIST}, KEY_ID + " = ?",
                new String[]{String.valueOf(id)}, null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();

        SavedPizza savedPizza = new SavedPizza(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        return savedPizza;
    }

    public List<Topping> getAllToppings(){
        List<Topping> toppingList = new ArrayList<Topping>();
        String selectQuery = "SELECT * FROM " + TABLE_TOPPINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                Topping topping = new Topping();
                topping.setID(Integer.parseInt(cursor.getString(0)));
                topping.setName(cursor.getString(1));
                toppingList.add(topping);
            } while(cursor.moveToNext());
        }
        return toppingList;
    }

    public int getToppingsCount(){
        String countQuery = "SELECT * FROM " + TABLE_TOPPINGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void deleteTopping(Topping topping){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TOPPINGS, KEY_ID + " = ?",
                new String[]{ String.valueOf(topping.getID())});
        db.close();
    }
}
