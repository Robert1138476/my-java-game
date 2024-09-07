package idk.somepackagename;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms. Listens to user input.
 */
public class Main implements ApplicationListener {
    public static PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public AssetManager assets;
    public Array<ModelInstance> instances = new Array<ModelInstance>();
    public Environment environment;
    public boolean loading;
    public TestMovement movement;
    public ui UI;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(new Input());
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        UI = new ui();
        UI.create();

        modelBatch = new ModelBatch();

        movement = new TestMovement();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(7f, 7f, 7f);
        cam.lookAt(0, 0, 0);
        cam.near = 0.1f;
        cam.far = 300f;
        cam.update();

        assets = new AssetManager();
        assets.load("ship.g3db", Model.class);
        loading = true;
    }

    private void doneLoading() {
        Model ship = assets.get("ship.g3db", Model.class);
        ModelInstance shipInstance = new ModelInstance(ship);
        ModelInstance shipInstance2 = new ModelInstance(ship);
        instances.add(shipInstance);
        shipInstance2.transform.setTranslation(1, 0, 1);
        instances.add(shipInstance2);
        loading = false;
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        instances.clear();
        assets.dispose();
        UI.dispose();
    }

    @Override
    public void resize(int width, int height) {
        UI.resize(width, height);
    }

    @Override
    public void render() {
        if (loading && assets.update())
            doneLoading();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        TestMovement.step();

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();

        UI.render();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    // Note: you can override methods from InputAdapter API to handle user's input.
}