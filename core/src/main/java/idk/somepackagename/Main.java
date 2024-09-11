package idk.somepackagename;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms. Listens to user input.
 */
public class Main implements ApplicationListener {
    public static uiRenderer UI;
    Player plr;
    customRenderer renderer;
    PlayerInput plrinput;
    Ground ground;

    @Override
    public void create() {
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        Gdx.graphics.setFullscreenMode(displayMode);

        UI = new uiRenderer();
        UI.create();

        renderer = new customRenderer();

        plr = new Player(renderer);
        plrinput = new PlayerInput(renderer);
        Gdx.input.setInputProcessor(plrinput);

        ground = new Ground(100f, 100f, renderer);
    }

    @Override
    public void dispose() {
        UI.dispose();
        ground.dispose();
    }

    @Override
    public void resize(int width, int height) {
        UI.resize(width, height);
    }

    @Override
    public void render() {
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