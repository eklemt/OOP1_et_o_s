import java.util.Scanner;

public class QuizRaum extends Raum {

    public static final String ANSI_RESET = "\u001B[0m";
    // muss das public sein ???
    public String schaltteilImRaum; 

    public QuizRaum(String beschreibung) {
        super(beschreibung);
        schaltteilImRaum = null; 
    }

    public void packeSchaltteilInRaum (String name) {
        schaltteilImRaum = name; 
    }

    public void gibSchaltteilImRaumAus () {
        System.out.println("Schaltteil in dem aktuellen Raum" + this.gibKurzbeschreibung() + schaltteilImRaum);
    }

    public String gibschaltteilString() {
        return schaltteilImRaum; 
    }

    public boolean entferneSchaltteilAusRaum () {
       schaltteilImRaum = null; 
       if (schaltteilImRaum == null) {
         return true;
       } 
       return false; 
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

    public void quizAufrufen(Spiel spiel) {
        boolean moechteSpielerQuizzen; 
        boolean richtigeAntwort; 
        try {
            Quiz quiz = new Quiz(spiel);
            moechteSpielerQuizzen = quizBetreten();
            if (moechteSpielerQuizzen) {
                richtigeAntwort = quiz.quizFrageStellen(spiel); 
                if (richtigeAntwort) {
                    System.out.println("Du erhältst für deinen Rucksack ... " + schaltteilImRaum);
                    spiel.packeSchaltteilInDenRucksack(schaltteilImRaum);
                    spiel.gibRucksackinhaltAus();
                    entferneSchaltteilAusRaum(); 
                }
                weiterImText();
            }
            else {
                quizBeenden();
            }

        } catch (Exception e) { // replace "Exception" with the actual exception type
            e.printStackTrace();
        }
    }

    /**
     * Methode zum Betreten des Quiz. Der User wird aufgefordert, mit einer Person im Raum zu interagieren,
     * um das Quiz zu starten oder zu beenden.
     *
     * @throws Exception wenn ein Fehler beim Lesen der Benutzereingabe auftritt
     */
    public boolean quizBetreten() throws Exception {
        //prüfen ob quiz gestartet werden soll?
        System.out.println("\n");
        System.out.println("In diesem Raum triffst du auf eine Person... durch das fehlende Tageslicht ist es schwer zu erkennen, wer vor dir steht...");
        System.out.println("Vielleicht kann die Person dir aber weiterhelfen - sprichst du sie an? (yes/no)");

        //user antwort abfragen: Befehl starten
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        if (command.equalsIgnoreCase("yes")) {
            return true; 
        } else {
            return false; 
        }
    }

     /**
     * Beendet das Quiz, nachdem der Benutzer sich dagegen entschieden hat, mit der Person im Raum zu interagieren.
     * Gibt eine entsprechende Nachricht aus und fährt mit dem nächsten Schritt im Text fort.
     */
    public void quizBeenden() {
        System.out.println("Du entscheidest dich dagegen. Eine gute Entscheidung..." + ANSI_RESET);
        weiterImText();
    }

       /**
     * Ueberleitung zum Raumplan nach Quizende. Gibt eine Nachricht aus und fragt den Benutzer,
     * wohin er als nächstes gehen möchte.
     */
    public void weiterImText() {
        System.out.println("----------------------------" + ANSI_RESET);
        System.out.println("Wo moechtest du hingehen?");
        //TODO hier aktuellen Raum und moegliche ausgaenge angeben
    }



}
