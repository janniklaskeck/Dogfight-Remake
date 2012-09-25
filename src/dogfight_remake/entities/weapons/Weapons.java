package dogfight_remake.entities.weapons;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import dogfight_remake.entities.Entity;
import dogfight_remake.main.GamePlayState;

;

public class Weapons extends Entity {
	public final static float DEFAULT_SPEED_BULLET = 15;
	public final static float DEFAULT_SPEED_GUIDED = 8;
	public final static float DEFAULT_SPEED_UNGUIDED = 12;
	public final static float DEFAULT_SPEED_BOMB = 12;
	public final static int MAX_LIFETIME_GUN = 50;
	public final static int MAX_LIFETIME_GUIDED_AIR = 230;
	public final static int MAX_LIFETIME_UNGUIDED = 150;
	public final static int MAX_LIFETIME_BOMB = 1000;
	public final static int BOMB_DAMAGE = 50;
	public final static int BOMB_SPLIT_DAMAGE = 30;
	public final static int BOMB_SPLIT_SMALL_DAMAGE = 15;
	private float angle;
	private boolean broken;
	private int height, width;
	private WeaponTypes type;
	private double speed_mod = 0;
	private double speed_mod_bomb = 0.3;
	private int damage = 0;
	private int lifetime = 0;
	private boolean hasTarget = true;
	private int id;
	private Image image;
	private boolean split = false;
	private boolean isSplit = false;
	private double firstHeight;

	public Weapons(float xpos, float ypos, float angle, int damage,
			WeaponTypes type, Image image, int id) {
		super(xpos, ypos);
		if (type == WeaponTypes.GUN || type == WeaponTypes.MINIGUN) {
			this.speed = DEFAULT_SPEED_BULLET;
		} else if (type == WeaponTypes.GUIDED_AIR
				|| type == WeaponTypes.GUIDED_GROUND) {
			this.speed = DEFAULT_SPEED_GUIDED;
		} else if (type == WeaponTypes.UNGUIDED) {
			this.speed = DEFAULT_SPEED_UNGUIDED;
		} else {
			this.speed = DEFAULT_SPEED_BOMB;
		}
		this.angle = angle;
		this.image = image;
		this.broken = false;
		this.damage = damage;
		this.type = type;
		this.id = id;
		this.firstHeight = ypos;
		this.height = image.getHeight();
		this.width = image.getWidth();
	}

	/**
	 * Paints weapons
	 */
	public void render(GameContainer container, Graphics g) {
		lifetime++;
		if (broken) {
			return;
		}
		if (g != null) {
			if (type == WeaponTypes.GUN && lifetime <= MAX_LIFETIME_GUN) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else if (type == WeaponTypes.MINIGUN
					&& lifetime <= MAX_LIFETIME_GUN) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else if (type == WeaponTypes.UNGUIDED
					&& lifetime <= MAX_LIFETIME_UNGUIDED) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else if (type == WeaponTypes.GUIDED_GROUND) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else if (type == WeaponTypes.GUIDED_AIR
					&& lifetime <= MAX_LIFETIME_GUIDED_AIR) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else if (type == WeaponTypes.RADAR_AIR) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else if (type == WeaponTypes.RADAR_GROUND) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else if (type == WeaponTypes.BOMB) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else if (type == WeaponTypes.BOMB_SPLIT) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else if (type == WeaponTypes.BOMB_SPLIT_SMALL) {
				image.setRotation(angle);
				image.draw(xpos, ypos);
			} else {
				broken = true;
			}
		}

	}

