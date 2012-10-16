package dogfight_remake.controls;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.GlbVar;

public class KeyControls {

	public static void update(GameContainer gc, StateBasedGame game, int delta) {
		Input input = gc.getInput();
		if (!GlbVar.paused) {
			// Player 1
			if (input.isKeyDown(Input.KEY_LEFT)) {
				GamePlayState.r.player1.increaseAngle(-0.12f * delta);
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				GamePlayState.r.player1.increaseAngle(0.12f * delta);
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				if (GamePlayState.r.player1.getVspeed() < 0
						&& !GamePlayState.r.player1.isInStall()) {
					GamePlayState.r.player1.incSpeed(0.005f * delta);
				} else if (GamePlayState.r.player1.getVspeed() >= 0
						&& !GamePlayState.r.player1.isInStall()) {
					GamePlayState.r.player1.incSpeed(0.01f * delta);
				} else if (GamePlayState.r.player1.getVspeed() >= 0
						&& GamePlayState.r.player1.isInStall()) {
					GamePlayState.r.player1.incSpeed(0.005f * delta);
				} else {
					GamePlayState.r.player1.incSpeed(0.0005f * delta);
				}
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				if (GamePlayState.r.player1.getSpeed() <= 0.6) {
					GamePlayState.r.player1.setSpeed(0.6f);
				} else {
					GamePlayState.r.player1.decSpeed(0.035f, false);
				}
			}
			if (!input.isKeyDown(Input.KEY_DOWN)
					&& !input.isKeyDown(Input.KEY_UP)) {

				if (GamePlayState.r.player1.getVspeed() < 0) {
					GamePlayState.r.player1.decSpeed(0.1f, true);
				} else {
					GamePlayState.r.player1.decSpeed(0.06f, true);
				}
			}
			if (input.isKeyDown(Input.KEY_COMMA)) {
				if (GamePlayState.r.player1 != null) {
					Weapons tmp = GamePlayState.r.player1.shoot_primary();
					if (tmp != null) {
						tmp.getSound().play(1, GlbVar.sounds_volume);
						GamePlayState.weapons.add(tmp);
					}
				}
			}
			if (input.isKeyDown(Input.KEY_PERIOD)) {
				if (GamePlayState.r.player1 != null) {
					Weapons tmp = GamePlayState.r.player1.shoot_secondary_1();
					if (tmp != null) {
						tmp.getSound().play(1, GlbVar.sounds_volume);
						GamePlayState.weapons.add(tmp);
					}
				}
			}
			if (input.isKeyDown(Input.KEY_MINUS)) {
				if (GamePlayState.r.player1 != null) {
					Weapons tmp = GamePlayState.r.player1.shoot_secondary_2();
					if (tmp != null) {
						tmp.getSound().play(1, GlbVar.sounds_volume);
						GamePlayState.weapons.add(tmp);
					}
				}
			}

			// Player 2
			if (input.isKeyDown(Input.KEY_D)) {
				GamePlayState.r.player2.increaseAngle(-0.12f * delta);
			}
			if (input.isKeyDown(Input.KEY_G)) {
				GamePlayState.r.player2.increaseAngle(0.12f * delta);
			}
			if (input.isKeyDown(Input.KEY_R)) {
				if (GamePlayState.r.player2.getVspeed() < 0
						&& !GamePlayState.r.player2.isInStall()) {
					GamePlayState.r.player2.incSpeed(0.1f);
				} else if (GamePlayState.r.player2.getVspeed() >= 0
						&& !GamePlayState.r.player2.isInStall()) {
					GamePlayState.r.player2.incSpeed(0.2f);
				} else if (GamePlayState.r.player2.getVspeed() >= 0
						&& GamePlayState.r.player2.isInStall()) {
					GamePlayState.r.player2.incSpeed(0.1f);
				} else {
					GamePlayState.r.player2.incSpeed(0.01f);
				}
			}
			if (input.isKeyDown(Input.KEY_F)) {
				if (GamePlayState.r.player2.getSpeed() <= 0.6) {
					GamePlayState.r.player2.setSpeed(0.6f);
				} else {
					GamePlayState.r.player2.decSpeed(0.035f, false);
				}
			}
			if (!input.isKeyDown(Input.KEY_F) && !input.isKeyDown(Input.KEY_R)) {
				if (GamePlayState.r.player2.getVspeed() < 0) {
					GamePlayState.r.player2.decSpeed(0.1f, true);
				} else {
					GamePlayState.r.player2.decSpeed(0.06f, true);
				}
			}
			if (input.isKeyDown(Input.KEY_1)) {
				if (GamePlayState.r.player2 != null) {
					Weapons tmp = GamePlayState.r.player2.shoot_primary();
					if (tmp != null) {
						tmp.getType().getSound().play(1, GlbVar.sounds_volume);
						GamePlayState.weapons.add(tmp);
					}
				}
			}
			if (input.isKeyDown(Input.KEY_2)) {
				if (GamePlayState.r.player2 != null) {
					Weapons tmp = GamePlayState.r.player2.shoot_secondary_1();
					if (tmp != null) {
						tmp.getSound().play(1, GlbVar.sounds_volume);
						GamePlayState.weapons.add(tmp);
					}
				}
			}
			if (input.isKeyDown(Input.KEY_3)) {
				if (GamePlayState.r.player2 != null) {
					Weapons tmp = GamePlayState.r.player2.shoot_secondary_2();
					if (tmp != null) {
						tmp.getSound().play(1, GlbVar.sounds_volume);
						GamePlayState.weapons.add(tmp);
					}
				}
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			// game.enterState(Dogfight_Remake.MAINMENUSTATE);
			gc.exit();
		}
		if (input.isKeyPressed(Input.KEY_P)) {
			GlbVar.paused = true;
		}

	}
}
