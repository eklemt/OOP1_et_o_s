import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.*;
import java.io.*;

import org.json.simple.JSONArray;

public class Quiz {
    private static final String quizFILE = "resources/quiz.json";
    private HashSet<Quizfragen> quizfragenSet;
    //Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    /*
    quizfrage stellen
    antwort handeln
    gegenstand ausgeben -> in inventar speichern

    professor ist raum fest zugeordnet -> raum muss an new Quiz() übergeben werden
     */

    /**
     * Konstruktor für die Quiz-Klasse. Initialisiert ein neues Quiz mit Fragen aus einem Dateispeicher.
     * Die Methode liest Fragen aus einer Datei ein und füllt das Quiz mit diesen Fragen.
     *
     * @throws Exception wenn ein Fehler beim Einlesen der Quizfragen aus der Datei auftritt
     */
    public Quiz() throws Exception
    {
        /*
        welcher raum
        welcher professor -> welcher fragenpool?

        quizfrage stellen
         */
        quizfragenSet = new HashSet<Quizfragen>();
        einlesen(quizFILE);

        //System.out.println(quizfragenSet);
        //alleFragenAusgeben();

        quizBetreten();
    }

    /**
     * Methode zum Betreten des Quiz. Der User wird aufgefordert, mit einer Person im Raum zu interagieren,
     * um das Quiz zu starten oder zu beenden.
     *
     * @throws Exception wenn ein Fehler beim Lesen der Benutzereingabe auftritt
     */
    public void quizBetreten() throws Exception {
        //prüfen ob quiz gestartet werden soll?
        System.out.println("\n");
        System.out.println("In diesem Raum triffst du auf eine Person... durch das fehlende Tageslicht ist es schwer zu erkennen, wer vor dir steht...");
        System.out.println("Vielleicht kann die Person dir aber weiterhelfen - sprichst du sie an? (yes/no)");

        //user antwort abfragen: Befehl starten
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        if (command.equalsIgnoreCase("yes")) {
            quizFrageStellen();
        } else {
            quizBeenden();
        }

    }

    /**
     * Beendet das Quiz, nachdem der Benutzer sich dagegen entschieden hat, mit der Person im Raum zu interagieren.
     * Gibt eine entsprechende Nachricht aus und fährt mit dem nächsten Schritt im Text fort.
     */
    public void quizBeenden() {
        System.out.println("Du entscheidest dich dagegen. Eine gute Entscheidung...");
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

    /**
     * Stellt eine zufällig ausgewählte Quizfrage aus dem Set der Quizfragen. Der Benutzer wird aufgefordert, die Frage zu beantworten,
     * und erhält Feedback basierend auf seiner Antwort.
     */
    public void quizFrageStellen() {
        ArrayList<Quizfragen> list = new ArrayList<>(quizfragenSet);
        int randomIndex = new Random().nextInt(list.size());
        Quizfragen randomFrage = list.get(randomIndex);
        System.out.println("Die Person tritt vor aus der Dunkelheit: vor dir steht " + randomFrage.getProf() + "!!");
        System.out.println("Das Quiz beginnt!");

        System.out.println(ANSI_YELLOW + "----------------------------");
        System.out.println(randomFrage.getFrage());
        System.out.println("Antwortoptionen: ");
        System.out.println(Arrays.toString(randomFrage.getAntwortoptionen()));
        System.out.println("Tippe deine Antwort ausgeschrieben ein:");

        Scanner scanner = new Scanner(System.in);
        String antwort = scanner.nextLine();

        if (antwort.equalsIgnoreCase(randomFrage.getSolution())) {
            System.out.println("Richtig! Du erhaeltst für deinen Rucksack: " + randomFrage.getReward());
            weiterImText();
        } else {
            System.out.println("Das war leider falsch. Hier kommst du nicht weiter.");
            weiterImText();
        }

    }

    /**
     * Gibt alle Quizfragen im Set aus, einschließlich Raum, Professor, Frage, Antwortoptionen, Lösung und Belohnung.
     */
    public void alleFragenAusgeben() {
        for (Quizfragen frage : quizfragenSet) {
            // Use getter method to access and print each quizfragen object's properties
            System.out.println("Raum: " + frage.getRaum());
            System.out.println("Prof: " + frage.getProf());
            System.out.println("Question: " + frage.getFrage());
            System.out.println("Antwortoptionen: ");
            System.out.println(Arrays.toString(frage.getAntwortoptionen()));
            System.out.println("Loesung: " + frage.getSolution());
            System.out.println("Gewonnenes Bauteil: " + frage.getReward());
            System.out.println("----------------------------");


            // print any other properties
        }
    }

    /**
     * Liest Quizfragen aus einer JSON-Datei ein und fügt sie dem Set von Quizfragen hinzu.
     *
     * @param dateiName der Name der JSON-Datei, aus der die Quizfragen eingelesen werden sollen
     * @throws Exception wenn ein Fehler beim Lesen oder Parsen der JSON-Datei auftritt
     */
    private void einlesen(String dateiName) throws Exception
    {
        JSONParser parser = new JSONParser();
        assert (quizFILE != null && quizFILE.contains(".json"));

        JSONArray quizJSON = (JSONArray) parser.parse(new java.io.FileReader(quizFILE));

        for (Object obj : quizJSON) {
            JSONObject quizObj = (JSONObject) obj;

            Quizfragen quizfragenObjekt = new Quizfragen(quizObj);

            quizfragenSet.add(quizfragenObjekt);
        }

    }
}
