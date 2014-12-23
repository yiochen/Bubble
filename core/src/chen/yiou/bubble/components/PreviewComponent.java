package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Yiou on 12/22/2014.
 */
public class PreviewComponent extends Component {
    public final int pointer;

    public PreviewComponent(int pointer){
        this.pointer=pointer;
    }
}
