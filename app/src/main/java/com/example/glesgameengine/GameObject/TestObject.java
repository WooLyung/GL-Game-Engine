package com.example.glesgameengine.GameObject;

import android.util.Log;

import com.example.glesgameengine.Component.RendererComponent.SpriteRenderer;
import com.example.glesgameengine.Component.TransformComponent;

public class TestObject extends GameObject
{
    @Override
    public void start()
    {
        attachComponent(new TransformComponent());
        attachComponent(new SpriteRenderer());
    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void finish()
    {
        super.finish();
    }
}
