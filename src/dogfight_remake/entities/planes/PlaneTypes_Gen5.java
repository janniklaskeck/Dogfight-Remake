package dogfight_remake.entities.planes;

import org.newdawn.slick.Image;

import dogfight_remake.entities.weapons.WeaponTypes_Primary;
import dogfight_remake.entities.weapons.WeaponTypes_Secondary;
import dogfight_remake.main.Var;

public enum PlaneTypes_Gen5 implements PlaneTypes_Interface {
 // Name, Hitpoints, MaxSpeed, Acceleration, TurnAngle, Damage-Modifier,
    // Wpn1, Wpn2, Wpn3, Image
    NORMAL("Normal", 100, 1, 0.01f, 0.12f, 1, WeaponTypes_Primary.MM30S,
	    WeaponTypes_Primary.MM30S, WeaponTypes_Secondary.AIM9L,
	    WeaponTypes_Secondary.AIM9L, Var.img_plane2), 
	    F35("F-35 Joint Strike Fighter", 95, 1.35f, 0.015f, 0.13f, 1f,
	    WeaponTypes_Primary.MM30S, WeaponTypes_Primary.MM30S,
	    WeaponTypes_Secondary.BOMB_SPLIT, WeaponTypes_Secondary.BOMB_SPLIT,
	    Var.f35), 
	    MIG35("MiG-35/MFI", 100, 1.35f, 0.015f, 0.10f, 1f,
	    WeaponTypes_Primary.MM30S, WeaponTypes_Primary.MM30S,
	    WeaponTypes_Secondary.GUIDED_AIR, WeaponTypes_Secondary.BOMB_SPLIT,
	    Var.mig35);

    private int hitpoints;
    private float speed;
    private float damage;
    private String name;
    private WeaponTypes_Primary wpn1;
    private WeaponTypes_Primary wpn2;
    private WeaponTypes_Secondary wpn3;
    private WeaponTypes_Secondary wpn4;
    private Image image;
    private float accel;
    private float turnAngle;

    PlaneTypes_Gen5(String name, int hitpoins, float speed, float accel,
	    float turnAngle, float damage, WeaponTypes_Primary wpn1,
	    WeaponTypes_Primary wpn2, WeaponTypes_Secondary wpn3,
	    WeaponTypes_Secondary wpn4, Image image) {
	this.setHitpoints(hitpoins);
	this.setName(name);
	this.setSpeed(speed);
	this.setAccel(accel);
	this.setTurnAngle(turnAngle);
	this.setDamage(damage);
	this.setWpn1(wpn1);
	this.setWpn2(wpn2);
	this.setWpn3(wpn3);
	this.setWpn4(wpn4);
	this.setImage(image);
    }

    public void init() {
	NORMAL.setImage(Var.f35);
	F35.setImage(Var.f35);
	MIG35.setImage(Var.mig35);
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

    public WeaponTypes_Primary getWpn1() {
	return wpn1;
    }

    public void setWpn1(WeaponTypes_Primary wpn1) {
	this.wpn1 = wpn1;
    }

    public WeaponTypes_Primary getWpn2() {
	return wpn2;
    }

    public void setWpn2(WeaponTypes_Primary wpn2) {
	this.wpn2 = wpn2;
    }

    public WeaponTypes_Secondary getWpn3() {
	return wpn3;
    }

    public void setWpn3(WeaponTypes_Secondary wpn3) {
	this.wpn3 = wpn3;
    }

    public WeaponTypes_Secondary getWpn4() {
	return wpn4;
    }

    public void setWpn4(WeaponTypes_Secondary wpn4) {
	this.wpn4 = wpn4;
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
