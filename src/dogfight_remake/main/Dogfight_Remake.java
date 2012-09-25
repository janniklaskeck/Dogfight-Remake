package dogfight_remake.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.ui.LoadingState;
import dogfight_remake.ui.MainMenuState;

public class Dogfight_Remake extends StateBasedGame {

	public Dogfight_Remake() {
		super("Dogfight");
	}

	public static final int MAINMENUSTATE = 1;
	public static final int GAMEPLAYSTATE = 2;
	public static final int LOADINGSTATE = 0;

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Dogfight_Remake());
		app.setMultiSample(GlbVar.getMultiSample());
		app.setDisplayMode(1680, 1050, true);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer gameContainer)
			throws SlickException {
		this.addState(new LoadingState(LOADINGSTATE));
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GamePlayState(GAMEPLAYSTATE));

	}

}
