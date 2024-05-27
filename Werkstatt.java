import java.util.ArrayList;
import java.util.Scanner;

/**
 * Diese Klasse modelliert eine Werkstatt in der Welt von Elektrotechniker ohne Schaltplan.
 * 
 * Ein "Raum" repraesentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Raeumen ueber Ausgaenge verbunden.
 * Fuer jeden existierenden Ausgang haelt ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * Eine Werkstatt erbt von der Klasse Raum und in ihr kann der Spieler seine eingesammelten Schaltteile zusammensetzen 
 * und seine Schaltung damit reparieren 
 * 
 * @author  Emily Klemt, Carolin Altstaedt auf Basis von Michael Koelling und David J. Barnes
 * @version 27.05.2024
 */



public class Werkstatt extends Raum {
    
    private final Schaltplan aktuellerSchaltplan; // Schaltplan, der dem Loesungsschaltplan entspricht
    Schaltplan spielerLoesung; // Schaltplan, der den vom Spieler gebauten Loesungsvorschlag enthaelt

    /**
     * Konstruktor, um einen Werkstattraum zu erstellen 
     * @param beschreibung Beschreibung des Raums als String 
     * @param aktuellerSchaltplan Loesungsschaltplan als Schaltplan 
     */
    public Werkstatt(String beschreibung, Schaltplan aktuellerSchaltplan ) {
        super(beschreibung);
        this.aktuellerSchaltplan = aktuellerSchaltplan;
        this.spielerLoesung = new Schaltplan("Loesung", aktuellerSchaltplan.anzahlSchaltteile);
    }

