
import java.util.HashMap;


public class Schaltplan {
    private HashMap<Integer, String> schaltplan;
    private String beschreibung; 
    private HashMap<Integer, String> aktuelleSpielerloesung; 
    private Parser parser;


    public void test() 
    {
        System.out.println("Hallo, du bist im SchaltplanMenu");
    }
   
    public Schaltplan(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        schaltplan = new HashMap<Integer, String>();
    }

    /**
     * Definiere einen Ausgang f�r diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der �ber diesen Ausgang erreicht wird
     */
    public void setzeSchaltteil(Integer Nummer, String Name) 
    {
        schaltplan.put(Nummer, Name);
    }


    public void gibAusgaenge()
    {
        System.out.println("Hash Map" + schaltplan);
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
   
}
