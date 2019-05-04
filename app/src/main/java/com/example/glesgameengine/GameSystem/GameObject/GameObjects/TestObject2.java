package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import android.util.Log;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.SpriteRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.TransformComponent;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.glesgameengine.GraphicSystem.GLRenderer;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.Main.Game;

public class TestObject2 extends GameObject
{
    @Override
    public void start()
    {
        attachComponent(new Transform());
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
