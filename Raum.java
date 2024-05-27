import java.util.Set;
import java.util.HashMap;

/**
 * Diese Klasse modelliert Raeume in der Welt von Elektrotechniker ohne Schaltplan.
 * 
 * Ein "Raum" repraesentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Raeumen ueber Ausgaenge verbunden.
 * Fuer jeden existierenden Ausgang haelt ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * 
 * @author  Emily Klemt, Carolin Altstaedt auf Basis von Michael Koelling und David J. Barnes
 * @version 27.05.2024
 */

class Raum 
{
    private String beschreibung;
    private HashMap<String, Raum> ausgaenge;        // die Ausgaenge dieses Raums

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausgaenge.
     * @param beschreibung enthaelt eine Beschreibung in der Form
     *        "in einer Kueche" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap<String, Raum>();
    }

    /**
     * Definiere einen Ausgang fuer diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der ueber diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * @return die kurze Beschreibung dieses Raums (die dem Konstruktor
     * uebergeben wurde).
     */
    public String gibKurzbeschreibung()
    {
        return beschreibung;
    }

    /**
     * Liefere eine lange Beschreibung dieses Raums, in der Form:
     *     Sie sind in der Kueche.
     *     Ausgaenge: nord west
     * @return eine lange Beschreibung dieses Raumes.
     */
    public String gibLangeBeschreibung()
    {
        return "Sie sind in folgendem Raum: " + beschreibung + ".\n" + gibAusgaengeAlsString();
    }

    /**
     * Liefere eine Beschreibung der Ausgaenge dieses Raumes,
     * beispielsweise
     * "Ausgaenge: north west".
     * @return eine Beschreibung der Ausgaenge dieses Raumes.
     */
    private String gibAusgaengeAlsString()
    {
        String ergebnis = "Ausgaenge:";
        Set<String> keys = ausgaenge.keySet();
        for(String ausgang : keys)
            ergebnis += " " + ausgang;
        return ergebnis;
    }

   

    /**
     * Liefere den Raum, den wir erreichen, wenn wir aus diesem Raum
     * in die angegebene Richtung gehen. Liefere 'null', wenn in
     * dieser Richtung kein Ausgang ist.
     * @param richtung die Richtung, in die gegangen werden soll.
     * @return den Raum in der angegebenen Richtung.
     */
    public Raum gibAusgang(String richtung) 
    {
        return ausgaenge.get(richtung);
    }

   /**
    * Funktion, die die jeweiligen Befehle vom Raum ausfuehrt, je nachdem welcher Befehl eingegebeben wurde
    * @param befehl, aktueller Befehl vom Parser
    * @param spiel, um auf das Spiel zurückgreifen zu koennen
    * @param rucksack um auf die Inhalte des Rucksacks zurückgreifen zu koennen
    * @return , ob der Spieler das Spiel beenden moechte
    */

    public boolean fuehreBefehlAus(Befehl befehl, Spiel spiel, Rucksack rucksack) {
        boolean moechteBeenden = false;
        String befehlswort = befehl.gibBefehlswort();
        // Hilfstext ausgeben
        if (befehlswort.equals("help")) {
            hilfstextAusgeben();
            System.out.println("");
        }
        // Raum wechseln 
        else if (befehlswort.equals("go")) {
            spiel.wechsleRaum(befehl);
        }
        // Spiel verlassen
        else if (befehlswort.equals("quit")) {
            moechteBeenden = beenden(befehl);
        }
        // Rucksackinhalt ausgeben
        else if (befehlswort.equals("ausgeben")) {
            rucksack.rucksackinhaltInKonsole();
        }
        else {
            System.out.println("Ich weiss nicht, was Sie meinen...");
        }
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
        this.zeigeBefehle();
        // Neues Zeige-Befehle muss her 
    }

    

    /**
     * "quit" wurde eingegeben. Ueberpruefe den Rest des Befehls,
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
    
    /**
     * Gebe, die Befehle aus, die im aktuellen Raum moeglich sind
     */
    protected void zeigeBefehle() {
        System.out.print("Aktuell moegliche Befehle: go, help, quit, ausgeben");
    }

}

