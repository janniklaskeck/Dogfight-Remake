package dogfight_remake.map;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BlockMap {
	public static TiledMap tmap;
	public static int mapWidth;
	public static int mapHeight;
	private int square[] = { 1, 1, 17, 1, 17, 16, 1, 16 }; // square shaped tile
	public static ArrayList<Object> entities;

	public BlockMap(String ref) throws SlickException {
		entities = new ArrayList<Object>();
		tmap = new TiledMap(ref, "src/dogfight_remake/map/map_test");
		mapWidth = tmap.getWidth() * tmap.getTileWidth();
		mapHeight = tmap.getHeight() * tmap.getTileHeight();

		for (int x = 0; x < tmap.getWidth(); x++) {
			for (int y = 0; y < tmap.getHeight(); y++) {
				int tileID = tmap.getTileId(x, y, 0);
				if (tileID == 1 || tileID == 2 || tileID == 3) {
					entities.add(new Block(x * 16, y * 15, square, "square"));
				}
			}
		}
	}
}
