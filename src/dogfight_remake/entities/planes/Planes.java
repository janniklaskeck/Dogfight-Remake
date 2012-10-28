package dogfight_remake.entities.planes;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

import dogfight_remake.entities.Entity;
import dogfight_remake.entities.Explosion;
import dogfight_remake.entities.weapons.WeaponTypes_Interface;
import dogfight_remake.entities.weapons.WeaponTypes_Primary;
import dogfight_remake.entities.weapons.WeaponTypes_Secondary;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.Var;

public class Planes extends Entity {
    private Random random;
    private Image image;
    private Ellipse aim;
    private Polygon collision;
    private Rectangle plane;
    private int id;
    private int hitpoints;
    private float angle;
    private long lastshot_prim_1, lastshot_prim_2, lastshot_sec_1,
	    lastshot_sec_2;
    private float heat_prim;
    private int ammo_sec_1;
    private int ammo_sec_2;
    private boolean broken;
    private float hspeed, vspeed;
    private WeaponTypes_Primary wpn1;
    private WeaponTypes_Primary wpn2;
    private WeaponTypes_Secondary wpn3;
    private WeaponTypes_Secondary wpn4;
    private boolean stall;
    private float speed_mod;
    private boolean accel = false;
    private boolean brake = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean shoot_prim1 = false;
    private boolean shoot_prim2 = false;
    private boolean shoot_sec1 = false;
    private boolean shoot_sec2 = false;
    private PlaneTypes_Interface type;
    private long respawn_timer;

    float xpos_reset;
    float ypos_reset;
    int hitpoints_reset;
    float angle_reset;
    int heat_prim_reset;
    int ammo_sec_1_reset;
    int ammo_sec_2_reset;

    public Planes(int id, float xpos, float ypos, float angle,
	    PlaneTypes_Interface type) {
	super(xpos, ypos);
	this.angle = angle;
	this.id = id;
	this.hitpoints = type.getHitpoints();
	this.speed_mod = type.getSpeed();
	if (id == 2) {
	    this.image = type.getImage().getFlippedCopy(false, true);
	} else {
	    this.image = type.getImage();
	}
	this.lastshot_prim_1 = 0;
	this.lastshot_prim_2 = 0;
	this.lastshot_sec_1 = 0;
	this.lastshot_sec_2 = 0;
	this.broken = false;
	this.wpn1 = type.getWpn1();
	this.wpn2 = type.getWpn2();
	this.wpn3 = type.getWpn3();
	this.wpn4 = type.getWpn4();
	this.heat_prim = 0;
	this.ammo_sec_1 = wpn3.getAmmoCount();
	this.ammo_sec_2 = wpn4.getAmmoCount();
	this.type = type;
	this.setRespawn_timer(Var.RESPAWNTIME_PLAYER);
	speed = 1.5f;
	xpos_reset = xpos;
	ypos_reset = ypos;
	hitpoints_reset = hitpoints;
	angle_reset = angle;
	heat_prim_reset = 0;
	ammo_sec_1_reset = ammo_sec_1;
	ammo_sec_2_reset = ammo_sec_2;
    }

    /**
     * update method
     * 
     * @param delta
     */

    public void update(float delta) {
	float deltaX, deltaY;


	if (broken && respawn_timer >= Var.RESPAWNTIME_PLAYER) {
	    GamePlayState.explosions.add(new Explosion(xpos, ypos, 4));
	    Var.explode.play(1, Var.sounds_volume);
	}
	if (hitpoints <= 0) {
	    respawn_timer -= delta;
	}
	if (!broken) {
	    hspeed = Math.abs(speed * speed_mod)
		    * (float) Math.cos(Math.toRadians(angle) * delta / 17);
	    vspeed = Math.abs(speed * speed_mod)
		    * (float) Math.sin(Math.toRadians(angle) * delta / 17);
	    if (Math.abs(hspeed) + Math.abs(vspeed) < 1.3f || stall) {
		stall = true;
		xpos += hspeed;
		ypos += vspeed + Var.GRAVITY;
		if (stall && vspeed > 3) {
		    stall = false;
		} else if (vspeed + Var.GRAVITY < 0) {
		    stall = false;
		}
	    } else {
		xpos += hspeed;
		Var.cx += hspeed;
		ypos += vspeed;
		Var.cy += vspeed;
	    }

	    if (!accel && !brake) {
		if (vspeed < 0) {
		    this.decSpeed(0.08f, true);
		} else {
		    this.decSpeed(0.04f, true);
		}
	    }
	    if (moveLeft) {
		increaseAngle(-type.getTurnAngle() * delta);
	    }
	    if (moveRight) {
		increaseAngle(type.getTurnAngle() * delta);
	    }
	    if (accel) {
		if (vspeed < 0 && !stall) {
		    incSpeed(type.getAccel() / 2 * delta);
		} else if (vspeed >= 0 && !stall) {
		    incSpeed(type.getAccel() * delta);
		} else if (vspeed >= 0 && stall) {
		    incSpeed(type.getAccel() / 2 * delta);
		} else {
		    incSpeed(type.getAccel() / 20 * delta);
		}
	    }
	    if (brake) {
		if (speed <= 0.6) {
		    setSpeed(0.6f);
		} else {
		    decSpeed(0.035f, false);
		}
	    }
	    if (shoot_prim1) {
		shoot_primary_1();
	    }
	    if (shoot_prim2) {
		shoot_primary_2();
	    }
	    if (shoot_sec1) {
		shoot_secondary_1();
	    }
	    if (shoot_sec2) {
		shoot_secondary_2();
	    }
	}

	if (!broken) {
	    if (getAim() != null) {
		plane = new Rectangle((int) getXpos(), (int) getYpos(),
			image.getWidth(), image.getHeight());
		deltaX = getCenterX() - getAim().getCenterX();
		deltaY = getCenterY() - getAim().getCenterY();
		collision = (Polygon) plane.transform(Transform
			.createRotateTransform(
				(float) Math.atan2(deltaY, deltaX),
				getCenterX(), getCenterY()));
	    }
	}
    }

