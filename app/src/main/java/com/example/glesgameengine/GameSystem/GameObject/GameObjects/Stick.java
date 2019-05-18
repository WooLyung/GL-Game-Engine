package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.animation.Animation;

import com.example.glesgameengine.GameIO.Input;
import com.example.glesgameengine.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.GraphicSystem.GL.GLRenderer;
import com.example.glesgameengine.Main.Game;
import com.example.glesgameengine.R;

import java.io.InputStream;

public class Stick extends GameObject {
    @Override
    public void start() {
        setName("part1");

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("stick"));

        Transform transform = new Transform();
        attachComponent(transform);
        transform.position.x = 4;
        transform.anchor.x = 0;
        transform.anchor.y = 0;

        AnimationComponent animationComponent = new AnimationComponent();
        attachComponent(animationComponent);
        animationComponent.addAnimation(Game.instance.getString(R.string.testAnim));
    }

    @Override
    public void update() {
        super.update();

        if (Input.getTouchState() == Input.TOUCH_STATE.DOWN) {
            ((AnimationComponent)getComponent("animationComponent")).play(0);
        }
        else if (Input.getTouchState(1) == Input.TOUCH_STATE.DOWN) {
            ((AnimationComponent)getComponent("animationComponent")).stop();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
