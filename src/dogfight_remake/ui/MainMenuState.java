package dogfight_remake.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.main.Dogfight_Remake;
import dogfight_remake.main.GlbVar;

public class MainMenuState extends BasicGameState {

	int stateID = -1;

	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}

	float startGameScale = 1;
	float exitScale = 1;
	float menuX = 300;
	float menuY = 300;
	float scaleStep = 0.0001f;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		// load the menu images
		GlbVar.startGameOption = GlbVar.menuOptions.getSubImage(0, 0, 377, 71);
		GlbVar.exitOption = GlbVar.menuOptions.getSubImage(0, 71, 377, 71);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// render the background
		GlbVar.background.draw(0, 0, 1680, 1050);
		// Draw menu
		GlbVar.startGameOption.draw(menuX, menuY, startGameScale);
		GlbVar.exitOption.draw(menuX, menuY + 80, exitScale);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStartGame = false;
		boolean insideExit = false;

		if ((mouseX >= menuX && mouseX <= menuX
				+ GlbVar.startGameOption.getWidth())
				&& (mouseY >= menuY && mouseY <= menuY
						+ GlbVar.startGameOption.getHeight())) {
			insideStartGame = true;
		} else if ((mouseX >= menuX && mouseX <= menuX
				+ GlbVar.exitOption.getWidth())
				&& (mouseY >= menuY + 80 && mouseY <= menuY + 80
						+ GlbVar.exitOption.getHeight())) {
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
