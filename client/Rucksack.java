package client;

import java.util.ArrayList;

/**
 * Diese Klasse modelliert einen client.Rucksack in der Welt von Elektrotechniker ohne client.Schaltplan.
 * 
 *  Ein client.Rucksack ist dabei eine ArrayList von verschiedenen Strings, die die einzelnen Schaltteile darstellen
 *  Sobald der Spieler ein Schaltteil einsammelt, soll dies seinem client.Rucksack hinzugefuegt werden
 * 
 * @author  Emily Klemt, Carolin Altstaedt 
 * @version 27.05.2024
 */


public class Rucksack {
    private ArrayList<String> rucksack;
    private int anzahlElemente; 
    
   /**
     * Konstruktor, der einen leeren client.Rucksack erstellt
     * @param nummerBauteile Integer, wie viele Teile sollen in dem client.Rucksack gepseichert werden können
     * 
     */
    public Rucksack(int nummerBauteile) 
    {
        anzahlElemente = nummerBauteile; 
        rucksack = new ArrayList<String>(anzahlElemente);
    }

    /**
     * Überprüft, ob der Spieler schon alle benoetigten Schaltteile eingesammelt hat 
     * @return, ob schon alle benoetigten Schaltteile im client.Rucksack sind
     */
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
     * Fuege ein Schaltteil zum client.Rucksack hinz
     * @param Name Name des Schaltteils 
     */
    public void packeSchaltteilEin(String Name) 
    {
        if (rucksack.size() <= (anzahlElemente-1)) {
        rucksack.add(Name);
        }
        else {
            System.out.println("Das aktuelle Schaltteil konnte nicht mehr eingepackt werden. Dein client.Rucksack ist bereits voll");
        }
    }

    /**
     * Methode, um ein Schaltteil aus dem client.Rucksack zu entfernen
     * @param Name Name des Schaltteils, das aus dem client.Rucksack entfernt werden soll
     */
    public void entferneSchaltteil(String Name) {
        rucksack.remove(Name); 
    }
      
    /**
    * Funktion, um den Inhalt des Rucksacks in der Konsole auszugeben
    */
    public void rucksackinhaltInKonsole()
    {
        System.out.println("----------------------------------");
        System.out.println("In deinem client.Rucksack befindet sich:");
        for (int i = 0; i<rucksack.size(); i++) {
            System.out.print("  " + rucksack.get(i));
            System.out.println("");
        }

        System.out.println("Du hast damit " + (rucksack.size()) + " von " + anzahlElemente + " Schaltteilen gesammelt."); 
        System.out.println("-----------------------------------");
    }
    
    /**
     * Funktion, um ein bestimmtes Schaltteil an einer Stelle des Rucksacks zurueckzugeben
     * @param Nummer, Stelle als Integer, von der das Schaltteil zurueckgegeben werden soll
     * @return String des Schaltteils an der gewuenschten Stelle
     */
    public String gibAktuellesSchaltteile(int Nummer) {
       String schaltteil; 
       
       schaltteil = rucksack.get(Nummer); 

       return schaltteil; 
    }


    /**
     * Funktion, um den client.Rucksack als ArrayList zurueckzugeben
     * @return ArrayList vom Typ String des Schaltplans
     */
    public ArrayList<String> gibDieRucksackArrayList () {
        return rucksack; 
    }


   
   
}
