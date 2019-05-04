package com.example.glesgameengine.GameSystem.Scene;

import com.example.glesgameengine.GameSystem.GameObject.GameObjects.TestObject;
import com.example.glesgameengine.GameSystem.GameObject.GameObjects.TestObject2;

public class TestScene extends Scene
{
    @Override
    public void start()
    {
        objs.add(new TestObject());
        objs.add(new TestObject2());

        camera.position.x = 2;
        camera.zoom.x = 0.5f;
        camera.zoom.y = 0.5f;
    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void finish()
    {

    }

    @Override
    public void render()
    {
        super.render();
    }
}
