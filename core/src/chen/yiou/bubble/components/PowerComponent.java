package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Yiou on 12/26/2014.
 */
public class PowerComponent extends Component implements Pool.Poolable {
    public float hp=0;
    public float dps=0;
    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {}
}
