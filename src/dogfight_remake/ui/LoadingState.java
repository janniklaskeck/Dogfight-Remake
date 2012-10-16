package dogfight_remake.ui;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

import dogfight_remake.main.Dogfight_Remake;
import dogfight_remake.main.GlbVar;
import dogfight_remake.map.BlockMap;

public class LoadingState extends BasicGameState {

	private DeferredResource nextResource;
	private boolean started;
	int stateID = -1;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		LoadingList.setDeferredLoading(true);
		// Images Main Menu
		GlbVar.background = new Image("dogfight_remake/images/menu/f4u.jpg");
		GlbVar.menuOptions = new Image(
				"dogfight_remake/images/menu/mainMenu.png");
		GlbVar.gameOptionsMenu = new Image(
				"dogfight_remake/images/menu/GameOptionsMenu.png");
		GlbVar.buttons = new Image("dogfight_remake/images/menu/buttons.png");
		GlbVar.button1 = GlbVar.buttons.getSubImage(0, 0, 32, 32);
		GlbVar.button2 = GlbVar.buttons.getSubImage(32, 0, 32, 32);
		GlbVar.exitCorner = GlbVar.buttons.getSubImage(64, 0, 32, 32);
		GlbVar.startGameOption = new Image(
				"dogfight_remake/images/menu/menu_start.png");
		GlbVar.plane_p1 = new Image(
				"dogfight_remake/images/menu/menu_player1.png");
		GlbVar.plane_p2 = new Image(
				"dogfight_remake/images/menu/menu_player2.png");
		// Images Plane Menu
		GlbVar.arrows = new Image("dogfight_remake/images/menu/buttons2.png");
		GlbVar.arrow_left = GlbVar.arrows.getSubImage(0, 0, 64, 64);
		GlbVar.arrow_right = GlbVar.arrows.getSubImage(64, 0, 64, 64);
		// Images Game
		GlbVar.img_plane1 = new Image(
				"dogfight_remake/images/planes/plane1.png");
		GlbVar.img_plane2 = new Image(
				"dogfight_remake/images/planes/plane2.png");
		GlbVar.img_bg = new Image("dogfight_remake/images/img_bg.jpg");
		GlbVar.img_missile1 = new Image(
				"dogfight_remake/images/weapons/missile1.png");
		GlbVar.img_bullet1 = new Image(
				"dogfight_remake/images/weapons/bullet1.png");
		GlbVar.img_bomb1 = new Image("dogfight_remake/images/weapons/bomb1.png");
		GlbVar.img_turret1 = new Image("dogfight_remake/images/turret.png");
		// Tiled Map
		GlbVar.tmap = new TiledMap("dogfight_remake/map/map_test/map1.tmx",
				"dogfight_remake/map/map_test");
		GlbVar.map = new BlockMap("dogfight_remake/map/map_test/map1.tmx");
		// Images Pause Menu
		GlbVar.pauseMenuText = new Image(
				"dogfight_remake/images/menu/pauseMenuText.png");
		GlbVar.pauseMenu = new Image(
				"dogfight_remake/images/menu/pauseMenu.png");
		// Sounds and Music

		// GlbVar.music1 = new
		// Music("dogfight_remake/sound/sounds/fight07.ogg");
		// GlbVar.music2 = new
		// Music("dogfight_remake/sound/sounds/fight08.ogg");
		
		GlbVar.prim_gun_heavy = new Sound(
				"dogfight_remake/sound/sounds/prim_gun_heavy.wav");
		GlbVar.prim_gun_middle = new Sound(
				"dogfight_remake/sound/sounds/prim_gun_middle.wav");
		GlbVar.prim_gun_light = new Sound(
				"dogfight_remake/sound/sounds/prim_gun_light.wav");
		GlbVar.sec_missile1 = new Sound(
				"dogfight_remake/sound/sounds/sec_missile1.wav");
		GlbVar.sec_missile2 = new Sound(
				"dogfight_remake/sound/sounds/sec_missile2.wav");
		GlbVar.sec_bomb_drop = new Sound(
				"dogfight_remake/sound/sounds/sec_bomb_drop.wav");
		GlbVar.explode = new Sound("dogfight_remake/sound/sounds/explode.wav");
		GlbVar.hit = new Sound("dogfight_remake/sound/sounds/hit.wav");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sgg, Graphics g)
			throws SlickException {
		if (nextResource != null) {
			g.drawString("Loading: " + nextResource.getDescription(),
					(float) GlbVar.dim_chosen.getWidth() / 3,
					(float) GlbVar.dim_chosen.getHeight() / 5);
		}
		int total = LoadingList.get().getTotalResources();
		int loaded = LoadingList.get().getTotalResources()
				- LoadingList.get().getRemainingResources();
		g.fillRect((float) GlbVar.dim_chosen.getWidth() / 3,
				(float) GlbVar.dim_chosen.getHeight() / 4, loaded * 40, 20);
		g.drawRect((float) GlbVar.dim_chosen.getWidth() / 3,
				(float) GlbVar.dim_chosen.getHeight() / 4, total * 40, 20);
		if (started) {
			g.drawString("LOADING COMPLETE", 100, 500);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if (nextResource != null) {
			try {
				nextResource.load();
			} catch (IOException e) {
				throw new SlickException("Failed to load: "
						+ nextResource.getDescription(), e);
			}
			nextResource = null;
		}

		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			sbg.enterState(Dogfight_Remake.MAINMENUSTATE);
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

	public LoadingState(int stateID) {
		this.stateID = stateID;
	}

}
