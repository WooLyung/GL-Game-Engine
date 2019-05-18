package com.example.glesgameengine.GameSystem.Scene;

import com.example.glesgameengine.GameSystem.GameObject.GameObjects.Body;
import com.example.glesgameengine.GameSystem.GameObject.GameObjects.Stick;
import com.example.glesgameengine.GameSystem.GameObject.GameObjects.TestObject;
import com.example.glesgameengine.GameSystem.GameObject.GameObjects.TestObject2;
import com.example.glesgameengine.Main.Game;

import javax.microedition.khronos.opengles.GL10;

public class TestScene extends Scene {

    @Override
    public void start() {
        objs.add(new Body());
        camera.position.x = 0;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void finish() {

    }

    @Override
    public void render(GL10 gl) {
        super.render(gl);
    }
}
