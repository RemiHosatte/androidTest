package com.example.pc.testandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "stade.db";
    int id;
    int numero;

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


/*


    public void create(Journee jr) {

        String req = "INSERT INTO journee(numero) values (" + this.numero + ");";
        System.out.println("Req insert Journée: " + req);


        ContentValues contentValues = new ContentValues();
        contentValues.put("numero", jr.getNumero());
        baseSQLite.insert("journee", null, contentValues);


    }

    public Journee getJournee(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Journee jr = new Journee(0, 0);

        Cursor c = database.rawQuery("SELECT * FROM journee WHERE numero=" + id, null);
        if (c.moveToFirst()) {
            jr.setId(c.getInt(c.getColumnIndex("id")));
            jr.setNumero(c.getInt(c.getColumnIndex("numero")));
            c.close();
        }
        return jr;

    }
    public Cursor getAllJournee()
    {
    return database.rawQuery("SELECT * FROM journee", null);
    }*/
}