    /**
     * Paint method
     */

    public void render(GameContainer container, Graphics g, float delta) {
	if (broken)
	    return;
	float x = (float) (getCenterX() + Math.cos(Math.toRadians(angle)) * 100);
	float y = (float) (getCenterY() + Math.sin(Math.toRadians(angle)) * 100);
	aim = new Ellipse(x, y, 10, 10);
	g.draw(aim);
	image.setRotation(angle);
	image.draw(xpos, ypos);

    }

    /**
     * Shoot Weapon depending on type
     * 
     * @param type
     * @return new Weapons object
     */
    public void shoot_primary_1() {
	float angle = this.angle;
	if (broken || heat_prim >= 100) {
	    return;
	}
	random = new Random();
	double rnd = random.nextDouble() * 2;
	double rnd1 = random.nextInt();
	if (rnd1 % 2 == 0) {
	    angle += rnd;
	} else if (rnd % 2 != 0) {
	    angle -= rnd;
	}
	long time = System.currentTimeMillis();
	if (Math.abs(lastshot_prim_1 - time) < wpn1.getShoot_delay()) {
	    return;
	}
	lastshot_prim_1 = time;
	float x = getCenterX();
	float y = getCenterY();
	addHeat_prim(wpn1.getHeat());
	wpn1.getSound().play(1, Var.sounds_volume);
	GamePlayState.weapons.add(new Weapons(x, y, angle, wpn1, 0, id));
    }

    /**
     * Shoot Weapon depending on type
     * 
     * @param type
     * @return new Weapons object
     */
    public void shoot_primary_2() {
	float angle = this.angle;
	if (broken || heat_prim >= 100) {
	    return;
	}
	random = new Random();
	double rnd = random.nextDouble() * 2;
	double rnd1 = random.nextInt();
	if (rnd1 % 2 == 0) {
	    angle += rnd;
	} else if (rnd % 2 != 0) {
	    angle -= rnd;
	}
	long time = System.currentTimeMillis();
	if (Math.abs(lastshot_prim_2 - time) < wpn2.getShoot_delay()) {
	    return;
	}
	lastshot_prim_2 = time;
	float x = getCenterX();
	float y = getCenterY();
	addHeat_prim(wpn2.getHeat());
	wpn2.getSound().play(1, Var.sounds_volume);
	GamePlayState.weapons.add(new Weapons(x, y, angle, wpn2, 0, id));
    }

    /**
     * Shoot Weapon depending on type
     * 
     * @param type
     * @return new Weapons object
     */
    public void shoot_secondary_1() {
	if (broken || ammo_sec_1 == 0) {
	    return;
	}
	long time = System.currentTimeMillis();
	if (Math.abs(lastshot_sec_1 - time) < wpn3.getShoot_delay()) {
	    return;
	}
	lastshot_sec_1 = time;
	float x = getCenterX();
	float y = getCenterY();
	ammo_sec_1--;
	wpn3.getSound().play(1, Var.sounds_volume);
	GamePlayState.weapons.add(new Weapons(x, y, angle, wpn3, 0, id));
    }

    /**
     * Shoot Weapon depending on type
     * 
     * @param type
     * @return new Weapons object
     */
    public void shoot_secondary_2() {
	if (broken || ammo_sec_2 == 0) {
	    return;
	}
	long time = System.currentTimeMillis();
	if (Math.abs(lastshot_sec_2 - time) < wpn4.getShoot_delay()) {
	    return;
	}
	lastshot_sec_2 = time;
	float x = getCenterX();
	float y = getCenterY();
	ammo_sec_2--;
	wpn4.getSound().play(1, Var.sounds_volume);
	GamePlayState.weapons.add(new Weapons(x, y, angle, wpn4, 0, id));
    }

