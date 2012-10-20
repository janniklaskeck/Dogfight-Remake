package dogfight_remake.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.Var;
import dogfight_remake.rendering.Render;

public class GameUI {
	static Render r = GamePlayState.r;

	public static void renderPlayerInterface(float xpos, float ypos,
			GameContainer gc, Graphics g, int delta) {
		if (r.player1 != null && r.player2 != null) {
			g.setColor(Color.black);
			// Player 1
			g.drawString("Player1: " + r.player1.getHitpoints(),
					Var.dim_chosen.width / 20, Var.dim_chosen.height / 20);
			g.drawString(
					r.player1.getWeapon(1).getName() + ": "
							+ r.player1.getAmmo(1), Var.dim_chosen.width / 20,
					Var.dim_chosen.height / 20 + Var.dim_chosen.height / 30);
			g.drawString(
					r.player1.getWeapon(2).getName() + ": "
							+ r.player1.getAmmo(2), Var.dim_chosen.width / 20,
					Var.dim_chosen.height / 20 + Var.dim_chosen.height / 15);
			g.drawString(
					r.player1.getWeapon(3).getName() + ": "
							+ r.player1.getAmmo(3), Var.dim_chosen.width / 20,
					(Var.dim_chosen.height / 20) + (Var.dim_chosen.height / 30)
							+ (Var.dim_chosen.height / 15));
			g.drawString(
					r.player1.getWeapon(4).getName() + ": "
							+ r.player1.getAmmo(4), Var.dim_chosen.width / 20,
					(Var.dim_chosen.height / 20) + (Var.dim_chosen.height / 30)
							+ (Var.dim_chosen.height / 10));
			if (r.player1.getRespawn_timer() < Var.RESPAWNTIME_PLAYER) {
				g.drawString("Respawn Player1: " + r.player1.getRespawn_timer()
						/ 100, Var.dim_chosen.width / 5,
						Var.dim_chosen.height / 20);
			}
			// g.drawString(GamePlayState.weapons.size() + "", 400, 500);
			// Player 2
			g.drawString("Player2: " + r.player2.getHitpoints(),
					Var.dim_chosen.width - Var.dim_chosen.width / 7,
					Var.dim_chosen.height / 20);
			g.drawString(
					r.player2.getWeapon(1).getName() + ": "
							+ r.player2.getAmmo(1), Var.dim_chosen.width
							- Var.dim_chosen.width / 7, Var.dim_chosen.height
							/ 20 + Var.dim_chosen.height / 30);
			g.drawString(
					r.player2.getWeapon(2).getName() + ": "
							+ r.player2.getAmmo(2), Var.dim_chosen.width
							- Var.dim_chosen.width / 7, Var.dim_chosen.height
							/ 20 + Var.dim_chosen.height / 15);
			g.drawString(
					r.player2.getWeapon(3).getName() + ": "
							+ r.player2.getAmmo(3), Var.dim_chosen.width
							- Var.dim_chosen.width / 7,
					(Var.dim_chosen.height / 20) + (Var.dim_chosen.height / 30)
							+ (Var.dim_chosen.height / 15));
			g.drawString(
					r.player1.getWeapon(4).getName() + ": "
							+ r.player2.getAmmo(4), Var.dim_chosen.width
							- Var.dim_chosen.width / 7,
					(Var.dim_chosen.height / 20) + (Var.dim_chosen.height / 30)
							+ (Var.dim_chosen.height / 10));
			if (r.player2.getRespawn_timer() < Var.RESPAWNTIME_PLAYER) {
				g.drawString("Respawn Player2: " + r.player2.getRespawn_timer()
						/ 100, Var.dim_chosen.width - Var.dim_chosen.width / 3,
						Var.dim_chosen.height / 20);
			}
			// FPS and score
			g.drawString(Var.score_p1 + " : " + Var.score_p2,
					Var.dim_chosen.width / 2, Var.dim_chosen.height / 10);
			g.drawString(Var.timePassed + "", Var.dim_chosen.width / 2,
					Var.dim_chosen.height / 13);
		}

	}
}
