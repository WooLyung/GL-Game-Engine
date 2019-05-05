package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import android.util.Log;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.AnimationRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.SpriteRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.TransformComponent;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.glesgameengine.GraphicSystem.GLRenderer;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.Main.Game;

public class TestObject2 extends GameObject
{
    @Override
    public void start()
    {
        AnimationRenderer animationRenderer = new AnimationRenderer();
        animationRenderer.bindingImage(new int[]{ GLRenderer.findImage("circle"), GLRenderer.findImage("circle2") });

        attachComponent(new GUITransform());
        attachComponent(animationRenderer);
        renderer.image[0] = GLRenderer.findImage("circle");
        transform.position.x = 1;
        transform.position.y = 1;

        getRenderer().setZ_index(-1);
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
