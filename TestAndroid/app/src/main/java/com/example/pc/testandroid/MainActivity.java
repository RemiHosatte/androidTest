package com.example.pc.testandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView maListView;
    private Button addBouton;
    private EditText text;
    private int numero = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
* INSERT
* INSERT
* RETRIEVE
* UPDATE
* FINDALL
* DELETE
* FINDALL
* */



        MyBaseSQLite maBaseSQLite = new MyBaseSQLite(getBaseContext());
        Global.bdd = maBaseSQLite.getWritableDatabase();

        maListView = (ListView) findViewById(R.id.listViewMatchsCalendrier);
        List<Equipe> mesEquipes = new ArrayList<Equipe>();
        mesEquipes.add(new Equipe(1,"Clermont","","Mr M",25,25,"Test"));
        List<Equipe> equipes =  mesEquipes;
        ListMatchCalendrierAdapter adapter = new ListMatchCalendrierAdapter(MainActivity.this, equipes);
        maListView.setAdapter(adapter);
        //___________JOURNEE___________
       //INSERT
     /*   Journee jour = new Journee(1,1);
        jour.insertJournee(jour);

        //RETRIEVE
        Log.d("Reading one: ", "Reading  ..");
       Journee jourR = new Journee();
        jourR.retrieve(1);
        System.out.println(jourR.getId());
        System.out.println(jourR.getNumero());

        //UPDATE
        Journee jourUP = new Journee(1,15);
        jourUP.updateJournee(jourUP);

        //FIND ALL
        Log.d("Reading: ", "Reading all ..");
        List<Journee> journees1 = jourR.getAllJournee();

        for (Journee journee : journees1) {
            String log = "Id: "+journee.getId()+" ,Numero: " + journee.getNumero();
            // Writing Contacts to log
            Log.d("List: ", log);
        }

        //DELETE
        Journee jourDE = new Journee();
        jourDE.deleteJournee(40);

        //FIND ALL
        Journee jourF = new Journee();
        Log.d("Reading: ", "Reading all ..");
        List<Journee> journees2 = jourF.getAllJournee();

        for (Journee journee : journees2) {
            String log = "Id: "+journee.getId()+" ,Numero: " + journee.getNumero();
            // Writing Contacts to log
            Log.d("List: ", log);
        }
        //___________EQUIPE___________
       //INSERT
      Equipe eq = new Equipe(1,"PSR", "/logo/asm.jpg", "Mr M", 45,5, "Terrain 1");
        eq.insertEquipe(eq);
  Equipe eq2 = new Equipe(1,"ASM", "/logo/asm.jpg", "Mr P", 25,1, "Terrain 2");
        eq2.insertEquipe(eq);
       //RETRIEVE
        Log.d("Reading one: ", "Reading  ..");
        Equipe eqR = new Equipe();
        eqR.retrieveEquipe(1);
        System.out.println(eqR.getId());
        System.out.println(eqR.getNom());
        System.out.println(eqR.getLogo());
        System.out.println(eqR.getEntraineur());
        System.out.println(eqR.getNbDePoints());
        System.out.println(eqR.getPointsBonus());
        System.out.println(eqR.getNomTerrainDomicile());

        //UPDATE
        Equipe eqUp = new Equipe(1,"PSR", "/logo/asm.jpg", "Mr Ok", 75,5, "Terrain 1");
        eqUp.updateEquipe(eqUp);

        //FIND ALL
        Log.d("Reading: ", "Reading all ..");
        List<Equipe> equipes1 = eq.getAllEquipe();

        for (Equipe equipe : equipes1) {
            String log = "Id: "+equipe.getId()+
                    "Nom: " + equipe.getNom() +
                    ",: " + equipe.getLogo() +
                    ",: " + equipe.getEntraineur() +
                    ",: " + equipe.getNbDePoints() +
                    ",: " + equipe.getPointsBonus() +
                    ",: " + equipe.getNomTerrainDomicile()
                    ;
            // Writing Contacts to log
            Log.d("List: ", log);
        }

        //DELETE
        Equipe eqDE = new Equipe();
        eqDE.deleteEquipe(12);

        //FIND ALL
       Log.d("Reading: ", "Reading all ..");
        Equipe eqF = new Equipe();
        List<Equipe> equipes2 = eqF.getAllEquipe();

        for (Equipe equipe : equipes2) {
            String log = "Id: "+equipe.getId()+
                    "Nom: " + equipe.getNom() +
                    ",: " + equipe.getLogo() +
                    ",: " + equipe.getEntraineur() +
                    ",: " + equipe.getNbDePoints() +
                    ",: " + equipe.getPointsBonus() +
                    ",: " + equipe.getNomTerrainDomicile()
                    ;
            // Writing Contacts to log
            Log.d("List: ", log);
        }



        //___________MATCHS___________
       //INSERT
        Equipe monEquipe1 = new Equipe();
        Equipe monEquipe2 = new Equipe();
        Journee maJournee = new Journee();
        int EquipeRecup1 = 1 ;
        int EquipeRecup2 = 2 ;
        int JourneeRecup = 1;
        monEquipe1.retrieveEquipe(EquipeRecup1);
        monEquipe2.retrieveEquipe(EquipeRecup2);
        maJournee.retrieve(JourneeRecup);
        Matchs ma = new Matchs(1,monEquipe1,monEquipe2,15,15,"02/05/2017","ez",maJournee);
        ma.insertMatch(ma);

       // RETRIEVE
        Log.d("Reading one: ", "Reading  ..");
        Matchs maR = new Matchs();
        maR.retrieveMatch(1);
        System.out.println(maR.getId());
        System.out.println(maR.getEquipeDom().getNom());
        System.out.println(maR.getEquipeVis().getNom());
        System.out.println(maR.getScoreDom());
        System.out.println(maR.getScoreVis());
        System.out.println(maR.getDatetime());
        System.out.println(maR.getArbitre());
        System.out.println(maR.getIdjournee().getNumero());

       //UPDATE

               monEquipe1.retrieveEquipe(EquipeRecup1);
        monEquipe2.retrieveEquipe(EquipeRecup2);
        maJournee.retrieve(JourneeRecup);
        Matchs maUp = new Matchs(1,monEquipe1,monEquipe2,78,12,"05/05/2017","New",maJournee);
        maUp.updateMatch(maUp);



        //FIND ALL
        Log.d("Reading: ", "Reading all ..");
        Matchs maF = new Matchs();
        List<Matchs> matchs1 = maF.getAllMatchs();

        for (Matchs match : matchs1) {
            String log = "Id: "+match.getId()+
                    ",: " + match.getEquipeDom().getNom() +
                    ",: " + match.getEquipeVis().getNom() +
                    ",: " + match.getScoreDom() +
                    ",: " + match.getScoreVis() +
                    ",: " + match.getDatetime() +
                    ",: " + match.getArbitre() +
                    ",: " + match.getIdjournee().getNumero()
                    ;
            // Writing Contacts to log
            Log.d("List: ", log);
        }


*/

        //___________joueurs___________

      /*  Equipe monEquipe = new Equipe();
        int EquipeRecup = 6;
        monEquipe.retrieveEquipe(EquipeRecup);
        Joueur jo = new Joueur(1,"J.Mackintosh",25,"1",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(2,"Q.Lespiaucq",31 ,"2",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(3,"M.Hamadache",27,"3",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(4,"F.Metz",35,"4",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(5,"M.Tutaia",32,"5",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(6,"B.Mowen",28,"6",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(7,"S.Armitage",27,"7",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(8,"S.Dougall",24,"8",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(9,"C.Slade",23,"9",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(10,"J.Tornas",25,"10",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(11,"M.Ratuvou",24,"11",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(12,"W.Votu",35,"12",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(13,"J.Fumat",28,"13",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(14,"L.Dupichot",24,"14",monEquipe);
        jo.insertJoueur(jo);
        jo = new Joueur(15,"C.Malié",29,"15",monEquipe);
        jo.insertJoueur(jo);

        Log.d("Reading: ", "Reading all ..");
        Joueur joF = new Joueur();
        List<Joueur> jo1 = joF.getAllJoueurs();

        for (Joueur joueur : jo1) {
            String log = "Id: "+joueur.getId()+
                    ",: " + joueur.getId() +
                    ",: " + joueur.getNom() +
                    ",: " + joueur.getAge() +
                    ",: " + joueur.getPoste() +
                    ",: " + joueur.getEquipeID().getNom()
                    ;
            // Writing Contacts to log
            Log.d("List: ", log);
        }*/
    }
}
