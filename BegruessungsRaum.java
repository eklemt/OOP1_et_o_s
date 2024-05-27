/**
 * Diese Klasse modelliert Begruessungsraeume in der Welt von Elektrotechniker ohne Schaltplan.
 * 
 * Ein "Raum" repraesentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Raeumen ueber Ausgaenge verbunden.
 * Fuer jeden existierenden Ausgang haelt ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * Ein Begruessungsraum erbt von der Klasse Raum und hat als zusätzliche Funktion, die Moeglichkeit einen Begrueßungstext auszugeben
 * 
 * @author  Emily Klemt, Carolin Altstaedt auf Basis von Michael Koelling und David J. Barnes
 * @version 27.05.2024
 */


public class BegruessungsRaum extends Raum {
    
    /**
     * Konstruktor für einen Begrueßungsraum, der nur den Konstruktor, der Superklasse aufruft
     * @param beschreibung
     */
    public BegruessungsRaum(String beschreibung) {
        super(beschreibung);
    }
    /**
     * Erweitert die Befehle, die verarbeitet koennen von der Superklasse um die Moeglichkeit des Befehls welcome
     * Wenn dieser nicht eingegeben wird, dann wird dieselbe Funktion der Superklasse aufgerufen
     * @param spiel
     * @param rucksack
     * @param befehl
     * @return, ob der Spieler das Spiel beenden moechte 
     */
    
    @Override
    public boolean fuehreBefehlAus(Befehl befehl, Spiel spiel, Rucksack rucksack) {
        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("welcome")) {
            willkommenstextAusgeben(); 
            return false; 
        }
        else {
            return super.fuehreBefehlAus(befehl, spiel, rucksack);
        }

    }

     /**
     * Einen Begrueßungstext fuer den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        System.out.println("Willkommen zum Spiel Elektrotechniker ohne Schaltplan");
        System.out.println("Du kommst wie gewohnt zur Uni ... Heute ist ET-Praktikum");
        System.out.println("Als du das Gebaeude betrittst fällt dir auf, das alles dunkel ist, wie merkwürdig ...");
        System.out.println("Herr Radt kommt dir entgegengelaufen und sagt:"); 
        System.out.println("Eine Schaltung im BT7 ist durchgebrannt und im ganzen Gebaeude gibt es kein Strom."); 
        System.out.println("Er bittet dich darum diese Schaltung zu reparieren ... Du fuehlst dich ueberfordert, aber nimmst die Aufgabe an"); 
        System.out.println();
        
        System.out.println("Dafuer musst du zunaechst die Ersatzschaltteile aus verschiedenen Raeumen im Gebaeude besorgen.");
        System.out.println("Erkunde, das Gebaeude und finde heraus, in welchen Raeumen sich Schaltteile befinden"); 
        System.out.println("Tippe 'help', wenn du Hilfe brauchst, oder wissen willst, welche Befehle, du im aktuellen Raum ausfuehren kannst");
        System.out.println("Sobald du alle Schaltteile gesammelt hast, kriegst du eine Nachricht, wie es weitergeht.");
        System.out.println("Fur das Spiel hast du 5 Leben, passe also auf, was du antwortest");
        System.out.println();
        System.out.println("-------------------------------------------------------------");
     
        System.out.println(this.gibLangeBeschreibung());
    }

    /** 
     * Gib die Befehle für den aktuellen Raum aus 
     */

    @Override 
    protected void zeigeBefehle() {
        super.zeigeBefehle();
        System.out.print(", welcome");
    }

}
