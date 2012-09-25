package dogfight_remake.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.map.BlockMap;

public class GlbVar {
	// Images Game
	public static Image img_player1;
	public static Image img_player2;
	public static Image img_missile1;
	public static Image img_bullet1;
	public static Image img_bomb1;
	public static Image img_bg;
	// Tiled Map
	public static BlockMap map;
	public static TiledMap tmap;
	// Images MainMenu
	public static Image background;
	public static Image startGameOption;
	public static Image exitOption;
	public static Image menuOptions;
	// Sounds and Music
	public static Music music1;
	public static Music music2;
	public static Sound prim_gun_heavy;
	public static Sound prim_gun_middle;
	public static Sound prim_gun_light;
	public static Sound sec_missile1;
	public static Sound sec_missile2;
	public static Sound sec_bomb_drop;
	public static Sound explode;
	public static Sound hit;

	private static boolean player_collision = false;
	private static int multisample = 2;

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

	public void setMultiSample(int amount) {
		multisample = amount;
	}

	public static int getMultiSample() {
		return multisample;
	}
}
