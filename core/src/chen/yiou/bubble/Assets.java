package chen.yiou.bubble;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Yiou on 12/20/2014.
 */
public class Assets{
    public static Texture circle;
    public static Texture locust;

    public static void load(){

        circle=new Texture("circle.png");
        locust=new Texture("Locust.png");
    }

    /**
     * Releases all resources.
     */
    public static void dispose() {
        circle.dispose();
        locust.dispose();
    }

}
