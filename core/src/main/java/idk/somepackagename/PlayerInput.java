package idk.somepackagename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

public class PlayerInput extends InputAdapter {
    private int mouseX = 0;
    private int mouseY = 0;
    private float rotSpeed = 0.2f;

    public customRenderer rdr;

    public PlayerInput(customRenderer renderer) {
        rdr = renderer; // ok so like rdr is a reference not a copy
        Gdx.input.setCursorCatched(true);
        mouseX = 0;
        mouseY = 0;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        int magX = Math.abs(mouseX - screenX);
        int magY = Math.abs(mouseY - screenY);

        if (mouseX > screenX) {
            rdr.cam.rotate(Vector3.Y, 1 * magX * rotSpeed);
        }

        if (mouseX < screenX) {
            rdr.cam.rotate(Vector3.Y, -1 * magX * rotSpeed);
        }

        if (mouseY < screenY) {
            if (rdr.cam.direction.y > -0.965) {
                rdr.cam.rotate(rdr.cam.direction.cpy().crs(Vector3.Y), -1 * magY * rotSpeed);
            }
        }

        if (mouseY > screenY) {
            if (rdr.cam.direction.y < 0.965) {
                rdr.cam.rotate(rdr.cam.direction.cpy().crs(Vector3.Y), 1 * magY * rotSpeed);
            }
        }

        rdr.cam.update();

        mouseX = screenX;
        mouseY = screenY;

        return super.mouseMoved(screenX, screenY);
    }
}
