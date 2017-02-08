package com.example.pc.testandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 03/02/2017.
 */

public class Journee {
/*
    /*"CREATE TABLE journee" +
            "(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "numero INTEGER NOT NULL," +
            ");"

            */
    //VARIABLES
    private int id;
    private int numero;
    private String TABLE_NAME = "journee";
    private String ATTR_ID = "id";
    private String ATTR_NUMERO = "numero";

    private SQLiteDatabase bdd;
    private MyBaseSQLite maBaseSQLite;

    //CONSTRUCTEUR
    public Journee(int numero) {
        this.numero = numero;
    }

    public Journee(int id, int numero) {
        this.id = id;
        this.numero = numero;
    }

    public Journee() {
    }


        // GET SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    //METHODES
    public Journee(Context context){

        maBaseSQLite = new MyBaseSQLite(context);
    }

    public void open(){

        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){

        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    //INSERT
    public long insertJournee(Journee jr){

        ContentValues values = new ContentValues();
        values.put(ATTR_NUMERO, jr.getNumero());

        return bdd.insert(TABLE_NAME, null, values);
    }
    //UPDATE
    public int updateJournee(int id, Journee jr){

        ContentValues values = new ContentValues();
        values.put(ATTR_NUMERO, jr.getNumero());
        return bdd.update(TABLE_NAME, values, ATTR_ID + " = " +id, null);
    }
    //DELETE
    public int deleteJournee(int id)
    {
        return bdd.delete(TABLE_NAME, ATTR_ID + " = " +id, null);

    }

    //RETRIEVE WITH ID
    public Journee retrieve(int id)
    {

        Cursor cursor = bdd.query(TABLE_NAME, new String[] {ATTR_ID,ATTR_NUMERO},ATTR_ID + "= "+ id,null,null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Journee journee = new Journee(cursor.getInt(0), cursor.getInt(1));

        return  journee;

    }
    //FIND ALL
    public List<Journee> getAllJournee()
    {
        List<Journee> listJournee = new ArrayList<Journee>();

        Cursor cursor = bdd.query(TABLE_NAME, new String[] {ATTR_ID,ATTR_NUMERO}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Journee jr = new Journee();
                jr.setId(cursor.getInt(0));
                jr.setNumero(cursor.getInt(1));
                listJournee.add(jr);

            } while (cursor.moveToNext());
        }

        return listJournee;
    }

}