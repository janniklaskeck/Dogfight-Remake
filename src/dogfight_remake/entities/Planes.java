package dogfight_remake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.main.GamePanel;

public class Planes extends Entity {

	private Random random;
	// constants
	// default values
	protected final static double MAX_SPEED = 7;

	private Image image;

	private Rectangle2D plane;
	protected int id;
	private int hitpoints;
	private double angle;
	private long lastshot_prim, lastshot_sec_1, lastshot_sec_2;
	private int counter_prim;
	private int counter_sec_1;
	private int counter_sec_2;
	private boolean broken;
	public double hspeed, vspeed;
	private WeaponTypes wpn1;
	private WeaponTypes wpn2;
	private WeaponTypes wpn3;

	public Planes(int id, double xpos, double ypos, double angle, Image image,
			int hitpoints, WeaponTypes wpn1, WeaponTypes wpn2, WeaponTypes wpn3) {
		super(xpos, ypos, angle);
		this.id = id;
		this.hitpoints = hitpoints;
		this.image = image;
		this.angle = angle;
		this.lastshot_prim = 0;
		this.lastshot_sec_1 = 0;
		this.lastshot_sec_2 = 0;
		this.broken = false;
		this.wpn1 = wpn1;
		this.wpn2 = wpn2;
		this.wpn3 = wpn3;
		counter_prim = wpn1.getAmmoCount();
		counter_sec_1 = wpn2.getAmmoCount();
		counter_sec_2 = wpn3.getAmmoCount();

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

		if (Math.abs(hspeed) + Math.abs(vspeed) < 1.3) {

			xpos += hspeed;
			ypos += vspeed + GamePanel.GRAVITY;
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
	public Weapons shoot_primary() {
		double angle = this.angle;
		if (broken || counter_prim == 0) {
			return null;
		}
		random = new Random();
		double rnd = random.nextDouble() * 2;
		double rnd1 = random.nextInt();
		if (rnd1 % 2 == 0) {
			angle += rnd;
		} else if (rnd % 2 != 0) {
			angle -= rnd;
		}

		int delay_prim = 0;
		long time = System.currentTimeMillis();
		if (counter_prim % wpn1.getAmmoCount() == 0) {
			delay_prim = wpn1.getReload_delay();
		}
		if (counter_prim % wpn1.getAmmoCount() != 0) {
			delay_prim = wpn1.getShoot_delay();
		}

		if (Math.abs(lastshot_prim - time) < delay_prim) {
			return null;
		}
		lastshot_prim = time;
		double x = (plane.getCenterX() + Math.cos(Math.toRadians(this.angle)));
		double y = (plane.getCenterY() + Math.sin(Math.toRadians(this.angle)));
		counter_prim--;
		return new Weapons(x, y, angle, wpn1.getDamage(), wpn1,
				wpn1.getImage(), id);
	}

	/**
	 * Shoot Weapon depending on type
	 * 
	 * @param type
	 * @return new Weapons object
	 */
	public Weapons shoot_secondary_1() {
		if (broken) {
			return null;
		}
		int delay_sec_1 = 0;
		long time = System.currentTimeMillis();
		if (counter_sec_1 % wpn2.getAmmoCount() == 0) {
			delay_sec_1 = wpn2.getReload_delay();
		}
		if (counter_sec_1 % wpn2.getAmmoCount() != 0) {
			delay_sec_1 = wpn2.getShoot_delay();
		}

		if (Math.abs(lastshot_sec_1 - time) < delay_sec_1) {
			return null;
		}
		lastshot_sec_1 = time;
		double x = plane.getCenterX();
		double y = plane.getCenterY();
		counter_sec_1--;
		return new Weapons(x, y, angle, wpn2.getDamage(), wpn2,
				wpn2.getImage(), id);
	}

	/**
	 * Shoot Weapon depending on type
	 * 
	 * @param type
	 * @return new Weapons object
	 */
	public Weapons shoot_secondary_2() {
		if (broken) {
			return null;
		}
		int delay_sec_2 = 0;
		long time = System.currentTimeMillis();
		if (counter_sec_2 % wpn3.getAmmoCount() == 0) {
			delay_sec_2 = wpn3.getReload_delay();
		}
		if (counter_sec_2 % wpn3.getAmmoCount() != 0) {
			delay_sec_2 = wpn3.getShoot_delay();
		}
		if (Math.abs(lastshot_sec_2 - time) < delay_sec_2) {
			return null;
		}
		lastshot_sec_2 = time;
		double x = (plane.getCenterX() + Math.cos(Math.toRadians(angle)));
		double y = (plane.getCenterY() + Math.sin(Math.toRadians(angle)) + 5);
		counter_sec_2--;
		return new Weapons(x, y, angle, wpn3.getDamage(), wpn3,
				wpn3.getImage(), id);
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
		Point p1 = new Point((int) xpos, (int) ypos + image.getHeight(null) / 2); // mitte
		Point p2 = new Point((int) xpos, (int) ypos + image.getHeight(null) - 2); // unten
		Point p3 = new Point((int) xpos - (int) speed * 5,
				(int) (ypos + 2 * image.getHeight(null) / 3)); // oben
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
	public void increaseAngle(int amount) {
		if (speed > MAX_SPEED) {
			angle += amount * 0.8;
		} else {
			angle += amount;
		}
		if (angle + amount < 0) {
			angle = angle + amount + 360;
		} else if (angle + amount > 360) {
			angle = angle + amount - 360;
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
		if (angle == 360 || angle == -360) {
			angle = 0;
		}
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

	/**
	 * Returns plane rectangle
	 * 
	 * @return
	 */
	public Rectangle2D getPlane() {
		return plane;
	}

	/**
	 * Returns Center xpos of plane
	 * 
	 * @return
	 */
	public double getCenterX() {
		if (plane != null) {
			return plane.getCenterX();
		} else {
			return xpos;
		}
	}

	/**
	 * Returns Center ypos of plane
	 * 
	 * @return
	 */
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
	public int getAmmo(int id) {
		if (id == 2) {
			return counter_sec_1;
		} else if (id == 1) {
			return counter_prim;
		} else if (id == 3) {
			return counter_sec_2;
		} else {
			return -1;
		}
	}

	/**
	 * Adds ammount of ammo to given weaponstype
	 * 
	 * @param type
	 * @param ammount
	 */
	public void addAmmo(int id, WeaponTypes type, int ammount) {
		if (id == 2) {
			if (counter_sec_1 < type.getAmmoCount()) {
				counter_sec_1 += ammount;
			}
		} else if (id == 1) {
			if (counter_prim < type.getAmmoCount()) {
				counter_prim += ammount;
			}
		} else if (id == 3) {
			if (counter_sec_2 < type.getAmmoCount()) {
				counter_sec_2 += ammount;
			}
		}
	}

	/**
	 * Returns wether this plane is broken or not
	 * 
	 * @return
	 */
	public boolean getBroken() {
		return broken;
	}

	/**
	 * Sets this plane broken state
	 * 
	 * @param broken
	 */
	public void setBroken(boolean broken) {
		this.broken = broken;
	}

	/**
	 * Gets total absolute Speed (vertical + horizontal)
	 * 
	 * @return
	 */
	public double getSpeedTotal() {
		return Math.abs(hspeed) + Math.abs(vspeed);
	}

	/**
	 * Gets total Speed (vertical + horizontal)
	 * 
	 * @return
	 */
	public double getSpeedTotalAbs() {
		return hspeed + vspeed;
	}

	/**
	 * Gets horizontal Speed
	 * 
	 * @return
	 */
	public double getHspeed() {
		return hspeed;
	}

	/**
	 * Gets vertical Speed
	 * 
	 * @return
	 */
	public double getVspeed() {
		return vspeed;
	}

	public WeaponTypes getWeapon(int id) {
		if (id == 1) {
			return wpn1;
		} else if (id == 2) {
			return wpn2;
		} else {
			return wpn3;
		}
	}
}