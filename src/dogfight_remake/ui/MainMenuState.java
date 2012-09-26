package dogfight_remake.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	Image gameOptionsMenu;
	Image buttons;
	Image button1;
	Image button2;
	Image button3;
	String options = "OPTIONS";

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gameOptionsMenu = new Image("dogfight_remake/images/GameOptionsMenu.png");
		buttons = new Image("dogfight_remake/images/buttons.png");
		button1 = buttons.getSubImage(0, 0, 32, 32);
		button2 = buttons.getSubImage(32, 0, 32, 32);
		button3 = buttons.getSubImage(64, 0, 32, 32);
		// load the menu images
		GlbVar.startGameOption = GlbVar.menuOptions.getSubImage(0, 0, 377, 71);
		GlbVar.exitOption = GlbVar.menuOptions.getSubImage(0, 71, 377, 71);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// render the background
		GlbVar.background.draw(0, 0, 1680, 1050);
		gameOptionsMenu.draw(GameOptionsX - gameOptionsMenu.getWidth() / 2,
				GameOptionsY, gameOptionsMenu.getWidth() / 2,
				gameOptionsMenu.getHeight() / 2);
		if (GlbVar.getPlayerCollision()) {
			button2.draw(GameOptionsX - gameOptionsMenu.getWidth() / 2 + 30, GameOptionsY + 50, 16, 16);
		} else {
			button1.draw(GameOptionsX - gameOptionsMenu.getWidth() / 2 + 30, GameOptionsY + 50, 16, 16);
		}
		g.drawString("Air Collisions", GameOptionsX - gameOptionsMenu.getWidth() / 2 + 50, GameOptionsY + 50);

		button3.draw(1680 - 91, 72, 16, 16);
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
		} else if ((mouseX >= GameOptionsX - gameOptionsMenu.getWidth() / 2 + 30 && mouseX <= GameOptionsX - gameOptionsMenu.getWidth() / 2 + 30 + 16)
				&& (mouseY >= GameOptionsY + 50 && mouseY <= GameOptionsY + 50 + 16)) {
			insidePlCol = true;
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
