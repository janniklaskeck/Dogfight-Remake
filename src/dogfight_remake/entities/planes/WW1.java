package dogfight_remake.entities.planes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import dogfight_remake.entities.Planes;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.main.GamePanel;
import dogfight_remake.rendering.Render;

/**
 * Class for World-War 1 plane types
 * @author Jan-Niklas
 *
 */

public class WW1 extends Planes {

	private double angle, turnspeed;
	private long lastshot_prim, lastshot_sec;
	private int hitpoints, speed;
	private Image image;
	private Rectangle2D plane;
	private boolean broken;
	private int counter_prim = 50;
	private int counter_bomb = 2;

	private final static double MAX_SPEED = 5;
	// maximum rate of fire (in milliseconds)
	private final static int MIN_SHOOT_DELAY_GUN = 50;
	private final static int RELOAD_DELAY_GUN = 3000;
	private final static int MIN_SHOOT_DELAY_BOMB = 500;
	private final static int RELOAD_DELAY_BOMB = 5000;

	public WW1(int id, double xpos, double ypos, double angle,
			Image image, int hitpoints, int speed, int turnspeed) {
		super(id, xpos, ypos, angle, image, hitpoints, null, null, null);
		this.hitpoints = hitpoints;
		this.image = image;
		this.angle = angle;
		this.lastshot_prim = 0;
		this.lastshot_sec = 0;
		this.broken = false;
		this.speed = speed;
		this.turnspeed = turnspeed;
	}

	public void move() {
		super.move();
		double hspeed = speed * Math.cos(Math.toRadians(angle));
		double vspeed = speed * Math.sin(Math.toRadians(angle));

		if (Math.abs(hspeed) > GamePanel.GRAVITY) {
			xpos += hspeed;
			ypos += vspeed;
		} else {
			ypos += GamePanel.GRAVITY * 2 + vspeed;
			xpos += hspeed * 0.75;
		}
	}

	/**
	 * Shoot Weapon depending on type
	 * 
	 * @param type
	 * @return new Weapons object
	 */
	public Weapons shoot_primary(WeaponTypes type) {
		if (broken) {
			return null;
		}
		if (type == WeaponTypes.GUN) {
			// Normal gunfire = slow middle damage
			int delay_prim = 0;
			long time = System.currentTimeMillis();
			if (counter_prim % 50 == 0) {
				delay_prim = RELOAD_DELAY_GUN;
			}
			if (counter_prim % 50 != 0) {
				delay_prim = MIN_SHOOT_DELAY_GUN;
			}
			if (Math.abs(lastshot_prim - time) < delay_prim) {
				return null;
			}
			lastshot_prim = time;
			double x = (plane.getCenterX() + Math.cos(Math.toRadians(angle)) * 50);
			double y = (plane.getCenterY() + Math.sin(Math.toRadians(angle)) * 50);
			counter_prim--;
			if (counter_prim == 0) {
				counter_prim = 50;
			}
			return new Weapons(x, y, angle, 5, WeaponTypes.GUN, Render.img_bullet1, id);
		} else {
			return null;
		}
	}

	/**
	 * Shoot Weapon depending on type
	 * 
	 * @param type
	 * @return new Weapons object
	 */
	public Weapons shoot_secondary(WeaponTypes type) {
		if (broken) {
			return null;
		}
		if (type == WeaponTypes.BOMB) {
			// Radar missiles = targeting is required all the time ground targets
			int delay_bomb = 0;
			long time = System.currentTimeMillis();
			if (counter_bomb % 2 == 0) {
				delay_bomb = RELOAD_DELAY_BOMB;
			}
			if (counter_bomb % 2 != 0) {
				delay_bomb = MIN_SHOOT_DELAY_BOMB;
			}
			if (Math.abs(lastshot_sec - time) < delay_bomb) {
				return null;
			}
			lastshot_sec = time;
			double x = (plane.getCenterX() + Math.cos(Math.toRadians(angle)) * 50);
			double y = (plane.getCenterY() + Math.sin(Math.toRadians(angle)) * 50);
			counter_bomb--;
			if (counter_bomb == 0) {
				counter_bomb = 2;
			}
			return new Weapons(x, y, angle, 75, WeaponTypes.BOMB, Render.img_bullet1, id);
		} else {
			return null;
		}

	}

