package dogfight_remake.entities.weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

public interface WeaponTypes_Interface {
    public void init();

    public int getLife_time();

    public void setLife_time(int life_time);

    public int getDamage();

    public Image getImage();

    public void setImage(Image img);

    public String getName();

    public int getAmmoCount();

    public int getReload_delay();

    public int getShoot_delay();

    public int getExploSize();

    public int getSpeed();

    public void setSpeed(int speed);

    public Sound getSound();

    public void setSound(Sound sound);
    
    public int getHeat();

    public void setHeat(int heat);
    
    public float getTurnRate();
    
    public void setTurnRate(float turnRate);
}
