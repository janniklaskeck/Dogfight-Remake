package dogfight_remake.map;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Map1 {
	public BlockMap map;

	public Map1() {
		try {
			map = new BlockMap("/src/dogfight_remake/map/map_test/test.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer container, org.newdawn.slick.Graphics g) {
		BlockMap.tmap.render(0, 0);

	}
}
