package dogfight_remake.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import dogfight_remake.entities.Planes;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.main.GamePanel;
import dogfight_remake.map.Map;
import dogfight_remake.main.GlbVar;

public class Render {
	public Planes player1;
	public Planes player2;
	public Planes player1_respawn;
	public Planes player2_respawn;
	public BufferedImage img_player1;
	public BufferedImage img_player2;
	public static BufferedImage img_missile1;
	public static BufferedImage img_bullet1;
	public static BufferedImage img_bomb1;
	private BufferedImage img_bg;

	protected static Dimension dim = GlbVar.dim_chosen;

	private long firstFrame = 0;
	private int frames = 0;
	private long currentFrame;
	public int fps = 0;

	public static Map map = new Map(dim.getWidth(), dim.getHeight());

	public void init() {

		try {
			InputStream inputstream_player1 = this.getClass().getClassLoader()
					.getResourceAsStream("dogfight_remake/images/plane1.png");
			img_player1 = ImageIO.read(inputstream_player1);
			InputStream inputstream_player2 = this.getClass().getClassLoader()
					.getResourceAsStream("dogfight_remake/images/plane2.png");
			img_player2 = ImageIO.read(inputstream_player2);
			InputStream inputstream_bg = this.getClass().getClassLoader()
					.getResourceAsStream("dogfight_remake/images/l_bg.jpg");
			img_bg = ImageIO.read(inputstream_bg);
			InputStream inputstream_missile1 = this.getClass().getClassLoader()
					.getResourceAsStream("dogfight_remake/images/missile1.png");
			img_missile1 = ImageIO.read(inputstream_missile1);
			InputStream inputstream_bullet1 = this.getClass().getClassLoader()
					.getResourceAsStream("dogfight_remake/images/bullet1.png");
			img_bullet1 = ImageIO.read(inputstream_bullet1);
			InputStream inputstream_bomb1 = this.getClass().getClassLoader()
					.getResourceAsStream("dogfight_remake/images/bomb1.png");
			img_bomb1 = ImageIO.read(inputstream_bomb1);
		} catch (IOException e) {
			System.out.println("Error Images 01");
			e.printStackTrace();
		}
		// Weapon 1 p1
		if (GlbVar.wpn1_p1 == WeaponTypes.GUN) {
			GlbVar.wpn1_p1.setImage(img_bullet1);
		} else if (GlbVar.wpn1_p1 == WeaponTypes.MINIGUN) {
			GlbVar.wpn1_p1.setImage(img_bullet1);
		}
		// Weapon 2 p1
		if (GlbVar.wpn2_p1 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn2_p1.setImage(img_missile1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn2_p1.setImage(img_missile1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn2_p1.setImage(img_missile1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.BOMB) {
			GlbVar.wpn2_p1.setImage(img_bomb1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn2_p1.setImage(img_bomb1);
		}
		// Weapon 3 p1
		if (GlbVar.wpn3_p1 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn3_p1.setImage(img_missile1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn3_p1.setImage(img_missile1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn3_p1.setImage(img_missile1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.BOMB) {
			GlbVar.wpn3_p1.setImage(img_bomb1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn3_p1.setImage(img_bomb1);
		}
		// Weapon 1 p2
		if (GlbVar.wpn1_p2 == WeaponTypes.GUN) {
			GlbVar.wpn1_p2.setImage(img_bullet1);
		} else if (GlbVar.wpn1_p2 == WeaponTypes.MINIGUN) {
			GlbVar.wpn1_p2.setImage(img_bullet1);
		}
		// Weapon 2 p2
		if (GlbVar.wpn2_p2 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn2_p2.setImage(img_missile1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn2_p2.setImage(img_missile1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn2_p2.setImage(img_missile1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.BOMB) {
			GlbVar.wpn2_p2.setImage(img_bomb1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn2_p2.setImage(img_bomb1);
		}
		// Weapon 3 p2
		if (GlbVar.wpn3_p2 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn3_p2.setImage(img_missile1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn3_p2.setImage(img_missile1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn3_p2.setImage(img_missile1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.BOMB) {
			GlbVar.wpn3_p2.setImage(img_bomb1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn3_p2.setImage(img_bomb1);
		}

		player1 = new Planes(1, 100, dim.height / 2, 0, img_player1, 100,
				GlbVar.wpn1_p1, GlbVar.wpn2_p1, GlbVar.wpn3_p1);
		player2 = new Planes(2, dim.width - 150, dim.height / 2, 180,
				img_player2, 100, GlbVar.wpn1_p2, GlbVar.wpn2_p2,
				GlbVar.wpn3_p2);
		player1_respawn = player1;
		player2_respawn = player2;
		map = new Map(dim.getWidth(), dim.getHeight());
	}

	/**
	 * PaintComponent override
	 */

	public void paintComponent(Graphics g) {

		frames++;
		currentFrame = System.currentTimeMillis();
		if (currentFrame > firstFrame + 1000) {
			firstFrame = currentFrame;
			fps = frames;
			frames = 0;
		}
		// Background and Entities
		Graphics2D g2d = (Graphics2D) g.create();

		g2d.drawImage(img_bg, 0, 0, null);

		if (player1 != null) {
			player1.paintComponent(g2d);
		}
		if (player2 != null) {
			player2.paintComponent(g2d);
		}

		if (GamePanel.weapons != null) {
			for (int i = 0; i < GamePanel.weapons.size(); i++) {
				GamePanel.weapons.get(i).paintComponent(g2d);
			}
		}
		if (GamePanel.explosions != null) {
			for (int i = 0; i < GamePanel.explosions.size(); i++) {
				if (!GamePanel.explosions.get(i).isMaxRadius()) {
					GamePanel.explosions.get(i).paintComponent(g2d);
				} else {
					GamePanel.explosions.remove(i);
				}

			}
		}
		// Hitpoints
		if (player1 != null && player2 != null) {
			Font font = new Font("Arial", Font.PLAIN, dim.width / 85);

			g2d.setColor(Color.BLACK);
			g2d.setFont(font);
			// Player 1
			g2d.drawString("Player1: " + player1.getHitpoints(),
					dim.width / 20, dim.height / 20);
			g2d.drawString(
					player1.getWeapon(1).getName() + ": " + player1.getAmmo(1),
					dim.width / 20, dim.height / 20 + dim.height / 30);
			g2d.drawString(
					player1.getWeapon(2).getName() + ": " + player1.getAmmo(2),
					dim.width / 20, dim.height / 20 + dim.height / 15);
			g2d.drawString(
					player1.getWeapon(3).getName() + ": " + player1.getAmmo(3),
					dim.width / 20, (dim.height / 20) + (dim.height / 30)
							+ (dim.height / 15));
			if (GamePanel.respawntimer_p1 < GamePanel.RESPAWNTIME_PLAYER) {
				g2d.drawString("Respawn Player1: " + GamePanel.respawntimer_p1
						/ 100, dim.width / 5, dim.height / 20);
			}
			//g2d.drawString("" + dim.height, 400, 400);
			// Player 2
			g2d.drawString("Player2: " + player2.getHitpoints(), dim.width
					- dim.width / 7, dim.height / 20);
			g2d.drawString(
					player2.getWeapon(1).getName() + ": " + player2.getAmmo(1),
					dim.width - dim.width / 7, dim.height / 20 + dim.height
							/ 30);
			g2d.drawString(
					player2.getWeapon(2).getName() + ": " + player2.getAmmo(2),
					dim.width - dim.width / 7, dim.height / 20 + dim.height
							/ 15);
			g2d.drawString(
					player2.getWeapon(3).getName() + ": " + player2.getAmmo(3),
					dim.width - dim.width / 7, (dim.height / 20)
							+ (dim.height / 30) + (dim.height / 15));
			if (GamePanel.respawntimer_p2 < GamePanel.RESPAWNTIME_PLAYER) {
				g2d.drawString("Respawn Player2: " + GamePanel.respawntimer_p2
						/ 100, dim.width - dim.width / 3, dim.height / 20);
			}
			// FPS and score
			g2d.drawString("FPS: " + fps, dim.width / 2, dim.height / 20);
			g2d.drawString(GamePanel.score_p1 + " : " + GamePanel.score_p2,
					dim.width / 2, dim.height / 10);
		}
		if (map.getMap() != null) {
			map.paintComponent(g2d);
		}
		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();

	}
}
