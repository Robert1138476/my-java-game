package idk.somepackagename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

public class Player {
    public float acceleration = .6f; // how fast the player gets faster?
    public float maxWalkSpeed = 1; // how fast before the player stops speeding up DOES NOT CAP SPEED
    Vector3 velocity = new Vector3(0f, 0f, 0f); // the players speed in all directions
    float height = 10;
    float gravity = 0.5f;
    float airFriction = .8f; // slows down player when no input is pressed for basic stopping ig
    Vector3 position = new Vector3(0, 5, 0);

    public customRenderer rdr; // for camera updates
    uiRenderer UI;

    public Player(customRenderer renderer, uiRenderer ui) {
        rdr = renderer; // ok so like rdr is a reference not a copy
        UI = ui;
    }

    public void step(float deltaTime) {
        Vector3 forward = new Vector3(rdr.cam.direction).nor();
        Vector3 right = new Vector3(rdr.cam.direction).crs(rdr.cam.up).nor();

        forward.y = 0;
        forward.nor();
        right.y = 0;
        right.nor();

        // Move forward/backward
        if (Gdx.input.isKeyPressed(Keys.W)) {
            velocity.add(forward.scl(acceleration * deltaTime));
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            velocity.sub(forward.scl(acceleration * deltaTime));
        }

        // Strafe left/right
        if (Gdx.input.isKeyPressed(Keys.A)) {
            velocity.sub(right.scl(acceleration * deltaTime / 2));
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            velocity.add(right.scl(acceleration * deltaTime / 2));
        }

        // Apply air friction or deceleration when no key is pressed
        if (!Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.S) &&
                !Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D)) {
            velocity.scl(airFriction * deltaTime * 60); // Slow down gradually
        }

        // Clamp velocity to maxWalkSpeed
        velocity.clamp(0, maxWalkSpeed);

        // Apply movement to position
        position.add(velocity.scl(deltaTime * 60));

        rdr.cam.position.set(position);
        rdr.cam.update();

        UI.setText(position.toString());

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

}
