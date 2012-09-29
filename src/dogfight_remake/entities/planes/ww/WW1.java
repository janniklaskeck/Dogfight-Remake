package dogfight_remake.entities.planes.ww;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import dogfight_remake.entities.Entity;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.GlbVar;

public class WW1 extends Entity {

	private Random random;
	// constants
	// default values
	public final static float MAX_SPEED = 7;

	private Image image;

	private Rectangle plane;
	private Ellipse aim;
	protected int id;
	private int hitpoints;
	private float angle;
	private long lastshot_prim, lastshot_sec_1, lastshot_sec_2;
	private int counter_prim;
	private int counter_sec_1;
	private int counter_sec_2;
	private boolean broken;
	public float hspeed, vspeed;
	private WeaponTypes wpn1;
	private WeaponTypes wpn2;
	private WeaponTypes wpn3;
	private boolean stall;

	public WW1(int id, float xpos, float ypos, float angle, Image image,
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
	public void move(int delta) {
		hspeed = Math.abs(speed)
				* (float) Math.cos(Math.toRadians(angle) * (float) delta / 17);
		vspeed = Math.abs(speed)
				* (float) Math.sin(Math.toRadians(angle) * (float) delta / 17);
		if (Math.abs(hspeed) + Math.abs(vspeed) < 1.3 || stall) {
			stall = true;
			xpos += hspeed;
			ypos += vspeed + GamePlayState.GRAVITY;
			if (stall && vspeed > 3) {
				stall = false;
			} else if (vspeed + GamePlayState.GRAVITY < 0) {
				stall = false;
			}
		} else {
			xpos += hspeed;
			GlbVar.cx += hspeed;
			ypos += vspeed;
			GlbVar.cy += vspeed;
		}
	}

	/**
	 * Shoot Weapon depending on type
	 * 
	 * @param type
	 * @return new Weapons object
	 */
	public Weapons shoot_primary() {
		float angle = this.angle;
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
		float x = (float) (plane.getCenterX() + Math.cos(Math.toRadians(angle)));
		float y = (float) (plane.getCenterY() + Math.sin(Math.toRadians(angle)) + 5);
		counter_prim--;
		return new Weapons(x, y, angle, wpn1.getDamage(), wpn1, 0,
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
		float x = (float) (plane.getCenterX() + Math.cos(Math.toRadians(angle)));
		float y = (float) (plane.getCenterY() + Math.sin(Math.toRadians(angle)) + 5);
		counter_sec_1--;
		return new Weapons(x, y, angle, wpn2.getDamage(), wpn2, 0,
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
		float x = (float) (plane.getCenterX() + Math.cos(Math.toRadians(angle)));
		float y = (float) (plane.getCenterY() + Math.sin(Math.toRadians(angle)) + 5);
		counter_sec_2--;
		return new Weapons(x, y, angle, wpn3.getDamage(), wpn3, 0,
				wpn3.getImage(), id);
	}

	/**
	 * Paint method
	 */
	@Override
	public void render(GameContainer container, Graphics g, int delta) {
		if (broken) {
			return;
		}
		plane = new Rectangle(xpos, ypos, image.getWidth(), image.getHeight());
		float x = (float) (plane.getCenterX() + Math.cos(Math.toRadians(angle)) * 100);
		float y = (float) (plane.getCenterY() + Math.sin(Math.toRadians(angle)) * 100);
		aim = new Ellipse(x, y, 10, 10);
		// g.draw(aim);
		image.setRotation(angle);
		image.draw(xpos, ypos);
	}

	/**
	 * Rotates plane
	 * 
	 * @param angle
	 */
	public void increaseAngle(float f) {
		if (speed > MAX_SPEED) {
			angle += f * 0.8;
		} else {
			angle += f;
		}
		if (angle + f < 0) {
			angle += f + 360;
		} else if (angle + f > 360) {
			angle += f - 360;
		}
		if (angle >= 360 || angle <= -360) {
			angle = 0;
		}
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
		if (hitpoints < 0) {
			return 0;
		} else {
			return hitpoints;
		}

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
	public Rectangle getPlane() {
		return plane;
	}

	public Ellipse getAim() {
		return aim;
	}

	/**
	 * Returns Center xpos of plane
	 * 
	 * @return
	 */
	public float getCenterX() {
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
	public float getCenterY() {
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
	public float getSpeedTotal() {
		return Math.abs(hspeed) + Math.abs(vspeed);
	}

	/**
	 * Gets total Speed (vertical + horizontal)
	 * 
	 * @return
	 */
	public float getSpeedTotalAbs() {
		return hspeed + vspeed;
	}

	/**
	 * Gets horizontal Speed
	 * 
	 * @return
	 */
	public float getHspeed() {
		return hspeed;
	}

	/**
	 * Gets vertical Speed
	 * 
	 * @return
	 */
	public float getVspeed() {
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

	public boolean isInStall() {
		return stall;
	}

}