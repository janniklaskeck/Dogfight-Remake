package dogfight_remake.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

import dogfight_remake.entities.planes.PlaneTypes;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.map.BlockMap;

public class GlbVar {
	public static AppGameContainer app;
	// Images Game
	public static Image img_player1;
	public static Image img_player2;
	public static Image img_missile1;
	public static Image img_bullet1;
	public static Image img_bomb1;
	public static Image img_bg;
	public static Image img_pause_bg;
	public static Image img_turret1;
	public static Image img_turret_base;
	public static Image img_turret_barrel;
	// Tiled Map
	public static BlockMap map;
	public static TiledMap tmap;
	// Images MainMenu
	public static Image background;
	public static Image startGameOption;
	public static Image exitOption;
	public static Image menuOptions;
	public static Image gameOptionsMenu;
	public static Image buttons;
	public static Image button1;
	public static Image button2;
	public static Image exitCorner;
	public static Image plane_p1;
	public static Image plane_p2;
	// Images Pause Menu
	public static Image pauseMenuText;
	public static Image pauseMenu;
	public static Image continueGame;
	public static Image mainMenu;
	public static Image exit;
	public static Image options;
	// Images PlaneMenu
	public static Image arrows;
	public static Image arrow_left;
	public static Image arrow_right;
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
	// Camera x and y
	public static float cx;
	public static float cy;

	// Game options
	public final static float PLANES_MAX_SPEED = 7;
	public static float GRAVITY = 6;
	private static boolean player_collision = false;
	private static int multisample = 2;
	public static boolean fullscreen = false;
	public static boolean paused = true;
	public static boolean vSync = true;
	public static int timePassed = 0;
	public static int plane_id = 1;
	public static PlaneTypes player1 = PlaneTypes.NORMAL;
	public static PlaneTypes player2 = PlaneTypes.NORMAL;
	// Scores
	public static int score_p1 = 0, score_p2 = 0;
	// RespawnTimer
	public static final long RESPAWNTIME_PLAYER = 3000;
	public static final long RESPAWNTIME_TURRET = 3000;
	public static long respawntimer_p1 = RESPAWNTIME_PLAYER;
	public static long respawntimer_p2 = RESPAWNTIME_PLAYER;
	public static long respawntimer_turret = RESPAWNTIME_TURRET;

	public static float music_volume = 0.2f;
	public static float sounds_volume = 0.05f;

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

	public static void setResolution(int x, int y) throws SlickException {
		app.setDisplayMode(x, y, fullscreen);
	}

	public static WeaponTypes wpn1_p1 = WeaponTypes.GUN;
	public static WeaponTypes wpn2_p1 = WeaponTypes.GUIDED_AIR;
	public static WeaponTypes wpn3_p1 = WeaponTypes.BOMB;
	public static WeaponTypes wpn1_p2 = WeaponTypes.GUN;
	public static WeaponTypes wpn2_p2 = WeaponTypes.GUIDED_AIR;
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
