package client;

/**
 * Objekte dieser Klasse halten Informationen �ber Befehle,
 * die der Benutzer eingegeben hat. Ein client.Befehl besteht momentan
 * aus zwei Zeichenketten: einem Befehlswort und einem zweiten
 * Wort. Beim client.Befehl "nimm karte" beispielsweise sind die beiden
 * Zeichenketten "nimm" und "karte".
 * 
 * Befehle werden von Benutzern dieser Klasse auf G�ltigkeit
 * �berpr�ft. Wenn ein Spieler einen ung�ltigen client.Befehl eingegeben
 * hat (ein unbekanntes Befehlswort), dann ist das Befehlswort <null>.
 *
 * Wenn der client.Befehl nur aus einem Wort bestand, dann ist das
 * zweite Wort <null>.
 * 
 * @author  Michael K�lling und David J. Barnes
 * @version 31.07.2011
 */

class Befehl
{
    private String befehlswort;
    private String zweitesWort;

    /**
     * Erzeuge ein Befehlsobjekt. Beide W�rter m�ssen angegeben werden,
     * aber jedes oder beide d�rfen 'null' sein.
     * @param erstesWort Das erste Wort des Befehls. Sollte
     *                   'null' sein, wenn dieser client.Befehl als nicht
     *                   vom client.Spiel erkannt gekennzeichnet werden soll.
     * @param zweitesWort Das zweite Wort des Befehls.
     */
    public Befehl(String erstesWort, String zweitesWort)
    {
        befehlswort = erstesWort;
        this.zweitesWort = zweitesWort;
    }

    /**
     * Liefere das Befehlswort (das erste Wort) dieses Befehls.
     * Wenn der client.Befehl nicht verstanden wurde, ist das Ergebnis
     * 'null'.
     * @return das Befehlswort.
     */
    public String gibBefehlswort()
    {
        return befehlswort;
    }

    /**
     * @return Das zweite Wort dieses Befehls. Liefere 'null', wenn
     * es kein zweites Wort gab.
     */
    public String gibZweitesWort()
    {
        return zweitesWort;
    }

    /**
     * @return 'true', wenn dieser client.Befehl nicht verstanden wurde.
     */
    public boolean istUnbekannt()
    {
        return (befehlswort == null);
    }

    /**
     * @return 'true', wenn dieser client.Befehl ein zweites Wort hat.
     */
    public boolean hatZweitesWort()
    {
        return (zweitesWort != null);
    }
}

