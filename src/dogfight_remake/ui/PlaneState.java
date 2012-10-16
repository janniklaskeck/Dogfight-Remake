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
import dogfight_remake.main.Var;

public class PlaneState extends BasicGameState {

	private int stateID = -1;
	public static ArrayList<PlaneTypes> planeList;
	private int index_plane1 = 0;
	private int index_plane2 = 0;
	public PlaneTypes chosen_plane;

	private float exitX;
	private float exitY;

	private float plane_leftX;
	private float plane_leftY;
	private float plane_rightX;
	private float plane_rightY;
	private float plane_nameX;
	private float plane_nameY;

	private float wpn1_leftX;
	private float wpn1_leftY;
	private float wpn1_rightX;
	private float wpn1_rightY;

	private float wpn2_leftX;
	private float wpn2_leftY;
	private float wpn2_rightX;
	private float wpn2_rightY;

	private float wpn3_leftX;
	private float wpn3_leftY;
	private float wpn3_rightX;
	private float wpn3_rightY;

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) {
		exitX = Var.dim_chosen.width - Var.dim_chosen.height / 50 - 16;
		exitY = Var.dim_chosen.height / 50;

		plane_leftX = Var.dim_chosen.width * 0.1f;
		plane_leftY = Var.dim_chosen.height - Var.dim_chosen.height
				* 0.15f;
		plane_rightX = Var.dim_chosen.width * 0.25f;
		plane_rightY = Var.dim_chosen.height - Var.dim_chosen.height
				* 0.15f;
		plane_nameX = Var.dim_chosen.width * 0.15f;
		plane_nameY = Var.dim_chosen.height - Var.dim_chosen.height
				* 0.15f;
		wpn1_leftX = 0;
		wpn1_leftY = 0;
		wpn1_rightX = 0;
		wpn1_rightY = 0;

		wpn2_leftX = 0;
		wpn2_leftY = 0;
		wpn2_rightX = 0;
		wpn2_rightY = 0;

		wpn3_leftX = 0;
		wpn3_leftY = 0;
		wpn3_rightX = 0;
		wpn3_rightY = 0;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(Var.vSync);
		planeList = new ArrayList<PlaneTypes>();
		planeList.add(PlaneTypes.NORMAL);
		planeList.add(PlaneTypes.FAST);
		planeList.add(PlaneTypes.SLOW);
		if (Var.plane_id == 1) {
			chosen_plane = planeList.get(index_plane1);
		} else {
			chosen_plane = planeList.get(index_plane2);
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		Var.background.draw(0, 0, Var.dim_chosen.width,
				Var.dim_chosen.height);
		// Plane left
		Var.arrow_left.draw(plane_leftX, plane_leftY);
		// Plane right
		Var.arrow_right.draw(plane_rightX, plane_rightY);
		// Plane Name
		if (Var.plane_id == 1) {
			g.drawString("" + planeList.get(index_plane1), plane_nameX,
					plane_nameY);
		} else {
			g.drawString("" + planeList.get(index_plane2), plane_nameX,
					plane_nameY);
		}
		// Weapon 1 left
		Var.arrow_left.draw(wpn1_leftX, wpn1_leftY);
		// Weapon 1 right
		Var.arrow_right.draw(wpn1_rightX, wpn1_rightY);
		// Weapon 2 left
		Var.arrow_left.draw(wpn2_leftX, wpn2_leftY);
		// Weapon 2 right
		Var.arrow_right.draw(wpn2_rightX, wpn2_rightY);
		// Weapon 3 left
		Var.arrow_left.draw(wpn3_leftX, wpn3_leftY);
		// Weapon 3 right
		Var.arrow_right.draw(wpn3_rightX, wpn3_rightY);

		// Exit Button
		Var.exitCorner.draw(exitX, exitY, 16, 16);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if (Var.plane_id == 1) {
			chosen_plane = planeList.get(index_plane1);
		} else {
			chosen_plane = planeList.get(index_plane2);
		}
		boolean insidePlaneLeft = false;
		boolean insidePlaneRight = false;
		boolean insideExitCorner = false;
		boolean insideWeapon1 = false;
		boolean insideWeapon2 = false;
		boolean insideWeapon3 = false;

		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		if (mouseX <= plane_leftX + Var.arrow_left.getWidth()
				&& mouseX >= plane_leftX
				&& mouseY <= plane_leftY + Var.arrow_left.getHeight()
				&& mouseY >= plane_leftY) {
			insidePlaneLeft = true;
		} else if (mouseX <= plane_rightX + Var.arrow_right.getWidth()
				&& mouseX >= plane_rightX
				&& mouseY <= plane_rightY + Var.arrow_right.getHeight()
				&& mouseY >= plane_rightY) {
			insidePlaneRight = true;
		} else if ((mouseX <= exitX + 16 && mouseX >= exitX)
				&& (mouseY <= exitY + 16 && mouseY >= exitY)) {
			insideExitCorner = true;
		} else if (mouseX >= 0 && mouseX <= 0 && mouseY >= 0 && mouseY <= 0) {
			insideWeapon1 = true;
		} else if (mouseX >= 0 && mouseX <= 0 && mouseY >= 0 && mouseY <= 0) {
			insideWeapon2 = true;
		} else if (mouseX >= 0 && mouseX <= 0 && mouseY >= 0 && mouseY <= 0) {
			insideWeapon3 = true;
		}

		if (insidePlaneLeft) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (Var.plane_id == 1) {
					if (index_plane1 - 1 < 0) {
						index_plane1 = planeList.size() - 1;
					} else {
						index_plane1--;
					}
					Var.player1 = planeList.get(index_plane1);
				} else {
					if (index_plane2 - 1 < 0) {
						index_plane2 = planeList.size() - 1;
					} else {
						index_plane2--;
					}
					Var.player2 = planeList.get(index_plane2);
				}
			}
		} else if (insidePlaneRight) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (Var.plane_id == 1) {
					if (index_plane1 + 1 > planeList.size() - 1) {
						index_plane1 = 0;
					} else {
						index_plane1++;
					}
					Var.player1 = planeList.get(index_plane1);
				} else {
					if (index_plane2 + 1 > planeList.size() - 1) {
						index_plane2 = 0;
					} else {
						index_plane2++;
					}
					Var.player2 = planeList.get(index_plane2);
				}
			}
		} else if (insideWeapon1) {

		} else if (insideWeapon2) {

		} else if (insideWeapon3) {

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
