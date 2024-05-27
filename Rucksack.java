
import java.util.ArrayList;


public class Rucksack {
    private ArrayList<String> rucksack;
    private int anzahlElemente; 
    // man koennte hier über eine ArrayList nachdenken 
    
   
    public Rucksack(int nummerBauteile) 
    {
        anzahlElemente = nummerBauteile; 
        rucksack = new ArrayList(anzahlElemente);
    }

    public void test() 
    {
        System.out.println("Hallo, du bist im Rucksackmenü");
    }

    public boolean alleTeileEingesammelt() 
    {
       int übrigeTeile;
       übrigeTeile = (anzahlElemente - rucksack.size()); 
       if (übrigeTeile == 0) {
          return true; 
       }
       else {
          return false; 
       }

    }


    /**
     * Definiere einen Ausgang f�r diesen Raum.
     * 
     * @param Name der Raum, der �ber diesen Ausgang erreicht wird
     */
    public void packeSchaltteilEin(String Name) 
    {
        if (rucksack.size() <= (anzahlElemente-1)) {
        rucksack.add(Name);
        }
        else {
            System.out.println("Das aktuelle Schaltteil konnte nicht mehr eingepackt werden. Dein Rucksack ist bereits voll");
        }
    }

    /**
     * Methode, um ein Schaltteil aus dem Rucksack zu entfernen
     * @param Name die Richtung, in der der Ausgang liegen sol
     */
    public void entferneSchaltteil(String Name) {
        rucksack.remove(Name); 
    }
      

    public void rucksackinhaltInKonsole()
    {
        System.out.println("----------------------------------");
        System.out.println("In deinem Rucksack befindet sich:");
        for (int i = 0; i<rucksack.size(); i++) {
            System.out.print("  " + rucksack.get(i));
            System.out.println("");
        }

        System.out.println("Du hast damit" + (rucksack.size()) + "von" + anzahlElemente + "Schaltteilen gesammelt."); 
        System.out.println("-----------------------------------");
    }

    public String gibAktuellesSchaltteile(int Nummer) {
       String schaltteil; 
       
       schaltteil = rucksack.get(Nummer); 

       return schaltteil; 
    }

    public ArrayList<String> gibDieRucksackArrayList () {
        return rucksack; 
    }


   
   
}
