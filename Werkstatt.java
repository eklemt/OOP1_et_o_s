public class Werkstatt extends Raum {
    
    private final Schaltplan aktuellerSchaltplan;
    Schaltplan spielerLoesung; 

    public Werkstatt(String beschreibung, Schaltplan aktuellerSchaltplan) {
        super(beschreibung);
        this.aktuellerSchaltplan = aktuellerSchaltplan;
    }


    @Override
    public boolean fuehreBefehlAus(Befehl befehl, Spiel spiel) {
        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("repair")) {
            System.out.println("... Deine Schaltung wird repariert ...");
            repariereSchaltung();
            return false; 
        }
        else if (befehlswort.equals("loesungbauen")) {
            System.out.println("... Deine Schaltung wird repariert ...");
            baueLoesungssSchaltplan(befehl);
            return false; 
        }
        else {
            return super.fuehreBefehlAus(befehl, spiel);
        }

    }

    private void baueLoesungssSchaltplan(Befehl befehl) {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Welches Schaltteil möchtest du einpacken?");
            return;
        }

        String Schaltteil = befehl.gibZweitesWort();

        spielerLoesung.setzeSchaltteil(Schaltteil);
    }

    private void repariereSchaltung() {
        // Loesungsschaltplan muss noch hierher verlegt werden 
        System.out.println("Du kannst nun die Schaltung reparieren");
        spielerLoesung = new Schaltplan("Loesung"); 
        int anzahlRichtigerAntworten = 0; 
        
       /* 
        for (int i = 0; i<aktuellerSchaltplan.anzahlElemente(); i++) {
           String aktuellesBauteil = spiel.gibAktuellesSchaltteilAus(i); 
           spielerLoesung.setzeSchaltteil(aktuellesBauteil);
        }
        */
        spielerLoesung.gibAusgaenge();
        aktuellerSchaltplan.gibAusgaenge();

        aktuellerSchaltplan.vergleicheMitAnderemPlan(spielerLoesung); 

        // Überprüfe aktuelle Loesung mit dem Schaltplan 
        for (int i = 0; i<aktuellerSchaltplan.anzahlElemente(); i++) {
            String aktuellesBauteil = aktuellerSchaltplan.gibAktuellesSchaltteile(i);  
            String aktuelleLoesung = spielerLoesung.gibAktuellesSchaltteile(i);
            
            if (aktuelleLoesung.equals(aktuellesBauteil)) {
                System.out.println( "Das " + i + " Bauteile " + aktuelleLoesung + " ist richtig ");
                anzahlRichtigerAntworten++; 
            }
            else {
                System.out.println( "Das " + i + " Bauteile " + aktuelleLoesung + " ist falsch ");
            }
         }


         System.out.println("Anzahl richtiger Antworten: " + anzahlRichtigerAntworten);

    }

    @Override 
    protected void zeigeBefehle() {
        super.zeigeBefehle();
        System.out.println("");
    }
 
}
