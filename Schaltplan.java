
import java.util.ArrayList;

/**
 * Diese Klasse modelliert einen Schaltplan in der Welt von Elektrotechniker ohne Schaltplan.
 * 
 *  Ein Schaltplan ist dabei eine ArrayList von verschiedenen Strings, die die einzelnen Schaltteile darstellen
 *  Der Schaltplan kann sich zudem mit einem anderen Schaltplan vergleichen, um herauszufinden, ob diese übereinstimmen
 * 
 * @author  Emily Klemt, Carolin Altstaedt 
 * @version 27.05.2024
 */


public class Schaltplan {
    private ArrayList<String> schaltplan;
    private String beschreibung; 
    int anzahlSchaltteile; // Anzahld der Schaltteile 
    
   /**
     * Konstruktor, der ein Schaltplanelement ohne Teile erstellt
     * @param beschreibung String der beschreibt, um was für eine Schaltung es sich handelt
     * @param anzahlTeile integer, der die Anzahl der Schaltteile in der Schaltung enthält
     * 
     */
    public Schaltplan(String beschreibung, int anzahlTeile) 
    {
        this.beschreibung = beschreibung;
        anzahlSchaltteile = anzahlTeile; 
        schaltplan = new ArrayList<String>();
    }

    /**
     * Fuege ein Schaltteil zum Schaltplan hinzu
     * @param Name, String der das aktuelle Schaltteil ist
     * 
     */
    public void setzeSchaltteil(String Name) 
    {
        schaltplan.add(Name); 
    }

     /**
     * Funktion, um die Anzahl der Schaltteile zurueckzugeben
     * @return integer, Anzahl wie viele Schaltteile der Plan hat
     */
    public int anzahlElemente() {
        int anzahl = schaltplan.size(); 
        return anzahl; 
    }
    

    /**
     * Funktion, um alle Schaltteile des Plans auszugeben 
      */
    public void gibAusgaenge()
    {
       for (int i = 0; i<schaltplan.size(); i++) {
         System.out.print("  -  " + schaltplan.get(i));
       }

    }

    /**
     * Funktion, um alle Schaltteile des Plans  zu loeschen
     */
    public void loescheSchaltteile()
    {
       this.schaltplan.removeAll(schaltplan); 
    }

    /**
     * Funktion, um den Schaltplan als ArrayList zurueckzugeben
     * @return ArrayList vom Typ String des Schaltplans
     */
    public ArrayList<String> gibDieSchallteile () {
        return schaltplan; 
    }

     /**
     * Funktion, um die Beschreibung des Plans als String zurueckzugeben
     * @return String, Beschreibung des Schaltplans
     */
    public String gibBeschreibungString () {
        return beschreibung; 
    }

    /**
     * Funktion, um ein bestimmtes Schaltteil an einer Stelle des Schaltplans zurueckzugeben
     * @param Nummer, Stelle als Integer, von der das Schaltteil zurueckgegeben werden soll
     * @return String des Schaltteils an der gewuenschten Stelle
     */
    public String gibAktuellesSchaltteile(int Nummer) {
       String schaltteil; 
       
       schaltteil = schaltplan.get(Nummer); 

       return schaltteil; 
    }

   /**
    * Funtkion um den aktuellen Schaltplan mit einem anderen Schaltplan zu vergleichen
    * @param andererSchaltplan schaltplan, der das Vergleichsobjekt ist 
    * @return boolean, ob der Schaltplan identisch ist oder nicht 
    */
   public boolean vergleicheMitAnderemPlan (Schaltplan andererSchaltplan) {
        int anzahlRichtigerAntworten = 0;
        for (int i = 0; i<schaltplan.size(); i++) {
            String aktuellesBauteil = schaltplan.get(i);  
            String aktuelleLoesung = andererSchaltplan.gibAktuellesSchaltteile(i);
            
            if (aktuelleLoesung.equals(aktuellesBauteil)) {
                System.out.println( "Das " + i + " Bauteile " + aktuelleLoesung + " ist richtig ");
                anzahlRichtigerAntworten++; 
            }
            else {
                System.out.println( "Das " + i + " Bauteile " + aktuelleLoesung + " ist falsch ");
            }
         }
         System.out.println("Anzahl richtiger Antworten: " + anzahlRichtigerAntworten);

            if (anzahlRichtigerAntworten == schaltplan.size()) {
                return true; 
            } else {
                return false; 
            }
   }
   
}
