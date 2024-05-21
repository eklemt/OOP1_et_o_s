
import java.util.ArrayList;


public class Schaltplan {
    private ArrayList<String> schaltplan;
    private String beschreibung; 

    public void test() 
    {
        System.out.println("Hallo, du bist im SchaltplanMenu");
    }
   
    public Schaltplan(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        schaltplan = new ArrayList();
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

    public void gibAusgaenge()
    {
       for (int i = 0; i<schaltplan.size(); i++) {
         System.out.println("" + schaltplan.get(i));
       }

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
