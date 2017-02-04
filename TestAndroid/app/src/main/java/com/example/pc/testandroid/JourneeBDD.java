package com.example.pc.testandroid;

/**
 * Created by PC on 03/02/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class JourneeBDD {


    private SQLiteDatabase bdd;

    private MyBaseSQLite maBaseSQLite;

    public JourneeBDD(Context context){
        //On créer la BDD et sa table
        maBaseSQLite = new MyBaseSQLite(context);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertLivre(Journee jr){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        values.put("numero", jr.getNumero());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert("journee", null, values);
    }

    public Cursor getAllJournee()
    {
        return bdd.rawQuery("SELECT * FROM journee", null);
    }

}