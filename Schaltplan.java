
import java.util.ArrayList;


public class Schaltplan {
    private ArrayList<String> schaltplan;
    private String beschreibung; 
    int anzahlSchaltteile; 

    public void test() 
    {
        System.out.println("Hallo, du bist im SchaltplanMenu");
    }
   
    public Schaltplan(String beschreibung, int AnzahlTeile) 
    {
        this.beschreibung = beschreibung;
        anzahlSchaltteile = AnzahlTeile; 
        System.out.println("" + anzahlSchaltteile);
        schaltplan = new ArrayList<String>();
    }

    /**
     * Definiere einen Ausgang f�r diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der �ber diesen Ausgang erreicht wird
     */
    public void setzeSchaltteil(String Name) 
    {
        schaltplan.add(Name); 
    }

    public int wieVieleTeileHatDerPlan() 
    { 
        System.out.println("" + anzahlSchaltteile);
        return anzahlSchaltteile; 
    }

    public void gibAusgaenge()
    {
       for (int i = 0; i<schaltplan.size(); i++) {
         System.out.print("  -  " + schaltplan.get(i));
       }

    }

    public void loescheSchaltteile()
    {
       this.schaltplan.removeAll(schaltplan); 
    }

    public ArrayList<String> gibDieSchallteile () {
        return schaltplan; 
    }

    public String gibBeschreibungString () {
        return beschreibung; 
    }

    public String gibAktuellesSchaltteile(int Nummer) {
       String schaltteil; 
       
       schaltteil = schaltplan.get(Nummer); 

       return schaltteil; 
    }

   public int anzahlElemente() {
       int anzahl = schaltplan.size(); 
       return anzahl; 
   }

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
