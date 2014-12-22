package chen.yiou.bubble;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import chen.yiou.bubble.components.BoundComponent;
import chen.yiou.bubble.components.BubbleComponent;
import chen.yiou.bubble.components.ColorComponent;
import chen.yiou.bubble.components.DimensionComponent;
import chen.yiou.bubble.components.PositionComponent;
import chen.yiou.bubble.components.RenderComponent;
import chen.yiou.bubble.components.VelocityComponent;
import chen.yiou.bubble.systems.BoundSystem;
import chen.yiou.bubble.systems.RenderSystem;

public class BubbleGame extends Game implements InputProcessor {

    private OrthographicCamera camera;
    private long startTime;
    private static final int timeVsSize=500;
    private Engine engine;
    private Viewport viewport;



    @Override
    public void create() {
        Assets.load();

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
        BoundSystem boundSystem=new BoundSystem();
        RenderSystem renderSystem=new RenderSystem(new SpriteBatch(),camera);
        engine.addSystem(boundSystem);
        engine.addSystem(renderSystem);
        engine.addEntityListener(boundSystem);
    }

    @Override
	public void render () {

        engine.update(Gdx.graphics.getDeltaTime());

//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
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
        startTime= TimeUtils.millis();
        return true;
    }
    public Vector3 screenToWorld(int screenX, int screenY){
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
        long interval=TimeUtils.millis()-startTime;
        Entity bubble=new Entity();
        DimensionComponent dim=new DimensionComponent((float)interval/timeVsSize*2,(float)interval/timeVsSize*2);
        Vector3 touch=screenToWorld(screenX,screenY);
        PositionComponent pos=new PositionComponent(touch.x-(float)interval/timeVsSize,touch.y-(float)interval/timeVsSize);
        BoundComponent bound=new BoundComponent();
        BubbleComponent bub=new BubbleComponent();
        ColorComponent color=new ColorComponent(Color.CYAN);
        RenderComponent render=new RenderComponent(Assets.circle);
        VelocityComponent vel=new VelocityComponent();
        Entity entity=new Entity();
        entity.add(bound).add(bub).add(color).add(dim).add(pos).add(render).add(vel);
        engine.addEntity(entity);
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
