import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.*;
import java.io.*;

import org.json.simple.JSONArray;

public class Quiz {
    private static final String quizFILE = "resources/quiz.json";
    private ArrayList<Quizfragen> quizfragenSet;
    private Spiel spiel;
    //Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public String professor; 
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
    public Quiz(Spiel spiel, String lehrer) throws Exception
    {
        this.spiel = spiel; 
        this.professor = lehrer; 
        /*
        welcher raum
        welcher professor -> welcher fragenpool?

        quizfrage stellen
         */
        quizfragenSet = new ArrayList<Quizfragen>();
        einlesen(quizFILE);

        //System.out.println(quizfragenSet);
        //alleFragenAusgeben();

        
        
    }

   
    /**
     * Stellt eine zufällig ausgewählte Quizfrage aus dem Set der Quizfragen. Der Benutzer wird aufgefordert, die Frage zu beantworten,
     * und erhält Feedback basierend auf seiner Antwort.
     */
    public boolean  quizFrageStellen(Spiel spiel) {
        int randomIndex = new Random().nextInt(quizfragenSet.size());
        Quizfragen randomFrage = quizfragenSet.get(randomIndex);
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
            System.out.println("Richtig!");
            return true;  
        } else {
            System.out.println("Das war leider falsch. Hier kommst du nicht weiter.");
            spiel.verliereEinLeben();
            return false; 
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
            String profDerFrage = quizfragenObjekt.getProf(); 
            if (profDerFrage.equals(this.professor)) {
               quizfragenSet.add(quizfragenObjekt);
            }
        } 

    }
}
