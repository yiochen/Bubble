package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

import chen.yiou.bubble.Assets;

/**
 * Created by Yiou on 12/21/2014.
 */
public class RenderComponent extends Component implements Pool.Poolable{
    public Texture texture= Assets.circle;
    public RenderComponent(){}
    public RenderComponent(Texture texture){
        this.texture=texture;
    }

    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {
        texture=Assets.circle;
    }
}
