package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Yiou on 12/21/2014.
 */
public class DimensionComponent extends Component{
    public float height=1;
    public float width=1;
    public DimensionComponent(){}
    public DimensionComponent(float width, float height){
        this.width=width;
        this.height=height;
    }
}
