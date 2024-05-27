public class BegruessungsRaum extends Raum {
    
    public BegruessungsRaum(String beschreibung) {
        super(beschreibung);
    }

    
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
        System.out.println();
        System.out.println("Willkommen zum Spiel Elektrotechniker ohne Schaltplan");
        System.out.println("Du kommst wie gewohnt zur Uni ... Heute ist ET-Praktikum");
        System.out.println("Als du das Gebaeude betrittst fällt dir auf, das alles dunkel ist, wie merkwürdig ...");
        System.out.println("Herr Radt kommt dir entgegengelaufen und sagt: Eine Schaltung im BT7 ist durchgebrannt und im ganzen Gebaeude gibt es kein Strom."); 
        System.out.println("Er bittet dich darum diese Schaltung zu reparieren ... Du fuehlst dich ueberfordert, aber nimmst die Aufgabe an"); 
        System.out.println();
        
        System.out.println("Dafuer musst du zunaechst die Ersatzschaltteile aus verschiedenen Raeumen im Gebaeude besorgen.");
        System.out.println("Erkunde, das Gebaeude und finde heraus, in welchen Raeumen sich Schaltteile befinden"); 
        System.out.println("Tippe 'help', wenn du Hilfe brauchst, oder wissen willst, welche Befehle, du im aktuellen Raum ausfuehren kannst");
        
        System.out.println();
        System.out.println(this.gibLangeBeschreibung());
    }

    @Override 
    protected void zeigeBefehle() {
        super.zeigeBefehle();
        System.out.print(", welcome");
    }

}
