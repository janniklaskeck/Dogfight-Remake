package dogfight_remake.entities.weapons;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

import dogfight_remake.entities.Entity;
import dogfight_remake.entities.Explosion;
import dogfight_remake.entities.ai.TurretAi;
import dogfight_remake.entities.planes.Planes;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.Var;

public class Weapons extends Entity {
    private float angle;
    private boolean broken;
    private int height, width;
    private WeaponTypes_Interface type;
    private float speed_mod = 0;
    private float speed_mod_bomb = 0.3f;
    private int damage = 0;
    private boolean hasTarget = true;
    private int id;
    private Image image;
    private boolean split = false;
    private boolean isSplit = false;
    private float firstHeight;
    private int time;
    private Sound sound;
    private Random rnd;
    private boolean hitTarget = false;

    public Weapons(float xpos, float ypos, float angle, WeaponTypes_Interface type,
	    int time, int id) {
	super(xpos, ypos, angle);
	this.speed = type.getSpeed();
	this.image = type.getImage();
	this.broken = false;
	this.damage = type.getDamage();
	this.type = type;
	this.id = id;
	this.firstHeight = ypos;
	this.height = image.getHeight();
	this.width = image.getWidth();
	this.time = time;
	this.sound = type.getSound();
    }

    /**
     * Paints weapons
     */

