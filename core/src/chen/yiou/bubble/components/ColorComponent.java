package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Yiou on 12/21/2014.
 */
public class ColorComponent extends Component implements Pool.Poolable{
    public Color color=Color.BLUE;
    public ColorComponent(){}
    public ColorComponent(Color color){
        this.color=color;
    }

    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {
        this.color=Color.BLUE;
    }
}
