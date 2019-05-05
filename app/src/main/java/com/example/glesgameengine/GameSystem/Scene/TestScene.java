package com.example.glesgameengine.GameSystem.Scene;

import com.example.glesgameengine.GameSystem.GameObject.GameObjects.TestObject;
import com.example.glesgameengine.GameSystem.GameObject.GameObjects.TestObject2;
import com.example.glesgameengine.Main.Game;

import javax.microedition.khronos.opengles.GL10;

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

        camera.position.x = 1.5f;
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
    public void render(GL10 gl)
    {
        super.render(gl);
    }
}