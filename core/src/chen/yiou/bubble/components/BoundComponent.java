package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Yiou on 12/21/2014.
 */
public class BoundComponent extends Component{
    public Rectangle getBound(PositionComponent pos, DimensionComponent dim){
        return new Rectangle(pos.x,pos.y,dim.width,dim.height);
    }

}
