package dogfight_remake.entities.planes;

import org.newdawn.slick.Image;

import dogfight_remake.entities.weapons.WeaponTypes_Primary;
import dogfight_remake.entities.weapons.WeaponTypes_Secondary;

public interface PlaneTypes_Interface {

    public void init();

    public float getDamage();

    public void setDamage(float damage);

    public float getSpeed();

    public void setSpeed(float speed);

    public int getHitpoints();

    public void setHitpoints(int hitpoints);

    public String getName();

    public void setName(String name);

    public WeaponTypes_Primary getWpn1();

    public void setWpn1(WeaponTypes_Primary wpn1);

    public WeaponTypes_Primary getWpn2();

    public void setWpn2(WeaponTypes_Primary wpn2);

    public WeaponTypes_Secondary getWpn3();

    public void setWpn3(WeaponTypes_Secondary wpn3);

    public WeaponTypes_Secondary getWpn4();

    public void setWpn4(WeaponTypes_Secondary wpn4);

    public Image getImage();

    public void setImage(Image image);

    public float getAccel();

    public void setAccel(float accel);

    public float getTurnAngle();

    public void setTurnAngle(float turnAngle);
}
