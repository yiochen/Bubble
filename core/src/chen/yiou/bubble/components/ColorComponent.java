package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Yiou on 12/21/2014.
 */
public class ColorComponent extends Component{
    public Color color=Color.BLUE;
    public ColorComponent(){}
    public ColorComponent(Color color){
        this.color=color;
    }
}
