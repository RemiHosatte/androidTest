package com.example.pc.testandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC on 03/02/2017.
 */


public class MyBaseSQLite  extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "STADEDB";

    private static final String CREATE_TABLE_JOURNEE = "CREATE TABLE journee" +
            "(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "numero INTEGER NOT NULL" +
            ");";

    private static final String CREATE_TABLE_EQUIPE = "CREATE TABLE equipe" +
            "(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "nom TEXT NOT NULL," +
            "logo TEXT NULL," +
            "entraineur TEXT NULL," +
            "nbDePoints INTEGER NULL," +
            "pointsBonus INTEGER NULL," +
            "nomTerrainDomicile TEXT NULL" +
            ");";

    private static final String CREATE_TABLE_MATCH = "CREATE TABLE matchs" +
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
            ");";

    private static final String CREATE_TABLE_JOUEUR = "CREATE TABLE joueur" +
            "(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "nom TEXT NOT NULL," +
            "age INTEGER NOT NULL," +
            "poste TEXT NOT NULL," +
            "equipeID INTEGER NOT NULL," +
            "FOREIGN KEY (equipeID) REFERENCES equipe(id)" +
            ");";

    private static final String CREATE_TABLE_COMPOSITION = "CREATE TABLE composition\n" +
            "(" +
            "idmatch INTEGER NOT NULL," +
            "idjoueur INTEGER NOT NULL," +
            "numeroJoueur INTEGER NOT NULL," +
            "etat NUMERIC NOT NULL," +
            "PRIMARY KEY (idmatch, idjoueur)," +
            "FOREIGN KEY (idmatch) REFERENCES matchs(id)," +
            "FOREIGN KEY (idjoueur) REFERENCES joueur(id)" +
            ");";
    private static MyBaseSQLite mInstance = null;
    private Context mCxt;

    public static MyBaseSQLite getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new MyBaseSQLite(ctx.getApplicationContext());
        }
        return mInstance;
    }



    public MyBaseSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_JOURNEE);
        db.execSQL(CREATE_TABLE_EQUIPE);
        db.execSQL(CREATE_TABLE_MATCH);
        db.execSQL(CREATE_TABLE_JOUEUR);
        db.execSQL(CREATE_TABLE_COMPOSITION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_JOURNEE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EQUIPE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_MATCH);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_JOUEUR);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_COMPOSITION);

        onCreate(db);
    }
}
