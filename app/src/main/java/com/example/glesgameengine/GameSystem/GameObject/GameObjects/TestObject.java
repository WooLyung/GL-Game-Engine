package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.glesgameengine.GraphicSystem.GL.GLRenderer;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;

public class TestObject extends GameObject {

    @Override
    public void start() {
        attachComponent(new Transform());
        attachComponent(new SpriteRenderer());
        renderer.image[0] = GLRenderer.findImage("circle");
        renderer.setZ_index(1);

        transform.position.x = 1.5f;
        transform.angle = 30;
        transform.scale.x = 0.9f;
        transform.scale.y = 0.9f;
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
