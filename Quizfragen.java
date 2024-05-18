import org.json.simple.JSONObject;

public class Quizfragen {
    private JSONObject jsonObject;

    //json-bestandteile
    private static final String QUESTION = "question";
    private static final String OPT1 = "option1";
    private static final String OPT2 = "option2";
    private static final String OPT3 = "option3";
    private static final String SOLUTION = "solution";
    private static final String PROF = "prof";
    private static final String RAUM = "location";
    private static final String REWARD = "reward";

    //fragen-bestandteile
    private String frage;
    private String option1;
    private String option2;
    private String option3;
    private String solution;
    private String raum;
    private String prof;
    private String reward;

    /**
     * Konstruktor für die Quizfragen-Klasse, der ein Objekt aus einem JSON-Objekt generiert.
     *
     * @param jsonObject das JSON-Objekt, aus dem die Eigenschaften für das Quizfragen-Objekt extrahiert werden
     */
    public Quizfragen(JSONObject jsonObject)
    {
        this.generateFrom(jsonObject);
    }

    /**
     * Extrahiert Eigenschaften aus einem JSON-Objekt und setzt sie als Attribute des Quizfragen-Objekts.
     *
     * @param jsonObject das JSON-Objekt, aus dem die Eigenschaften extrahiert werden sollen
     */
    private void generateFrom(JSONObject jsonObject)
    {
        this.jsonObject = jsonObject;
        this.frage =    (String) jsonObject.get(QUESTION);
        this.option1 =    (String) jsonObject.get(OPT1);
        this.option2 =    (String) jsonObject.get(OPT2);
        this.option3 =    (String) jsonObject.get(OPT3);
        this.solution = (String) jsonObject.get(SOLUTION);
        this.raum = (String) jsonObject.get(RAUM);
        this.prof = (String) jsonObject.get(PROF);
        this.reward = (String) jsonObject.get(REWARD);

    }

    /**
     * Gibt den Namen des Professors zurück, der die Frage stellt.
     *
     * @return der Name des Professors
     */
    public String getProf() {
        return this.prof;
    }

    /**
     * Gibt den Raum zurück, in dem die Frage gestellt wird.
     *
     * @return der Name des Raums
     */
    public String getRaum() {
        return this.raum;
    }

    /**
     * Gibt die Frage zurück, die gestellt wird.
     *
     * @return die Frage
     */
    public String getFrage() {
        return this.frage;
    }

    /**
     * Gibt die Antwortoptionen als Array zurück.
     *
     * @return ein String-Array mit den Antwortoptionen
     */
    public String[] getAntwortoptionen() {
        return new String[] {option1, option2, option3};
    }

    /**
     * Gibt die Lösung der Frage zurück.
     *
     * @return die Lösung der Frage
     */
    public String getSolution() {
        return this.solution;
    }

    /**
     * Gibt die Belohnung für die richtige Beantwortung der Frage zurück.
     *
     * @return die Belohnung
     */
    public String getReward() {
        return this.reward;
    }
}
