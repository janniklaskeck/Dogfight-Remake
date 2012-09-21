package dogfight_remake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Explosion extends Entity {

	private final int MAX_RADIUS = 15;
	private double xpos, ypos, size;
	private Ellipse2D explosion1, explosion2;
	private double radius = 0;
	private double radius_small = 0;

	public Explosion(double xpos, double ypos, double size) {
		super(xpos, ypos);
		this.xpos = xpos;
		this.ypos = ypos;
		this.size = size;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.YELLOW);
		explosion1 = new Ellipse2D.Double(xpos - radius / 2, ypos - radius / 2,
				radius, radius);
		g2d.fill(explosion1);
		if (size > 1) {
			radius_small = 2 * radius / 4;
			g2d.setColor(Color.RED);
			explosion2 = new Ellipse2D.Double(xpos - radius_small / 2, ypos
					- radius_small / 2, radius_small, radius_small);
			g2d.fill(explosion2);
		}
		g2d.dispose();
	}

	public void move(double delta) {
		if (radius < (MAX_RADIUS * size)) {
			radius += 2;
		}
	}

	public boolean isMaxRadius() {
		if (radius >= MAX_RADIUS * size) {
			return true;
		} else {
			return false;
		}
	}

}
