package chen.yiou.bubble.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import chen.yiou.bubble.BubbleGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width=500;
        config.height=800;
		new LwjglApplication(new BubbleGame(), config);
	}
}
