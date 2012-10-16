package dogfight_remake.entities.planes;

import org.newdawn.slick.Image;

import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.main.Var;

public enum PlaneTypes {
	NORMAL("Normal", 100, 1, 1, WeaponTypes.GUN, WeaponTypes.GUIDED_AIR,
			WeaponTypes.BOMB, Var.img_plane1), 
	FAST("Fast", 75, 1.25f, 0.75f, WeaponTypes.GUN,
			WeaponTypes.UNGUIDED, WeaponTypes.UNGUIDED, Var.img_plane2), 
	SLOW("Slow", 150, 0.75f,
			1.25f, WeaponTypes.GUN, WeaponTypes.GUIDED_AIR, WeaponTypes.BOMB_SPLIT, Var.img_plane1);

	private int hitpoints;
	private float speed;
	private float damage;
	private String name;
	private WeaponTypes wpn1;
	private WeaponTypes wpn2;
	private WeaponTypes wpn3;
	private Image image;

	PlaneTypes(String name, int hitpoins, float speed, float damage,
			WeaponTypes wpn1, WeaponTypes wpn2, WeaponTypes wpn3, Image image) {
		this.setHitpoints(hitpoins);
		this.setName(name);
		this.setSpeed(speed);
		this.setDamage(damage);
		this.wpn1 = wpn1;
		this.wpn2 = wpn2;
		this.wpn3 = wpn3;
		this.setImage(image);

	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WeaponTypes getWpn1() {
		return wpn1;
	}

	public void setWpn3(WeaponTypes wpn1) {
		this.wpn1 = wpn1;
	}

	public WeaponTypes getWpn2() {
		return wpn2;
	}

	public void setWpn1(WeaponTypes wpn2) {
		this.wpn2 = wpn2;
	}

	public WeaponTypes getWpn3() {
		return wpn3;
	}

	public void setWpn2(WeaponTypes wpn3) {
		this.wpn3 = wpn3;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
