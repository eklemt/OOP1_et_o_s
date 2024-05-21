public class BegruessungsRaum extends Raum {
    
    public BegruessungsRaum(String beschreibung) {
        super(beschreibung);
    }

    
    @Override
    public boolean fuehreBefehlAus(Befehl befehl, Spiel spiel) {
        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("welcome")) {
            willkommenstextAusgeben(); 
            return false; 
        }
        else {
            return super.fuehreBefehlAus(befehl, spiel);
        }

    }

     /**
     * Einen Begrueßungstext fuer den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen zu deinem ersten Job nach dem REE-Studium!");
        System.out.println("Du arbeitest in einem wichtigen Verteilerzentrum, fuer die erneuerbaren Energien in Schleswig Hosltein");
        System.out.println("An deinem ersten Tag ist eine wichtige Schaltung kaputt gegangen und nun haben viele Hauashalte keine Energie");
        System.out.println("Du hast die Aufgabe diese wieder zu reparieren"); 
        System.out.println();
        
        System.out.println("Dafuer musst du zunaechst die Ersatzschaltteile aus verschiedenen Raeumen im Gebaeude besorgen.");
        System.out.println("Die Raeume erinnern dich stark an dein Studium :-)"); 
        System.out.println("Tippe 'help', wenn du Hilfe brauchst.");
        
        System.out.println();
        System.out.println(this.gibLangeBeschreibung());
    }

    @Override 
    protected void zeigeBefehle() {
        super.zeigeBefehle();
        System.out.println("welcome");
    }

}
