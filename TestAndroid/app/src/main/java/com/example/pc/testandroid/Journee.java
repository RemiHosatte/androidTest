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


    //CONSTRUCTEUR


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

    //INSERT
    public long insertJournee(Journee jr){

        ContentValues values = new ContentValues();
        values.put(ATTR_NUMERO, jr.getNumero());

        return Global.bdd.insert(TABLE_NAME, null, values);
    }
    //UPDATE
    public int updateJournee(Journee jr){

        ContentValues values = new ContentValues();
        values.put(ATTR_NUMERO, jr.getNumero());
        return  Global.bdd.update(TABLE_NAME, values, ATTR_ID + " = " + jr.getId(), null);
    }
    //DELETE
    public int deleteJournee(int id)
    {
        return  Global.bdd.delete(TABLE_NAME, ATTR_ID + " = " +id, null);

    }

    //RETRIEVE WITH ID
    public void retrieve(int id)
    {

        Cursor cursor =  Global.bdd.query(TABLE_NAME, new String[] {ATTR_ID,ATTR_NUMERO},ATTR_ID + " = "+ id,null,null,null, null, null);

            cursor.moveToNext();

        this.id = cursor.getInt(0);
        this.numero = cursor.getInt(1);
    }
    //FIND ALL
    public List<Journee> getAllJournee()
    {
        List<Journee> listJournee = new ArrayList<Journee>();

        Cursor cursor =  Global.bdd.query(TABLE_NAME, new String[] {ATTR_ID,ATTR_NUMERO}, null, null, null, null, null);

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