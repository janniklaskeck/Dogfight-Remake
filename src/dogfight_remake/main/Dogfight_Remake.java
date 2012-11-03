package dogfight_remake.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.ui.LoadingState;
import dogfight_remake.ui.MainMenuState;
import dogfight_remake.ui.PausedState;
import dogfight_remake.ui.PlaneState;

public class Dogfight_Remake extends StateBasedGame {

	public Dogfight_Remake() {
		super("Dogfight");
	}

	public static final int MAINMENUSTATE = 1;
	public static final int GAMEPLAYSTATE = 2;
	public static final int PAUSEDSTATE = 3;
	public static final int LOADINGSTATE = 0;
	public static final int PLANESTATE = 4;

	public static void main(String[] args) throws SlickException {
		Var.app = new AppGameContainer(new Dogfight_Remake());
		Var.app.setMultiSample(Var.getMultiSample());
		Var.app.setDisplayMode(Var.dim_chosen.width,
				Var.dim_chosen.height, Var.fullscreen);
		Var.app.start();
		Var.app.setVSync(Var.vSync);
		Var.app.setShowFPS(false);
		Var.app.setSmoothDeltas(true);
	}

	@Override
	public void initStatesList(GameContainer gameContainer)
			throws SlickException {
		this.addState(new LoadingState(LOADINGSTATE));
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GamePlayState(GAMEPLAYSTATE));
		this.addState(new PausedState(PAUSEDSTATE));
		this.addState(new PlaneState(PLANESTATE));
	}

}
