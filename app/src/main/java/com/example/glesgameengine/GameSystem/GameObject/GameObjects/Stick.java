package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.GraphicSystem.GL.GLRenderer;
import com.example.glesgameengine.Main.Game;

public class Stick extends GameObject {
    @Override
    public void start() {
        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.bindingImage(GLRenderer.findImage("stick"));

        Transform transform = new Transform();
        transform.position.x = 2;
        transform.anchor.x = 0;
        transform.anchor.y = 0;

        attachComponent(spriteRenderer);
        attachComponent(transform);
    }

    @Override
    public void update() {
        super.update();
        transform.angle += Game.deltaTime * 180;
    }

    @Override
    public void finish() {
        super.finish();
    }
}
