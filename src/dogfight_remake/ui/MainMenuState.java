package dogfight_remake.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.main.Dogfight_Remake;

public class MainMenuState extends BasicGameState {

	int stateID = -1;

	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}

	Image background = null;
	Image startGameOption = null;
	Image exitOption = null;

	float startGameScale = 1;
	float exitScale = 1;
	float menuX = 100;
	float menuY = 100;
	float scaleStep = 0.0001f;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = new Image("dogfight_remake/images/mig29_2.jpg");

		// load the menu images
		Image menuOptions = new Image("dogfight_remake/images/menu.png");

		startGameOption = menuOptions.getSubImage(0, 0, 377, 71);

		exitOption = menuOptions.getSubImage(0, 71, 377, 71);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// render the background
		background.draw(0, 0, 2.0f);

		// Draw menu
		startGameOption.draw(menuX, menuY, startGameScale);

		exitOption.draw(menuX, menuY + 80, exitScale);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStartGame = false;
		boolean insideExit = false;

		if ((mouseX >= menuX && mouseX <= menuX + startGameOption.getWidth())
				&& (mouseY >= menuY && mouseY <= menuY
						+ startGameOption.getHeight())) {
			insideStartGame = true;
		} else if ((mouseX >= menuX && mouseX <= menuX + exitOption.getWidth())
				&& (mouseY >= menuY + 80 && mouseY <= menuY + 80
						+ exitOption.getHeight())) {
			insideExit = true;
		}

		if (insideStartGame) {
			if (startGameScale < 1.05f)
				startGameScale += scaleStep * delta;

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(Dogfight_Remake.GAMEPLAYSTATE);
			}
		} else {
			if (startGameScale > 1.0f)
				startGameScale -= scaleStep * delta;

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				gc.exit();
		}

		if (insideExit) {
			if (exitScale < 1.05f)
				exitScale += scaleStep * delta;
		} else {
			if (exitScale > 1.0f)
				exitScale -= scaleStep * delta;
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
