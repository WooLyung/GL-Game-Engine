package com.example.glesgameengine.GameObject;

import android.util.Log;

import com.example.glesgameengine.Component.TransformComponent;

public class TestObject extends GameObject
{
    @Override
    public void start()
    {
        attachComponent(new TransformComponent());
    }

    @Override
    public void update()
    {
        super.update();

        Log.i("TestObject", "Running");
    }

    @Override
    public void finish()
    {
        super.finish();
    }
}
