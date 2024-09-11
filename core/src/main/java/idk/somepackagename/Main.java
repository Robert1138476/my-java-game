package idk.somepackagename;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms. Listens to user input.
 */
public class Main implements ApplicationListener {
    public AssetManager assets;
    public Environment environment;
    public boolean loading;
    public static uiRenderer UI;
    Player plr;
    customRenderer renderer;
    PlayerInput plrinput;

    @Override
    public void create() {
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        Gdx.graphics.setFullscreenMode(displayMode);

        UI = new uiRenderer();
        UI.create();

        assets = new AssetManager();
        assets.load("ship.g3db", Model.class);
        loading = true;

        renderer = new customRenderer();

        plr = new Player(renderer);
        plrinput = new PlayerInput(renderer);
        Gdx.input.setInputProcessor(plrinput);
    }

    private void doneLoading() {
        Model ship = assets.get("ship.g3db", Model.class);
        ModelInstance shipInstance = new ModelInstance(ship);
        ModelInstance shipInstance2 = new ModelInstance(ship);
        shipInstance2.transform.setTranslation(1, 0, 1);
        renderer.add(shipInstance);
        renderer.add(shipInstance2);
        loading = false;
    }

    @Override
    public void dispose() {
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

        plr.step();
        renderer.render();

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