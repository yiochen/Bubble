package chen.yiou.bubble.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import chen.yiou.bubble.BubbleGame;
import chen.yiou.bubble.components.DimensionComponent;
import chen.yiou.bubble.components.PositionComponent;
import chen.yiou.bubble.components.PreviewComponent;

/**
 * Created by Yiou on 12/22/2014.
 */
public class PreviewSystem extends IteratingSystem {
    private static final int timeVsSize=500;
    private static final String TAG = "PreviewSystem";
    private final ComponentMapper<DimensionComponent> dimMap;
    private final ComponentMapper<PositionComponent> posMap;
    private final ComponentMapper<PreviewComponent> prevMap;
    private final OrthographicCamera camera;

    /**
     * Instantiates a system that will iterate over the entities described by the Family.
     */
    public PreviewSystem(OrthographicCamera camera) {
        super(Family.getFor(PreviewComponent.class, PositionComponent.class, DimensionComponent.class));
        this.camera=camera;
        prevMap=ComponentMapper.getFor(PreviewComponent.class);
        dimMap= ComponentMapper.getFor(DimensionComponent.class);
        posMap = ComponentMapper.getFor(PositionComponent.class);
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
        PreviewComponent prev=prevMap.get(entity);
        DimensionComponent dim=dimMap.get(entity);
        PositionComponent pos=posMap.get(entity);
        float inc=deltaTime*4;
        dim.height+=inc;
        dim.width+=inc;
        //Gdx.app.log(TAG,"the height is "+dim.height);
        Vector3 touch= BubbleGame.screenToWorld(Gdx.input.getX(prev.pointer),Gdx.input.getY(prev.pointer),camera);
        pos.x= touch.x-dim.height/2;
        pos.y= touch.y-dim.width/2;
    }
}
