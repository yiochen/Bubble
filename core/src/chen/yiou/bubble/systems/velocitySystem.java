package chen.yiou.bubble.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import chen.yiou.bubble.components.AccelerationComponentU;
import chen.yiou.bubble.components.DimensionComponent;
import chen.yiou.bubble.components.VelocityComponentU;

/**
 * Created by Yiou on 12/22/2014.
 */
public class VelocitySystem extends IteratingSystem{
    public static final String TAG="VelocitySystem";
    private final ComponentMapper<VelocityComponentU> velMap;
    private final ComponentMapper<DimensionComponent> dimMap;
    private final ComponentMapper<AccelerationComponentU> accelMap;


    /**
     * Instantiates a system that will iterate over the entities described by the Family.
     *
     * @param family The family of entities iterated over in this System
     */
    public VelocitySystem() {
        super(Family.getFor(VelocityComponentU.class, DimensionComponent.class, AccelerationComponentU.class));
        velMap=ComponentMapper.getFor(VelocityComponentU.class);
        dimMap=ComponentMapper.getFor(DimensionComponent.class);
        accelMap=ComponentMapper.getFor(AccelerationComponentU.class);
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
        velMap.get(entity).v.add(new Vector2(accelMap.get(entity).accel).scl(deltaTime));
    }
}
