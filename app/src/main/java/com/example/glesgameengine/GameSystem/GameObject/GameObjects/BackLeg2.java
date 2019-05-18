package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.GraphicSystem.GL.GLRenderer;

public class BackLeg2 extends GameObject {
    @Override
    public void start() {
        setName("backLeg2");

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("stick"));

        Transform transform = new Transform();
        attachComponent(transform);
        transform.anchor.x = 0;
        transform.position.x = -4;
    }

    @Override
    public void update() {
        super.update();
    }
}
