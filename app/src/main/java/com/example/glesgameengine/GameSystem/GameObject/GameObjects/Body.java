package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import com.example.glesgameengine.GameIO.Input;
import com.example.glesgameengine.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.GraphicSystem.GL.GLRenderer;
import com.example.glesgameengine.Main.Game;
import com.example.glesgameengine.R;

public class Body extends GameObject {
    @Override
    public void start() {
        appendChild(new FrontLeg1());
        appendChild(new BackLeg1());

        setName("body");

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("stick"));

        Transform transform = new Transform();
        attachComponent(transform);

        AnimationComponent animationComponent = new AnimationComponent();
        attachComponent(animationComponent);
        animationComponent.addAnimation(Game.instance.getString(R.string.walk));
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
