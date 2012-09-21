package dogfight_remake.entities.weapons;

import java.awt.Image;

import dogfight_remake.rendering.Render;

public enum WeaponTypes {
	// Type, Name, Damage, Ammo Count, Reload Delay, Shoot Delay, Image
	MINIGUN("MiniGun", 2, 100, 2500, 50, Render.img_bullet1),
	GUN("Gun", 3, 50, 3000, 75, Render.img_bullet1),
	BOMB("Bomb", 40, 2, 8000, 300, Render.img_bomb1),
	BOMB_SPLIT("Split Bomb", 30, 2, 8000, 300, Render.img_bomb1),
	BOMB_SPLIT_SMALL("Split Bomb(small)", 10, 5, 8000, 300, Render.img_bomb1),
	UNGUIDED("Unguided Missile", 25, 8, 4000, 350, Render.img_missile1),
	GUIDED_AIR("Guided Air Missile", 20, 2, 5000, 500, Render.img_missile1), 
	GUIDED_GROUND("Guided Ground Missile", 2, 35, 5000, 500, Render.img_missile1), 
	RADAR_AIR("Radar Air Missile", 35, 4, 5000, 500, Render.img_missile1), 
	RADAR_GROUND("Radar Ground Missile", 40, 4, 5000, 500, Render.img_missile1);

	private int damage;
	private int ammoCount;
	private int reload_delay;
	private int shoot_delay;
	private Image image;
	private String name;

	private WeaponTypes(String name, int damage, int ammoCount, int reload_delay, int shoot_delay, Image image) {
		this.damage = damage;
		this.image = image;
		this.name = name;
		this.ammoCount = ammoCount;
		this.reload_delay = reload_delay;
		this.shoot_delay = shoot_delay;
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
}