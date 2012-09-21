package dogfight_remake.entities.planes.modern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.entities.planes.modern.Modern;
import dogfight_remake.rendering.Render;

public class ModernTestPlane2 extends Modern {

	private Random random;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension dim = toolkit.getScreenSize();
	// constants
	// default values
	protected final int XSIZE = (int) dim.getWidth();
	protected final int YSIZE = (int) dim.getHeight();
	protected double GRAVITY = 6;
	protected final static double MAX_SPEED = 6;

	private Image image;
	// maximum rate of fire (in milliseconds)
	private final static int MIN_SHOOT_DELAY_GUN = 50;
	private final static int RELOAD_DELAY_GUN = 3000;
	private final static int MIN_SHOOT_DELAY_ROCKET = 100;
	private final static int RELOAD_DELAY_ROCKET = 4000;
	private Rectangle2D plane;

	private int id;
	private int hitpoints;
	private double angle;
	private long lastshot_gun, lastshot_rocket;
	private final int PRIM_AMMO = 50;
	private final int SEC_AMMO = 32;
	private int counter_rocket = SEC_AMMO;
	private int counter_gun = PRIM_AMMO;
	private boolean broken;
	private double hspeed, vspeed;

	public ModernTestPlane2(int id, double xpos, double ypos, double angle,
			Image image, int hitpoints) {
		super(id, xpos, ypos, angle, image, hitpoints);
		this.hitpoints = hitpoints;
		this.image = image;
		this.angle = angle;
		this.lastshot_gun = 0;
		this.lastshot_rocket = 0;
		this.broken = false;
	}

	/**
	 * move method currently clunky possible rewrite: use gravity only when
	 * speed is too low else gravity does nothing
	 * 
	 * @param delta
	 */
	public void move(double delta) {
		if (id == 1) {
			hspeed = Math.abs(speed) * Math.cos(Math.toRadians(angle)) * delta;
			vspeed = Math.abs(speed) * Math.sin(Math.toRadians(angle)) * delta;
		} else {
			hspeed = Math.abs(speed) * Math.cos(Math.toRadians(angle)) * delta;
			vspeed = Math.abs(speed) * Math.sin(Math.toRadians(angle)) * delta;
		}
		if ((Math.abs(hspeed) + Math.abs(vspeed)) < 1.5) {
			xpos += hspeed;
			ypos += vspeed + GRAVITY;
		} else {
			xpos += hspeed;
			ypos += vspeed;
		}
	}

	/**
	 * Shoot Weapon depending on type
	 * 
	 * @param type
	 * @return new Weapons object
	 */
	public Weapons shoot_gun() {
		if (broken) {
			return null;
		}
		random = new Random();
		double rnd = random.nextDouble() * 10;

		int delay_gun = 0;
		long time = System.currentTimeMillis();
		if (counter_gun % PRIM_AMMO == 0) {
			delay_gun = RELOAD_DELAY_GUN;
		}
		if (counter_gun % PRIM_AMMO != 0) {
			delay_gun = MIN_SHOOT_DELAY_GUN;
		}
		if (Math.abs(lastshot_gun - time) < delay_gun) {
			return null;
		}
		lastshot_gun = time;
		double x = (plane.getCenterX() + Math.cos(Math.toRadians(angle + rnd)) * 15);
		double y = (plane.getCenterY() + Math.sin(Math.toRadians(angle - rnd)) * 15);
		counter_gun--;
		if (counter_gun == 0) {
			counter_gun = PRIM_AMMO;
		}
		return new Weapons(x, y, angle, 3, WeaponTypes.GUN, Render.img_bullet1, id);
	}

	/**
	 * Shoot Weapon depending on type
	 * 
	 * @param type
	 * @return new Weapons object
	 */
	public Weapons shoot_missile() {
		if (broken) {
			return null;
		}
		int delay_rocket = 0;
		long time = System.currentTimeMillis();
		if (counter_rocket % SEC_AMMO == 0) {
			delay_rocket = RELOAD_DELAY_ROCKET;
		}
		if (counter_rocket % SEC_AMMO != 0) {
			delay_rocket = MIN_SHOOT_DELAY_ROCKET;
		}
		if (Math.abs(lastshot_rocket - time) < delay_rocket) {
			return null;
		}
		lastshot_rocket = time;
		double x = (plane.getCenterX() + Math.cos(Math.toRadians(angle)) + 7 * Math
				.sin(Math.toRadians(Math.abs(angle))));
		double y = (plane.getCenterY()
				+ Math.sin(Math.toRadians(Math.abs(angle))) + 7 * Math.cos(Math
				.toRadians(angle - 180)));
		counter_rocket--;
		if (counter_rocket == 0) {
			counter_rocket = SEC_AMMO;
		}
		return new Weapons(x, y, angle, 10, WeaponTypes.UNGUIDED, Render.img_bullet1, id);
	}

	/**
	 * Paint method
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		if (broken) {
			return;
		}
		plane = new Rectangle2D.Double(xpos, ypos, image.getWidth(null),
				image.getHeight(null));
		g2d.rotate(Math.toRadians(angle), plane.getCenterX(),
				plane.getCenterY());
		g2d.drawImage(image, (int) xpos, (int) ypos, null);
		// Flame
		g2d.setColor(Color.RED);
		Point p1 = new Point((int) xpos, (int) ypos + 2 * image.getHeight(null)
				/ 8); // mitte
		Point p2 = new Point((int) xpos, (int) ypos + 6 * image.getHeight(null)
				/ 8); // unten
		Point p3 = new Point((int) xpos - (int) speed * 2,
				(int) (ypos + 4 * image.getHeight(null) / 8)); // oben
		int[] xs = { p1.x, p2.x, p3.x };
		int[] ys = { p1.y, p2.y, p3.y };
		Polygon flame = new Polygon(xs, ys, 3);

		g2d.draw(flame);
		g2d.fill(flame);
		g2d.dispose();
	}

	/**
	 * Rotates plane
	 * 
	 * @param amount
	 */
	public void rotateDirection(int amount) {
		if (speed > MAX_SPEED * 1) {
			angle += amount * 1;
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
		if (type == WeaponTypes.UNGUIDED) {
			return counter_rocket;
		} else if (type == WeaponTypes.GUN) {
			return counter_gun;
		} else {
			return -1;
		}
	}

	public boolean getBroken() {
		return broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}

	public double getSpeedTotal() {
		return Math.abs(hspeed) + Math.abs(vspeed);
	}

	public double getSpeedTotal1() {
		return hspeed + vspeed;
	}

	public double getHspeed() {
		return hspeed;
	}

	public double getVspeed() {
		return vspeed;
	}

}
