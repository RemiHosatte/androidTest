package com.example.pc.testandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by PC on 05/02/2017.
 */

public class Matchs {


        /*
       CREATE TABLE matchs" +
            "(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "equipeDom INTEGER NOT NULL," +
            "equipeVis INTEGER NOT NULL," +
            "scoreDom INTEGER NULL," +
            "scoreVis INTEGER NULL," +
            "datetime NUMERIC NULL," +
            "arbitre TEXT NULL," +
            "idjournee INTEGER NULL," +
            "FOREIGN KEY (idjournee) REFERENCES journee(id)," +
            "FOREIGN KEY (equipeDom) REFERENCES equipe(id)," +
            "FOREIGN KEY (equipeVis) REFERENCES equipe(id)" +
            ");
                */
        //VARIABLES
        private int id;
        private Equipe equipeDom;
        private Equipe equipeVis;
        private int scoreDom;
        private int scoreVis;
        private String datetime;
        private String arbitre;
        private Journee idjournee;

        private String TABLE_NAME = "matchs";
        private String ATTR_ID = "id";
        private String ATTR_EQUIPEDOM = "equipeDom";
        private String ATTR_EQUIPEVIS = "equipeVis";
        private String ATTR_SCOREDOM = "scoreDom";
        private String ATTR_SCOREVIS = "scoreVis";
        private String ATTR_DATETIME = "datetime";
        private String ATTR_ARBITRE = "arbitre";
        private String ATTR_IDJOURNEE = "idjournee";

        private SQLiteDatabase bdd;
        private MyBaseSQLite maBaseSQLite;

        private  String[] attributs =
                {       ATTR_ID,
                        ATTR_EQUIPEDOM,
                        ATTR_EQUIPEVIS,
                        ATTR_SCOREDOM,
                        ATTR_SCOREVIS,
                        ATTR_DATETIME,
                        ATTR_ARBITRE,
                        ATTR_IDJOURNEE
                };
        //CONSTRUCTEUR


    public Matchs(int id, Equipe equipeDom, Equipe equipeVis, int scoreDom, int scoreVis, String datetime, String arbitre, Journee idjournee) {
        this.id = id;
        this.equipeDom = equipeDom;
        this.equipeVis = equipeVis;
        this.scoreDom = scoreDom;
        this.scoreVis = scoreVis;
        this.datetime = datetime;
        this.arbitre = arbitre;
        this.idjournee = idjournee;
    }

  public Matchs(Equipe equipeDom, Equipe equipeVis, int scoreDom, int scoreVis, String datetime, String arbitre, Journee idjournee) {
        this.equipeDom = equipeDom;
        this.equipeVis = equipeVis;
        this.scoreDom = scoreDom;
        this.scoreVis = scoreVis;
        this.datetime = datetime;
        this.arbitre = arbitre;
        this.idjournee = idjournee;
    }
    public Matchs()
        {

        }

        //GET ET SET


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipe getEquipeDom() {
        return equipeDom;
    }

    public void setEquipeDom(Equipe equipeDom) {
        this.equipeDom = equipeDom;
    }

    public Equipe getEquipeVis() {
        return equipeVis;
    }

    public void setEquipeVis(Equipe equipeVis) {
        this.equipeVis = equipeVis;
    }

    public int getScoreDom() {
        return scoreDom;
    }

    public void setScoreDom(int scoreDom) {
        this.scoreDom = scoreDom;
    }

    public int getScoreVis() {
        return scoreVis;
    }

    public void setScoreVis(int scoreVis) {
        this.scoreVis = scoreVis;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getArbitre() {
        return arbitre;
    }

    public void setArbitre(String arbitre) {
        this.arbitre = arbitre;
    }

    public Journee getIdjournee() {
        return idjournee;
    }

    public void setIdjournee(Journee idjournee) {
        this.idjournee = idjournee;
    }

    //METHODES
        public Matchs(Context context){

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
        public long insertMatch(Matchs ma){

            Journee jr = new Journee();
            Equipe eq = new Equipe();
            ContentValues values = new ContentValues();
            values.put(ATTR_EQUIPEDOM, eq.getId());
            values.put(ATTR_EQUIPEVIS, eq.getId());
            values.put(ATTR_SCOREDOM, ma.getScoreDom());
            values.put(ATTR_SCOREVIS, ma.getScoreVis());
            values.put(ATTR_DATETIME, ma.getDatetime());
            values.put(ATTR_ARBITRE, ma.getArbitre());
            values.put(ATTR_IDJOURNEE, jr.getId());

            return bdd.insert(TABLE_NAME, null, values);
        }



        //UPDATE
        public int updateMatch(int id, Matchs ma){
            Journee jr = new Journee();
            ContentValues values = new ContentValues();
            values.put(ATTR_EQUIPEDOM, this.equipeDom.getId());
            values.put(ATTR_EQUIPEVIS, this.equipeVis.getId());
            values.put(ATTR_SCOREDOM, ma.getScoreDom());
            values.put(ATTR_SCOREVIS, ma.getScoreVis());
            values.put(ATTR_DATETIME, ma.getDatetime());
            values.put(ATTR_ARBITRE, ma.getArbitre());
            values.put(ATTR_IDJOURNEE, this.idjournee.getId());
            return bdd.update(TABLE_NAME, values, ATTR_ID + " = " +id, null);
        }
        //DELETE
        public int deleteMatch(int id)
        {
            return bdd.delete(TABLE_NAME, ATTR_ID + " = " +id, null);

        }

        //RETRIEVE WITH ID
        public Matchs retrieveMatch(int id)
        {

            Cursor cursor = bdd.query(TABLE_NAME,attributs,ATTR_ID + "= ",
                    new String[]{String.valueOf(id)},null,null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            Equipe eq1 = new Equipe();
            Equipe eq2 = new Equipe();
            Journee jr = new Journee();
            jr.retrieve(cursor.getInt(1));
            eq1.retrieveEquipe(cursor.getInt(1));
            eq2.retrieveEquipe(cursor.getInt(2));
            Matchs ma = new Matchs(
                    cursor.getInt(0),
                    eq1,
                    eq2,
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    jr
            );


            return  ma;

        }
        //FIND ALL
        public List<Matchs> getAllMatchs()
        {
            List<Matchs> listMatchs = new ArrayList<Matchs>();

            Cursor cursor = bdd.query(TABLE_NAME, attributs, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    System.out.println(cursor.getInt(0));
                   Matchs maFindAll = new Matchs();
                   /*  Equipe eq1 = new Equipe();
                    Equipe eq2 = new Equipe();
                    Journee jr = new Journee();


                    maFindAll.setEquipeDom(eq1);
                    maFindAll.setEquipeVis(eq2);
                    maFindAll.setIdjournee(jr);


                    maFindAll.setEquipeDom(eq1.retrieveEquipe(cursor.getInt(1)));
                    maFindAll.setEquipeVis(eq2.retrieveEquipe(cursor.getInt(2)));
                    maFindAll.setScoreDom(cursor.getInt(3));
                    maFindAll.setScoreVis(cursor.getInt(4));
                    maFindAll.setDatetime(cursor.getString(5));

                    jr.retrieve(cursor.getInt(7));*/
                    maFindAll.setId(cursor.getInt(0));
                    maFindAll.setId(cursor.getInt(1));
                    maFindAll.setArbitre(cursor.getString(6));

                    listMatchs.add(maFindAll);

                } while (cursor.moveToNext());
            }

            return listMatchs;
        }



}
