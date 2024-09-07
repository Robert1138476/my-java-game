package idk.somepackagename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

public class TestMovement {
    private static float speed = 0.5f;

    public static void step() {
        Vector3 forward = new Vector3(Main.cam.direction).nor(); // Get normalized forward direction
        Vector3 right = new Vector3(Main.cam.direction).crs(Main.cam.up).nor(); // Right vector
        Vector3 up = new Vector3(Main.cam.up).nor(); // Up vector

        // Move forward/backward
        if (Gdx.input.isKeyPressed(Keys.W)) {
            Main.cam.position.add(forward.scl(speed));
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            Main.cam.position.add(forward.scl(-speed));
        }

        // Strafe left/right
        if (Gdx.input.isKeyPressed(Keys.A)) {
            Main.cam.position.add(right.scl(-speed));
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            Main.cam.position.add(right.scl(speed));
        }

        // Move up/down
        if (Gdx.input.isKeyPressed(Keys.E)) {
            Main.cam.position.add(up.scl(speed));
        }
        if (Gdx.input.isKeyPressed(Keys.Q)) {
            Main.cam.position.add(up.scl(-speed));
        }
        Main.cam.update();
    }
}