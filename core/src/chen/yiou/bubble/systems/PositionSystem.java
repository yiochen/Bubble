package chen.yiou.bubble.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import chen.yiou.bubble.components.PositionComponent;
import chen.yiou.bubble.components.VelocityComponent;

/**
 * Created by Yiou on 12/22/2014.
 */
public class PositionSystem extends IteratingSystem {
    private final ComponentMapper<PositionComponent> posMap;
    private final ComponentMapper<VelocityComponent> velMap;

    /**
     * Instantiates a system that will iterate over the entities described by the Family.
    */
    public PositionSystem() {
        super(Family.getFor(PositionComponent.class, VelocityComponent.class));
        posMap= ComponentMapper.getFor(PositionComponent.class);
        velMap=ComponentMapper.getFor(VelocityComponent.class);
    }

    /**
     * This method is called on every entity on every update call of the EntitySystem. Override this to implement
     * your system's specific processing.
     *
     * @param entity    The current Entity being processed
     * @param deltaTime The delta time between the last and current frame
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos=posMap.get(entity);
        VelocityComponent vel=velMap.get(entity);
        Vector2 newPos=new Vector2(pos.x,pos.y);
        newPos.add(vel.v);
        pos.x=newPos.x;
        pos.y=newPos.y;
    }
}
