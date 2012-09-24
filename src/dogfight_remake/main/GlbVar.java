package dogfight_remake.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import dogfight_remake.entities.weapons.WeaponTypes;

public class GlbVar {

	private static boolean player_collision = true;

	public static float music_volume = 0.1f;
	public static float sounds_volume = 0.1f;

	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension dim_fullscreen = toolkit.getScreenSize();
	public static Dimension dim_1080p = new Dimension(1920, 1080);
	public static Dimension dim_720p = new Dimension(1280, 720);
	public static Dimension dim_chosen = dim_fullscreen;

	public static void setPlayerCollision(boolean bool) {
		player_collision = bool;
	}

	public static boolean getPlayerCollision() {
		return player_collision;
	}

	public static void setResolution() {

	}

	public static WeaponTypes wpn1_p1 = WeaponTypes.GUN;
	public static WeaponTypes wpn2_p1 = WeaponTypes.GUIDED_AIR;
	public static WeaponTypes wpn3_p1 = WeaponTypes.BOMB;
	public static WeaponTypes wpn1_p2 = WeaponTypes.GUN;
	public static WeaponTypes wpn2_p2 = WeaponTypes.UNGUIDED;
	public static WeaponTypes wpn3_p2 = WeaponTypes.BOMB;

	public static void setWeapons() {

	}
}
