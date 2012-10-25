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
	float xpos_p2 = gc.getScreenWidth() - 150;
	float ypos_p2 = 50;
	if (r.player1 != null && r.player2 != null) {
	    // Player 1
	    Var.img_player_ui.draw(0, 0);
	    if (r.player1.getHitpoints() < 20) {
		g.setColor(Color.red);
	    } else if (r.player1.getHitpoints() < 60) {
		g.setColor(Color.yellow);
	    } else {
		g.setColor(Color.green);
	    }
	    for (int i = 0; i < r.player1.getHitpoints(); i++) {
		g.drawRect(3 + i * 0.73f, 9, 0.73f, 5);
	    }
	    if (r.player1.getHeat_prim() > 80) {
		g.setColor(Color.red);
	    } else if (r.player1.getHeat_prim() > 50) {
		g.setColor(Color.yellow);
	    } else {
		g.setColor(Color.green);
	    }
	    for (int i = 0; i < r.player1.getHeat_prim(); i++) {
		g.fillRect(83 + i * 1.2f, 67, 1.2f, 6);
	    }
	    Var.img_missile1.setRotation(0);
	    Var.img_bomb1.setRotation(0);
	    
	    r.player1.getWeapon(3).getImage().draw(86, 10);
	    g.drawString("" + r.player1.getAmmo(3), 50 + 86, 6);
	    r.player1.getWeapon(4).getImage().draw(157, 10);
	    g.drawString("" + r.player1.getAmmo(4), 50 + 157, 6);
	    if (r.player1.getRespawn_timer() < Var.RESPAWNTIME_PLAYER) {
		g.drawString("" + r.player1.getRespawn_timer() / 100, 100, 0);
	    }
	    g.drawString(gc.getFPS() + "", 400, 500);

	    // Player 2
	    g.setColor(Color.green);
	    for (int i = 0; i < r.player2.getHitpoints(); i++) {
		g.drawRect(xpos_p2 + i, ypos_p2, 1, 10);
	    }
	    g.setColor(Color.black);
	    for (int i = 0; i < r.player2.getHeat_prim(); i++) {
		g.fillRect(xpos_p2 + i, ypos_p2 + 25, 1, 10);
	    }
	    Var.img_missile1.setRotation(0);
	    Var.img_missile1.draw(xpos_p2, ypos_p2 + 50);

	    g.drawString("" + r.player2.getAmmo(3), xpos_p2 + 50, ypos_p2 + 50);
	    r.player2.getWeapon(3).getImage().draw(xpos_p2, ypos_p2 + 75);
	    g.drawString("" + r.player2.getAmmo(4), xpos_p2 + 50, ypos_p2 + 75);
	    if (r.player2.getRespawn_timer() < Var.RESPAWNTIME_PLAYER) {
		g.drawString("" + r.player2.getRespawn_timer() / 100,
			Var.dim_chosen.width - Var.dim_chosen.width / 3,
			Var.dim_chosen.height / 2);
	    }
	    // FPS and score
	    g.drawString(Var.score_p1 + " : " + Var.score_p2,
		    Var.dim_chosen.width / 2, Var.dim_chosen.height / 10);
	    g.drawString(Var.timePassed + "", Var.dim_chosen.width / 2,
		    Var.dim_chosen.height / 13);
	}

    }
}
