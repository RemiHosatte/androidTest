package com.example.pc.testandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {


    private Button addBouton;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création d'une instance de ma classe LivresBDD
        JourneeBDD jrBDD = new JourneeBDD(this);

        //Création d'un livre
        Journee jr = new Journee(1);

        //On ouvre la base de données pour écrire dedans
        jrBDD.open();
        //On insère le livre que l'on vient de créer
        jrBDD.insertLivre(jr);
        System.out.println("test");
        Cursor c = jrBDD.getAllJournee();
        if (c.moveToFirst())
        {
            do {
                Log.d("TEST",
                        c.getInt(c.getColumnIndex("id")) + "," +
                                c.getInt(c.getColumnIndex("numero"))
                );
            }
            while (c.moveToNext());
        }
        c.close();

        jrBDD.close();
    }
}
