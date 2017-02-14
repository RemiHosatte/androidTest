package com.example.pc.testandroid;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

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
        private Equipe equipeDom = new Equipe();
        private Equipe equipeVis = new Equipe();
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


        //INSERT
        public long insertMatch(Matchs ma){


            ContentValues values = new ContentValues();
            values.put(ATTR_EQUIPEDOM, ma.getEquipeDom().getId());
            values.put(ATTR_EQUIPEVIS, ma.getEquipeVis().getId());
            values.put(ATTR_SCOREDOM, ma.getScoreDom());
            values.put(ATTR_SCOREVIS, ma.getScoreVis());
            values.put(ATTR_DATETIME, ma.getDatetime());
            values.put(ATTR_ARBITRE, ma.getArbitre());
            values.put(ATTR_IDJOURNEE, ma.getIdjournee().getId());


            return Global.bdd.insert(TABLE_NAME, null, values);
        }



        //UPDATE
        public int updateMatch(Matchs ma){
            ContentValues values = new ContentValues();
            System.out.println("Crea" + ma.getEquipeDom().getId());
            values.put(ATTR_EQUIPEDOM, ma.getEquipeDom().getId());
            values.put(ATTR_EQUIPEVIS, ma.getEquipeVis().getId());
            values.put(ATTR_SCOREDOM, ma.getScoreDom());
            values.put(ATTR_SCOREVIS, ma.getScoreVis());
            values.put(ATTR_DATETIME, ma.getDatetime());
            values.put(ATTR_ARBITRE, ma.getArbitre());
            values.put(ATTR_IDJOURNEE, ma.getIdjournee().getId());
            return Global.bdd.update(TABLE_NAME, values, ATTR_ID + " = " +ma.getId(), null);
        }


        //DELETE
        public int deleteMatch(int id)
        {
            return Global.bdd.delete(TABLE_NAME, ATTR_ID + " = " +id, null);

        }

        //RETRIEVE WITH ID
        public void retrieveMatch(int id)
        {

            Cursor cursor = Global.bdd.query(TABLE_NAME,attributs,ATTR_ID + " = " + id ,null,null,null, null, null);
            cursor.moveToNext();

                this.id          =      cursor.getInt(0);
                Equipe eq1 = new Equipe();
                Equipe eq2 = new Equipe();
               eq1.retrieveEquipe(cursor.getInt(1));
               eq2.retrieveEquipe(cursor.getInt(2));
                this.equipeDom = eq1;
                this.equipeVis = eq2;
                this.scoreDom    =      cursor.getInt(3);
                this.scoreVis    =      cursor.getInt(4);
                this.datetime    =      cursor.getString(5);
                this.arbitre     =      cursor.getString(6);
                Journee jr = new Journee();
                jr.retrieve(cursor.getInt(7));
                this.idjournee = jr;
            System.out.println("CursorJour: " + cursor.getInt(7));
            System.out.println("CursorEq1: " + cursor.getInt(1));
            System.out.println("CursorEq2: " + cursor.getInt(2));


        }

        //FIND ALL
        public List<Matchs> getAllMatchs()
        {
            List<Matchs> listMatchs = new ArrayList<Matchs>();
            Cursor cursor = Global.bdd.query(TABLE_NAME, attributs, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    Matchs maFindAll = new Matchs();
                    Equipe eq1 = new Equipe();
                    eq1.retrieveEquipe(cursor.getInt(1));
                    Equipe eq2 = new Equipe();
                    eq2.retrieveEquipe(cursor.getInt(2));
                    Journee jr = new Journee();
                    jr.retrieve(cursor.getInt(7));

                    maFindAll.setId(cursor.getInt(0));
                    maFindAll.setEquipeDom(eq1);
                    maFindAll.setEquipeVis(eq2);
                    maFindAll.setScoreDom(cursor.getInt(3));
                    maFindAll.setScoreVis(cursor.getInt(4));
                    maFindAll.setDatetime(cursor.getString(5));
                    maFindAll.setArbitre(cursor.getString(6));
                    maFindAll.setIdjournee(jr);
                    listMatchs.add(maFindAll);

                } while (cursor.moveToNext());
            }
            return listMatchs;
        }
}
