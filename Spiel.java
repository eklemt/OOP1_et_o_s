import java.io.DataInputStream;
import java.io.IOException;
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
    private String beschreibungDraussen = "vor dem Haupteingang der Universitaet"; 
    Schaltplan aktuellerSchaltplan; 
    Schaltplan loesungssSchaltplan; 

        
    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        raeumeAnlegen();
        schaltplanAnlegen();
        parser = new Parser();
    }

    private void schaltplanAnlegen() {
        
        Random random = new Random();
        int zufallszahl = random.nextInt(2);

        System.out.println("" + zufallszahl);
        

        if (zufallszahl == 0) {
          aktuellerSchaltplan = new Schaltplan("Reihenschaltung2Widerstaende"); 
          aktuellerSchaltplan.setzeSchaltteil(0, "Kabel");
          aktuellerSchaltplan.setzeSchaltteil(1, "Widerstand");
          aktuellerSchaltplan.setzeSchaltteil(2, "Kabel");
          aktuellerSchaltplan.setzeSchaltteil(3, "Widerstand");
          aktuellerSchaltplan.setzeSchaltteil(4, "Kabel");
        } else {
            aktuellerSchaltplan = new Schaltplan("DiodeVermessenOhneMessgeraetImPLan"); 
            aktuellerSchaltplan.setzeSchaltteil(0, "Kabel");
            aktuellerSchaltplan.setzeSchaltteil(1, "Widerstand");
            aktuellerSchaltplan.setzeSchaltteil(2, "Kabel");
            aktuellerSchaltplan.setzeSchaltteil(3, "Diode");
            aktuellerSchaltplan.setzeSchaltteil(4, "Kabel");
        }

    }

    /**
     * Erzeuge alle R�ume und verbinde ihre Ausg�nge miteinander.
     */
    private void raeumeAnlegen()
    {
        Raum draussen, hoersaal, cafeteria, labor, buero;
      
        // die R�ume erzeugen
        draussen = new Raum("vor dem Haupteingang der Universitaet");
        hoersaal = new Raum("in einem Vorlesungssaal");
        cafeteria = new Raum("in der Cafeteria der Uni");
        labor = new Raum("in einem Rechnerraum");
        buero = new Raum("im Verwaltungsbuero der Informatik");
        
        // die Ausg�nge initialisieren
        draussen.setzeAusgang("east", hoersaal);
        draussen.setzeAusgang("south", labor);
        draussen.setzeAusgang("west", cafeteria);

        hoersaal.setzeAusgang("west", draussen);

        cafeteria.setzeAusgang("east", draussen);

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
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("Hoffentlich hast du jetzt mehr Plan. Auf Wiedersehen.");
    }

    /**
     * Einen Begrueßungstext fuer den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen zu deinem ersten Job nach dem REE-Studium!");
        System.out.println("Du arbeitest in einem wichtigen Verteilerzentrum, fuer die erneuerbaren Energien in Schleswig Hosltein");
        System.out.println("An deinem ersten Tag ist eine wichtige Schaltung kaputt gegangen und nun haben viele Hauashalte keine Energie");
        System.out.println("Du hast die Aufgabe diese wieder zu reparieren"); 
        System.out.println();
        
        System.out.println("Dafuer musst du zunaechst die Ersatzschaltteile aus verschiedenen Raeumen im Gebaeude besorgen.");
        System.out.println("Die Raeume erinnern dich stark an dein Studium :-)"); 
        System.out.println("Tippe 'help', wenn du Hilfe brauchst.");
        
        System.out.println();
        System.out.println(aktuellerRaum.gibLangeBeschreibung());
    }

    /**
     * Verarbeite einen gegebenen Befehl (fuhre ihn aus).
     * @param befehl Der zu verarbeitende Befehl.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean verarbeiteBefehl(Befehl befehl) 
    {
        boolean moechteBeenden = false;

        if(befehl.istUnbekannt()) {
            System.out.println("Ich weiss nicht, was Sie meinen...");
            return false;
        }

        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("help")) {
            hilfstextAusgeben();
        }
        else if (befehlswort.equals("go")) {
            wechsleRaum(befehl);
        }
        else if (befehlswort.equals("welcome")) {
            willkommenstextAusgeben(); 
        }
        else if (befehlswort.equals("repair")) {
             if (aktuellerRaum.gibKurzbeschreibung().equals(beschreibungDraussen)) {
                // Schaltplan schaltplan = new Schaltplan(); 
                //schaltplan.test();
                aktuellerSchaltplan.gibAusgaenge();
                repariereSchaltung(); 
             }
             else {
                System.out.println("Du warst leider im falschen Raum. Repair lässt sich nur im Hauptraum ausführen");
                return moechteBeenden; 
                
             }

             //}
            // hier kommt die repair Methode rein 
            // hier soll auch noch nach dem richtigen Raum gesucht werden 
        }
        else if (befehlswort.equals("quit")) {
            moechteBeenden = beenden(befehl);
        }
        // ansonsten: Befehl nicht erkannt.
        return moechteBeenden;
    }

    // Implementierung der Benutzerbefehle:

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlsw�rter.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("Du stehst in einem Rauum und kannst folgende Befehle nutzen");
        System.out.println("Zur Fortbewegung gib einfach go south/east/west/north ein");
        System.out.println("Der Befehl Setze ist nur im Menu repair vorhanden");
        parser.zeigeBefehle();
    }


    private void repariereSchaltung() {
        System.out.println("Du kannst nun die Schaltung reparieren");
        loesungssSchaltplan = new Schaltplan("Loesung"); 
        int anzahlRichtigerAntworten = 0; 
        

        // setze Loesungsbauteile 
        for (int i = 0; i<aktuellerSchaltplan.anzahlElemente(); i++) {
           String aktuellesBauteil = "Widerstand"; 
           loesungssSchaltplan.setzeSchaltteil(i, aktuellesBauteil);
        }
        loesungssSchaltplan.gibAusgaenge();
        
        // Überprüfe aktuelle Loesung mit dem Schaltplan 
        for (int i = 0; i<aktuellerSchaltplan.anzahlElemente(); i++) {
            String aktuellesBauteil = aktuellerSchaltplan.gibAktuellesSchaltteile(i);  
            String aktuelleLoesung = loesungssSchaltplan.gibAktuellesSchaltteile(i);
            
            if (aktuelleLoesung == aktuellesBauteil) {
                System.out.println( "Das " + i + " Bauteile " + aktuelleLoesung + " ist richtig ");
                anzahlRichtigerAntworten++; 
            }
            else {
                System.out.println( "Das " + i + " Bauteile " + aktuelleLoesung + " ist falsch ");
            }
         }
         System.out.println("Anzahl richtiger Antworten: " + anzahlRichtigerAntworten);
 
    }

    /**
     * Versuche, in eine Richtung zu gehen. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    private void wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin moechten Sie gehen?");
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


    /**
     * "quit" wurde eingegeben. �berpr�fe den Rest des Befehls,
     * ob das Spiel wirklich beendet werden soll.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean beenden(Befehl befehl) 
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Was soll beendet werden?");
            return false;
        }
        else {
            return true;  // Das Spiel soll beendet werden.
        }
    }

    private void printState() {
        System.out.println("AktuRaum:'" + aktuellerRaum.gibLangeBeschreibung() +"'");
        System.out.println("Gesammelte GEgenstände:'" + "'");
        //schaltplan.gibState();
        
        
    }

    public static void main(String[] args){
        System.out.println("Yo!");
        
        Spiel spiel = new Spiel();
        spiel.spielen();


    }
}
