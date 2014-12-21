package chen.yiou.bubble;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class BubbleGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private OrthographicCamera camera;

    @Override
	public void create () {
        camera=new OrthographicCamera();
        FillViewport viewport=new FillViewport(Constants.WIDTH,Constants.HEIGHT,camera);

		batch = new SpriteBatch();


	}

	@Override
	public void render () {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}