    public void render(GameContainer container, Graphics g, float delta) {
	time += delta;
	if (broken) {
	    return;
	}
	if (type == WeaponTypes_Primary.MM30S && time <= type.getLife_time()) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Primary.MINIGUN && time <= type.getLife_time()) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Secondary.UNGUIDED && time <= type.getLife_time()) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Secondary.GUIDED_GROUND
		&& time <= type.getLife_time()) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Secondary.GUIDED_AIR
		&& time <= type.getLife_time()) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Secondary.RADAR_AIR) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Secondary.RADAR_GROUND) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Secondary.BOMB) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Secondary.BOMB_SPLIT) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Secondary.BOMB_SPLIT_SMALL) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	} else if (type == WeaponTypes_Primary.TURRET_MIDDLE
		&& time <= type.getLife_time()) {
	    image.setRotation(angle);
	    image.draw(xpos, ypos);
	}
    }

    /**
     * Moves weapon
     * 
     * @param delta
     */

    public void update(float delta) {
	if (hitTarget) {
	    GamePlayState.explosions.add(new Explosion(xpos, ypos, type
		    .getExploSize()));
	    Var.hit.play(1, Var.sounds_volume);
	    broken = true;
	}
	rnd = new Random();
	if (type == WeaponTypes_Primary.MM30S || type == WeaponTypes_Primary.MINIGUN
		|| type == WeaponTypes_Primary.TURRET_MIDDLE) {
	    float hspeed = speed * (float) Math.cos(Math.toRadians(angle))
		    * delta / 17;
	    float vspeed = speed * (float) Math.sin(Math.toRadians(angle))
		    * delta / 17;
	    xpos += hspeed;
	    ypos += vspeed;
	} else if (type == WeaponTypes_Secondary.UNGUIDED) {
	    if (speed_mod <= 1) {
		speed_mod = speed_mod + 0.05f;
	    }
	    float hspeed = (speed * speed_mod)
		    * (float) Math.cos(Math.toRadians(angle)) * delta / 17;
	    float vspeed = (speed * speed_mod)
		    * (float) Math.sin(Math.toRadians(angle)) * delta / 17;
	    xpos += hspeed;
	    ypos += vspeed;
	} else if (type == WeaponTypes_Secondary.GUIDED_AIR) {
	    if (speed_mod <= 1) {
		speed_mod = speed_mod + 0.05f;
	    }
	    float hspeed = (speed * speed_mod)
		    * (float) Math.cos(Math.toRadians(angle)) * delta / 17;
	    float vspeed = (speed * speed_mod)
		    * (float) Math.sin(Math.toRadians(angle)) * delta / 17;
	    xpos += hspeed;
	    ypos += vspeed;
	} else if (type == WeaponTypes_Secondary.GUIDED_GROUND) {
	    if (GamePlayState.r.turret.getHitpoints() > 0 && hasTarget()) {
		update(GamePlayState.r.player1, GamePlayState.r.turret, delta);
	    } else {
		setTarget(false);
		if (speed_mod <= 1) {
		    speed_mod = speed_mod + 0.05f;
		}
		float hspeed = (speed * speed_mod)
			* (float) Math.cos(Math.toRadians(angle)) * delta / 17;
		float vspeed = (speed * speed_mod)
			* (float) Math.sin(Math.toRadians(angle)) * delta / 17;
		xpos += hspeed;
		ypos += vspeed;
	    }
	} else if (type == WeaponTypes_Secondary.BOMB) {
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
		speed_mod_bomb = speed_mod_bomb - 0.01f;
	    }
	    float hspeed = (speed * speed_mod_bomb)
		    * (float) Math.cos(Math.toRadians(angle)) * delta / 17;
	    xpos += hspeed;
	    ypos += Var.GRAVITY * 4 * speed_mod_bomb;
	} else if (type == WeaponTypes_Secondary.BOMB_SPLIT) {
	    angleCheck();
	    if (ypos - firstHeight > 700 && !isSplit) {
		for (int i = 0; i < 20; i++) {
		    GamePlayState.weapons.add(new Weapons(xpos, ypos
			    - rnd.nextInt(50), rnd.nextFloat() * 180,
			    WeaponTypes_Secondary.BOMB_SPLIT_SMALL, 0, id));
		}
		setSplit();
		setHit();
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
	    if (speed_mod_bomb >= 1.5f) {
		speed_mod_bomb = speed_mod_bomb - 0.02f;
	    }
	    float hspeed = (speed * speed_mod_bomb)
		    * (float) Math.cos(Math.toRadians(angle)) * delta / 17;
	    xpos += hspeed;
	    ypos += Var.GRAVITY * 3 * speed_mod_bomb;
	} else if (type == WeaponTypes_Secondary.BOMB_SPLIT_SMALL) {
	    if (angle <= 90 && angle >= 0) {
		angle += (90 - angle) / 75;
	    } else if (angle >= 270 && angle <= 360) {
		angle += (angle - 270) / 75;
	    } else if (angle < 270 && angle > 180) {
		angle += (angle - 270) / 75;
	    } else if (angle > 90 && angle <= 180) {
		angle += (90 - angle) / 75;
	    }
	    if (speed_mod_bomb <= 1.5f) {
		speed_mod_bomb = speed_mod_bomb + 0.05f;
	    }
	    float hspeed = (speed / 5)
		    * (float) Math.cos(Math.toRadians(angle)) * delta / 17;
	    xpos += hspeed;
	    ypos += Var.GRAVITY * speed_mod_bomb;
	}
    }

    public void update(Planes pln1, Planes pln2, float delta) {
	if (hitTarget) {
	    GamePlayState.explosions.add(new Explosion(xpos, ypos, type
		    .getExploSize()));
	    Var.hit.play(1, Var.sounds_volume);
	    broken = true;
	}
	float speed = getType().getSpeed();
	float plnx = pln2.getCenterX();
	float plny = pln2.getCenterY();
	float deltaX = plnx - xpos;
	float deltaY = plny - ypos;

	double atan2 = Math.atan2(deltaY, deltaX);
	// change atan2 to 0-360 degrees
	if (atan2 < 0) {
	    atan2 = Math.abs(atan2);
	} else {
	    atan2 = 2 * Math.PI - atan2;
	}
	float angle = Math.round(Math.toDegrees(atan2));
	if (angle >= 360) {
	    angle = 0;
	}
	// with help from http://krinstudio.com/?p=523
	angle = (360 - (int) angle) % 360;

	// Keep them on the right side. (Direction is in degrees, not radians)
	if (getAngle() < 0)
	    increaseAngle(360);
	if (angle < getAngle())
	    angle += 360;

	// Find the difference in the angle.
	float angleDifference = angle - getAngle();

	// Turn the actual direction towards the target direction.
	if (((angleDifference < 180) && (angleDifference > 0))
		|| ((angleDifference < -180))) {
	    increaseAngle(1.5f);
	} else if (angleDifference == 0) {
	    increaseAngle(0);
	} else {
	    increaseAngle(-1.5f);
	}
	// speed calculation
	float hspeed = (float) (speed * Math.cos(Math.toRadians(getAngle()))
		* (float) delta / 17);
	float vspeed = (float) (speed * Math.sin(Math.toRadians(getAngle()))
		* (float) delta / 17);
	setXpos(getXpos() + hspeed);
	setYpos(getYpos() + vspeed);
    }

    public void update(Planes pln1, TurretAi ta, float delta) {
	if (hitTarget) {
	    GamePlayState.explosions.add(new Explosion(xpos, ypos, type
		    .getExploSize()));
	    Var.hit.play(1, Var.sounds_volume);
	    broken = true;
	}
	float speed = getType().getSpeed();
	float plnx = ta.getCenterX();
	float plny = ta.getCenterY();
	float deltaX = plnx - xpos;
	float deltaY = plny - ypos;

	double atan2 = Math.atan2(deltaY, deltaX);
	// change atan2 to 0-360 degrees
	if (atan2 < 0) {
	    atan2 = Math.abs(atan2);
	} else {
	    atan2 = 2 * Math.PI - atan2;
	}
	float angle = Math.round(Math.toDegrees(atan2));
	if (angle >= 360) {
	    angle = 0;
	}
	// with help from http://krinstudio.com/?p=523
	angle = (360 - (int) angle) % 360;

	// Keep them on the right side. (Direction is in degrees, not radians)
	if (getAngle() < 0)
	    increaseAngle(360);
	if (angle < getAngle())
	    angle += 360;

	// Find the difference in the angle.
	float angleDifference = angle - getAngle();

	// Turn the actual direction towards the target direction.
	if (((angleDifference < 180) && (angleDifference > 0))
		|| ((angleDifference < -180))) {
	    increaseAngle(1.5f);
	} else if (angleDifference == 0) {
	    increaseAngle(0);
	} else {
	    increaseAngle(-1.5f);
	}
	// speed calculation
	float hspeed = (float) (speed * Math.cos(Math.toRadians(getAngle()))
		* (float) delta / 17);
	float vspeed = (float) (speed * Math.sin(Math.toRadians(getAngle()))
		* (float) delta / 17);
	setXpos(getXpos() + hspeed);
	setYpos(getYpos() + vspeed);

    }

    private void angleCheck() {
	if (angle >= 360) {
	    angle = 0;
	}
    }

    public float getAngle() {
	if (angle >= 360) {
	    angle = 0;
	}
	return angle;
    }

    public void setAngle(float amount) {
	angle = amount;
    }

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
    public WeaponTypes_Interface getType() {
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
	if (time > type.getLife_time()) {
	    return false;
	}
	return true;
    }

    public Sound getSound() {
	return sound;
    }

    public void setSound(Sound sound) {
	this.sound = sound;
    }

    public boolean isHitTarget() {
	return hitTarget;
    }

    public void setHitTarget() {
	this.hitTarget = true;

    }
}
