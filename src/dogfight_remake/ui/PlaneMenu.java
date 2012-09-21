package dogfight_remake.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

/**
 * Plane choose window
 * @author Jan-Niklas
 *
 */
public class PlaneMenu {
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension dim = toolkit.getScreenSize();
	public void paintComponent(Graphics g) {
		
		// Background and Entities
		Graphics2D g2d = (Graphics2D) g.create();
			Font font = new Font("Arial", Font.PLAIN, 20);
			g2d.setFont(font);
			g2d.drawString("Paused", (int) (dim.getWidth() / 2) - 30,
					(int) dim.getHeight() / 2);
			g2d.drawString("Press 'P' to continue",
					(int) (dim.getWidth() / 2) - 70,
					(int) dim.getHeight() / 2 + 50);
			g2d.dispose();
		}
}
