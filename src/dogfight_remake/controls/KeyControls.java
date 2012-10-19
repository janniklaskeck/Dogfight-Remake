package dogfight_remake.controls;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.Var;

public class KeyControls {
	public static void update(GameContainer gc, StateBasedGame sbg, int delta) {
		Input input = gc.getInput();
		if (!Var.paused) {
			// Player 1
			if (input.isKeyDown(Var.p1_key_left)) {
				GamePlayState.r.player1.setMoveLeft(true);
			} else {
				GamePlayState.r.player1.setMoveLeft(false);
			}
			if (input.isKeyDown(Var.p1_key_right)) {
				GamePlayState.r.player1.setMoveRight(true);
			} else {
				GamePlayState.r.player1.setMoveRight(false);
			}
			if (input.isKeyDown(Var.p1_key_up)) {
				GamePlayState.r.player1.setAccel(true);
			}
			if (input.isKeyDown(Var.p1_key_down)) {
				GamePlayState.r.player1.setBrake(true);

			}
			if (!input.isKeyDown(Var.p1_key_down)
					&& !input.isKeyDown(Var.p1_key_up)) {
				GamePlayState.r.player1.setAccel(false);
				GamePlayState.r.player1.setBrake(false);
			}
			if (input.isKeyDown(Var.p1_key_prim1)) {
				if (GamePlayState.r.player1 != null) {
					GamePlayState.r.player1.setShoot_prim1(true);
				}
			} else {
				GamePlayState.r.player1.setShoot_prim1(false);
			}
			if (input.isKeyDown(Var.p1_key_prim2)) {
				if (GamePlayState.r.player1 != null) {
					GamePlayState.r.player1.setShoot_prim2(true);
				}
			} else {
				GamePlayState.r.player1.setShoot_prim2(false);
			}
			if (input.isKeyDown(Var.p1_key_sec1)) {
				if (GamePlayState.r.player1 != null) {
					GamePlayState.r.player1.setShoot_sec1(true);
				}
			} else {
				GamePlayState.r.player1.setShoot_sec1(false);
			}
			if (input.isKeyDown(Var.p1_key_sec2)) {
				if (GamePlayState.r.player1 != null) {
					GamePlayState.r.player1.setShoot_sec2(true);
				}
			} else {
				GamePlayState.r.player1.setShoot_sec2(false);
			}

			// Player 2
			if (input.isKeyDown(Var.p2_key_left)) {
				GamePlayState.r.player2.setMoveLeft(true);
			} else {
				GamePlayState.r.player2.setMoveLeft(false);
			}
			if (input.isKeyDown(Var.p2_key_right)) {
				GamePlayState.r.player2.setMoveRight(true);
			} else {
				GamePlayState.r.player2.setMoveRight(false);
			}
			if (input.isKeyDown(Var.p2_key_up)) {
				GamePlayState.r.player2.setAccel(true);
			}
			if (input.isKeyDown(Var.p2_key_down)) {
				GamePlayState.r.player2.setBrake(true);

			}
			if (!input.isKeyDown(Var.p2_key_down)
					&& !input.isKeyDown(Var.p2_key_up)) {
				GamePlayState.r.player2.setAccel(false);
				GamePlayState.r.player2.setBrake(false);
			}
			if (input.isKeyDown(Var.p2_key_prim1)) {
				if (GamePlayState.r.player2 != null) {
					GamePlayState.r.player2.setShoot_prim1(true);
				}
			} else {
				GamePlayState.r.player2.setShoot_prim1(false);
			}
			if (input.isKeyDown(Var.p2_key_prim2)) {
				if (GamePlayState.r.player2 != null) {
					GamePlayState.r.player2.setShoot_prim2(true);
				}
			} else {
				GamePlayState.r.player2.setShoot_prim2(false);
			}
			if (input.isKeyDown(Var.p2_key_sec1)) {
				if (GamePlayState.r.player2 != null) {
					GamePlayState.r.player2.setShoot_sec1(true);
				}
			} else {
				GamePlayState.r.player2.setShoot_sec1(false);
			}
			if (input.isKeyDown(Var.p2_key_sec2)) {
				if (GamePlayState.r.player2 != null) {
					GamePlayState.r.player2.setShoot_sec2(true);
				}
			} else {
				GamePlayState.r.player2.setShoot_sec2(false);
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			// game.enterState(Dogfight_Remake.MAINMENUSTATE);
			gc.exit();
		}
		if (input.isKeyPressed(Input.KEY_P)) {
			Var.paused = true;
		}

	}
}
