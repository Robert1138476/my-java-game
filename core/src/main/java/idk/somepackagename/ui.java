package idk.somepackagename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class ui {
    private Stage stage;
    private Table table;
    private Skin skin;

    public void create() {
        stage = new Stage();

        table = new Table();
        table.setFillParent(false);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("ui\\uiskin.json"));

        table.debug();

        // Add widgets to the table here.
        Label label = new Label("test text", skin);

        table.setSize(stage.getWidth(), stage.getHeight());
        table.add(label).expand().align(Align.top);
        // table.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, Align.center);
        // // Center the table on the stage

    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
