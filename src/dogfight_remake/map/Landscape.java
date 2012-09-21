package dogfight_remake.map;
/** Diese Klasse implementiert alle f�r die Landschaft wichtigen Funktionen */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.*;


public class Landscape
{
	// Deklaration der Variablen
	private int start;			// Gibt die Starth�he der Landschaft vor
	private int change;			// Entscheidet, ob die Richtung der Landschaftsentwicklung ge�ndert wird
	private int faktor;			// Entscheidet �ber Addition oder Substraktion
	private int faktorc;
	private int last;			// Speichert die letzte H�he der letzten gezeichneten Linie
	private int plus;			// Wieviel wird addiert oder subtraiert

	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private final int mapsize = (int) dim.getWidth();	// Konstante f�r die Gr��e des Arrays

	// Variable zur Erzeugung einer Zufallszahl
	Random rnd = new Random ();

	// Array zum Speichern der H�henpunkte
	public int [] map;

	// Array zum Speichern der Farbwerte der jeweiligen H�henpunkte
	public Color [] colors;

	/** Construktormethode */

	public Landscape ()
	{
		// Initialisierung der Variable plus
		faktor = 1;
		faktorc = 1;

		// Initialisierung der Arraygr��e von map und colors
		map = new int [mapsize];
		colors = new Color [mapsize];

		// Aufruf der Methode generateLandscape
		generateLandscape ();
	}

	/** Diese Methode initialisiert das Array map mit Integerzahlen. Diese liefern sp�ter die
	oberen y - Werte der Linien, die die Landschaft bilden. Hierzu wird zu Beginn der Funktion
	ein Startwert zwischen 250 und 350. Diese Zahl wird als erste Position im Array gespeichert.
	Nun werden alle Felder des Arrays abgelaufen und der Wert plus wird zur letzten Position
	entweder hizugez�hlt oder abgezogen. Die eingeschlagene Richtung wird dabei in 70% der F�lle
	beibehahlten, in 30% der F�lle wechselt die Richtung. Diese Positionen werden nun im Array
	gespeichert */

	public void generateLandscape ()
	{
		// Initialisierung von plus
		plus = 2;

		// Initialisierung des Startwertes
		start = Math.abs(850 + (rnd.nextInt() % 30));

		// Speichern des Startwertes an der ersten Stelle des Arrays
		map [0] = start;

		// Initialisierung der Startfarbwerte
		//int greenvalue = 200;
		//int redvalue = Math.abs(rnd.nextInt() % 200);
		//int bluevalue = Math.abs(rnd.nextInt() % 201);

		// Speichern des ersten Startwertes für das Farbenarray
		//colors [0] = new Color (redvalue, greenvalue, bluevalue);

		// Loop zur Initialisierung aller anderen Arrayfelder
		for (int i = 1; i < mapsize; i ++)
		{
			// Speichern der letzten Arrayposition für Höhe und Farbe
			last = map [i - 1];

			// Entscheidet, ob die eingeschlagene Richtung gewechselt wird
			change = Math.abs(rnd.nextInt() % 10);

			// Wenn change > 7 ist, dann ändert sich die Richtung und möglicherweise plus
			if (change > 1)
			{
				// ändern der Richtung
				faktor = - (faktor);
				faktorc = - (faktorc);
				// Wieviel wird addiert bzw. substrahiert
				plus = 1 + Math.abs(rnd.nextInt() % 2);
			}

			// Wird ein bestimmter Wert unter- bzw. überschritten, dann wird die Richtung geändert
			if (last > 350 || last < 120)
			{
				// ändern der Richtung
				faktor = - (faktor);
				faktorc = - (faktorc);
			}

			// Hält die Farbwerte immer in einem bestimmten Rahmen
			/*if (greenvalue > 240)
			{
				// Wenn Farbwert zu groß wird, erniedrigen des Wertes
				greenvalue -= 30;
			}
			else if (greenvalue < 100)
			{
				// Wenn Farbwert zu klein wird erhöhen des Farbwertes
				greenvalue += 10;
			}*/

			// Werte für das Feld an i - Stelle werden berechnet
			map [i] = last + (faktor * plus);

			/** Um die Farbewerte für zunehmende Höhe heller werden zu lassen, wird der Faktor
			umgekehrt. Dies ist wegen dem umgekehrten Koordinatensystem von Java nötig */
			//greenvalue = greenvalue + (-faktorc * plus);
			//colors [i] = new Color (redvalue, greenvalue, bluevalue);
		}
	}

	/** Die Funktion paintMap zeichnet die Landschaft. Dazu werden die Felder des Arrays durchlaufen
	und eine Linie wird an x - Position = Arrayindex und von der y - Position = Wert an Arrayindex
	bis zum Boden wird dann gezeichnet */
	public void paintComponent (Graphics g) {
		// Loop zeichnet für alle Felder des Arrays eine Linie
		for (int index = 0; index < mapsize; index ++) {
			// Definition der Farbe gemäß dem colors - Array (grün - türkies - blau)
			//g.setColor (colors [index]);

			// Linie zeichnen
			g.drawLine (index, map[index], index, (int) dim.getHeight());
		}
	}
	
	
	public int[] getMap() {
		return map;
	}
	
}