    /**
     * Resets the plane to starting values;
     */
    public void reset() {
	xpos = xpos_reset;
	ypos = ypos_reset;
	hitpoints = hitpoints_reset;
	angle = angle_reset;
	heat_prim = heat_prim_reset;
	ammo_sec_1 = ammo_sec_1_reset;
	ammo_sec_2 = ammo_sec_2_reset;
	broken = false;
	stall = false;
    }

    /**
     * Rotates plane
     * 
     * @param angle
     */
    public void increaseAngle(float f) {
	if (speed > Var.PLANES_MAX_SPEED) {
	    angle += f * 0.8f;
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
     * Decreases speed the given amount and stops if base is true at base value
     * 
     * @param speed
     * @param base
     */
    public void decSpeed(float speed, boolean base) {
	if (base && this.speed < 2f) {
	    this.speed = 2f;
	} else if (!base && this.speed < 0.5f) {
	    this.speed = 0.5f;
	} else {
	    this.speed -= speed;
	}
    }

    /**
     * Increases speed the given amount, stops at max speed
     * 
     * @param speed
     */
    public void incSpeed(float speed) {
	if (this.speed + speed > Var.PLANES_MAX_SPEED * type.getSpeed()) {
	    this.speed = Var.PLANES_MAX_SPEED * type.getSpeed();
	} else {
	    this.speed += speed;
	}
    }

    /**
     * Sets Hitpoints to desired value
     * 
     * @param hitpoints
     */
    public void setHitpoints(int hitpoints) {
	this.hitpoints = hitpoints;
	if (hitpoints <= 0)
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

    public Ellipse getAim() {
	return aim;
    }

    /**
     * Returns Center xpos of plane
     * 
     * @return
     */
    public float getCenterX() {
	return xpos + image.getWidth() / 2;
    }

    /**
     * Returns Center ypos of plane
     * 
     * @return
     */
    public float getCenterY() {
	return ypos + image.getHeight() / 2;
    }

    /**
     * Returns current ammo count
     * 
     * @param type
     * @return
     */
    public int getAmmo(int id) {
	if (id == 3) {
	    return ammo_sec_1;
	} else if (id == 4) {
	    return ammo_sec_2;
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
    public void addAmmo(int id, WeaponTypes_Interface type, int amount) {
	if (id == 3) {
	    if (ammo_sec_1 < type.getAmmoCount()) {
		ammo_sec_1 += amount;
	    }
	} else if (id == 4) {
	    if (ammo_sec_2 < type.getAmmoCount()) {
		ammo_sec_2 += amount;
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

    public WeaponTypes_Interface getWeapon(int id) {
	if (id == 1) {
	    return wpn1;
	} else if (id == 2) {
	    return wpn2;
	} else if (id == 3) {
	    return wpn3;
	} else if (id == 4) {
	    return wpn4;
	} else {
	    return null;
	}
    }

    public boolean isInStall() {
	return stall;
    }

    public boolean isBrake() {
	return brake;
    }

    public void setBrake(boolean brake) {
	this.brake = brake;
    }

    public boolean isAccel() {
	return accel;
    }

    public void setAccel(boolean accel) {
	this.accel = accel;
    }

    public boolean isMoveRight() {
	return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
	this.moveRight = moveRight;
    }

    public boolean isMoveLeft() {
	return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
	this.moveLeft = moveLeft;
    }

    public boolean isShoot_prim1() {
	return shoot_prim1;
    }

    public void setShoot_prim1(boolean shoot_prim1) {
	this.shoot_prim1 = shoot_prim1;
    }

    public boolean isShoot_prim2() {
	return shoot_prim2;
    }

    public void setShoot_prim2(boolean shoot_prim2) {
	this.shoot_prim2 = shoot_prim2;
    }

    public boolean isShoot_sec1() {
	return shoot_sec1;
    }

    public void setShoot_sec1(boolean shoot_sec1) {
	this.shoot_sec1 = shoot_sec1;
    }

    public boolean isShoot_sec2() {
	return shoot_sec2;
    }

    public void setShoot_sec2(boolean shoot_sec2) {
	this.shoot_sec2 = shoot_sec2;
    }

    public long getRespawn_timer() {
	return respawn_timer;
    }

    public void setRespawn_timer(long respawn_timer) {
	this.respawn_timer = respawn_timer;
    }

    public float getHeat_prim() {
	return heat_prim;
    }

    public void setHeat_prim(float heat_prim) {
	this.heat_prim = heat_prim;
    }

    public void addHeat_prim(float f) {
	if (this.heat_prim + f > 100) {
	    this.heat_prim = 100;
	} else {
	    this.heat_prim += f;
	}

    }

    public Polygon getCollision() {
	return collision;
    }

    public void setCollision(Polygon collision) {
	this.collision = collision;
    }
}