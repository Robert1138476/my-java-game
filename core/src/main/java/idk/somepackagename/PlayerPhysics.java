package idk.somepackagename;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.CollisionObjectWrapper;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionAlgorithm;
import com.badlogic.gdx.physics.bullet.collision.btCollisionAlgorithmConstructionInfo;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btDispatcherInfo;
import com.badlogic.gdx.physics.bullet.collision.btManifoldResult;
import com.badlogic.gdx.physics.bullet.collision.btSphereBoxCollisionAlgorithm;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;

public class PlayerPhysics {
    static btCollisionObject phyPlayer = new btCollisionObject();
    static btCollisionObject phyGround = new btCollisionObject();

    static btCollisionConfiguration collisionConfig;
    static btDispatcher dispatcher;

    public static void create() {
        btSphereShape playerShape = new btSphereShape(1);
        btBoxShape groundShape = new btBoxShape(new Vector3(10, 1, 10));

        phyGround.setCollisionShape(groundShape);
        phyGround.setWorldTransform(Ground.transform);

        phyPlayer.setCollisionShape(playerShape);
        phyGround.setWorldTransform(Player.transform);

        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);

        playerShape.dispose();
        groundShape.dispose();
    }

    public static boolean checkCollision() {
        CollisionObjectWrapper co0 = new CollisionObjectWrapper(phyPlayer);
        CollisionObjectWrapper co1 = new CollisionObjectWrapper(phyGround);

        btCollisionAlgorithmConstructionInfo ci = new btCollisionAlgorithmConstructionInfo();
        ci.setDispatcher1(dispatcher);
        btCollisionAlgorithm algorithm = new btSphereBoxCollisionAlgorithm(null, ci, co0.wrapper, co1.wrapper, false);

        btDispatcherInfo info = new btDispatcherInfo();
        btManifoldResult result = new btManifoldResult(co0.wrapper, co1.wrapper);

        algorithm.processCollision(co0.wrapper, co1.wrapper, info, result);

        boolean r = result.getPersistentManifold().getNumContacts() > 0;

        result.dispose();
        info.dispose();
        algorithm.dispose();
        ci.dispose();
        co1.dispose();
        co0.dispose();

        return r;
    }

    static public void dispose() {
        phyGround.dispose();
        phyPlayer.dispose();

        dispatcher.dispose();
        collisionConfig.dispose();
    }
}
