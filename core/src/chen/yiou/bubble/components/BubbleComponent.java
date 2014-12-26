package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Yiou on 12/21/2014.
 */
public class BubbleComponent extends Component implements Pool.Poolable{
    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {

    }
    //Marker class, only to indicate the the entity is a bubble.
}
