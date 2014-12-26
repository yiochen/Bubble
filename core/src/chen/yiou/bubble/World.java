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
    private PositionSystem posSystem;
    private VelocitySystem velSystem;
    private BoundSystem boundSystem;
    private RenderSystem renderSystem;
    private PreviewSystem previewSystem;

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
        posSystem=new PositionSystem();
        velSystem=new VelocitySystem();
        boundSystem=new BoundSystem();
        renderSystem=new RenderSystem(new SpriteBatch(),camera);
        previewSystem=new PreviewSystem(camera);

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
        DimensionComponent dim=engine.createComponent(DimensionComponent.class);
        Vector3 touch=BubbleGame.screenToWorld(screenX, screenY, this.camera);
        PositionComponent pos=engine.createComponent(PositionComponent.class);
        pos.x=touch.x;
        pos.y=touch.y;
        BoundComponent bound=engine.createComponent(BoundComponent.class);
        BubbleComponent bub=engine.createComponent(BubbleComponent.class);
        ColorComponent color= engine.createComponent(ColorComponent.class);
        color.color=(new Color((int)(Math.random()*0xFFFFFF)<<8|0xFF));
        RenderComponent render=engine.createComponent(RenderComponent.class);
        PreviewComponent prev=engine.createComponent(PreviewComponent.class);
        prev.pointer=pointer;
        Entity entity=engine.createEntity();
        entity.add(bound).add(bub).add(color).add(dim).add(pos).add(render).add(prev);
        engine.addEntity(entity);
        previews[pointer]=entity;
        return true;
    }
    public void pause(){
        //empty preview
        for (int i=0; i<previews.length;i++){
            if (previews[i]!=null && previews[i] instanceof Entity){
                engine.removeEntity(previews[i]);
            }
        }
        //pause systems
       setSystemsProcessing(false);
    }

    public void resume(){
        setSystemsProcessing(true);
    }
    public void dispose(){
        engine.removeAllEntities();
        engine.clearPools();
        renderSystem.dispose();
    }
    private void setSystemsProcessing(boolean status){
        posSystem.setProcessing(status);
        velSystem.setProcessing(status);
        boundSystem.setProcessing(status);
        renderSystem.setProcessing(status);
        previewSystem.setProcessing(status);
    }

}
