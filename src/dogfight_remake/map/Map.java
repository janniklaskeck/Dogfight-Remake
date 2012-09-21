package dogfight_remake.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Map {

	private Random random;
	private double[][] map;
	private double tableHeight, tableWidth;
	private double mapHeight, mapWidth;

	/**
	 * Map object. Creates a ground structure, currently randomised
	 * @param x
	 * @param y
	 */
	public Map(double x, double y) {
		// Initialisierung der Arraygröße von map und colors
		tableWidth = x;
		tableHeight = y;
		map = new double[(int) tableWidth][(int) tableHeight];
		random = new Random();
		mapHeight = tableHeight - 50;
		mapWidth = tableWidth;
		// Aufruf der Methode generateMap
		generateMap();
	}

	/**
	 * Fills map with content
	 */
	private void generateMap() {
		for (int i = 0; i < tableWidth; i++) {
			for (int j = (int) mapHeight; j < tableHeight; j++) {
				if (i < 1500 && i > 100 && j < 1000) {
					map[i][j] = random.nextInt(2) + 1;
				} else {
					map[i][j] = random.nextInt(2) + 1;
				}

			}
		}

	}

	/**
	 * Paints the Background depending on map
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		Color brown = new Color(160, 82, 45);
		Color brown_bright = new Color(205, 133, 63);
		Color green_dark = new Color(34, 139, 34);
		// Loop zeichnet für alle Felder des Arrays eine Linie
		for (int i = 0; i < mapWidth; i += 7) {
			for (int j = (int) mapHeight; j < tableHeight; j += 7) {
				if (map[i][j] == 1 && j != (tableHeight - 50)) {
					g.setColor(brown);
					g.fillRect(i, j, 7, 7);
				} else if ((map[i][j] == 2 || map[i][j] == 3) && j != (tableHeight - 50)) {
					g.setColor(brown_bright);
					g.fillRect(i, j, 7, 7);
				} else if (j == mapHeight) {
					g.setColor(green_dark);
					g.fillRect(i, j, 7, 7);
				} else {
					// Clean part
				}
			}
		}
	}

	/**
	 * Returns map to use in other locations
	 * 
	 * @return
	 */

	public double[][] getMap() {
		return map;
	}

	public double getMapHeight() {
		return mapHeight;
	}
	public double getMapWidth() {
		return mapWidth;
	}
}
