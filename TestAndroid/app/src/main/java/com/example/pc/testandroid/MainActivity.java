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

        Equipe monEquipe = new Equipe(this);
        monEquipe.open();
        Equipe eq = new Equipe("ASM", "/logo/asm.jpg", "Mme Chassat", 45,5, "Terrain 1");
        monEquipe.insertEquipe(eq);
        String id = monEquipe.retrieveEquipe(1).getEntraineur();
        System.out.println(id);
        monEquipe.close();

//
        Matchs monMatchs = new Matchs(this);
        Journee maJournee = new Journee(this);
        monEquipe.open();
        monMatchs.open();
        maJournee.open();
        int EquipeRecup1 = 1 ;
        int EquipeRecup2 = 2 ;
        int JourneeRecup = 1;
        Journee jour = new Journee(10);
        maJournee.insertJournee();
        Equipe eq1 = monEquipe.retrieveEquipe(EquipeRecup1);
        Equipe eq2 = monEquipe.retrieveEquipe(EquipeRecup2);
        Journee jr = maJournee.retrieve(JourneeRecup);

        Matchs ma = new Matchs(eq1,eq2,15,15,"02/05/2017","Jo",jr);
        monMatchs.insertMatch(ma);


        Log.d("Reading: ", "Reading all ..");
        List<Journee> lesJours = maJournee.getAllJournee();

       for (Journee leJour : lesJours) {
            String log = "Id: "+leJour.getId()+" ,Date: " + leJour.getNumero();
            // Writing Contacts to log
            Log.d("List: ", log);
        }
      // monMatchs.insertMatch(ma);
    }
}
