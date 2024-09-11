package idk.somepackagename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class uiRenderer {
    private Stage stage;
    private Table table;
    private Skin skin;
    private Label label;

    public void create() {
        stage = new Stage();

        table = new Table();
        table.setFillParent(false);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("ui\\uiskin.json"));

        // table.debug(); // ENABLE WHEN DEBUG

        label = new Label("test text", skin);

        table.setSize(stage.getWidth(), stage.getHeight());
        table.add(label).pad(3f).expand().align(Align.top);

        label.setText("this is NEW text");

    }

    public void setText(String text) {
        label.setText(text);
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
