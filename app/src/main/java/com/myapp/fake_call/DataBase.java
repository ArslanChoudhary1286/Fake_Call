package com.myapp.fake_call;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private static String DB_NAME = "newtable.db";
    public static int DB_VERSION = 1;
    public static String TABLE_NAME = "char_data";


    Context context;


    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, NUMBER TEXT, URI TEXT);";
        sqLiteDatabase.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addData(Params p) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("NAME",p.getName());
        cv.put("NUMBER",p.getNumber());
        cv.put("URI",p.getImageUri());

       try {
           db.insert(TABLE_NAME,null,cv);
           db.close();
       }
       catch (Exception e){
           e.printStackTrace();
       }

    }



    public ArrayList<Params> getAllData(String query){

        ArrayList<Params> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
//        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        try {
            if (cursor.moveToFirst()){
                do {
                    Params params = new Params();

                    params.setId(Integer.parseInt(cursor.getString(0)));
                    params.setName(cursor.getString(1));
                    params.setNumber(cursor.getString(2));
                    params.setImageUri(cursor.getString(3));

                    arrayList.add(params);
                }while (cursor.moveToNext());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        // closing connection
        cursor.close();
        db.close();
        return arrayList;
    }
    /**
     * Re crate database
     * Delete all tables and create them again
     **/
//    public void resetTables(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        // Delete All Rows
//        db.delete(TABLE_LOGIN, null, null);
//        db.close();
//    }
}
