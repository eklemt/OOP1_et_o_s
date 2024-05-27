import java.util.Set;
import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse modelliert R�ume in der Welt von Zuul.
 * 
 * Ein "Raum" repr�sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen R�umen �ber Ausg�nge verbunden.
 * F�r jeden existierenden Ausgang h�lt ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * 
 * @author  Michael K�lling und David J. Barnes
 * @version 31.07.2011
 */

class Raum 
{
    private String beschreibung;
    private HashMap<String, Raum> ausgaenge;        // die Ausg�nge dieses Raums

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausg�nge.
     * @param beschreibung enth�lt eine Beschreibung in der Form
     *        "in einer K�che" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap<String, Raum>();
    }

    /**
     * Definiere einen Ausgang f�r diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der �ber diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * @return die kurze Beschreibung dieses Raums (die dem Konstruktor
     * �bergeben wurde).
     */
    public String gibKurzbeschreibung()
    {
        return beschreibung;
    }

    /**
     * Liefere eine lange Beschreibung dieses Raums, in der Form:
     *     Sie sind in der K�che.
     *     Ausg�nge: nord west
     * @return eine lange Beschreibung dieses Raumes.
     */
    public String gibLangeBeschreibung()
    {
        return "Sie sind in folgendem Raum: " + beschreibung + ".\n" + gibAusgaengeAlsString();
    }

    /**
     * Liefere eine Beschreibung der Ausg�nge dieses Raumes,
     * beispielsweise
     * "Ausg�nge: north west".
     * @return eine Beschreibung der Ausg�nge dieses Raumes.
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

   

    public boolean fuehreBefehlAus(Befehl befehl, Spiel spiel, Rucksack rucksack) {
        boolean moechteBeenden = false;
        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("help")) {
            hilfstextAusgeben();
            System.out.println("");
        }
        else if (befehlswort.equals("go")) {
            spiel.wechsleRaum(befehl);
        }
        else if (befehlswort.equals("quit")) {
            moechteBeenden = beenden(befehl);
        }
        else if (befehlswort.equals("ausgeben")) {
            spiel.gibRucksackinhaltAus();
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

    protected void zeigeBefehle() {
        System.out.print("go, help, quit");
    }

}

