package com.example.glesgameengine.GameObject;

import android.util.Log;

import com.example.glesgameengine.Component.RendererComponent.SpriteRenderer;
import com.example.glesgameengine.Component.TransformComponent;
import com.example.glesgameengine.GL.GLRenderer;

public class TestObject2 extends GameObject
{
    @Override
    public void start()
    {
        attachComponent(new TransformComponent());
        attachComponent(new SpriteRenderer());
        renderer.image[0] = GLRenderer.findImage("img1");
        renderer.setZ_index(2);
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
