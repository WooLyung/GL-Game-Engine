package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.glesgameengine.GraphicSystem.GL.GLRenderer;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;

public class TestObject2 extends GameObject {

    @Override
    public void start() {
        AnimationRenderer animationRenderer = new AnimationRenderer();
        animationRenderer.bindingImage(new int[]{ GLRenderer.findImage("circle"), GLRenderer.findImage("circle2") });

        attachComponent(new GUITransform());
        attachComponent(animationRenderer);
        renderer.image[0] = GLRenderer.findImage("circle");
        transform.position.x = 1;
        transform.position.y = 1;

        getRenderer().setZ_index(-1);
        setTag("TEST");
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
