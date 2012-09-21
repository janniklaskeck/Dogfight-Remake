package dogfight_remake.sound;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {
	public Music music1;
	public Music music2;
	public Sound prim_gun_heavy;
	public Sound prim_gun_middle;
	public Sound prim_gun_light;
	public Sound sec_missile1;
	public Sound sec_missile2;
	public Sound sec_bomb_drop;
	public Sound explode;
	public Sound hit;

	public Sounds() {
		try {
			music1 = new Music(
					"/src/dogfight_remake/sound/sounds/fight08.ogg");
			music2 = new Music(
					"/src/dogfight_remake/sound/sounds/fight08.ogg");
			prim_gun_heavy = new Sound(
					"/src/dogfight_remake/sound/sounds/prim_gun_heavy.wav");
			prim_gun_middle = new Sound(
					"/src/dogfight_remake/sound/sounds/prim_gun_middle.wav");
			prim_gun_light = new Sound(
					"/src/dogfight_remake/sound/sounds/prim_gun_light.wav");
			sec_missile1 = new Sound(
					"/src/dogfight_remake/sound/sounds/sec_missile1.wav");
			sec_missile2 = new Sound(
					"/src/dogfight_remake/sound/sounds/sec_missile2.wav");
			sec_bomb_drop = new Sound(
					"/src/dogfight_remake/sound/sounds/sec_bomb_drop.wav");
			explode = new Sound("/src/dogfight_remake/sound/sounds/explode.wav");
			hit = new Sound("/src/dogfight_remake/sound/sounds/hit.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
