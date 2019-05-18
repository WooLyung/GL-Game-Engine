package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.glesgameengine.GraphicSystem.GL.GLRenderer;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.Main.Game;

public class TestObject extends GameObject {

    @Override
    public void start() {
        attachComponent(new Transform());
        attachComponent(new SpriteRenderer());
        renderer.image[0] = GLRenderer.findImage("img1");
        renderer.setZ_index(1);

        transform.position.x = 1.5f;
        transform.scale.x = 0.9f;
        transform.scale.y = 0.9f;

        ((SpriteRenderer)renderer).setDir(RendererComponent.DIRECTION.RIGHT);
    }

    @Override
    public void update() {
        super.update();
        if (((SpriteRenderer)renderer).getFill() < 0) {
            ((SpriteRenderer)renderer).setFill(1);
        }
        else {
            ((SpriteRenderer)renderer).setFill(((SpriteRenderer)renderer).getFill() - Game.deltaTime);
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
