package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Yiou on 12/21/2014.
 */
public class PositionComponent extends Component implements Pool.Poolable{
    public float x=0;
    public float y=0;

    public PositionComponent(){
    }
    public PositionComponent(float x, float y){
        this.x=x;
        this.y=y;
    }

    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {
    }
}
