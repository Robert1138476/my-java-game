package idk.somepackagename;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. Listens to user input. */
public class Main extends InputAdapter implements ApplicationListener {
    
    public Texture sandTexture;
    public SpriteBatch spriteBatch;
    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        sandTexture = new Texture(Gdx.files.internal("Untitled.png"));
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(final int width, final int height) {
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(sandTexture, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        sandTexture.dispose();
        spriteBatch.dispose();
    }

    // Note: you can override methods from InputAdapter API to handle user's input.
}