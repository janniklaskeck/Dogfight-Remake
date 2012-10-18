package dogfight_remake.entities.planes;

import org.newdawn.slick.Image;

import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.main.Var;

public enum PlaneTypes {
	// Name, Hitpoints, MaxSpeed, Acceleration, TurnAngle, Damage-Modifier, Wpn1, Wpn2, Wpn3, Image
	NORMAL("Normal", 100, 1, 0.01f, 0.12f, 1, WeaponTypes.GUN, WeaponTypes.GUIDED_AIR,WeaponTypes.BOMB, Var.img_plane1), 
	FAST("Fast", 75, 1.25f, 0.01f, 0.12f,0.75f, WeaponTypes.GUN,WeaponTypes.UNGUIDED, WeaponTypes.UNGUIDED, Var.img_plane1), 
	SLOW("Slow", 150, 0.75f, 0.01f, 0.12f,1.25f, WeaponTypes.GUN, WeaponTypes.GUIDED_AIR, WeaponTypes.BOMB_SPLIT, Var.img_plane1);

	private int hitpoints;
	private float speed;
	private float damage;
	private String name;
	private WeaponTypes wpn1;
	private WeaponTypes wpn2;
	private WeaponTypes wpn3;
	private Image image;
	private float accel;
	private float turnAngle;

	PlaneTypes(String name, int hitpoins, float speed, float accel, float turnAngle, float damage,
			WeaponTypes wpn1, WeaponTypes wpn2, WeaponTypes wpn3, Image image) {
		this.setHitpoints(hitpoins);
		this.setName(name);
		this.setSpeed(speed);
		this.setAccel(accel);
		this.setTurnAngle(turnAngle);
		this.setDamage(damage);
		this.setWpn1(wpn1);
		this.setWpn2(wpn2);
		this.setWpn3(wpn3);
		this.setImage(image);
	}

	public static void init() {
		NORMAL.setImage(Var.img_plane1);
		FAST.setImage(Var.img_plane1);
		SLOW.setImage(Var.img_plane1);
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

	public void setWpn1(WeaponTypes wpn1) {
		this.wpn1 = wpn1;
	}

	public WeaponTypes getWpn2() {
		return wpn2;
	}

	public void setWpn2(WeaponTypes wpn2) {
		this.wpn2 = wpn2;
	}

	public WeaponTypes getWpn3() {
		return wpn3;
	}

	public void setWpn3(WeaponTypes wpn3) {
		this.wpn3 = wpn3;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public float getAccel() {
		return accel;
	}

	public void setAccel(float accel) {
		this.accel = accel;
	}

	public float getTurnAngle() {
		return turnAngle;
	}

	public void setTurnAngle(float turnAngle) {
		this.turnAngle = turnAngle;
	}

}
