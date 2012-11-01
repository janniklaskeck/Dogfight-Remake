package dogfight_remake.entities.weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

import dogfight_remake.main.Var;

public enum WeaponTypes_Secondary implements WeaponTypes_Interface {

    // Type(Name, Damage, Ammo Count, Speed, Reload Delay, Shoot Delay, Heat
    // Lifetime(ms), Explosion size, Image)
    // Type(Name, Damage, Ammo Count, Speed, Reload Delay, Shoot Delay, Heat
    // creation, Lifetime(ms), Explosion size, Image)
    BOMB("Bomb", 40, 2, 15, 0, 8000, 300, -1, 10000, 5, Var.img_bomb1,
	    Var.sec_bomb_drop), 
	    BOMB_SPLIT("Split Bomb", 30, 2, 15, 0, 8000, 300,
	    -1, 10000, 4, Var.img_bomb1, Var.sec_bomb_drop), 
	    BOMB_SPLIT_SMALL("Split Bomb(small)", 10, 5, 15, 0, 8000, 300, -1, 10000, 3,
	    Var.img_bomb1_split, Var.sec_bomb_drop), 
	    UNGUIDED("Unguided Missile", 25, 8, 15, 0, 4000, 350, -1, 2000, 3,
	    Var.img_missile1, Var.sec_missile1), 
	    GUIDED_AIR("Guided Air Missile", 20, 2, 15, 1.5f, 5000, 500, -1, 3500, 3,
	    Var.img_missile1, Var.sec_missile1), 
	    GUIDED_GROUND("Guided Ground Missile", 35, 2, 15, 1.5f, 5000, 500, -1, 4000, 3,
	    Var.img_missile1, Var.sec_missile1), 
	    RADAR_AIR("Radar Air Missile", 35, 4, 15, 1.5f, 5000, 500, -1, 3500, 3, Var.img_missile1,
	    Var.sec_missile1), 
	    RADAR_GROUND("Radar Ground Missile", 40, 4, 15, 1.5f,
	    5000, 500, -1, 4000, 3, Var.img_missile1, Var.sec_missile1), 
	    TURRET_MIDDLE("Turret middle", 15, -1, 15, 0, -1, 400, -1, 1300, 1, Var.img_bullet1,
	    Var.prim_gun_heavy),
	    AIM9L("AIM-9L Sidewinder", 30, 4, 17, 2.5f, 5000, 500, -1, 2500, 3, 
		    Var.img_missile1, Var.sec_missile1);

    private int damage;
    private int ammoCount;
    private int speed;
    private int reload_delay;
    private int shoot_delay;
    private int life_time;
    private int exploSize;
    private Image image;
    private String name;
    private Sound sound;
    private int heat;
    private float turnRate;

    private WeaponTypes_Secondary(String name, int damage, int ammoCount,
	    int speed, float turnRate, int reload_delay, int shoot_delay, int heat,
	    int life_time, int exploSize, Image image, Sound sound) {
	this.damage = damage;
	this.image = image;
	this.name = name;
	this.ammoCount = ammoCount;
	this.reload_delay = reload_delay;
	this.shoot_delay = shoot_delay;
	this.exploSize = exploSize;
	this.speed = speed;
	this.life_time = life_time;
	this.sound = sound;
	this.heat = heat;
	this.turnRate = turnRate;
    }

    public void init() {
	// Sounds
	BOMB.setSound(Var.sec_bomb_drop);
	BOMB_SPLIT.setSound(Var.sec_bomb_drop);
	BOMB_SPLIT_SMALL.setSound(Var.sec_bomb_drop);
	UNGUIDED.setSound(Var.sec_missile1);
	GUIDED_AIR.setSound(Var.sec_missile1);
	GUIDED_GROUND.setSound(Var.sec_missile1);
	RADAR_AIR.setSound(Var.sec_missile1);
	RADAR_GROUND.setSound(Var.sec_missile1);
	TURRET_MIDDLE.setSound(Var.prim_gun_heavy);
	AIM9L.setSound(Var.sec_missile1);
	// Images
	BOMB.setImage(Var.img_bomb1);
	BOMB_SPLIT.setImage(Var.img_bomb1);
	BOMB_SPLIT_SMALL.setImage(Var.img_bomb1_split);
	UNGUIDED.setImage(Var.img_missile1);
	GUIDED_AIR.setImage(Var.img_missile1);
	GUIDED_GROUND.setImage(Var.img_missile1);
	RADAR_AIR.setImage(Var.img_missile1);
	RADAR_GROUND.setImage(Var.img_missile1);
	TURRET_MIDDLE.setImage(Var.img_bullet1);
	AIM9L.setImage(Var.aim9l);
    }

    public int getLife_time() {
	return life_time;
    }

    public void setLife_time(int life_time) {
	this.life_time = life_time;
    }

    public int getDamage() {
	return damage;
    }

    public Image getImage() {
	return image;
    }

    public void setImage(Image img) {
	this.image = img;
    }

    public String getName() {
	return name;
    }

    public int getAmmoCount() {
	return ammoCount;
    }

    public int getReload_delay() {
	return reload_delay;
    }

    public int getShoot_delay() {
	return shoot_delay;
    }

    public int getExploSize() {
	return exploSize;
    }

    public int getSpeed() {
	return speed;
    }

    public void setSpeed(int speed) {
	this.speed = speed;
    }

    public Sound getSound() {
	return sound;
    }

    public void setSound(Sound sound) {
	this.sound = sound;
    }

    public int getHeat() {
	return heat;
    }

    public void setHeat(int heat) {
	this.heat = heat;
    }

    public float getTurnRate() {
	return turnRate;
    }

    public void setTurnRate(float turnRate) {
	this.turnRate = turnRate;
    }

}
