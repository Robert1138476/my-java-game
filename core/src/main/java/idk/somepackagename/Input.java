package idk.somepackagename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector3;

public class Input extends InputAdapter {
    private int mouseX = 0;
    private int mouseY = 0;
    private float rotSpeed = 0.2f;
    private boolean isRightButtonPressed = false;

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
                Main.cam.rotate(Vector3.Y, 1 * magX * rotSpeed);
            }

            if (mouseX < screenX) {
                Main.cam.rotate(Vector3.Y, -1 * magX * rotSpeed);
            }

            if (mouseY < screenY) {
                if (Main.cam.direction.y > -0.965) {
                    Main.cam.rotate(Main.cam.direction.cpy().crs(Vector3.Y), -1 * magY * rotSpeed);
                }
            }

            if (mouseY > screenY) {
                if (Main.cam.direction.y < 0.965) {
                    Main.cam.rotate(Main.cam.direction.cpy().crs(Vector3.Y), 1 * magY * rotSpeed);
                }
            }

            Main.cam.update();

            mouseX = screenX;
            mouseY = screenY;
        }

        return false;
    }
}
