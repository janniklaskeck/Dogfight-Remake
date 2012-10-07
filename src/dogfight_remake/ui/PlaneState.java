package dogfight_remake.ui;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.entities.planes.PlaneTypes;
import dogfight_remake.main.Dogfight_Remake;
import dogfight_remake.main.GlbVar;

public class PlaneState extends BasicGameState {

	private int stateID = -1;
	public static ArrayList<PlaneTypes> planeList;
	float GameOptionsX = 1680 - 50;
	float GameOptionsY = 50;
	int i = 0;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(GlbVar.vSync);
		planeList = new ArrayList<PlaneTypes>();
		planeList.add(PlaneTypes.NORMAL);
		planeList.add(PlaneTypes.FAST);
		planeList.add(PlaneTypes.SLOW);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		GlbVar.background.draw(0, 0, GlbVar.dim_chosen.width,
				GlbVar.dim_chosen.height);
		GlbVar.arrow_left.draw(150, GlbVar.dim_chosen.height - 100);
		GlbVar.arrow_right.draw(414, GlbVar.dim_chosen.height - 100);
		g.drawString("" + planeList.get(i), 224, GlbVar.dim_chosen.height - 100);
		GlbVar.button3.draw(GameOptionsX - 41, GameOptionsY + 22, 16, 16);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		boolean insideArrowLeft = false;
		boolean insideArrowRight = false;
		boolean insideExitCorner = false;
		boolean insideWeapon1 = false;
		boolean insideWeapon2 = false;
		boolean insideWeapon3 = false;

		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		if (mouseX <= 214 && mouseX >= 150
				&& mouseY <= GlbVar.dim_chosen.height - 36
				&& mouseY >= GlbVar.dim_chosen.height - 100) {
			insideArrowLeft = true;
		} else if (mouseX <= 478 && mouseX >= 414
				&& mouseY <= GlbVar.dim_chosen.height - 36
				&& mouseY >= GlbVar.dim_chosen.height - 100) {
			insideArrowRight = true;
		} else if ((mouseX >= GameOptionsX - 41 && mouseX <= GameOptionsX - 25)
				&& (mouseY >= GameOptionsY + 22 && mouseY <= GameOptionsY + 38)) {
			insideExitCorner = true;
		}

		if (insideArrowLeft) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (i - 1 < 0) {
					i = planeList.size() - 1;
				} else {
					i--;
				}
			}
		} else if (insideArrowRight) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (i + 1 > planeList.size() - 1) {
					i = 0;
				} else {
					i++;
				}
			}
		} else if (insideExitCorner) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				sbg.enterState(Dogfight_Remake.MAINMENUSTATE);
		}
	}

	public PlaneState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public int getID() {
		return stateID;
	}

}
