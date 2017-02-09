package com.example.pc.testandroid;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 09/02/2017.
 */

public class Composition {
   /* "idmatch INTEGER NOT NULL," +
            "idjoueur INTEGER NOT NULL," +
            "numeroJoueur INTEGER NOT NULL," +
            "etat NUMERIC NOT NULL," +
            "PRIMARY KEY (idmatch, idjoueur)," +
            "FOREIGN KEY (idmatch) REFERENCES matchs(id)," +
            "FOREIGN KEY (idjoueur) REFERENCES joueur(id)" +
            ");*/

    //VARIABLES
    private Matchs idmatch = new Matchs();
    private Joueur idjoueur = new Joueur();
    private int numeroJoueur;
    private int etat;


    private String TABLE_NAME = "composition";
    private String ATTR_IDMATCH = "idmatch";
    private String ATTR_IDJOUEUR = "idjoueur";
    private String ATTR_NUMEROJOUEUR = "numeroJoueur";
    private String ATTR_ETAT = "etat";


    private  String[] attributs =
            {       ATTR_IDMATCH,
                    ATTR_IDJOUEUR,
                    ATTR_NUMEROJOUEUR,
                    ATTR_ETAT
            };
    //CONSTRUCTEUR


    public Composition(Matchs idmatch, Joueur idjoueur, int numeroJoueur, int etat) {
        this.idmatch = idmatch;
        this.idjoueur = idjoueur;
        this.numeroJoueur = numeroJoueur;
        this.etat = etat;
    }

    public Composition()
    {

    }

    //GET ET SET


    public Matchs getIdmatch() {
        return idmatch;
    }

    public void setIdmatch(Matchs idmatch) {
        this.idmatch = idmatch;
    }

    public Joueur getIdjoueur() {
        return idjoueur;
    }

    public void setIdjoueur(Joueur idjoueur) {
        this.idjoueur = idjoueur;
    }

    public int getNumeroJoueur() {
        return numeroJoueur;
    }

    public void setNumeroJoueur(int numeroJoueur) {
        this.numeroJoueur = numeroJoueur;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    //INSERT
    public long insertComposition(Composition co){


        ContentValues values = new ContentValues();
        values.put(ATTR_IDMATCH, co.getIdmatch().getId());
        values.put(ATTR_IDJOUEUR, co.getIdjoueur().getId());
        values.put(ATTR_NUMEROJOUEUR, co.getNumeroJoueur());
        values.put(ATTR_ETAT, co.getEtat());


        return Global.bdd.insert(TABLE_NAME, null, values);
    }



    //UPDATE
    /*
    A FINIR
     */
    public int updateComposition(Composition co){
        ContentValues values = new ContentValues();
        values.put(ATTR_IDMATCH, co.getIdmatch().getId());
        values.put(ATTR_IDJOUEUR, co.getIdjoueur().getId());
        values.put(ATTR_NUMEROJOUEUR, co.getNumeroJoueur());
        values.put(ATTR_ETAT, co.getEtat());
        return Global.bdd.update(TABLE_NAME, values, ATTR_IDJOUEUR + " = " + co.getId(), null);
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
