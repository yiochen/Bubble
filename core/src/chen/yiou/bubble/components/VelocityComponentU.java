package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Yiou on 12/21/2014.
 */
public class VelocityComponentU extends Component{
    public Vector2 v;
    public VelocityComponentU(){
        v=new Vector2(0,0);
    }
    public VelocityComponentU(float x, float y){
        v=new Vector2(x,y);
    }
}
