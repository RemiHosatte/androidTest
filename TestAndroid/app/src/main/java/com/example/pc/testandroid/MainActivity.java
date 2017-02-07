package com.example.pc.testandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLOutput;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button addBouton;
    private EditText text;
    private int numero = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création d'une instance de ma classe LivresBDD
        //Journee myJournee = new Journee(this);
//        Equipe myEquipe = new Equipe(this);
        Matchs myMatch = new Matchs(this);

        Journee jr = new Journee(numero);
        Equipe eq = new Equipe("ASM", "/logo/asm.jpg", "Mme Chassat", 45,5, "Terrain 1");
        Matchs ma = new Matchs()
        myEquipe.open();
        //On insère le livre que l'on vient de créer
        myEquipe.insertEquipe(eq);
        System.out.println("INSERT FAIT");

        Log.d("Reading: ", "Reading all ..");
        List<Equipe> equipes = myEquipe.getAllEquipe();

        for (Equipe equipe : equipes) {
            String log = "Id: "+equipe.getId()+" ,Numero: " + equipe.getNom();
            // Writing Contacts to log
            Log.d("List: ", log);
        }/*
        System.out.println("Delete id 15");
        myJournee.deleteJournee(15);
        myJournee.deleteJournee(14);
        myJournee.deleteJournee(16);
        System.out.println("Delete fin");*/
        myEquipe.close();
    }
}
