import java.util.Scanner;

/**
 * Diese Klasse modelliert eine einen Quizraum in der Welt von Elektrotechniker ohne Schaltplan.
 * 
 * Ein "Raum" repraesentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Raeumen ueber Ausgaenge verbunden.
 * Fuer jeden existierenden Ausgang haelt ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * 
 * Ein Quizraum erbt von der Klasse Raum und in ihm kann der Spieler eine Quizfrage beantworten,
 * um ein Schaltteil zu bekommen, mit dem er später seine Schaltung reparieren kann 
 * 
 * @author  Emily Klemt, Carolin Altstaedt auf Basis von Michael Koelling und David J. Barnes
 * @version 27.05.2024
 */
public class QuizRaum extends Raum {

    public static final String ANSI_RESET = "\u001B[0m";
    public String schaltteilImRaum; 
    public String professor; 

    /**
     * Konstruktor, um einen Quizraum zu erstellen 
     * @param beschreibung Beschreibung des Raums als String 
     * @param lehrer String, der den Professor fuer den Raum angibt 
     */
    public QuizRaum(String beschreibung, String lehrer) {
        super(beschreibung);
        schaltteilImRaum = null; 
        this.professor = lehrer; 
    }
  
    /**
     * Funktion, die den Professor des Raums, als String zurueckgibt
     * @return String mit Namen des Professors 
     */
    public String gibProfessorString() {
        return professor; 
    }

    /**
     * Funktion, die ein Schaltteil in dem aktuellen Raum platziert 
     * @param name Name des Schaltteils als String, welches im Raum platziert werden soll
     */
    public void packeSchaltteilInRaum (String name) {
        schaltteilImRaum = name; 
    }

    /**
     * Funktion, die ausgibt, welches Schaltteil sich im aktuellen Raum befindet
     */
    public void gibSchaltteilImRaumAus () {
        System.out.println("Schaltteil in dem aktuellen Raum" + this.gibKurzbeschreibung() + schaltteilImRaum);
    }

    /**
     * Methode, die die Schaltteilvariable des Raums null setzt
     * @return String, die das Schaltteil im Raum als String zurückgibt
     */
    public String gibschaltteilString() {
        return schaltteilImRaum; 
    }


    /**
     * Methode, die die Schaltteilvariable des Raums null setzt
     */
    public void entferneSchaltteilAusRaum () {
       schaltteilImRaum = null; 
    }

    /**
     * Funktion, die ein Quiz mit dem SPieler durchfuehrt
     * Dabei wird dem Spieler eine Quizfrage, passend zum Professor gestellt und 
     * wenn diese richtig beantwortet wird, bekommt der Spieler ein Schaltteil passend zum Raum 
     * @param spiel Spiel das aktuell gespielt wird
     * @param rucksack Rucksack des Spielers 
     */
    public void quizAufrufen(Spiel spiel, Rucksack rucksack) {
        boolean moechteSpielerQuizzen; 
        boolean richtigeAntwort; 
        try {
            System.out.println(" Prof: " + professor);
            Quiz quiz = new Quiz(spiel, professor);
            moechteSpielerQuizzen = quizBetreten();
            if (moechteSpielerQuizzen) {
                richtigeAntwort = quiz.quizFrageStellen(spiel); 
                if (richtigeAntwort) {
                    boolean alleTeileEingesammelt; 
                    System.out.println("Du erhältst für deinen Rucksack ... " + schaltteilImRaum);
                    System.out.println("----------------------------" + ANSI_RESET);
                    spiel.packeSchaltteilInDenRucksack(schaltteilImRaum);
                    spiel.gibRucksackinhaltAus();
                    alleTeileEingesammelt = rucksack.alleTeileEingesammelt(); 
                    if (alleTeileEingesammelt == true) {
                       spiel.macheWerkstattzugänglich();
                    }
                    entferneSchaltteilAusRaum(); 
                    System.out.println("Wo moechtest du hingehen?");
                }
                else {
                    weiterImText();
                }
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
     * @return boolean, der angibt, ob der Spieler ein Quiz spielen moechte oder nicht
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
