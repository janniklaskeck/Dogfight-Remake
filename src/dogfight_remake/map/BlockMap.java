package dogfight_remake.map;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import dogfight_remake.main.Var;

public class BlockMap {
	public static int mapWidth;
	public static int mapHeight;
	private int square[] = { 0, 0, Var.tmap.getTileWidth(), 0,
			Var.tmap.getTileWidth(), Var.tmap.getTileHeight(), 0,
			Var.tmap.getTileHeight() }; // square shaped tile
	private int triangle_left_up[] = { 0, 0, 0, Var.tmap.getTileHeight(),
			Var.tmap.getTileWidth(), Var.tmap.getTileHeight() };
	public static ArrayList<Object> entities;

	public BlockMap(String ref) throws SlickException {
		entities = new ArrayList<Object>();
		mapWidth = Var.tmap.getWidth() * Var.tmap.getTileWidth();
		mapHeight = Var.tmap.getHeight() * Var.tmap.getTileHeight();

		for (int x = 0; x < Var.tmap.getWidth(); x++) {
			for (int y = 0; y < Var.tmap.getHeight(); y++) {
				int tileID = Var.tmap.getTileId(x, y, 0);
				if (tileID == 1) {
					entities.add(new Block(x * Var.tmap.getTileWidth(), y
							* Var.tmap.getTileHeight(), square, "square",
							tileID));
				} else if (tileID == 2) {
					entities.add(new Triangle(x * Var.tmap.getTileWidth(), y
							* Var.tmap.getTileHeight(), triangle_left_up,
							"triangle_left_up", tileID));
				}
			}
		}
	}
}
