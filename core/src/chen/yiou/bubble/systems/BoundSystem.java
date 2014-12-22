package chen.yiou.bubble.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

import chen.yiou.bubble.Constants;
import chen.yiou.bubble.components.BoundComponent;
import chen.yiou.bubble.components.DimensionComponent;
import chen.yiou.bubble.components.PositionComponent;

/**
 * Created by Yiou on 12/21/2014.
 */
public class BoundSystem extends IteratingSystem implements EntityListener{
    private static final String TAG="BoundSystem";
    private final ComponentMapper<PositionComponent> posMap;
    private final ComponentMapper<DimensionComponent> dimMap;
    private final ComponentMapper<BoundComponent> boundMap;
    private Engine engine;

    /**
     * Instantiates a system that will iterate over the entities described by the Family, with a
     * specific priority.
     */
    public BoundSystem() {
        super(Family.getFor(PositionComponent.class, DimensionComponent.class, BoundComponent.class));
        posMap = ComponentMapper.getFor(PositionComponent.class);
        dimMap = ComponentMapper.getFor(DimensionComponent.class);
        boundMap =ComponentMapper.getFor(BoundComponent.class);

    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
       this.engine = engine;
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
        //remove the entity when it touch the boundary.
        if (!Constants.SCREE_BOUND.contains(boundMap.get(entity).getBound(posMap.get(entity), dimMap.get(entity)))){
            engine.removeEntity(entity);
        }
    }

    /**
     * Called whenever an {@link com.badlogic.ashley.core.Entity} is added to {@link com.badlogic.ashley.core.Engine} or a specific {@link com.badlogic.ashley.core.Family}
     * <p/>
     * See {@link com.badlogic.ashley.core.Engine#addEntityListener(com.badlogic.ashley.core.EntityListener)} and {@link com.badlogic.ashley.core.Engine#addEntityListener(com.badlogic.ashley.core.Family, com.badlogic.ashley.core.EntityListener)}}
     *
     * @param entity
     */
    @Override
    public void entityAdded(Entity entity) {
        Gdx.app.log(TAG,"entity added, at "+ posMap.get(entity).x +" , "+posMap.get(entity).y);
    }

    /**
     * Called whenever an {@link com.badlogic.ashley.core.Entity} is removed from {@link com.badlogic.ashley.core.Engine} or a specific {@link com.badlogic.ashley.core.Family}
     * <p/>
     * See {@link com.badlogic.ashley.core.Engine#addEntityListener(com.badlogic.ashley.core.EntityListener)} and {@link com.badlogic.ashley.core.Engine#addEntityListener(com.badlogic.ashley.core.Family, com.badlogic.ashley.core.EntityListener)}}
     *
     * @param entity
     */
    @Override
    public void entityRemoved(Entity entity) {
        Gdx.app.log(TAG,"entity removed, bound system change");
    }
}
