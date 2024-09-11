package idk.somepackagename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private float speed = 0.5f;
    public customRenderer rdr;

    public Player(customRenderer renderer) {
        rdr = renderer; // ok so like rdr is a refrence not a copy
    }

    public void step() {
        Vector3 forward = new Vector3(rdr.cam.direction).nor(); // Get normalized forward direction
        Vector3 right = new Vector3(rdr.cam.direction).crs(rdr.cam.up).nor(); // Right vector
        Vector3 up = new Vector3(rdr.cam.up).nor(); // Up vector

        // Move forward/backward
        if (Gdx.input.isKeyPressed(Keys.W)) {
            rdr.cam.position.add(forward.scl(speed));
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            rdr.cam.position.add(forward.scl(-speed));
        }

        // Strafe left/right
        if (Gdx.input.isKeyPressed(Keys.A)) {
            rdr.cam.position.add(right.scl(-speed));
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            rdr.cam.position.add(right.scl(speed));
        }

        // Move up/down
        if (Gdx.input.isKeyPressed(Keys.E)) {
            rdr.cam.position.add(up.scl(speed));
        }
        if (Gdx.input.isKeyPressed(Keys.Q)) {
            rdr.cam.position.add(up.scl(-speed));
        }
        rdr.cam.update();

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }
}
