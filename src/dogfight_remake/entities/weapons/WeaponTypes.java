package dogfight_remake.entities.weapons;

import org.newdawn.slick.Image;

import dogfight_remake.main.GlbVar;

public enum WeaponTypes {
	// Type(Name, Damage, Ammo Count, Speed, Reload Delay, Shoot Delay, Explosion size, Image)
	MINIGUN("MiniGun", 2, 100, 15, 2500, 50, 1, GlbVar.img_bullet1),
	GUN("Gun", 3, 50, 15, 3000, 75, 1, GlbVar.img_bullet1),
	BOMB("Bomb", 40, 2, 12, 8000, 300, 5, GlbVar.img_bomb1),
	BOMB_SPLIT("Split Bomb", 30, 2, 12, 8000, 300, 4, GlbVar.img_bomb1),
	BOMB_SPLIT_SMALL("Split Bomb(small)", 10, 5, 12, 8000, 300, 3, GlbVar.img_bomb1),
	UNGUIDED("Unguided Missile", 25, 8, 12, 4000, 350, 3, GlbVar.img_missile1),
	GUIDED_AIR("Guided Air Missile", 20, 2, 8, 5000, 500, 3, GlbVar.img_missile1), 
	GUIDED_GROUND("Guided Ground Missile", 35, 2, 8, 5000, 500, 3, GlbVar.img_missile1), 
	RADAR_AIR("Radar Air Missile", 35, 4, 8, 5000, 500, 3, GlbVar.img_missile1), 
	RADAR_GROUND("Radar Ground Missile", 40, 4, 8, 5000, 500, 3, GlbVar.img_missile1),
	TURRET_MIDDLE("Turret middle", 10, -1, 13, -1, 300, 1, GlbVar.img_bullet1);

	private int damage;
	private int ammoCount;
	private int speed;
	private int reload_delay;
	private int shoot_delay;
	private int exploSize;
	private Image image;
	private String name;
	

	private WeaponTypes(String name, int damage, int ammoCount, int speed, int reload_delay, int shoot_delay, int exploSize, Image image) {
		this.damage = damage;
		this.image = image;
		this.name = name;
		this.ammoCount = ammoCount;
		this.reload_delay = reload_delay;
		this.shoot_delay = shoot_delay;
		this.exploSize = exploSize;
		this.speed = speed;
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
}