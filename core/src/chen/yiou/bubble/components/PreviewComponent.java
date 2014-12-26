package chen.yiou.bubble.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Yiou on 12/22/2014.
 */
public class PreviewComponent extends Component implements Pool.Poolable{
    public  int pointer;
    public PreviewComponent(){}
    public PreviewComponent(int pointer){
        this.pointer=pointer;
    }

    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {

    }
}
