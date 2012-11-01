package dogfight_remake.entities.weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

import dogfight_remake.main.Var;

public enum WeaponTypes_Primary implements WeaponTypes_Interface {
    // Type(Name, Damage, Ammo Count, Speed, Reload Delay, Shoot Delay,
    // Lifetime(ms), Explosion size, Image)
    // Type(Name, Damage, Ammo Count, Speed, Reload Delay, Shoot Delay, Heat
    // creation, Lifetime(ms), Explosion size, Image)
    MINIGUN("MiniGun", 2, 100, 25, 2500, 50, 2, 1000, 1, Var.img_bullet1, Var.prim_gun_light), 
	    MM30S("Gun", 5, 25, 25, 3000, 150, 3, 1500, 1, Var.mm30s, Var.prim_gun_middle), 
	    TURRET_MIDDLE("Turret middle", 15, -1, 15, -1, 400, -1, 1300, 1, Var.img_bullet1, Var.prim_gun_heavy);

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

    private WeaponTypes_Primary(String name, int damage, int ammoCount,
	    int speed, int reload_delay, int shoot_delay, int heat,
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
    }

    public void init() {
	// Sounds
	MM30S.setSound(Var.prim_gun_middle);
	MINIGUN.setSound(Var.prim_gun_light);
	TURRET_MIDDLE.setSound(Var.prim_gun_heavy);
	// Images
	MM30S.setImage(Var.mm30s);
	MINIGUN.setImage(Var.img_bullet1);
	TURRET_MIDDLE.setImage(Var.img_bullet1);
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

    @Override
    public float getTurnRate() {
	return -1;
    }

    @Override
    public void setTurnRate(float turnRate) {
	
    }
}