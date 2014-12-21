package chen.yiou.bubble;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Yiou on 12/20/2014.
 */
public class Assets{
    public static Texture circle;

    public static void load(){
       circle=new Texture("circle.png");
    }

    /**
     * Releases all resources.
     */
    public static void dispose() {
        circle.dispose();
    }

}
