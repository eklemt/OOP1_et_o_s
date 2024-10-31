package client;

import java.util.Scanner;

/**
 * Diese Klasse modelliert eine einen Quizraum in der Welt von Elektrotechniker ohne client.Schaltplan.
 * 
 * Ein "client.Raum" repraesentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein client.Raum ist mit anderen Raeumen ueber Ausgaenge verbunden.
 * Fuer jeden existierenden Ausgang haelt ein client.Raum eine Referenz auf
 * den benachbarten client.Raum.
 * 
 * Ein Quizraum erbt von der Klasse client.Raum und in ihm kann der Spieler eine Quizfrage beantworten,
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
     * @param lehrer String, der den Professor fuer den client.Raum angibt
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
     * Funktion, die ein Schaltteil in dem aktuellen client.Raum platziert
     * @param name Name des Schaltteils als String, welches im client.Raum platziert werden soll
     */
    public void packeSchaltteilInRaum (String name) {
        schaltteilImRaum = name; 
    }

    /**
     * Funktion, die ausgibt, welches Schaltteil sich im aktuellen client.Raum befindet
     */
    public void gibSchaltteilImRaumAus () {
        System.out.println("Schaltteil in dem aktuellen client.Raum" + this.gibKurzbeschreibung() + schaltteilImRaum);
    }

    /**
     * Methode, die die Schaltteilvariable des Raums null setzt
     * @return String, die das Schaltteil im client.Raum als String zurückgibt
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
     * Funktion, die ein client.Quiz mit dem SPieler durchfuehrt
     * Dabei wird dem Spieler eine Quizfrage, passend zum Professor gestellt und 
     * wenn diese richtig beantwortet wird, bekommt der Spieler ein Schaltteil passend zum client.Raum
     * @param spiel client.Spiel das aktuell gespielt wird
     * @param rucksack client.Rucksack des Spielers
     */
    public void quizAufrufen(Spiel spiel, Rucksack rucksack) {
        boolean moechteSpielerQuizzen; 
        boolean richtigeAntwort; 
        try {
            Quiz quiz = new Quiz(spiel, professor);
            moechteSpielerQuizzen = quizBetreten();
            if (moechteSpielerQuizzen) {
                richtigeAntwort = quiz.quizFrageStellen(spiel); 
                if (richtigeAntwort) {
                    boolean alleTeileEingesammelt; 
                    System.out.println("Du erhältst für deinen client.Rucksack ... " + schaltteilImRaum + " von " + this.professor);
                    System.out.println("----------------------------" + ANSI_RESET);
                    rucksack.packeSchaltteilEin(schaltteilImRaum);
                    rucksack.rucksackinhaltInKonsole();;
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
     * Methode zum Betreten des client.Quiz. Der User wird aufgefordert, mit einer Person im client.Raum zu interagieren,
     * um das client.Quiz zu starten oder zu beenden.
     *
     * @return boolean, der angibt, ob der Spieler ein client.Quiz spielen moechte oder nicht
     * @throws Exception wenn ein Fehler beim Lesen der Benutzereingabe auftritt
     */
    public boolean quizBetreten() throws Exception {
        //prüfen ob quiz gestartet werden soll?
        System.out.println("\n");
        System.out.println("In diesem client.Raum triffst du auf eine Person... durch das fehlende Tageslicht ist es schwer zu erkennen, wer vor dir steht...");
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
     * Beendet das client.Quiz, nachdem der Benutzer sich dagegen entschieden hat, mit der Person im client.Raum zu interagieren.
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
    }



}
