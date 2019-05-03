package com.example.glesgameengine.Scene;

import android.util.Log;

import com.example.glesgameengine.Camera;
import com.example.glesgameengine.GameObject.TestObject;

import javax.microedition.khronos.opengles.GL10;

public class TestScene extends Scene
{
    @Override
    public void start()
    {
        objs.add(new TestObject());
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
    public void render(GL10 gl)
    {
        super.render(gl);
    }
}
