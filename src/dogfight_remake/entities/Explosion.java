package dogfight_remake.entities;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;

public class Explosion extends Entity {

	private final int MAX_RADIUS = 15;
	private float xpos, ypos, size;
	private Ellipse explosion1, explosion2;
	private float radius = 0;
	private float radius_small = 0;

	public Explosion(float xpos, float ypos, float size) {
		super(xpos, ypos);
		this.xpos = xpos;
		this.ypos = ypos;
		this.size = size;
	}

	public void render(GameContainer container, Graphics g) {

		explosion1 = new Ellipse(xpos - radius / 2, ypos - radius / 2, radius,
				radius);
		g.setColor(Color.red);
		g.fill(explosion1);
		if (size > 1) {
			radius_small = radius / 2;

			explosion2 = new Ellipse(xpos - radius_small / 2, ypos
					- radius_small / 2, radius_small, radius_small);
			g.setColor(Color.yellow);
			g.fill(explosion2);
		}
	}

	public void move(float delta) {
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
