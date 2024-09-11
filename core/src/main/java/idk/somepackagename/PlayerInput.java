package idk.somepackagename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector3;

public class PlayerInput extends InputAdapter {
    private int mouseX = 0;
    private int mouseY = 0;
    private float rotSpeed = 0.2f;
    private boolean isRightButtonPressed = false;

    public customRenderer rdr;

    public PlayerInput(customRenderer renderer) {
        rdr = renderer; // ok so like rdr is a refrence not a copy
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Buttons.RIGHT) {
            isRightButtonPressed = true;
            mouseX = screenX;
            mouseY = screenY;
            Gdx.input.setCursorCatched(true);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Buttons.RIGHT) {
            isRightButtonPressed = false;
            Gdx.input.setCursorCatched(false);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isRightButtonPressed) {
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
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        Main.UI.setText(com.badlogic.gdx.Input.Keys.toString(keycode));
        return super.keyDown(keycode);
    }
}
