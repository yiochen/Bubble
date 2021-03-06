package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Yiou on 12/21/2014.
 */
public class DimensionComponent extends Component implements Pool.Poolable{
    public float height=0;
    public float width=0;
    public DimensionComponent(){}
    public DimensionComponent(float width, float height){
        this.width=width;
        this.height=height;
    }

    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {
        height=0;
        width=0;
    }
}
