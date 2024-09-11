package idk.somepackagename;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.Material;

public class Ground {
    private Model model;
    private ModelInstance instance;

    public Ground(float width, float depth, customRenderer renderer) {
        Material material = new Material(
                ColorAttribute.createDiffuse(Color.GRAY),
                ColorAttribute.createFog(Color.BLACK));

        ModelBuilder MB = new ModelBuilder();
        model = MB.createBox(width, 1f, depth, material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);

        renderer.add(instance);
    }

    public ModelInstance getInstance() {
        return instance;
    }

    public void dispose() {
        model.dispose();
    }
}
