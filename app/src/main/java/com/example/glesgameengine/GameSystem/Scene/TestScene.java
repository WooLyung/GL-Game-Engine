package com.example.glesgameengine.GameSystem.Scene;

import com.example.glesgameengine.GameSystem.GameObject.GameObjects.TestObject;
import com.example.glesgameengine.GameSystem.GameObject.GameObjects.TestObject2;
import com.example.glesgameengine.Main.Game;

public class TestScene extends Scene
{
    @Override
    public void start()
    {
        TestObject t1 = new TestObject();
        TestObject t2;

        for (int i = 0; i < 15; i++)
        {
            objs.add(t1);
            t2 = t1;
            t1 = new TestObject();

            t2.appendChild(t1);
        }

        camera.position.x = 2;
    }

    @Override
    public void update()
    {
        super.update();

        camera.angle += Game.deltaTime * 30;
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
