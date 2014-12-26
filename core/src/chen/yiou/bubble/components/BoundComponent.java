package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Yiou on 12/21/2014.
 */
public class BoundComponent extends Component implements Pool.Poolable{
    public Rectangle getBound(PositionComponent pos, DimensionComponent dim){
        return new Rectangle(pos.x,pos.y,dim.width,dim.height);
    }

    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {

    }
}
