package dogfight_remake.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.ui.MainMenu;

public class GlbVar {

	private static boolean player_collision = false;

	public static float music_volume = 1;
	public static float sounds_volume = 1;

	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension dim_fullscreen = toolkit.getScreenSize();
	public static Dimension dim_1080p = new Dimension(1920, 1080);
	public static Dimension dim_720p = new Dimension(1280, 720);
	public static Dimension dim_chosen;

	public static void setPlayerCollision(boolean bool) {
		player_collision = bool;
	}

	public static boolean getPlayerCollision() {
		return player_collision;
	}

	public static void setResolution() {
		if (MainMenu.resolution.getSelectedIndex() == 1) {
			dim_chosen = dim_1080p;
		} else if (MainMenu.resolution.getSelectedIndex() == 2) {
			dim_chosen = dim_720p;
		} else {
			dim_chosen = dim_fullscreen;
		}
	}

	public static WeaponTypes wpn1_p1;
	public static WeaponTypes wpn2_p1;
	public static WeaponTypes wpn3_p1;
	public static WeaponTypes wpn1_p2;
	public static WeaponTypes wpn2_p2;
	public static WeaponTypes wpn3_p2;

	public static void setWeapons() {
		// Player 1
		if (MainMenu.Weapon_1_p1.getSelectedItem() == "Normal Gun") {
			wpn1_p1 = WeaponTypes.GUN;
		} else {
			wpn1_p1 = WeaponTypes.MINIGUN;
		}
		if (MainMenu.Weapon_2_p1.getSelectedItem() == "Unguided Missiles") {
			wpn2_p1 = WeaponTypes.UNGUIDED;
		} else if (MainMenu.Weapon_2_p1.getSelectedItem() == "Guided Air Missiles") {
			wpn2_p1 = WeaponTypes.GUIDED_AIR;
		} else if (MainMenu.Weapon_2_p1.getSelectedItem() == "Normal Bomb") {
			wpn2_p1 = WeaponTypes.BOMB;
		} else {
			wpn2_p1 = WeaponTypes.BOMB_SPLIT;
		}
		if (MainMenu.Weapon_3_p1.getSelectedItem() == "Unguided Missiles") {
			wpn3_p1 = WeaponTypes.UNGUIDED;
		} else if (MainMenu.Weapon_3_p1.getSelectedItem() == "Guided Air Missiles") {
			wpn3_p1 = WeaponTypes.GUIDED_AIR;
		} else if (MainMenu.Weapon_3_p1.getSelectedItem() == "Normal Bomb") {
			wpn3_p1 = WeaponTypes.BOMB;
		} else {
			wpn3_p1 = WeaponTypes.BOMB_SPLIT;
		}
		// Player 2
		if (MainMenu.Weapon_1_p2.getSelectedIndex() == 0) {
			wpn1_p2 = WeaponTypes.GUN;
		} else {
			wpn1_p2 = WeaponTypes.MINIGUN;
		}
		if (MainMenu.Weapon_2_p2.getSelectedItem() == "Unguided Missiles") {
			wpn2_p2 = WeaponTypes.UNGUIDED;
		} else if (MainMenu.Weapon_2_p2.getSelectedItem() == "Guided Air Missiles") {
			wpn2_p2 = WeaponTypes.GUIDED_AIR;
		} else if (MainMenu.Weapon_2_p2.getSelectedItem() == "Normal Bomb") {
			wpn2_p2 = WeaponTypes.BOMB;
		} else {
			wpn2_p2 = WeaponTypes.BOMB_SPLIT;
		}
		if (MainMenu.Weapon_3_p2.getSelectedItem() == "Unguided Missiles") {
			wpn3_p2 = WeaponTypes.UNGUIDED;
		} else if (MainMenu.Weapon_3_p2.getSelectedItem() == "Guided Air Missiles") {
			wpn3_p2 = WeaponTypes.GUIDED_AIR;
		} else if (MainMenu.Weapon_3_p2.getSelectedItem() == "Normal Bomb") {
			wpn3_p2 = WeaponTypes.BOMB;
		} else {
			wpn3_p2 = WeaponTypes.BOMB_SPLIT;
		}
	}
}
