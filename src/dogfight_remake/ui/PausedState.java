package dogfight_remake.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.controls.KeyControls;
import dogfight_remake.main.Dogfight_Remake;
import dogfight_remake.main.Var;

public class PausedState extends BasicGameState {
	private int stateID = -1;

	public PausedState(int stateID) {
		this.stateID = stateID;
	}

	int menuX = 400;
	int menuY = 400;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		Var.continueGame = Var.pauseMenuText.getSubImage(0, 0, 250, 60);
		Var.mainMenu = Var.pauseMenuText.getSubImage(0, 60, 250, 60);
		Var.exit = Var.pauseMenuText.getSubImage(0, 120, 250, 60);
		Var.options = Var.pauseMenuText.getSubImage(0, 180, 250, 60);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		Var.img_pause_bg.draw(0, 0);
		Var.pauseMenu.draw(menuX, menuY);
		Var.continueGame.draw(menuX + 100, menuY + 35, 100, 45);
		Var.options.draw(menuX + 100, menuY + 110, 100, 45);
		Var.mainMenu.draw(menuX + 100, menuY + 185, 100, 45);
		Var.exit.draw(menuX + 100, menuY + 270, 100, 45);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		KeyControls.update(gc, sbg, delta);

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideContinue = false;
		boolean insideExit = false;
		boolean insideMainMenu = false;
		boolean insideOptions = false;

		if ((mouseX >= menuX + 100 && mouseX <= menuX + 100
				+ Var.continueGame.getWidth())
				&& (mouseY >= menuY + 35 && mouseY <= menuY + 35
						+ Var.continueGame.getHeight())) {
			insideContinue = true;
		} else if ((mouseX >= menuX + 100 && mouseX <= menuX + 100
				+ Var.exit.getWidth())
				&& (mouseY >= menuY + 270 && mouseY <= menuY + 270
						+ Var.exit.getHeight())) {
			insideExit = true;
		} else if ((mouseX >= menuX + 100 && mouseX <= menuX + 100
				+ Var.mainMenu.getWidth())
				&& (mouseY >= menuY + 185 && mouseY <= menuY + 185
						+ Var.mainMenu.getHeight())) {
			insideMainMenu = true;
		} else if ((mouseX >= menuX + 100 && mouseX <= menuX + 100
				+ Var.options.getWidth())
				&& (mouseY >= menuY + 110 && mouseY <= menuY + 110
						+ Var.options.getHeight())) {
			insideOptions = true;
		}
		if (insideContinue) {

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				Var.paused = false;
				sbg.enterState(Dogfight_Remake.GAMEPLAYSTATE);
			}
		} else if (insideExit) {

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				gc.exit();
		} else if (insideMainMenu) {
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

				sbg.enterState(Dogfight_Remake.MAINMENUSTATE);
			}
		} else if (insideOptions) {
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				Var.app.setDisplayMode(Var.dim_chosen.width,
						Var.dim_chosen.height, Var.fullscreen);
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
}
