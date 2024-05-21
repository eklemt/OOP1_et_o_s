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
    Schaltplan aktuellerSchaltplan; 
    Schaltplan loesungssSchaltplan; 
    Rucksack rucksackDesSpielers; 

        
    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        this.aktuellerSchaltplan = schaltplanAnlegen();
        raeumeAnlegen(this.aktuellerSchaltplan);
        parser = new Parser();
    }

    private Schaltplan schaltplanAnlegen() {
        
        Random random = new Random();
        int zufallszahl = random.nextInt(2);
        int anzahlElemente; 
        System.out.println("" + zufallszahl);
        
        Schaltplan schaltplan;

        if (zufallszahl == 0) {
            schaltplan = new Schaltplan("Reihenschaltung2Widerstaende"); 
            schaltplan.setzeSchaltteil("Kabel");
            schaltplan.setzeSchaltteil("Widerstand");
            schaltplan.setzeSchaltteil("Kabel");
            schaltplan.setzeSchaltteil("Widerstand");
            schaltplan.setzeSchaltteil("Kabel");
            anzahlElemente = 5; 
        } else {
            schaltplan = new Schaltplan("DiodeVermessenOhneMessgeraetImPLan"); 
            schaltplan.setzeSchaltteil("Kabel");
            schaltplan.setzeSchaltteil("Widerstand");
            schaltplan.setzeSchaltteil("Kabel");
            schaltplan.setzeSchaltteil("Diode");
            schaltplan.setzeSchaltteil("Kabel");
            anzahlElemente = 5; 
        }
        
        rucksackDesSpielers = new Rucksack(anzahlElemente); 
        return schaltplan; 
        // Hier Rucksack initialiseren 
        // Rucksack braucht die Anzahl der Schaltteile 
    }

    /**
     * Erzeuge alle R�ume und verbinde ihre Ausg�nge miteinander.
     */
    private void raeumeAnlegen(Schaltplan aktuellerSchaltplan)
    {
        Raum draussen, hoersaal, werkstatt, labor, buero;
      
        // die R�ume erzeugen
        draussen = new BegruessungsRaum("vor dem Haupteingang der Universitaet");
        hoersaal = new QuizRaum("in einem Vorlesungssaal");
        werkstatt = new Werkstatt("die Werkstatt", aktuellerSchaltplan);
        labor = new Raum("in einem Rechnerraum");
        buero = new Raum("im Verwaltungsbuero der Informatik");
        
        // die Ausg�nge initialisieren
        draussen.setzeAusgang("east", hoersaal);
        draussen.setzeAusgang("south", labor);
        draussen.setzeAusgang("west", werkstatt);

        hoersaal.setzeAusgang("west", draussen);

        werkstatt.setzeAusgang("east", draussen);

        labor.setzeAusgang("north", draussen);
        labor.setzeAusgang("east", buero);

        buero.setzeAusgang("west", labor);

        aktuellerRaum = draussen;  // das Spiel startet draussen

    }

    /**
     * Die Hauptmethode zum Spielen. L�uft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen() 
    {            
        System.out.println("Willkommen zu Elektrotechniker ohne (Schalt-)plan"); 
        System.out.println("Fuer mehr Informationen zur Bedinung gib help ein, f�r die Einf�hrung in das Spiel welcome"); 
        System.out.println("Wenn du das Spiel schon kennst, fang einfach an");
                
        boolean beendet = false;
        while (! beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl, this);
        }
        System.out.println("Hoffentlich hast du jetzt mehr Plan. Auf Wiedersehen.");
    }

   
    /**
     * Verarbeite einen gegebenen Befehl (fuhre ihn aus).
     * @param befehl Der zu verarbeitende Befehl.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean verarbeiteBefehl(Befehl befehl, Spiel spiel) 
    {
        boolean moechteBeenden;
        moechteBeenden = aktuellerRaum.fuehreBefehlAus(befehl, spiel);
        return moechteBeenden;  
    }




    //erste Rucksackmethoden, die müssen zentral sein 
    // Schaltteil einpacken, zu koppeln mit dem Quiz 
    public void packeSchaltteilInDenRucksack(String Schaltteil) {
       rucksackDesSpielers.packeSchaltteilEin(Schaltteil); 
    }
    
    // Rucksackinhalt ausgeben 
    public void gibRucksackinhaltAus() {
        rucksackDesSpielers.gibAusgaenge(); 
     }
    
    public String gibAktuellesSchaltteilAus(int Nummer) {
        return rucksackDesSpielers.gibAktuellesSchaltteile(Nummer); 
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
        }
    } 

    public static void main(String[] args){
        System.out.println("Du hast ein neues Spiel gestartet");
        
        Spiel spiel = new Spiel();
        spiel.spielen();


    }
}
