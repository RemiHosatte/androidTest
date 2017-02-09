package com.example.pc.testandroid;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 09/02/2017.
 */

public class Joueur {
    /*CREATE TABLE joueur" +
            "(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "nom TEXT NOT NULL," +
            "age INTEGER NOT NULL," +
            "poste TEXT NOT NULL," +
            "equipeID INTEGER NOT NULL," +
            "FOREIGN KEY (equipeID) REFERENCES equipe(id)" +
            ");*/

    //VARIABLES
    private int id;
    private String nom;
    private int age;
    private String poste;
    private Equipe equipeID = new Equipe();


    private String TABLE_NAME = "joueur";
    private String ATTR_ID = "id";
    private String ATTR_NOM = "nom";
    private String ATTR_AGE = "age";
    private String ATTR_POSTE = "poste";
    private String ATTR_EQUIPEID = "equipeID";


    private  String[] attributs =
            {       ATTR_ID,
                    ATTR_NOM,
                    ATTR_AGE,
                    ATTR_POSTE,
                    ATTR_EQUIPEID
            };
    //CONSTRUCTEUR


    public Joueur(int id, String nom, int age, String poste, Equipe equipeID) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.poste = poste;
        this.equipeID = equipeID;
    }

    public Joueur()
    {

    }

    //GET ET SET


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Equipe getEquipeID() {
        return equipeID;
    }

    public void setEquipeID(Equipe equipeID) {
        this.equipeID = equipeID;
    }

    //INSERT
    public long insertJoueur(Joueur jr){


        ContentValues values = new ContentValues();
        values.put(ATTR_NOM, jr.getNom());
        values.put(ATTR_AGE, jr.getAge());
        values.put(ATTR_POSTE, jr.getPoste());
        values.put(ATTR_EQUIPEID, jr.getEquipeID().getId());


        return Global.bdd.insert(TABLE_NAME, null, values);
    }



    //UPDATE
    public int updateJoueur(Joueur jr){
        ContentValues values = new ContentValues();
        values.put(ATTR_NOM, jr.getNom());
        values.put(ATTR_AGE, jr.getAge());
        values.put(ATTR_POSTE, jr.getPoste());
        values.put(ATTR_EQUIPEID, jr.getEquipeID().getId());
        return Global.bdd.update(TABLE_NAME, values, ATTR_ID + " = " + jr.getId(), null);
    }


    //DELETE
    public int deleteJoueur(int id)
    {
        return Global.bdd.delete(TABLE_NAME, ATTR_ID + " = " +id, null);

    }

    //RETRIEVE WITH ID
    public void retrieveJoueur(int id)
    {

        Cursor cursor = Global.bdd.query(TABLE_NAME,attributs,ATTR_ID + " = " + id ,null,null,null, null, null);
        cursor.moveToNext();

        this.id = cursor.getInt(0);
        this.nom = cursor.getString(1);
        this.age = cursor.getInt(2);
        this.poste = cursor.getString(3);
        Equipe eq = new Equipe();
        eq.retrieveEquipe(cursor.getInt(4));
        this.equipeID = eq;


    }

    //FIND ALL
    public List<Joueur> getAllJoueurs()
    {
        List<Joueur> listJoueurs = new ArrayList<Joueur>();
        Cursor cursor = Global.bdd.query(TABLE_NAME, attributs, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Joueur joFindAll = new Joueur();
                Equipe eq = new Equipe();
                eq.retrieveEquipe(cursor.getInt(4));


                joFindAll.setId(cursor.getInt(0));
                joFindAll.setNom(cursor.getString(1));
                joFindAll.setAge(cursor.getInt(2));
                joFindAll.setPoste(cursor.getString(3));
                joFindAll.setEquipeID(eq);

                listJoueurs.add(joFindAll);

            } while (cursor.moveToNext());
        }
        return listJoueurs;
    }
}
