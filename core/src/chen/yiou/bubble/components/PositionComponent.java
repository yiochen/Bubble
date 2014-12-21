package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Yiou on 12/21/2014.
 */
public class PositionComponent extends Component{
    public float x=0;
    public float y=0;

    public PositionComponent(){
    }
    public PositionComponent(float x, float y){
        this.x=x;
        this.y=y;
    }
}