	/**
	 * Paint method
	 */
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		if (broken)
			return;
		plane = new Rectangle2D.Double(xpos, ypos, image.getWidth(null),
				image.getHeight(null));

		g2d.rotate(Math.toRadians(angle), plane.getCenterX(),
				plane.getCenterY());
		g2d.drawImage(image, (int) xpos, (int) ypos, null);
		// Flame - no flames for ww1 :(
		/*g2d.setColor(Color.RED);
		Point p1 = new Point((int) xpos, (int) ypos + image.getHeight(null) / 2); // mitte
		Point p2 = new Point((int) xpos, (int) ypos + image.getHeight(null) - 2); // unten
		Point p3 = new Point((int) xpos - (int) speed * 5,
				(int) (ypos + 2 * image.getHeight(null) / 3)); // oben
		int[] xs = { p1.x, p2.x, p3.x };
		int[] ys = { p1.y, p2.y, p3.y };
		Polygon flame = new Polygon(xs, ys, 3);
		g2d.draw(flame);
		g2d.fill(flame);*/
		// g2d.drawString("1", (int) xpos + 20, (int) ypos);
		g2d.dispose();
	}

	/**
	 * Rotates plane
	 * 
	 * @param amount
	 */
	public void rotateDirection(int amount) {
		if (speed > MAX_SPEED * 0.8) {
			angle += amount * 0.8;
		} else {
			angle += amount;
		}
		if (angle >= 360 || angle <= -360) {
			angle = 0;
		}
	}

	/**
	 * Returns angle of plane
	 * 
	 * @return
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Sets Hitpoints to desired value
	 * 
	 * @param hitpoints
	 */
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
		if (hitpoints == 0)
			broken = true;
	}

	/**
	 * Returns current Hitpoints
	 * 
	 * @return
	 */
	public int getHitpoints() {
		return hitpoints;
	}

	/**
	 * Sets turnspeed to desired value
	 * 
	 * @param turnspeed
	 */
	public void setTurnspeed(int turnspeed) {
		this.turnspeed = turnspeed;
	}

	/**
	 * Returns current turnspeed
	 * 
	 * @return
	 */
	public double getTurnspeed() {
		return turnspeed;
	}

	/**
	 * Returns players plane image
	 * 
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Decreases hitpoints
	 */
	public void decreaseHitpoints(int damage) {
		hitpoints -= damage;
		if (hitpoints <= 0)
			broken = true;
	}

	/**
	 * If plane is too steep(cone of ?? degree in up direction)
	 * 
	 * @return true if too steep
	 */
	public boolean isSteep() {
		if ((angle < -60 && angle > -120) || (angle < 300 && angle > 240)) {
			GamePanel.GRAVITY = 1.5;
			return true;
		} else if ((angle < -75 && angle > -105)
				|| (angle < 285 && angle > 255)) {
			GamePanel.GRAVITY = 2.5;
			return true;
		}
		GamePanel.GRAVITY = 1;
		return false;
	}

	public Rectangle2D getPlane() {
		return plane;
	}

	public double getCenterX() {
		if (plane != null) {
			return plane.getCenterX();
		} else {
			return xpos;
		}
	}

	public double getCenterY() {
		if (plane != null) {
			return plane.getCenterY();
		} else {
			return ypos;
		}
	}

	/**
	 * Returns current ammo count
	 * 
	 * @param type
	 * @return
	 */
	public int getAmmo(WeaponTypes type) {
		if (type == WeaponTypes.GUN) {
			return counter_prim;
		} else if (type == WeaponTypes.BOMB) {
			return counter_bomb;
		} else {
			return -1;
		}
	}

	public boolean getBroken() {
		return broken;
	}
}
