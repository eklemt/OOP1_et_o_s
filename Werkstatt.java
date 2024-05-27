import java.util.ArrayList;
import java.util.Scanner;

public class Werkstatt extends Raum {
    
    private final Schaltplan aktuellerSchaltplan;
    Schaltplan spielerLoesung; 

    public Werkstatt(String beschreibung, Schaltplan aktuellerSchaltplan ) {
        super(beschreibung);
        this.aktuellerSchaltplan = aktuellerSchaltplan;
        this.spielerLoesung = new Schaltplan("Loesung", aktuellerSchaltplan.anzahlSchaltteile);
    }


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
            System.out.println("... Du schaltest Strom auf deine Schaltung und guckst, ob sie funktioniert...");
            boolean loesungGefunden = repariereSchaltung();
            spiel.spielerhatgewonnen();
            return loesungGefunden; 
        }
        else if (befehlswort.equals("remove")) {
            spielerLoesung.loescheSchaltteile();
            return false; 
        }
        else {
            return super.fuehreBefehlAus(befehl, spiel, rucksack);
        }

    }  

    private void baueSpielerloesung(Rucksack rucksack) {
        String aktuellesTeil; 
        ArrayList<String> nichtVerteilteSchaltteile = (ArrayList<String>) rucksack.gibDieRucksackArrayList().clone(); 
        System.out.println("------------------------------");
        System.out.println("Du hast nun die Moeglichkeit die Schaltung zu reparieren");
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

    private boolean hatDerSpielerDasSchaltteil (String schaltteil, ArrayList<String> rucksackinhalt) {
        for (String aktuellesRucksackteil : rucksackinhalt) {
            if (aktuellesRucksackteil.equals(schaltteil)) {
              return true; 
            }
        }
        return false; 
    }

    private boolean repariereSchaltung() {
        System.out.println("Du kannst nun die Schaltung reparieren"); 
    
        spielerLoesung.gibAusgaenge();
        aktuellerSchaltplan.gibAusgaenge();

        boolean richtigerPlan = aktuellerSchaltplan.vergleicheMitAnderemPlan(spielerLoesung); 
        return richtigerPlan; 
        
    }

    @Override 
    protected void zeigeBefehle() {
        super.zeigeBefehle();
        System.out.println(", repair, anschalten");
    }
 
}
