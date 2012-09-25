package dogfight_remake.map;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import dogfight_remake.main.GlbVar;

public class BlockMap {
	public static int mapWidth;
	public static int mapHeight;
	private int square[] = { 1, 1, 17, 1, 17, 16, 1, 16 }; // square shaped tile
	public static ArrayList<Object> entities;

	public BlockMap(String ref) throws SlickException {
		entities = new ArrayList<Object>();
		mapWidth = GlbVar.tmap.getWidth() * GlbVar.tmap.getTileWidth();
		mapHeight = GlbVar.tmap.getHeight() * GlbVar.tmap.getTileHeight();

		for (int x = 0; x < GlbVar.tmap.getWidth(); x++) {
			for (int y = 0; y < GlbVar.tmap.getHeight(); y++) {
				int tileID = GlbVar.tmap.getTileId(x, y, 0);
				if (tileID == 1 || tileID == 2 || tileID == 3) {
					entities.add(new Block(x * 16, y * 15, square, "square"));
				}
			}
		}
	}
}
