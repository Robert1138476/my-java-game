package idk.somepackagename;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;



/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. Listens to user input. */
public class Main extends InputAdapter implements ApplicationListener {
    
    public SpriteBatch spriteBatch;
    public Sprite textureSprite;

    private TextureAtlas atlas;
    @Override
    public void create() {
        atlas = new TextureAtlas(Gdx.files.internal("atlas/textures.atlas"));
        spriteBatch = new SpriteBatch();
        textureSprite = atlas.createSprite("sand");
        textureSprite.setBounds(0,0,200,200);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(final int width, final int height) {
        spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        textureSprite.draw(spriteBatch);
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
        spriteBatch.dispose();
        atlas.dispose();
    }

    // Note: you can override methods from InputAdapter API to handle user's input.
}