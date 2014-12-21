package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

import chen.yiou.bubble.Assets;

/**
 * Created by Yiou on 12/21/2014.
 */
public class RenderComponent extends Component{
    public Texture texture= Assets.circle;
    public RenderComponent(){}
    public RenderComponent(Texture texture){
        this.texture=texture;
    }
}
