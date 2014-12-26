package chen.yiou.bubble;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import chen.yiou.bubble.components.AccelerationComponent;
import chen.yiou.bubble.components.BoundComponent;
import chen.yiou.bubble.components.BubbleComponent;
import chen.yiou.bubble.components.ColorComponent;
import chen.yiou.bubble.components.DimensionComponent;
import chen.yiou.bubble.components.PositionComponent;
import chen.yiou.bubble.components.PreviewComponent;
import chen.yiou.bubble.components.RenderComponent;
import chen.yiou.bubble.components.VelocityComponent;
import chen.yiou.bubble.systems.BoundSystem;
import chen.yiou.bubble.systems.PositionSystem;
import chen.yiou.bubble.systems.PreviewSystem;
import chen.yiou.bubble.systems.RenderSystem;
import chen.yiou.bubble.systems.VelocitySystem;

/**
 * Created by Yiou on 12/25/2014.
 */
public class World extends InputAdapter{
    private static World instance = new World();
    private OrthographicCamera camera;
    private Entity[] previews;
    private PooledEngine engine;
    private Viewport viewport;
    public static World getInstance() {
        return instance;
    }

    private World() {
        create();
    }
    public void create(){
        previews =new Entity[20];
        engine=new PooledEngine();
        camera=new OrthographicCamera();
        viewport=new StretchViewport(Constants.WIDTH,Constants.HEIGHT,camera);
        viewport.apply(true);
        createSystem();

    }
    public void resize(int width, int height) {
        viewport.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }
    private void createSystem() {
        PositionSystem posSystem=new PositionSystem();
        VelocitySystem velSystem=new VelocitySystem();
        BoundSystem boundSystem=new BoundSystem();
        RenderSystem renderSystem=new RenderSystem(new SpriteBatch(),camera);
        PreviewSystem previewSystem=new PreviewSystem(camera);
        engine.addSystem(velSystem);
        engine.addSystem(posSystem);
        engine.addSystem(boundSystem);
        engine.addSystem(previewSystem);
        engine.addSystem(renderSystem);
        engine.addEntityListener(boundSystem);
    }
    public void render () {
        engine.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Entity bubble=previews[pointer];
        VelocityComponent vel=new VelocityComponent();
        AccelerationComponent accel=new AccelerationComponent();
        bubble.add(vel).add(accel).remove(PreviewComponent.class);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        DimensionComponent dim=new DimensionComponent(0,0);
        Vector3 touch=BubbleGame.screenToWorld(screenX, screenY, this.camera);
        PositionComponent pos=new PositionComponent(touch.x,touch.y);
        BoundComponent bound=new BoundComponent();
        BubbleComponent bub=new BubbleComponent();
        ColorComponent color=new ColorComponent(new Color((int)(Math.random()*0xFFFFFF)<<8|0xFF));
        RenderComponent render=new RenderComponent(Assets.circle);
        PreviewComponent prev=new PreviewComponent(pointer);
        Entity entity=engine.createEntity();
        entity.add(bound).add(bub).add(color).add(dim).add(pos).add(render).add(prev);
        engine.addEntity(entity);
        previews[pointer]=entity;
        return true;
    }
}
