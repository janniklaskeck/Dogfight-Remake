package dogfight_remake.controls;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;

import dogfight_remake.main.GamePanel.States;
import dogfight_remake.main.GamePanel;
import dogfight_remake.main.GlbVar;

@SuppressWarnings("serial")
public class KeyInput {

	boolean test = false;

	public KeyInput() {
		controls();
	}

	public void controls() {
		// Miscellaneous
		Action esc_pressed = new AbstractAction("esc_pressed") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_ESCAPE] = true;
				GamePanel.key_state_down[KeyEvent.VK_ESCAPE] = false;
			}
		};
		Action esc_released = new AbstractAction("esc_released") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_ESCAPE] = false;
				GamePanel.key_state_down[KeyEvent.VK_ESCAPE] = true;
			}
		};

		Action pause_pressed = new AbstractAction("pause_pressed") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_P] = true;
				GamePanel.key_state_down[KeyEvent.VK_P] = false;
			}
		};
		Action pause_released = new AbstractAction("pause_released") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_P] = false;
				GamePanel.key_state_down[KeyEvent.VK_P] = true;
			}
		};
		// Player 1 Movement
		Action p1_mov_up = new AbstractAction("p1_mov_up") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_UP] = false;
				GamePanel.key_state_down[KeyEvent.VK_UP] = true;
			}
		};
		Action p1_mov_down = new AbstractAction("p1_mov_down") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_DOWN] = false;
				GamePanel.key_state_down[KeyEvent.VK_DOWN] = true;
			}
		};
		Action p1_mov_left = new AbstractAction("p1_mov_left") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_LEFT] = false;
				GamePanel.key_state_down[KeyEvent.VK_LEFT] = true;
			}
		};
		Action p1_mov_right = new AbstractAction("p1_mov_right") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_RIGHT] = false;
				GamePanel.key_state_down[KeyEvent.VK_RIGHT] = true;
			}
		};
		Action p1_sht_gun = new AbstractAction("p1_sht_gun") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_PERIOD] = false;
				GamePanel.key_state_down[KeyEvent.VK_PERIOD] = true;
			}
		};
		Action p1_sht_mis = new AbstractAction("p1_sht_mis") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_COMMA] = false;
				GamePanel.key_state_down[KeyEvent.VK_COMMA] = true;
			}
		};
		Action p1_sht_bomb = new AbstractAction("p1_sht_bomb") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_MINUS] = false;
				GamePanel.key_state_down[KeyEvent.VK_MINUS] = true;
			}
		};
		// Player 1 Movement released
		Action p1_mov_up_r = new AbstractAction("p1_mov_up_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_UP] = true;
				GamePanel.key_state_down[KeyEvent.VK_UP] = false;
			}
		};
		Action p1_mov_down_r = new AbstractAction("p1_mov_down_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_DOWN] = true;
				GamePanel.key_state_down[KeyEvent.VK_DOWN] = false;
			}
		};
		Action p1_mov_left_r = new AbstractAction("p1_mov_left_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_LEFT] = true;
				GamePanel.key_state_down[KeyEvent.VK_LEFT] = false;
			}
		};
		Action p1_mov_right_r = new AbstractAction("p1_mov_right_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_RIGHT] = true;
				GamePanel.key_state_down[KeyEvent.VK_RIGHT] = false;
			}
		};
		Action p1_sht_gun_r = new AbstractAction("p1_sht_gun_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_PERIOD] = true;
				GamePanel.key_state_down[KeyEvent.VK_PERIOD] = false;
			}
		};
		Action p1_sht_mis_r = new AbstractAction("p1_sht_mis_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_COMMA] = true;
				GamePanel.key_state_down[KeyEvent.VK_COMMA] = false;
			}
		};
		Action p1_sht_bomb_r = new AbstractAction("p1_sht_bomb_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_MINUS] = true;
				GamePanel.key_state_down[KeyEvent.VK_MINUS] = false;
			}
		};
		// ************************************************************************************************
		// Player 2 Movement
		Action p2_mov_up = new AbstractAction("p1_mov_up") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_R] = false;
				GamePanel.key_state_down[KeyEvent.VK_R] = true;
			}
		};
		Action p2_mov_down = new AbstractAction("p1_mov_down") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_F] = false;
				GamePanel.key_state_down[KeyEvent.VK_F] = true;
			}
		};
		Action p2_mov_left = new AbstractAction("p1_mov_left") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_D] = false;
				GamePanel.key_state_down[KeyEvent.VK_D] = true;
			}
		};
		Action p2_mov_right = new AbstractAction("p1_mov_right") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_G] = false;
				GamePanel.key_state_down[KeyEvent.VK_G] = true;
			}
		};
		Action p2_sht_gun = new AbstractAction("p1_sht_gun") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_1] = false;
				GamePanel.key_state_down[KeyEvent.VK_1] = true;
			}
		};
		Action p2_sht_mis = new AbstractAction("p1_sht_mis") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_2] = false;
				GamePanel.key_state_down[KeyEvent.VK_2] = true;
			}
		};
		Action p2_sht_bomb = new AbstractAction("p1_sht_bomb") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_3] = false;
				GamePanel.key_state_down[KeyEvent.VK_3] = true;
			}
		};

		// Player 2 Movement released
		Action p2_mov_up_r = new AbstractAction("p1_mov_up_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_R] = true;
				GamePanel.key_state_down[KeyEvent.VK_R] = false;
			}
		};
		Action p2_mov_down_r = new AbstractAction("p1_mov_down_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_F] = true;
				GamePanel.key_state_down[KeyEvent.VK_F] = false;
			}
		};
		Action p2_mov_left_r = new AbstractAction("p1_mov_left_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_D] = true;
				GamePanel.key_state_down[KeyEvent.VK_D] = false;
			}
		};
		Action p2_mov_right_r = new AbstractAction("p1_mov_right_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_G] = true;
				GamePanel.key_state_down[KeyEvent.VK_G] = false;
			}
		};
		Action p2_sht_gun_r = new AbstractAction("p1_sht_gun_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_1] = true;
				GamePanel.key_state_down[KeyEvent.VK_1] = false;
			}
		};
		Action p2_sht_mis_r = new AbstractAction("p1_sht_mis_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_2] = true;
				GamePanel.key_state_down[KeyEvent.VK_2] = false;
			}
		};
		Action p2_sht_bomb_r = new AbstractAction("p1_sht_bomb_r") {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.key_state_up[KeyEvent.VK_3] = true;
				GamePanel.key_state_down[KeyEvent.VK_3] = false;
			}
		};
		// ************************************************************************************************
		// Action Map
		// Miscellaneous
		GamePanel.content.getActionMap().put("esc_pressed", esc_pressed);
		GamePanel.content.getActionMap().put("esc_released", esc_released);
		GamePanel.content.getActionMap().put("pause_pressed", pause_pressed);
		GamePanel.content.getActionMap().put("pause_released", pause_released);
		// Player 1
		GamePanel.content.getActionMap().put("p1_mov_down", p1_mov_down);
		GamePanel.content.getActionMap().put("p1_mov_down_r", p1_mov_down_r);
		GamePanel.content.getActionMap().put("p1_mov_left", p1_mov_left);
		GamePanel.content.getActionMap().put("p1_mov_left_r", p1_mov_left_r);
		GamePanel.content.getActionMap().put("p1_mov_right", p1_mov_right);
		GamePanel.content.getActionMap().put("p1_mov_right_r", p1_mov_right_r);
		GamePanel.content.getActionMap().put("p1_mov_up", p1_mov_up);
		GamePanel.content.getActionMap().put("p1_mov_up_r", p1_mov_up_r);
		GamePanel.content.getActionMap().put("p1_sht_bomb", p1_sht_bomb);
		GamePanel.content.getActionMap().put("p1_sht_bomb_r", p1_sht_bomb_r);
		GamePanel.content.getActionMap().put("p1_sht_gun", p1_sht_gun);
		GamePanel.content.getActionMap().put("p1_sht_gun_r", p1_sht_gun_r);
		GamePanel.content.getActionMap().put("p1_sht_mis", p1_sht_mis);
		GamePanel.content.getActionMap().put("p1_sht_mis_r", p1_sht_mis_r);
		// Player 2
		GamePanel.content.getActionMap().put("p2_mov_down", p2_mov_down);
		GamePanel.content.getActionMap().put("p2_mov_down_r", p2_mov_down_r);
		GamePanel.content.getActionMap().put("p2_mov_left", p2_mov_left);
		GamePanel.content.getActionMap().put("p2_mov_left_r", p2_mov_left_r);
		GamePanel.content.getActionMap().put("p2_mov_right", p2_mov_right);
		GamePanel.content.getActionMap().put("p2_mov_right_r", p2_mov_right_r);
		GamePanel.content.getActionMap().put("p2_mov_up", p2_mov_up);
		GamePanel.content.getActionMap().put("p2_mov_up_r", p2_mov_up_r);
		GamePanel.content.getActionMap().put("p2_sht_bomb", p2_sht_bomb);
		GamePanel.content.getActionMap().put("p2_sht_bomb_r", p2_sht_bomb_r);
		GamePanel.content.getActionMap().put("p2_sht_gun", p2_sht_gun);
		GamePanel.content.getActionMap().put("p2_sht_gun_r", p2_sht_gun_r);
		GamePanel.content.getActionMap().put("p2_sht_mis", p2_sht_mis);
		GamePanel.content.getActionMap().put("p2_sht_mis_r", p2_sht_mis_r);
		// ************************************************************************************************
		// Input Map
		// Miscellaneous
		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("ESCAPE"), "esc_pressed");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("released ESCAPE"), "esc_released");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("P"), "pause_pressed");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("released P"), "pause_released");
		// ************************************************************************************************
		// Player 1
		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true),
				"p1_mov_right_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false),
				"p1_mov_right");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true),
				"p1_mov_left_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false),
				"p1_mov_left");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "p1_mov_up_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "p1_mov_up");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true),
				"p1_mov_down_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),
				"p1_mov_down");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0, true),
				"p1_sht_gun_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0, false),
				"p1_sht_gun");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, 0, true),
				"p1_sht_mis_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, 0, false),
				"p1_sht_mis");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0, true),
				"p1_sht_bomb_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0, false),
				"p1_sht_bomb");
		// ************************************************************************************************
		// Player 2
		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_G, 0, true),
				"p2_mov_right_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0, false),
						"p2_mov_right");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true),
						"p2_mov_left_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "p2_mov_left");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, true), "p2_mov_up_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, false), "p2_mov_up");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0, true),
						"p2_mov_down_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F, 0, false), "p2_mov_down");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, true), "p2_sht_gun_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "p2_sht_gun");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, true), "p2_sht_mis_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), "p2_sht_mis");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, true),
						"p2_sht_bomb_r");

		GamePanel.content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, false), "p2_sht_bomb");
		// ************************************************************************************************\\

		// Miscellaneous
		if (GamePanel.key_state_down[KeyEvent.VK_ESCAPE] == true
				&& GamePanel.key_state_up[KeyEvent.VK_ESCAPE] == false) {

			System.exit(0);

		}
		int test = 0;
		if (GamePanel.key_state_down[KeyEvent.VK_P] == true
				&& GamePanel.key_state_up[KeyEvent.VK_P] == false) {
			if (test == 0) {

			}
		}

		if (GamePanel.key_state_down[KeyEvent.VK_P] == false
				&& GamePanel.key_state_up[KeyEvent.VK_P] == true) {
			// GamePanel.state = States.PLAYING;

		}
		// ************************************************************************************************\\
		if (GamePanel.state == States.PLAYING) {
			if (GamePanel.r.player1 != null) {
				// Movement Player 1
				if (GamePanel.key_state_down[KeyEvent.VK_LEFT] == true
						&& GamePanel.key_state_up[KeyEvent.VK_LEFT] == false) {
					GamePanel.r.player1.increaseAngle(-2);
				}
				if (GamePanel.key_state_down[KeyEvent.VK_RIGHT] == true
						&& GamePanel.key_state_up[KeyEvent.VK_RIGHT] == false) {
					GamePanel.r.player1.increaseAngle(2);
				}
				if (GamePanel.key_state_down[KeyEvent.VK_UP] == true) {
					if (GamePanel.r.player1.getHspeed() < 1
							|| GamePanel.r.player1.getHspeed() > -1) {
						GamePanel.r.player1.incSpeed(0.1);
					} else {
						GamePanel.r.player1.incSpeed(0.2);
					}
				} else if (GamePanel.key_state_down[KeyEvent.VK_DOWN] == true) {
					if (GamePanel.r.player1.getSpeed() <= 0.6) {
						GamePanel.r.player1.setSpeed(0.6);
					} else {
						GamePanel.r.player1.decSpeed(0.035, false);
					}
				}
				if (GamePanel.key_state_down[KeyEvent.VK_UP] == false
						&& GamePanel.key_state_down[KeyEvent.VK_DOWN] == false) {
					if (GamePanel.r.player1.getHspeed() < 1
							|| GamePanel.r.player1.getHspeed() > -1) {
						if (GamePanel.r.player1.getVspeed() < 0) {
							GamePanel.r.player1.decSpeed(0.1, true);
						} else {
							GamePanel.r.player1.decSpeed(0.06, true);
						}
					} else {
						GamePanel.r.player1.decSpeed(0.1, true);
					}
				}

				if (GamePanel.key_state_down[KeyEvent.VK_COMMA]) {
					if (GamePanel.r.player1 != null) {
						Weapons tmp = GamePanel.r.player1.shoot_primary();
						if (tmp != null) {
							if (tmp.getType() == WeaponTypes.GUN) {
								GamePanel.snd.prim_gun_heavy.play(1,
										GlbVar.sounds_volume);
							} else {
								GamePanel.snd.prim_gun_light.play(1,
										GlbVar.sounds_volume);
							}
							GamePanel.weapons.add(tmp);
						}
					}
				}

				if (GamePanel.key_state_down[KeyEvent.VK_PERIOD]) {
					if (GamePanel.r.player1 != null) {
						Weapons tmp = GamePanel.r.player1.shoot_secondary_1();
						if (tmp != null) {
							if (tmp.getType() == WeaponTypes.GUIDED_AIR
									|| tmp.getType() == WeaponTypes.UNGUIDED) {
								GamePanel.snd.sec_missile1
										.play(1, GlbVar.sounds_volume);
							} else {
								GamePanel.snd.sec_bomb_drop
								.play(1, GlbVar.sounds_volume);
							}
							GamePanel.weapons.add(tmp);
						}
					}
				}
				if (GamePanel.key_state_down[KeyEvent.VK_MINUS]) {
					if (GamePanel.r.player1 != null) {
						Weapons tmp = GamePanel.r.player1.shoot_secondary_2();
						if (tmp != null) {
							if (tmp.getType() == WeaponTypes.GUIDED_AIR
									|| tmp.getType() == WeaponTypes.UNGUIDED) {
								GamePanel.snd.sec_missile1
										.play(1, GlbVar.sounds_volume);
							} else {
								GamePanel.snd.sec_bomb_drop
								.play(1, GlbVar.sounds_volume);
							}
							GamePanel.weapons.add(tmp);
						}
					}
				}
			}

			// ************************************************************************************************\\
			// Movement Player 2
			if (GamePanel.r.player2 != null) {
				if (GamePanel.key_state_down[KeyEvent.VK_D] == true
						&& GamePanel.key_state_up[KeyEvent.VK_D] == false) {
					GamePanel.r.player2.increaseAngle(-2);
				}
				if (GamePanel.key_state_down[KeyEvent.VK_G] == true
						&& GamePanel.key_state_up[KeyEvent.VK_G] == false) {
					GamePanel.r.player2.increaseAngle(2);
				}
				if (GamePanel.key_state_down[KeyEvent.VK_R] == true) {
					if (GamePanel.r.player2.getHspeed() < 1
							|| GamePanel.r.player2.getHspeed() > -1) {
						GamePanel.r.player2.incSpeed(0.1);
					} else {
						GamePanel.r.player2.incSpeed(0.2);
					}
				} else if (GamePanel.key_state_down[KeyEvent.VK_F] == true) {
					if (GamePanel.r.player2.getSpeed() < 0.6) {
						GamePanel.r.player2.setSpeed(0.5);
					} else {
						GamePanel.r.player2.decSpeed(0.035, false);
					}
				}
				if (GamePanel.key_state_down[KeyEvent.VK_R] == false
						&& GamePanel.key_state_up[KeyEvent.VK_F] == false) {
					if (GamePanel.r.player2.getHspeed() < 1
							|| GamePanel.r.player2.getHspeed() > -1) {
						if (GamePanel.r.player2.getVspeed() < 0) {
							GamePanel.r.player2.decSpeed(0.1, true);
						} else {
							GamePanel.r.player2.decSpeed(0.06, true);
						}
					} else {
						GamePanel.r.player2.decSpeed(0.1, true);
					}
				}
				if (GamePanel.key_state_down[KeyEvent.VK_1]) {
					if (GamePanel.r.player2 != null) {
						Weapons tmp = GamePanel.r.player2.shoot_primary();
						if (tmp != null) {
							if (tmp.getType() == WeaponTypes.GUN) {
								GamePanel.snd.prim_gun_heavy.play(1,
										GlbVar.sounds_volume);
							} else {
								GamePanel.snd.prim_gun_light.play(1,
										GlbVar.sounds_volume);
							}
							GamePanel.weapons.add(tmp);
						}
					}
				}
				if (GamePanel.key_state_down[KeyEvent.VK_2]) {
					if (GamePanel.r.player2 != null) {
						Weapons tmp = GamePanel.r.player2.shoot_secondary_1();
						if (tmp != null) {
							if (tmp.getType() == WeaponTypes.GUIDED_AIR
									|| tmp.getType() == WeaponTypes.UNGUIDED) {
								GamePanel.snd.sec_missile1
										.play(1, GlbVar.sounds_volume);
							} else {
								GamePanel.snd.sec_bomb_drop
								.play(1, GlbVar.sounds_volume);
							}
							GamePanel.weapons.add(tmp);
						}
					}
				}
				if (GamePanel.key_state_down[KeyEvent.VK_3]) {
					if (GamePanel.r.player2 != null) {
						Weapons tmp = GamePanel.r.player2.shoot_secondary_2();
						if (tmp != null) {
							if (tmp.getType() == WeaponTypes.GUIDED_AIR
									|| tmp.getType() == WeaponTypes.UNGUIDED) {
								GamePanel.snd.sec_missile1
										.play(1, GlbVar.sounds_volume);
							} else {
								GamePanel.snd.sec_bomb_drop
								.play(1, GlbVar.sounds_volume);
							}
							GamePanel.weapons.add(tmp);
						}
					}
				}
			}
		}
	}
}
