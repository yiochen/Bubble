package chen.yiou.bubble.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import chen.yiou.bubble.components.BoundComponent;
import chen.yiou.bubble.components.ColorComponent;
import chen.yiou.bubble.components.DimensionComponent;
import chen.yiou.bubble.components.PositionComponent;
import chen.yiou.bubble.components.RenderComponent;

/**
 * Created by Yiou on 12/22/2014.
 */
public class RenderSystem extends IteratingSystem {
    private static final String TAG = "RenderSystem";
    private final ComponentMapper<RenderComponent> renderMap;
    private final ComponentMapper<DimensionComponent> dimMap;
    private final ComponentMapper<PositionComponent> posMap;
    private final ComponentMapper<BoundComponent> boundMap;
    private final Array<Entity> renderQueue;
    private final Comparator<Entity> comparator;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final ComponentMapper<ColorComponent> colorMap;

    public RenderSystem(SpriteBatch batch,OrthographicCamera camera) {
        super(Family.getFor(RenderComponent.class, DimensionComponent.class, PositionComponent.class, BoundComponent.class,ColorComponent.class));
        renderMap= ComponentMapper.getFor(RenderComponent.class);
        dimMap=ComponentMapper.getFor(DimensionComponent.class);
        posMap=ComponentMapper.getFor(PositionComponent.class);
        colorMap=ComponentMapper.getFor(ColorComponent.class);
        boundMap=ComponentMapper.getFor(BoundComponent.class);

        renderQueue=new Array<Entity>();

        comparator=new Comparator<Entity>(){
            @Override
            public int compare(Entity o1, Entity o2) {
                PositionComponent pos1=posMap.get(o1);
                PositionComponent pos2=posMap.get(o2);
                float diff=pos1.x+pos1.y-pos2.x-pos2.y;
                if (diff>0) return 1;
                if (diff==0) return 0;
                if (diff<0) return -1;
                return 0;
            }
        };

        this.batch=batch;
        this.camera=camera;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        renderQueue.sort(comparator);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //draw all the entities
        for (Entity entity:renderQueue){
            renderEntity(entity);
        }
        batch.end();
        renderQueue.clear();
    }

    private void renderEntity(Entity entity) {
        Color color=colorMap.get(entity).color;
        Color oldColor=batch.getColor();
        batch.setColor(color);
        batch.draw(renderMap.get(entity).texture,
                posMap.get(entity).x,
                posMap.get(entity).y,
                dimMap.get(entity).width,
                dimMap.get(entity).height
        );

        batch.setColor(oldColor);
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
        //add entity to queues
        renderQueue.add(entity);
    }
}
