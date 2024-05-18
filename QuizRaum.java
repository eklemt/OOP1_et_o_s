import java.util.HashMap;

public class QuizRaum extends Raum {
    public QuizRaum(String beschreibung) {
        super(beschreibung);
    }

    @Override
    public boolean fuehreBefehlAus(Befehl befehl, Spiel spiel) {
        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("nehme")) {
            System.out.println("Hallo, du bist im Einpackmenu");
            aufnehmenSchaltteil(befehl, spiel);
            return false;
        }
        else {
            return super.fuehreBefehlAus(befehl, spiel);
        }

    }
    
    // muss noch angepasst werden, damit wir dann auch das mit dem Quiz koppeln 
    public void aufnehmenSchaltteil(Befehl befehl, Spiel spiel) 
    {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Welches Schaltteil möchtest du einpacken?");
            return;
        }

        String Schaltteil = befehl.gibZweitesWort();

        spiel.packeSchaltteilInDenRucksack(Schaltteil);
    }
    
    @Override 
    protected void zeigeBefehle() {
        super.zeigeBefehle();
        System.out.println("nehme");
    }

    public void quizAufrufen() {
        try {
            Quiz quiz = new Quiz();
        } catch (Exception e) { // replace "Exception" with the actual exception type
            e.printStackTrace();
        }
    }

}
