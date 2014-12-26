package chen.yiou.bubble.builder;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;

import chen.yiou.bubble.Assets;
import chen.yiou.bubble.Constants;
import chen.yiou.bubble.components.BoundComponent;
import chen.yiou.bubble.components.ColorComponent;
import chen.yiou.bubble.components.DimensionComponent;
import chen.yiou.bubble.components.EnemyComponent;
import chen.yiou.bubble.components.PositionComponent;
import chen.yiou.bubble.components.PowerComponent;
import chen.yiou.bubble.components.RenderComponent;
import chen.yiou.bubble.components.VelocityComponentU;

/**
 * Created by Yiou on 12/25/2014.
 */
public class EnemyBuilder {

    public static final short NORMAL=0;
    public float hp=-1;
    public float dps=0;
    public float speed=-0.05f;
    public short type=0;
    public Texture texture= Assets.circle;
    public EnemyBuilder hp(float hp){
        this.hp=hp;
        return this;
    }
    public EnemyBuilder dps(float dps){
        this.dps=dps;
        return this;
    }
    public EnemyBuilder speed(float speed){
        this.speed=speed;
        return this;
    }
    public EnemyBuilder type(short type){
        this.type=NORMAL;
        return this;
    }
    public EnemyBuilder(){}

    public Entity build(PooledEngine engine){
        Entity enemy=engine.createEntity();
        //make sure all the required properties are specified.
        if (this.hp==-1) throw new IllegalArgumentException("Didn't initialize required properties");
        PowerComponent power=engine.createComponent(PowerComponent.class);
        power.hp=this.hp;
        power.dps=this.dps;
        VelocityComponentU vel=new VelocityComponentU(0,speed);
        RenderComponent render=engine.createComponent(RenderComponent.class);
        switch (type){
            case 0:
                render.texture=Assets.locust;
        }
        BoundComponent bound=engine.createComponent(BoundComponent.class);
        DimensionComponent dim=engine.createComponent(DimensionComponent.class);
        dim.width=1f;
        dim.height=1.5f;
        ColorComponent color=engine.createComponent(ColorComponent.class);
        EnemyComponent en=engine.createComponent(EnemyComponent.class);
        PositionComponent pos=engine.createComponent(PositionComponent.class);
        pos.x=(float)Math.random()*(Constants.WIDTH-dim.width);
        pos.y=(float)Math.random()*1+Constants.HEIGHT-1;
        enemy.add(power).add(vel).add(render).add(bound).add(dim).add(pos).add(color).add(en);
        return enemy;
    }
}
