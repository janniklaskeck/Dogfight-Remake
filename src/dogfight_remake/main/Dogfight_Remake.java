package dogfight_remake.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.ui.MainMenuState;

public class Dogfight_Remake extends StateBasedGame {

	public Dogfight_Remake() {
		super("Dogfight");
	}

	public static final int MAINMENUSTATE = 0;
	public static final int GAMEPLAYSTATE = 1;

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Dogfight_Remake());

		app.setDisplayMode(1680, 1050, false);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer gameContainer)
			throws SlickException {
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GamePlayState(GAMEPLAYSTATE));
	}

}
