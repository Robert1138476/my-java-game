package idk.somepackagename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

public class TestMovement {
    private static float movementSpeed = 0.01f;
    private static Vector3 position = new Vector3(0f, 0f, 0f);

    public static Vector3 step() {
        float xMove = 0;
        float zMove = 0;
        if (Gdx.input.isKeyPressed(Keys.A)) {
            xMove++;
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            xMove--;
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            zMove++;
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            zMove--;
        }
        xMove *= movementSpeed;
        zMove *= movementSpeed;
        position = new Vector3(position.x + xMove, 0f, position.z + zMove);

        if (Gdx.input.isKeyPressed(Keys.F)) {
            Main.cam.lookAt(position);
            Main.cam.update();
        }
        return position;
    }
}