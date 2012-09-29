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
	float GameOptionsX = 1680 - 50;
	float GameOptionsY = 50;
	float scaleStep = 0.0001f;
	String options = "OPTIONS";
	String fullscreen = "FULLSCREEN";

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		GlbVar.button1 = GlbVar.buttons.getSubImage(0, 0, 32, 32);
		GlbVar.button2 = GlbVar.buttons.getSubImage(32, 0, 32, 32);
		GlbVar.button3 = GlbVar.buttons.getSubImage(64, 0, 32, 32);
		// load the menu images
		GlbVar.startGameOption = GlbVar.menuOptions.getSubImage(0, 0, 377, 71);
		GlbVar.exitOption = GlbVar.menuOptions.getSubImage(0, 71, 377, 71);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// render the background
		GlbVar.background.draw(0, 0, 1680, 1050);
		GlbVar.gameOptionsMenu.draw(
				GameOptionsX - GlbVar.gameOptionsMenu.getWidth() / 2,
				GameOptionsY, GlbVar.gameOptionsMenu.getWidth() / 2,
				GlbVar.gameOptionsMenu.getHeight() / 2);
		if (GlbVar.getPlayerCollision()) {
			GlbVar.button2.draw(
					GameOptionsX - GlbVar.gameOptionsMenu.getWidth() / 2 + 30,
					GameOptionsY + 50, 16, 16);
		} else {
			GlbVar.button1.draw(
					GameOptionsX - GlbVar.gameOptionsMenu.getWidth() / 2 + 30,
					GameOptionsY + 50, 16, 16);
		}
		g.drawString("Air Collisions",
				GameOptionsX - GlbVar.gameOptionsMenu.getWidth() / 2 + 50,
				GameOptionsY + 50);
		if (GlbVar.fullscreen) {
			GlbVar.button2.draw(
					GameOptionsX - GlbVar.gameOptionsMenu.getWidth() / 2 + 30,
					GameOptionsY + 70, 16, 16);
		} else {
			GlbVar.button1.draw(
					GameOptionsX - GlbVar.gameOptionsMenu.getWidth() / 2 + 30,
					GameOptionsY + 70, 16, 16);
		}
		g.drawString(fullscreen,
				GameOptionsX - GlbVar.gameOptionsMenu.getWidth() / 2 + 50,
				GameOptionsY + 70);
		GlbVar.button3.draw(1680 - 91, 72, 16, 16);
		g.drawString(options, 1680 - 512, 72);
		
		
		
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
		boolean insidePlCol = false;
		boolean insideFullscreen = false;

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
		} else if ((mouseX >= GameOptionsX - GlbVar.gameOptionsMenu.getWidth()
				/ 2 + 30 && mouseX <= GameOptionsX
				- GlbVar.gameOptionsMenu.getWidth() / 2 + 30 + 16)
				&& (mouseY >= GameOptionsY + 50 && mouseY <= GameOptionsY + 50 + 16)) {
			insidePlCol = true;
		} else if ((mouseX >= GameOptionsX - GlbVar.gameOptionsMenu.getWidth()
				/ 2 + 30 && mouseX <= GameOptionsX
				- GlbVar.gameOptionsMenu.getWidth() / 2 + 30 + 16)
				&& (mouseY >= GameOptionsY + 70 && mouseY <= GameOptionsY + 70 + 16)) {
			insideFullscreen = true;
		}

		if (insideStartGame) {
			if (startGameScale < 1.05f)
				startGameScale += scaleStep * delta;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(Dogfight_Remake.GAMEPLAYSTATE);
			}
		} else if (insideExit) {
			if (startGameScale > 1.0f)
				startGameScale -= scaleStep * delta;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				gc.exit();
		} else if (insidePlCol) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				if (!GlbVar.getPlayerCollision()) {
					GlbVar.setPlayerCollision(true);
				} else {
					GlbVar.setPlayerCollision(false);
				}
		} else if (insideFullscreen) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (GlbVar.fullscreen) {
					GlbVar.fullscreen = false;
				} else {
					GlbVar.fullscreen = true;
				}
			}
			GlbVar.app.setDisplayMode(GlbVar.dim_chosen.width,
					GlbVar.dim_chosen.height, GlbVar.fullscreen);
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
