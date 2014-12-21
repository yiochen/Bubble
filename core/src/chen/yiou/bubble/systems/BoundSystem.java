package chen.yiou.bubble.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Rectangle;

import chen.yiou.bubble.Constants;
import chen.yiou.bubble.components.BoundComponent;
import chen.yiou.bubble.components.DimensionComponent;
import chen.yiou.bubble.components.PositionComponent;

/**
 * Created by Yiou on 12/21/2014.
 */
public class BoundSystem extends IteratingSystem {
    private final ComponentMapper<PositionComponent> PosMap;
    private final ComponentMapper<DimensionComponent> DimMap;
    private final ComponentMapper<BoundComponent> BoundMap;

    /**
     * Instantiates a system that will iterate over the entities described by the Family, with a
     * specific priority.
     *
     * @param family   The family of entities iterated over in this System
     * @param priority The priority to execute this system with (lower means higher priority)
     */
    public BoundSystem(int priority) {
        super(Family.getFor(PositionComponent.class, DimensionComponent.class, BoundComponent.class), priority);
        PosMap= ComponentMapper.getFor(PositionComponent.class);
        DimMap= ComponentMapper.getFor(DimensionComponent.class);
        BoundMap=ComponentMapper.getFor(BoundComponent.class);

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
        //wrap the entity around if if reach the upper bound.
        if (!Constants.SCREE_BOUND.contains(BoundMap.get(entity).getBound(PosMap.get(entity),DimMap.get(entity)))){
            PosMap.get(entity).y=0;
        }
    }
}
