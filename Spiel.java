import java.util.ArrayList;
import java.util.Random;

/**
 *  Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul".
 *  "Die Welt von Zuul" ist ein sehr einfaches, textbasiertes
 *  Adventure-Game. Ein Spieler kann sich in einer Umgebung bewegen,
 *  mehr nicht. Das Spiel sollte auf jeden Fall ausgebaut werden,
 *  damit es interessanter wird!
 * 
 *  Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und
 *  an ihr die Methode "spielen" aufgerufen werden.
 * 
 *  Diese Instanz dieser Klasse erzeugt und initialisiert alle
 *  anderen Objekte der Anwendung: Sie legt alle R�ume und einen
 *  Parser an und startet das Spiel. Sie wertet auch die Befehle
 *  aus, die der Parser liefert und sorgt f�r ihre Ausf�hrung.
 * 
 * @author  Michael K�lling und David J. Barnes
 * @version 31.07.2011
 */

class Spiel 
{
    private Parser parser;
    private Raum aktuellerRaum;
    private Raum raumZUrWerkstatt; 
    private Raum werkstattraum; 
    private String beschreibungDraussen = "im Haupteingang, der Ort des Geschehens";
    int anzahlLebendesSpielers = 3; 
    Schaltplan aktuellerSchaltplan; 
    Schaltplan loesungssSchaltplan; 
    Rucksack rucksackDesSpielers;
    int anzahlElemente; // Anzahl der Elemente in der aktuellen Schaltung 
    boolean spielWurdeGewonnen; 

        
    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        schaltplanAnlegen();
        raeumeAnlegen(this.aktuellerSchaltplan);
        parser = new Parser();
        spielWurdeGewonnen = false; 
    }

    private void schaltplanAnlegen() {
        
        Random random = new Random();
        int zufallszahl = random.nextInt(2);

        System.out.println("" + zufallszahl);
        
        Schaltplan schaltplan;

        if (zufallszahl == 0) {
            schaltplan = new Schaltplan("Reihenschaltung2Widerstaende", 5);
            schaltplan.setzeSchaltteil("Kabel");
            schaltplan.setzeSchaltteil("Widerstand");
            schaltplan.setzeSchaltteil("Kabel");
            schaltplan.setzeSchaltteil("Widerstand");
            schaltplan.setzeSchaltteil("Kabel");
        } else {
            schaltplan = new Schaltplan("DiodeVermessenOhneMessgeraetImPLan", 5);
            schaltplan.setzeSchaltteil("Kabel");
            schaltplan.setzeSchaltteil("Widerstand");
            schaltplan.setzeSchaltteil("Kabel");
            schaltplan.setzeSchaltteil("Diode");
            schaltplan.setzeSchaltteil("Kabel");
            aktuellerSchaltplan = schaltplan; 
        }
        aktuellerSchaltplan = schaltplan; 
        System.out.println("" + aktuellerSchaltplan.wieVieleTeileHatDerPlan());
        anzahlElemente = aktuellerSchaltplan.wieVieleTeileHatDerPlan(); 
        rucksackDesSpielers = new Rucksack(anzahlElemente);
        for (String schaltteil : schaltplan.gibDieSchallteile()) {
            rucksackDesSpielers.packeSchaltteilEin(schaltteil);
        }
        
        // Hier Rucksack initialiseren
        // Rucksack braucht die Anzahl der Schaltteile
    }

    /**
     * Erzeuge alle Raeume und verbinde ihre Ausgaenge miteinander.
     * 
     */
    private void raeumeAnlegen(Schaltplan aktuellerSchaltplan)
    {
        Raum draussen, flur0, fahrstuhl, werkstatt, treppenaufgang, stock1, labor1, stock2, fensterraum, stock3, pcpool ;

        QuizRaum ee1, physikraum, pmraum, etraum, laborEEScz3, matheraum, computerraum;

        draussen = new BegruessungsRaum("Vor dem Hautpeingang der Universitaet");
        flur0 = new Raum("Flur 0");
        raumZUrWerkstatt = flur0; 
        // die Raeume erzeugen
        fahrstuhl = new Raum("fahrstuhl e, doch er ist kaputt... :(");
        werkstatt = new Werkstatt("Werkstatt", aktuellerSchaltplan);
        werkstattraum = werkstatt; 
        ee1 = new QuizRaum("EE1-Labor e", "Haase");
        treppenaufgang = new Raum("im Treppenaufgang e");
        stock1 = new Raum("erster Stock 1");
        labor1 = new Raum("Laborraum mit Equipment 1");
        physikraum = new QuizRaum("Physik bei Juenemann 1", "Juenemann");
        pmraum = new QuizRaum("12.81 1", "Becker");
        stock2 = new Raum("zweiter Stock 2");
        etraum = new QuizRaum("5.60 ET Radt 2", "Radt");
        fensterraum = new Raum("Zwischenraum mit Fenstern 2");
        computerraum = new QuizRaum("Computerraum bei Eger 2", "Eger");
        stock3 = new Raum("dritter Stock 3");
        pcpool = new Raum("pcpool 3");
        laborEEScz3 = new QuizRaum("laborraum sczesny 3", "Sczesny" );
        matheraum = new QuizRaum("mathe bei landenfeld 3", "Landenfeld");


        // die Ausgaenge initialisieren
        draussen.setzeAusgang("north", flur0);
        draussen.setzeAusgang("east", fahrstuhl);
        //HAUPTEINGANG
        flur0.setzeAusgang("north", treppenaufgang);
        flur0.setzeAusgang("east", ee1);
        flur0.setzeAusgang("south", draussen);
       
        //CAFETERIA
        werkstatt.setzeAusgang("east", flur0);

        //FAHRSTUHL
        //fahrstuhl.setzeAusgang("north", flur0);

        //EE1
        ee1.setzeAusgang("west", flur0);

        //TREPPENAUFGANG
        treppenaufgang.setzeAusgang("north", stock3);
        treppenaufgang.setzeAusgang("east", stock1);
        treppenaufgang.setzeAusgang("west", stock2);
        treppenaufgang.setzeAusgang("south", flur0);

        //STOCK1
        stock1.setzeAusgang("north", pmraum);
        stock1.setzeAusgang("east", physikraum);
        stock1.setzeAusgang("south", treppenaufgang);
        stock1.setzeAusgang("west", labor1);

        //STOCK2
        stock2.setzeAusgang("north", computerraum);
        stock2.setzeAusgang("east", etraum);
        stock2.setzeAusgang("south", treppenaufgang);
        stock2.setzeAusgang("west", fensterraum);

        //STOCK3
        stock3.setzeAusgang("north", matheraum);
        stock3.setzeAusgang("east", pcpool);
        stock3.setzeAusgang("south", treppenaufgang);
        stock3.setzeAusgang("west", laborEEScz3);

        //Raeume in STOCK1
        pmraum.setzeAusgang("south", stock1);
        physikraum.setzeAusgang("west", stock1);
        labor1.setzeAusgang("east", stock1);

        //Raeume in STOCK2
        computerraum.setzeAusgang("south", stock2);
        etraum.setzeAusgang("west", stock2);
        fensterraum.setzeAusgang("east", stock2);

        //Raeume in STOCK3
        matheraum.setzeAusgang("south", stock3);
        pcpool.setzeAusgang("west", stock3);
        laborEEScz3.setzeAusgang("east", stock3);



        aktuellerRaum = draussen;  // das Spiel startet draussen

        // Gegenstände werden den Räumen zugeordnet nach Zufallsprinzip
        // ArrayList mit den Quizraeumen erstellen 
        ArrayList<QuizRaum> leereQuizraeume = new ArrayList<QuizRaum>(); 
        leereQuizraeume.add(matheraum); 
        leereQuizraeume.add(ee1); 
        leereQuizraeume.add(etraum); 
        leereQuizraeume.add(physikraum); 
        leereQuizraeume.add(pmraum); 
        leereQuizraeume.add(laborEEScz3); 
        leereQuizraeume.add(computerraum); 

        for (QuizRaum raum : leereQuizraeume) {
            System.out.println("Aktueller Raum, leer:" + raum.gibKurzbeschreibung());
            System.out.println("Aktueller Prof, leer:" + raum.gibProfessorString());
        }

        ArrayList<String> nichtVerteilteSchaltteile = (ArrayList<String>) aktuellerSchaltplan.gibDieSchallteile().clone(); 

        for (String nichtVerteiltesTeil : nichtVerteilteSchaltteile) {
            System.out.println("Aktuelles Teil, nicht verteilt: " + nichtVerteiltesTeil);
        }
        

        for (int i = anzahlElemente; i != 0; i--) {
            Random random = new Random();
            int zufallszahl = random.nextInt(i);

            String nichtVerteiltesSchaltteil = nichtVerteilteSchaltteile.get(zufallszahl); 
            nichtVerteilteSchaltteile.remove(zufallszahl); 
            System.out.println("Gerade zu verteilen:" + nichtVerteiltesSchaltteil);
            
           
            int zufaelligeRaumnummer = random.nextInt(i);
            leereQuizraeume.get(zufaelligeRaumnummer).packeSchaltteilInRaum(nichtVerteiltesSchaltteil); 
            leereQuizraeume.get(zufaelligeRaumnummer).gibKurzbeschreibung(); 
            leereQuizraeume.get(zufaelligeRaumnummer).gibSchaltteilImRaumAus();
            leereQuizraeume.remove(zufaelligeRaumnummer); 

        }
    }

    /**
     * Die Hauptmethode zum Spielen. Laeuft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen() 
    {            
        System.out.println("Willkommen zu Elektrotechniker ohne (Schalt-)plan"); 
        System.out.println("Fuer mehr Informationen zur Bedinung gib help ein, fuer die Einfuehrung in das Spiel welcome");
        System.out.println("Welcome funktioniert dabei nur in diesem aktuellen Raum ");
        System.out.println("Wenn du das Spiel schon kennst, fang einfach an");
        
        
        // Spiel durchgehen, spiel gewinnen 
        boolean beendet = false;
        while (! beendet && (anzahlLebendesSpielers != 0)) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl, this, rucksackDesSpielers);
        }
        if (anzahlLebendesSpielers == 0) {
            System.out.println("... Du hast keine Leben mehr ... Dir fehlte einfach das Wissen um die Schaltung zu reparieren.");
            System.out.println("Du solltest mehr lernen, um irgendwann noch ein Ingenieur zu werden");
            System.out.println("In Hamburg sagt man Tschüss! Das heißt Auf Wiedersehen!");
        }
        else {
            if (spielWurdeGewonnen) {
                System.out.println("Du hast die Schaltung reparieren können. Super! Wenn du Lust hast eine weitere Schaltung zu reparieren starte das Spiel neu.");
            }
            else {
                System.out.println("Unglaublich, dass du das das beste Spiel der Welt nicht mehr spielen möchtest ...");
                System.out.println(".....");
                System.out.println(".....");
                System.out.println(".....");
                System.out.println(".....");
                System.out.println("Tschüss");
            }
        }
    }

    public void spielerhatgewonnen () {
        spielWurdeGewonnen = true; 
    }


    /**
     * Verarbeite einen gegebenen Befehl (fuhre ihn aus).
     * @param befehl Der zu verarbeitende Befehl.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean verarbeiteBefehl(Befehl befehl, Spiel spiel, Rucksack rucksack)
    {
        boolean moechteBeenden;
        moechteBeenden = aktuellerRaum.fuehreBefehlAus(befehl, spiel, rucksack);
        return moechteBeenden;
    }




    //erste Rucksackmethoden, die müssen zentral sein
    // Schaltteil einpacken, zu koppeln mit dem Quiz
    public void packeSchaltteilInDenRucksack(String Schaltteil) {
       rucksackDesSpielers.packeSchaltteilEin(Schaltteil);
    }

    // Rucksackinhalt ausgeben
    public void gibRucksackinhaltAus() {
        rucksackDesSpielers.rucksackinhaltInKonsole();
     }

    public String gibAktuellesSchaltteilAus(int Nummer) {
        return rucksackDesSpielers.gibAktuellesSchaltteile(Nummer);
    }

    public void gibAnzahlDerLebenAus () {
        System.out.println("Du hast noch" + anzahlLebendesSpielers + "Leben.");
    }

    public void verliereEinLeben () {
        anzahlLebendesSpielers = anzahlLebendesSpielers -1; 
        System.out.println("Du hast ein Leben verloren. Du hast noch " + anzahlLebendesSpielers + "Leben.");
    }

    // Methode, um die Werkstatt zugänglich zu machen
    public void macheWerkstattzugänglich () {
        raumZUrWerkstatt.setzeAusgang("west", werkstattraum);
        System.out.println(".... Knarz ....");
        System.out.println("Du hörst ein entferntes Knarzen im Erdgeschoss, sobald du das Teil eingesammlt hast ...");
        System.out.println("Die Werkstatt des Schreckens wurde nun geöffnet. Nehme dich in Acht, wahrer Elektrotechniker!");
        System.out.println("Versuche nun den Eingang zur Werkstatt zu finden.");
        System.out.println("Du kannst dort deine gesammelten Schaltteile nutzen, um die Schaltung zu reparieren.");
    }





    /**
     * Versuche, in eine Richtung zu gehen. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    public void wechsleRaum(Befehl befehl)
    {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin moechten Sie gehen? Gib go mit der Richtung ein");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen, den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.gibAusgang(richtung);

        if (naechsterRaum == null) {
            System.out.println("Dort ist keine Tuer!");
        }
        else {
            aktuellerRaum = naechsterRaum;
            System.out.println(aktuellerRaum.gibLangeBeschreibung());
    
            if (naechsterRaum instanceof QuizRaum) {
                if (((QuizRaum) naechsterRaum).gibschaltteilString() != null) {
                    ((QuizRaum) naechsterRaum).quizAufrufen(this, rucksackDesSpielers);
                }  
                else {
                   System.out.println("Du schaust dich im Raum um und es ist komplett dunkel ...");
                   System.out.println("... Hier scheint nichts zu finden zu sein ... Es ist wahrscheinlich eine gute Idee weiterzuziehen.");
                }
            }

        }
    } 

    public static void main(String[] args){
        System.out.println("Du hast ein neues Spiel gestartet");
        
        Spiel spiel = new Spiel();
        spiel.spielen();


    }
}
