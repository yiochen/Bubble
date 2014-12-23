package chen.yiou.bubble;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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

public class BubbleGame extends Game implements InputProcessor {

    private static final String TAG = "BubbleGame";
    private OrthographicCamera camera;
    private Entity[] previews;
    private Engine engine;
    private Viewport viewport;



    @Override
    public void create() {
        Assets.load();
        previews =new Entity[20];
        engine=new Engine();
        camera=new OrthographicCamera();
        viewport=new StretchViewport(Constants.WIDTH,Constants.HEIGHT,camera);
        viewport.apply(true);
        createSystem();
        Gdx.input.setInputProcessor(this);
    }

    @Override
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

    @Override
	public void render () {
        engine.update(Gdx.graphics.getDeltaTime());
	}

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link com.badlogic.gdx.Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link com.badlogic.gdx.Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link com.badlogic.gdx.Input.Buttons#LEFT} on iOS.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log(TAG,"finger "+pointer+" down!");
        DimensionComponent dim=new DimensionComponent(0,0);
        Vector3 touch=screenToWorld(screenX,screenY,this.camera);
        PositionComponent pos=new PositionComponent(touch.x,touch.y);
        BoundComponent bound=new BoundComponent();
        BubbleComponent bub=new BubbleComponent();
        ColorComponent color=new ColorComponent(new Color((int)(Math.random()*0xFFFFFF)<<8|0xFF));
        RenderComponent render=new RenderComponent(Assets.circle);
//        VelocityComponent vel=new VelocityComponent();
        PreviewComponent prev=new PreviewComponent(pointer);
        Entity entity=new Entity();
        entity.add(bound).add(bub).add(color).add(dim).add(pos).add(render).add(prev);
        engine.addEntity(entity);
        previews[pointer]=entity;
        return true;
    }
    public static Vector3 screenToWorld(int screenX, int screenY, OrthographicCamera camera){
        Vector3 pos = new Vector3(screenX, screenY, 0);
        camera.unproject(pos);
        return pos;
    }
    /**
     * Called when a finger was lifted or a mouse button was released. The button parameter will be {@link com.badlogic.gdx.Input.Buttons#LEFT} on iOS.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @param button  the button   @return whether the input was processed
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Entity bubble=previews[pointer];
        VelocityComponent vel=new VelocityComponent();
        bubble.add(vel).remove(PreviewComponent.class);
        return true;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.  @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
