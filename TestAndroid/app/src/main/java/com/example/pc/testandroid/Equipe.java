package com.example.pc.testandroid;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 05/02/2017.
 */

public class Equipe {

    /*
    "CREATE TABLE equipe" +
            "(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "nom TEXT NOT NULL," +
            "logo TEXT NULL," +
            "entraineur TEXT NULL," +
            "nbDePoints INTEGER NULL," +
            "pointsBonus INTEGER NULL," +
            "nomTerrainDomicile TEXT NULL" +
            ");"
            */
    //VARIABLES
    private int id;
    private String nom;
    private String logo;
    private String entraineur;
    private int nbDePoints;
    private int pointsBonus;
    private String nomTerrainDomicile;

    private String TABLE_NAME = "equipe";
    private String ATTR_ID = "id";
    private String ATTR_NOM = "nom";
    private String ATTR_LOGO = "logo";
    private String ATTR_ENTRAINEUR = "entraineur";
    private String ATTR_NBDEPOINTS = "nbDePoints";
    private String ATTR_POINTSBONUS = "pointsBonus";
    private String ATTR_NOMTERRAINDOMICILE = "nomTerrainDomicile";

    private SQLiteDatabase bdd;
    private MyBaseSQLite maBaseSQLite;

    private  String[] attributs =
            {       ATTR_ID,
                    ATTR_NOM,
                    ATTR_LOGO,
                    ATTR_ENTRAINEUR,
                    ATTR_NBDEPOINTS,
                    ATTR_POINTSBONUS,
                    ATTR_NOMTERRAINDOMICILE};
    //CONSTRUCTEUR

    public Equipe(int id, String nom, String logo, String entraineur, int nbDePoints, int pointsBonus, String nomTerrainDomicile) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
        this.entraineur = entraineur;
        this.nbDePoints = nbDePoints;
        this.pointsBonus = pointsBonus;
        this.nomTerrainDomicile = nomTerrainDomicile;
    }
    public Equipe( String nom, String logo, String entraineur, int nbDePoints, int pointsBonus, String nomTerrainDomicile) {
        this.nom = nom;
        this.logo = logo;
        this.entraineur = entraineur;
        this.nbDePoints = nbDePoints;
        this.pointsBonus = pointsBonus;
        this.nomTerrainDomicile = nomTerrainDomicile;
    }
    public Equipe()
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEntraineur() {
        return entraineur;
    }

    public void setEntraineur(String entraineur) {
        this.entraineur = entraineur;
    }

    public int getNbDePoints() {
        return nbDePoints;
    }

    public void setNbDePoints(int nbDePoints) {
        this.nbDePoints = nbDePoints;
    }

    public int getPointsBonus() {
        return pointsBonus;
    }

    public void setPointsBonus(int pointsBonus) {
        this.pointsBonus = pointsBonus;
    }

    public String getNomTerrainDomicile() {
        return nomTerrainDomicile;
    }

    public void setNomTerrainDomicile(String nomTerrainDomicile) {
        this.nomTerrainDomicile = nomTerrainDomicile;
    }


    //METHODES
    public Equipe(Context context){

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
    public long insertEquipe(Equipe eq){

        ContentValues values = new ContentValues();
        values.put(ATTR_NOM, eq.getNom());
        values.put(ATTR_LOGO, eq.getLogo());
        values.put(ATTR_ENTRAINEUR, eq.getEntraineur());
        values.put(ATTR_NBDEPOINTS, eq.getNbDePoints());
        values.put(ATTR_POINTSBONUS, eq.getPointsBonus());
        values.put(ATTR_NOMTERRAINDOMICILE, eq.getNomTerrainDomicile());

        return bdd.insert(TABLE_NAME, null, values);
    }



    //UPDATE
    public int updateEquipe(int id, Equipe eq){

        ContentValues values = new ContentValues();
        values.put(ATTR_NOM, eq.getNom());
        values.put(ATTR_LOGO, eq.getLogo());
        values.put(ATTR_ENTRAINEUR, eq.getEntraineur());
        values.put(ATTR_NBDEPOINTS, eq.getNbDePoints());
        values.put(ATTR_POINTSBONUS, eq.getPointsBonus());
        values.put(ATTR_NOMTERRAINDOMICILE, eq.getNomTerrainDomicile());
        return bdd.update(TABLE_NAME, values, ATTR_ID + " = " +id, null);
    }
    //DELETE
    public int deleteEquipe(int id)
    {
        return bdd.delete(TABLE_NAME, ATTR_ID + " = " +id, null);

    }

    //RETRIEVE WITH ID
    public Equipe retrieveEquipe(int id)
    {

        Cursor cursor = bdd.query(TABLE_NAME,attributs,ATTR_ID + "= "+ id,null,null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Equipe eq = new Equipe(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cursor.getString(6));
        return  eq;

    }
    //FIND ALL
    public List<Equipe> getAllEquipe()
    {
        List<Equipe> listEquipe = new ArrayList<Equipe>();

        Cursor cursor = bdd.query(TABLE_NAME, attributs, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Equipe eq = new Equipe();
                eq.setId(cursor.getInt(0));
                eq.setNom(cursor.getString(1));
                eq.setLogo(cursor.getString(2));
                eq.setEntraineur(cursor.getString(3));
                eq.setNbDePoints(cursor.getInt(4));
                eq.setPointsBonus(cursor.getInt(5));
                eq.setNomTerrainDomicile(cursor.getString(6));


                listEquipe.add(eq);

            } while (cursor.moveToNext());
        }

        return listEquipe;
    }

}
