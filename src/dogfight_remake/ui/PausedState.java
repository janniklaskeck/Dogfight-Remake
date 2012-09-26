package dogfight_remake.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.controls.KeyControls;
import dogfight_remake.main.Dogfight_Remake;
import dogfight_remake.main.GlbVar;

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
		GlbVar.continueGame = GlbVar.pauseMenuText.getSubImage(0, 0, 250, 60);
		GlbVar.mainMenu = GlbVar.pauseMenuText.getSubImage(0, 60, 250, 60);
		GlbVar.exit = GlbVar.pauseMenuText.getSubImage(0, 120, 250, 60);
		GlbVar.options = GlbVar.pauseMenuText.getSubImage(0, 180, 250, 60);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		GlbVar.img_pause_bg.draw(0, 0);
		GlbVar.pauseMenu.draw(menuX, menuY);
		GlbVar.continueGame.draw(menuX + 100, menuY + 35, 100, 45);
		GlbVar.options.draw(menuX + 100, menuY + 110, 100, 45);
		GlbVar.mainMenu.draw(menuX + 100, menuY + 185, 100, 45);
		GlbVar.exit.draw(menuX + 100, menuY + 270, 100, 45);
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
				+ GlbVar.continueGame.getWidth())
				&& (mouseY >= menuY + 35 && mouseY <= menuY + 35
						+ GlbVar.continueGame.getHeight())) {
			insideContinue = true;
		} else if ((mouseX >= menuX + 100 && mouseX <= menuX + 100
				+ GlbVar.exit.getWidth())
				&& (mouseY >= menuY + 270 && mouseY <= menuY + 270
						+ GlbVar.exit.getHeight())) {
			insideExit = true;
		} else if ((mouseX >= menuX + 100 && mouseX <= menuX + 100
				+ GlbVar.mainMenu.getWidth())
				&& (mouseY >= menuY + 185 && mouseY <= menuY + 185
						+ GlbVar.mainMenu.getHeight())) {
			insideMainMenu = true;
		} else if ((mouseX >= menuX + 100 && mouseX <= menuX + 100
				+ GlbVar.options.getWidth())
				&& (mouseY >= menuY + 110 && mouseY <= menuY + 110
						+ GlbVar.options.getHeight())) {
			insideOptions = true;
		}
		if (insideContinue) {

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				GlbVar.paused = false;
				sbg.enterState(Dogfight_Remake.GAMEPLAYSTATE);
			}
		} else if (insideExit) {

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				gc.exit();
		} else if (insideMainMenu) {
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				GlbVar.paused = false;
				sbg.enterState(Dogfight_Remake.MAINMENUSTATE);
			}
		} else if (insideOptions) {
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
}