    /**
    * Funktion, die die jeweiligen Befehle vom Raum ausfuehrt, je nachdem welcher Befehl eingegebeben wurde
    * für die Werkstatt gibt es dabei die Zusatzbefehle repair, anschalten und remove 
    * @param befehl, aktueller Befehl vom Parser
    * @param spiel, um auf das Spiel zurückgreifen zu koennen
    * @param rucksack um auf die Inhalte des Rucksacks zurückgreifen zu koennen
    * @return , ob der Spieler das Spiel beenden moechte
    */
    @Override
    public boolean fuehreBefehlAus(Befehl befehl, Spiel spiel, Rucksack rucksack) {
        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("repair")) {
            if (spielerLoesung.anzahlSchaltteile == 0) {
                System.out.println("... Deine Schaltung wird repariert ...");
                baueSpielerloesung(rucksack);
            }
            else {
                System.out.println("Du hast bereits eine Loesung erstellt, wenn du eine neue erstellen moechtest, gib Remove ein");
            }
            return false; 
        }
        else if (befehlswort.equals("anschalten")) {
            if (spielerLoesung.anzahlElemente() == aktuellerSchaltplan.anzahlElemente()) {
            System.out.println("... Du schaltest Strom auf deine Schaltung und guckst, ob sie funktioniert...");
            boolean loesungGefunden = repariereSchaltung();
            if (loesungGefunden) {
                spiel.spielerhatgewonnen();
            } 
            else {
                spiel.verliereEinLeben();
                System.out.println("Das war leider noch nicht die richtige Loesung. Wenn du noch Leben hast, verwende remove und versuche es erneut");
            }
            return loesungGefunden; 
            }
            else {
                System.out.println("Deine Loesung scheint noch nicht vollständig zu sein, druecke remove und baue eine neue Loesung");
                return false; 
            }
            
        }
        else if (befehlswort.equals("remove")) {
            spielerLoesung.loescheSchaltteile();
            return false; 
        }
        else {
            return super.fuehreBefehlAus(befehl, spiel, rucksack);
        }

    }  

    /**
     * Funktion, in der der Spieler einen Loesungsschaltplan zusammenbauen kann
     * @param rucksack rucksack, der die gesammelten Schaltteile des Spieler enthält 
     */

    private void baueSpielerloesung(Rucksack rucksack) {
        String aktuellesTeil; 
        ArrayList<String> nichtVerteilteSchaltteile = (ArrayList<String>) rucksack.gibDieRucksackArrayList().clone(); 
        System.out.println("------------------------------");
        System.out.println("Du hast nun die Moeglichkeit die Schaltung zu reparieren");
        System.out.println("Wenn du abbreichen moechtest, gib quit ein.");
        System.out.println("Es handelt sich um folgende Schaltung: " +aktuellerSchaltplan.gibBeschreibungString());
        System.out.println("------------------------------");
        for (int i = 0; i < aktuellerSchaltplan.anzahlSchaltteile ; i++) {
            System.out.print("Du hast noch folgede Schaltteile zur Verfügung:");
            for (String rucksackinhaltString : nichtVerteilteSchaltteile) {
                System.out.print("  -  " + rucksackinhaltString);
            }
            System.out.println("");
            System.out.print("Dein Schaltplan sieht bisher so aus: Quelle");
            spielerLoesung.gibAusgaenge();
            System.out.print("  -   Quelle");
            System.out.println("");
            System.out.println("Setze nun das " + i + "Schaltteil:");
            aktuellesTeil = holeEinSchaltteilDerSpielerloesung(nichtVerteilteSchaltteile); 
            if (aktuellesTeil.equals("quit")) {
                 System.out.println("------------------------------");
                return; 
            }
            else {
            spielerLoesung.setzeSchaltteil(aktuellesTeil);
            nichtVerteilteSchaltteile.remove(aktuellesTeil); 
            }
        }
         System.out.println("------------------------------");
    }
    /**
     * Funktion ,die ueber einen Scanner ein Schaltteil einliest, welches der Spieler einbauen moechte 
     * @param nichtVerteilteSchaltteile ArrayList, mit allen Schaltteil, die der Spieler noch nicht eingebaut hat 
     * @return String, mit dem vom Spieler eingegebenen Schaltteil oder quit um das Menu zu verlassen 
     */
    private String holeEinSchaltteilDerSpielerloesung(ArrayList<String> nichtVerteilteSchaltteile) {
       Scanner scanner = new Scanner(System.in);
       String antwort = null;
       boolean falscheAntwort = true; 
        while (falscheAntwort) {
            antwort = scanner.nextLine(); 
            if (antwort.equalsIgnoreCase("quit")) {
              falscheAntwort = false; 
            } else {
            boolean istSpielerImBesitz = hatDerSpielerDasSchaltteil(antwort, nichtVerteilteSchaltteile); 
            if (istSpielerImBesitz) {
                falscheAntwort = false;  
            }
            else {
                System.out.println("Das war keine gueltige Antwort. Wenn du das Menu verlassen willst, gib quit ein.");
            }
            }
        }
        return antwort; 
    }

   /**
    * FUnktion, die ueberprueft, ob der SPieler dieses Schaltteil auch wirklich im Rucksack hat
    * @param schaltteil String, der dem Schaltteil entspricht, welches der Spieler einbauen moechte
    * @param rucksackinhalt ArrayList, die alle noch uebrigen Schaltteile enthaelt 
    */
    private boolean hatDerSpielerDasSchaltteil (String schaltteil, ArrayList<String> rucksackinhalt) {
        for (String aktuellesRucksackteil : rucksackinhalt) {
            if (aktuellesRucksackteil.equals(schaltteil)) {
              return true; 
            }
        }
        return false; 
    }

    /**
     * Funktion, um zu ueberprüfen, ob der eingegebene Schaltplan, dem loesungsschaltplan entspricht
     * @return boolean, ob dies der Fall ist oder nicht 
     */

    private boolean repariereSchaltung() {
        System.out.println("Du kannst nun die Schaltung reparieren"); 
    
        spielerLoesung.gibAusgaenge();
        aktuellerSchaltplan.gibAusgaenge();

        boolean richtigerPlan = aktuellerSchaltplan.vergleicheMitAnderemPlan(spielerLoesung); 
        return richtigerPlan; 
        
    }

    /**
     * Gebe alle im aktuellen Raum moeglichen Befehle aus 
     */
    @Override 
    protected void zeigeBefehle() {
        super.zeigeBefehle();
        System.out.println(", repair, anschalten");
    }
 
}
