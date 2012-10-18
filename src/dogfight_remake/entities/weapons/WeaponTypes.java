package dogfight_remake.entities.weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

import dogfight_remake.main.Var;

public enum WeaponTypes {
	// Type(Name, Damage, Ammo Count, Speed, Reload Delay, Shoot Delay, Lifetime(ms), Explosion size, Image)
	MINIGUN("MiniGun", 2, 100, 15, 2500, 50, 1000, 1, Var.img_bullet1,Var.prim_gun_light), 
	GUN("Gun", 3, 50, 15, 3000, 75, 1000, 1,Var.img_bullet1, Var.prim_gun_middle), 
	BOMB("Bomb", 40, 2,12, 8000, 300, 10000, 5, Var.img_bomb1, Var.sec_bomb_drop), 
	BOMB_SPLIT("Split Bomb", 30, 2, 12, 8000, 300, 10000, 4, Var.img_bomb1,Var.sec_bomb_drop), 
	BOMB_SPLIT_SMALL("Split Bomb(small)", 10, 5,12, 8000, 300, 10000, 3, Var.img_bomb1, Var.sec_bomb_drop), 
	UNGUIDED("Unguided Missile", 25, 8, 12, 4000, 350, 2000, 3,Var.img_missile1, Var.sec_missile1), 
	GUIDED_AIR("Guided Air Missile", 20, 2, 8, 5000, 500, 3500, 3,Var.img_missile1, Var.sec_missile1), 
	GUIDED_GROUND("Guided Ground Missile", 35, 2, 8, 5000, 500, 4000, 3,Var.img_missile1, Var.sec_missile1), 
	RADAR_AIR("Radar Air Missile", 35, 4, 8, 5000, 500, 3500, 3,Var.img_missile1, Var.sec_missile1), 
	RADAR_GROUND("Radar Ground Missile", 40, 4, 8, 5000, 500, 4000, 3,Var.img_missile1, Var.sec_missile1), 
	TURRET_MIDDLE("Turret middle", 15, -1, 13, -1, 400, 1300, 1, Var.img_bullet1,Var.prim_gun_heavy);

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

	private WeaponTypes(String name, int damage, int ammoCount, int speed,
			int reload_delay, int shoot_delay, int life_time, int exploSize,
			Image image, Sound sound) {
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
	}

	public static void init() {
		// Sounds
		GUN.setSound(Var.prim_gun_middle);
		MINIGUN.setSound(Var.prim_gun_light);
		BOMB.setSound(Var.sec_bomb_drop);
		BOMB_SPLIT.setSound(Var.sec_bomb_drop);
		BOMB_SPLIT_SMALL.setSound(Var.sec_bomb_drop);
		UNGUIDED.setSound(Var.sec_missile1);
		GUIDED_AIR.setSound(Var.sec_missile1);
		GUIDED_GROUND.setSound(Var.sec_missile1);
		RADAR_AIR.setSound(Var.sec_missile1);
		RADAR_GROUND.setSound(Var.sec_missile1);
		TURRET_MIDDLE.setSound(Var.prim_gun_heavy);
		// Images
		GUN.setImage(Var.img_bullet1);
		MINIGUN.setImage(Var.img_bullet1);
		BOMB.setImage(Var.img_bomb1);
		BOMB_SPLIT.setImage(Var.img_bomb1);
		BOMB_SPLIT_SMALL.setImage(Var.img_bomb1);
		UNGUIDED.setImage(Var.img_missile1);
		GUIDED_AIR.setImage(Var.img_missile1);
		GUIDED_GROUND.setImage(Var.img_missile1);
		RADAR_AIR.setImage(Var.img_missile1);
		RADAR_GROUND.setImage(Var.img_missile1);
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
}