package dogfight_remake.controls;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.GlbVar;

public class KeyControls {

	public static void update(GameContainer gc, StateBasedGame game, int delta) {
		Input input = gc.getInput();
		// Player 1
		if (input.isKeyDown(Input.KEY_LEFT)) {
			GamePlayState.r.player1.increaseAngle(-2);
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			GamePlayState.r.player1.increaseAngle(2);
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			if (GamePlayState.r.player1.getHspeed() < 1
					|| GamePlayState.r.player1.getHspeed() > -1) {
				GamePlayState.r.player1.incSpeed(0.1f);
			} else {
				GamePlayState.r.player1.incSpeed(0.2f);
			}
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			if (GamePlayState.r.player1.getSpeed() <= 0.6) {
				GamePlayState.r.player1.setSpeed(0.6f);
			} else {
				GamePlayState.r.player1.decSpeed(0.035f, false);
			}
		}
		if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
			if (GamePlayState.r.player1.getHspeed() < 1
					|| GamePlayState.r.player1.getHspeed() > -1) {
				if (GamePlayState.r.player1.getVspeed() < 0) {
					GamePlayState.r.player1.decSpeed(0.1f, true);
				} else {
					GamePlayState.r.player1.decSpeed(0.06f, true);
				}
			} else {
				GamePlayState.r.player1.decSpeed(0.1f, true);
			}
		}
		if (input.isKeyDown(Input.KEY_COMMA)) {
			if (GamePlayState.r.player1 != null) {
				Weapons tmp = GamePlayState.r.player1.shoot_primary();
				if (tmp != null) {
					if (tmp.getType() == WeaponTypes.GUN) {
						GamePlayState.snd.prim_gun_heavy.play(1,
								GlbVar.sounds_volume);
					} else {
						GamePlayState.snd.prim_gun_light.play(1,
								GlbVar.sounds_volume);
					}
					GamePlayState.weapons.add(tmp);
				}
			}
		}

		if (input.isKeyDown(Input.KEY_PERIOD)) {
			if (GamePlayState.r.player1 != null) {
				Weapons tmp = GamePlayState.r.player1.shoot_secondary_1();
				if (tmp != null) {
					if (tmp.getType() == WeaponTypes.GUIDED_AIR
							|| tmp.getType() == WeaponTypes.UNGUIDED) {
						GamePlayState.snd.sec_missile1.play(1,
								GlbVar.sounds_volume);
					} else {
						GamePlayState.snd.sec_bomb_drop.play(1,
								GlbVar.sounds_volume);
					}
					GamePlayState.weapons.add(tmp);
				}
			}
		}

		if (input.isKeyDown(Input.KEY_MINUS)) {
			if (GamePlayState.r.player1 != null) {
				Weapons tmp = GamePlayState.r.player1.shoot_secondary_2();
				if (tmp != null) {
					if (tmp.getType() == WeaponTypes.GUIDED_AIR
							|| tmp.getType() == WeaponTypes.UNGUIDED) {
						GamePlayState.snd.sec_missile1.play(1,
								GlbVar.sounds_volume);
					} else {
						GamePlayState.snd.sec_bomb_drop.play(1,
								GlbVar.sounds_volume);
					}
					GamePlayState.weapons.add(tmp);
				}
			}
		}

		// Player 2
		if (input.isKeyDown(Input.KEY_D)) {
			GamePlayState.r.player2.increaseAngle(-2);
		}

		if (input.isKeyDown(Input.KEY_G)) {
			GamePlayState.r.player2.increaseAngle(2);
		}

		if (input.isKeyDown(Input.KEY_R)) {
			if (GamePlayState.r.player2.getHspeed() < 1
					|| GamePlayState.r.player2.getHspeed() > -1) {
				GamePlayState.r.player2.incSpeed(0.1f);
			} else {
				GamePlayState.r.player2.incSpeed(0.2f);
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
			if (GamePlayState.r.player2.getHspeed() < 1
					|| GamePlayState.r.player2.getHspeed() > -1) {
				if (GamePlayState.r.player2.getVspeed() < 0) {
					GamePlayState.r.player2.decSpeed(0.1f, true);
				} else {
					GamePlayState.r.player2.decSpeed(0.06f, true);
				}
			} else {
				GamePlayState.r.player2.decSpeed(0.1f, true);
			}
		}
		if (input.isKeyDown(Input.KEY_1)) {
			if (GamePlayState.r.player2 != null) {
				Weapons tmp = GamePlayState.r.player2.shoot_primary();
				if (tmp != null) {
					if (tmp.getType() == WeaponTypes.GUN) {
						GamePlayState.snd.prim_gun_heavy.play(1,
								GlbVar.sounds_volume);
					} else {
						GamePlayState.snd.prim_gun_light.play(1,
								GlbVar.sounds_volume);
					}
					GamePlayState.weapons.add(tmp);
				}
			}
		}

		if (input.isKeyDown(Input.KEY_2)) {
			if (GamePlayState.r.player2 != null) {
				Weapons tmp = GamePlayState.r.player2.shoot_secondary_1();
				if (tmp != null) {
					if (tmp.getType() == WeaponTypes.GUIDED_AIR
							|| tmp.getType() == WeaponTypes.UNGUIDED) {
						GamePlayState.snd.sec_missile1.play(1,
								GlbVar.sounds_volume);
					} else {
						GamePlayState.snd.sec_bomb_drop.play(1,
								GlbVar.sounds_volume);
					}
					GamePlayState.weapons.add(tmp);
				}
			}
		}

		if (input.isKeyDown(Input.KEY_3)) {
			if (GamePlayState.r.player2 != null) {
				Weapons tmp = GamePlayState.r.player2.shoot_secondary_2();
				if (tmp != null) {
					if (tmp.getType() == WeaponTypes.GUIDED_AIR
							|| tmp.getType() == WeaponTypes.UNGUIDED) {
						GamePlayState.snd.sec_missile1.play(1,
								GlbVar.sounds_volume);
					} else {
						GamePlayState.snd.sec_bomb_drop.play(1,
								GlbVar.sounds_volume);
					}
					GamePlayState.weapons.add(tmp);
				}
			}
		}
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			//game.enterState(Dogfight_Remake.MAINMENUSTATE);
			gc.exit();
		}
	}
}