	/**
	 * Moves weapon
	 * 
	 * @param delta
	 */
	public void move(double delta) {
		super.move();

		if (type == WeaponTypes.GUN || type == WeaponTypes.MINIGUN) {
			double hspeed = speed * Math.cos(Math.toRadians(angle));
			double vspeed = speed * Math.sin(Math.toRadians(angle));
			xpos += hspeed;
			ypos += vspeed;
		} else if (type == WeaponTypes.UNGUIDED) {
			if (speed_mod <= 1) {
				speed_mod = speed_mod + 0.05;
			}
			double hspeed = (speed * speed_mod)
					* Math.cos(Math.toRadians(angle));
			double vspeed = (speed * speed_mod)
					* Math.sin(Math.toRadians(angle));
			xpos += hspeed;
			ypos += vspeed;
		} else if (type == WeaponTypes.GUIDED_AIR) {
			if (speed_mod <= 1) {
				speed_mod = speed_mod + 0.05;
			}
			double hspeed = (speed * speed_mod)
					* Math.cos(Math.toRadians(angle));
			double vspeed = (speed * speed_mod)
					* Math.sin(Math.toRadians(angle));
			xpos += hspeed;
			ypos += vspeed;
		} else if (type == WeaponTypes.BOMB) {
			angleCheck();
			if (angle <= 90 && angle >= 0) {
				angle += (90 - angle) / 75;
			} else if (angle >= 270 && angle < 360) {
				angle += (angle - 270) / 75;
			} else if (angle < 270 && angle > 180) {
				angle += (angle - 270) / 75;
			} else if (angle > 90 && angle <= 180) {
				angle += (90 - angle) / 75;
			}
			if (speed_mod_bomb >= 0.2) {
				speed_mod_bomb = speed_mod_bomb - 0.01;
			}
			double hspeed = (speed * speed_mod_bomb)
					* Math.cos(Math.toRadians(angle));
			xpos += hspeed;
			ypos += GamePlayState.GRAVITY * 4 * speed_mod_bomb;
		} else if (type == WeaponTypes.BOMB_SPLIT) {
			angleCheck();
			if (ypos - firstHeight > 300 && !isSplit) {
				split = true;
			} else {
				split = false;
			}
			if (angle <= 90 && angle >= 0) {
				angle += (90 - angle) / 75;
			} else if (angle >= 270 && angle <= 360) {
				angle += (angle - 270) / 75;
			} else if (angle < 270 && angle > 180) {
				angle += (angle - 270) / 75;
			} else if (angle > 90 && angle <= 180) {
				angle += (90 - angle) / 75;
			}
			if (speed_mod_bomb >= 0.2) {
				speed_mod_bomb = speed_mod_bomb - 0.01;
			}
			double hspeed = (speed * speed_mod_bomb)
					* Math.cos(Math.toRadians(angle));
			xpos += hspeed;
			ypos += GamePlayState.GRAVITY * 4 * speed_mod_bomb;
		} else if (type == WeaponTypes.BOMB_SPLIT_SMALL) {
			if (angle <= 90 && angle >= 0) {
				angle += (90 - angle) / 75;
			} else if (angle >= 270 && angle <= 360) {
				angle += (angle - 270) / 75;
			} else if (angle < 270 && angle > 180) {
				angle += (angle - 270) / 75;
			} else if (angle > 90 && angle <= 180) {
				angle += (90 - angle) / 75;
			}
			if (speed_mod_bomb >= 0.2) {
				speed_mod_bomb = speed_mod_bomb - 0.01;
			}
			double hspeed = (speed * speed_mod_bomb)
					* Math.cos(Math.toRadians(angle));
			xpos += hspeed;
			ypos += GamePlayState.GRAVITY * 4 * speed_mod_bomb;
		}
	}

	private void angleCheck() {
		if (angle >= 360) {
			angle = 0;
		}
	}
	
	/**
	 * Rotates weapon adds amount to angle
	 * 
	 * @param amount
	 */
	public void increaseAngle(float amount) {
		angle = angle + amount;

		if (angle == 360 || angle == -360) {
			angle = 0;
		}
	}

	/**
	 * Returns if a weapon can split itself
	 * 
	 * @return
	 */
	public boolean canSplit() {
		return split;
	}

	/**
	 * Sets a weapon to split
	 * 
	 * @return
	 */
	public void setSplit() {
		isSplit = true;
	}

	/**
	 * Returns angle of Weapon
	 * 
	 * @return
	 */
	public int getAngle() {
		return (int) angle;
	}

	/**
	 * Sets angle of plane
	 * 
	 * @return
	 */
	public void setAngle(float angle) {
		this.angle = angle;
	}

	/**
	 * Sets this weapons hit status to true
	 */
	public void setHit() {
		broken = true;
	}

	/**
	 * Returns wether this weapon has hit something
	 * 
	 * @return
	 */
	public boolean isHit() {
		return broken;
	}

	/**
	 * Returns Weapon type
	 * 
	 * @return
	 */
	public WeaponTypes getType() {
		return type;
	}

	/**
	 * Returns width of weapon
	 * 
	 * @return
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Returns height of weapon
	 * 
	 * @return
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Returns damage of weapon
	 * 
	 * @return
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets xpos of weapon
	 */
	public void setXpos(float xpos) {
		this.xpos = xpos;
	}

	/**
	 * Sets ypos of weapon
	 */
	public void setYpos(float ypos) {
		this.ypos = ypos;
	}

	/**
	 * Returns if weapon is homing and has a target
	 * 
	 * @return
	 */
	public boolean hasTarget() {
		return hasTarget;
	}

	/**
	 * Sets a target for weapon
	 * 
	 * @param target
	 */
	public void setTarget(boolean target) {
		this.hasTarget = target;
	}

	/**
	 * Returns player id to whom this weapon belongs
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}

	/**
	 * Sets player id to whom this weapon belongs
	 * 
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Returns weapon image
	 * 
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets weapon image
	 * 
	 * @param id
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Returns wether a weapon is 'alive'. Alive means not destroyed or max
	 * range achieved
	 * 
	 * @return
	 */
	public boolean isAlive() {
		if (type == WeaponTypes.GUN || type == WeaponTypes.MINIGUN) {
			if (lifetime > MAX_LIFETIME_GUN) {
				return false;
			}
		} else if (type == WeaponTypes.GUIDED_AIR) {
			if (lifetime > MAX_LIFETIME_GUIDED_AIR) {
				return false;
			}
		} else if (type == WeaponTypes.UNGUIDED) {
			if (lifetime > MAX_LIFETIME_UNGUIDED) {
				return false;
			}
		} else if (type == WeaponTypes.GUIDED_GROUND) {

		} else if (type == WeaponTypes.BOMB || type == WeaponTypes.BOMB_SPLIT) {
			if (lifetime > MAX_LIFETIME_BOMB) {
				return false;
			}
		}
		return true;
	}
}
