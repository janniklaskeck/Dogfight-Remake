package dogfight_remake.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMapPlus;

import dogfight_remake.entities.planes.PlaneTypes_Interface;
import dogfight_remake.entities.weapons.WeaponTypes_Primary;
import dogfight_remake.entities.weapons.WeaponTypes_Secondary;

public class Var {
    public static AppGameContainer app;
    // Images Game
    public static Image img_plane1;
    public static Image img_plane2;
    public static Image aim9l;
    public static Image img_missile1;
    public static Image img_bullet1;
    public static Image img_bomb1;
    public static Image img_bomb1_split;
    public static Image img_bg;
    public static Image img_pause_bg;
    public static Image img_turret1;
    public static Image img_turret_base;
    public static Image img_turret_barrel;
    public static Image img_player_ui;
    public static Image img_player_ui_text;
    public static Image img_timer;
    // Images planes gen 5
    public static Image f35;
    public static Image plane_collis_generic;
    public static Image mig35;
    // Images weapons primary
    public static Image mm30s;
    // Tiled Map
    public static TiledMapPlus tmap;
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
    public final static float PLANES_MAX_SPEED = 7f;
    public static float GRAVITY = 6f;
    private static boolean player_collision = false;
    private static int multisample = 2;
    public static boolean fullscreen = false;
    public static boolean paused = true;
    public static boolean vSync = true;
    public static int timePassed_sec = 0;
    public static int timePassed_min = 0;
    public static int plane_id = 1;
    public static boolean singlePlayer = false;
    public static boolean vertical_split = false;

    public static PlaneTypes_Interface player1_type;
    public static PlaneTypes_Interface player2_type;
    // Scores
    public static int score_p1 = 0, score_p2 = 0;
    // RespawnTimer
    public static final long RESPAWNTIME_PLAYER = 3000L;
    public static final long RESPAWNTIME_TURRET = 9000L;

    public static float music_volume = 0.2f;
    public static float sounds_volume = 0.05f;
    public final static Color COLLISION_COLOR = new Color(0, 0, 0, 0);
    
    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Dimension dim_fullscreen = toolkit.getScreenSize();
    public static Dimension dim_1080p = new Dimension(1920, 1080);
    public static Dimension dim_720p = new Dimension(1280, 720);
    public static Dimension dim_chosen = dim_720p;

    public static int p1_key_left = Input.KEY_LEFT;
    public static int p1_key_right = Input.KEY_RIGHT;
    public static int p1_key_up = Input.KEY_UP;
    public static int p1_key_down = Input.KEY_DOWN;
    public static int p1_key_prim1 = Input.KEY_COMMA;
    public static int p1_key_prim2 = Input.KEY_COMMA;
    public static int p1_key_sec1 = Input.KEY_PERIOD;
    public static int p1_key_sec2 = Input.KEY_MINUS;

    public static boolean p1_mov_left = false;
    public static boolean p1_mov_right = false;
    public static boolean p1_mov_up = false;
    public static boolean p1_mov_down = false;
    public static boolean p1_act_prim1 = false;
    public static boolean p1_act_prim2 = false;
    public static boolean p1_act_sec1 = false;
    public static boolean p1_act_sec2 = false;

    public static WeaponTypes_Primary wpn1_p1 = WeaponTypes_Primary.MM30S;
    public static WeaponTypes_Primary wpn2_p1 = WeaponTypes_Primary.MM30S;
    public static WeaponTypes_Secondary wpn3_p1 = WeaponTypes_Secondary.AIM9L;
    public static WeaponTypes_Secondary wpn4_p1 = WeaponTypes_Secondary.AIM9L;

    public static int p2_key_left = Input.KEY_D;
    public static int p2_key_right = Input.KEY_G;
    public static int p2_key_up = Input.KEY_R;
    public static int p2_key_down = Input.KEY_F;
    public static int p2_key_prim1 = Input.KEY_1;
    public static int p2_key_prim2 = Input.KEY_1;
    public static int p2_key_sec1 = Input.KEY_2;
    public static int p2_key_sec2 = Input.KEY_3;

    public static boolean p2_mov_left = false;
    public static boolean p2_mov_right = false;
    public static boolean p2_mov_up = false;
    public static boolean p2_mov_down = false;
    public static boolean p2_act_prim1 = false;
    public static boolean p2_act_prim2 = false;
    public static boolean p2_act_sec1 = false;
    public static boolean p2_act_sec2 = false;

    public static WeaponTypes_Primary wpn1_p2 = WeaponTypes_Primary.MM30S;
    public static WeaponTypes_Primary wpn2_p2 = WeaponTypes_Primary.MM30S;
    public static WeaponTypes_Secondary wpn3_p2 = WeaponTypes_Secondary.GUIDED_AIR;
    public static WeaponTypes_Secondary wpn4_p2 = WeaponTypes_Secondary.BOMB;

    public static void setPlayerCollision(boolean bool) {
	player_collision = bool;
    }

    public static boolean getPlayerCollision() {
	return player_collision;
    }

    public static void setResolution(int x, int y) throws SlickException {
	app.setDisplayMode(x, y, fullscreen);
    }

    public static void setWeapons() {

    }

    public void setMultiSample(int amount) {
	multisample = amount;
    }

    public static int getMultiSample() {
	return multisample;
    }
}
