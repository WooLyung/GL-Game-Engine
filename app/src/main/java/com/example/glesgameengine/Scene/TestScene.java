package com.example.glesgameengine.Scene;

import android.util.Log;

import com.example.glesgameengine.Camera;
import com.example.glesgameengine.GameObject.TestObject;

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

        Log.i("TestScene", "Running");
    }

    @Override
    public void finish()
    {

    }
}
