package com.example.glesgameengine.GameSystem.GameObject.GameObjects;

import android.graphics.Color;
import android.widget.EditText;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.EditTextRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.glesgameengine.GraphicSystem.GL.GLRenderer;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.GameIO.Input;
import com.example.glesgameengine.Main.Game;

public class TestObject2 extends GameObject {

    @Override
    public void start() {
        AnimationRenderer animationRenderer = new AnimationRenderer();
        animationRenderer.bindingImage(new int[]{ GLRenderer.findImage("circle"), GLRenderer.findImage("circle2") });

        TextRenderer textRenderer = new TextRenderer();
        textRenderer.getTextView().setBackgroundColor(Color.GRAY);

        attachComponent(new GUITransform());
        attachComponent(animationRenderer);
        attachComponent(textRenderer);
        renderer.image[0] = GLRenderer.findImage("circle");
        transform.position.x = 2;
        transform.position.y = 4;

        getRenderer().setZ_index(-1);
    }

    @Override
    public void update() {
        super.update();

        if (getParent() == null) {
            for (int i = 3; i >= -1; i--) {
                if (i == -1) {
                    if (Input.getTouchState() == Input.TOUCH_STATE.STAY
                            || Input.getTouchState() == Input.TOUCH_STATE.DOWN) {
                        transform.position = Input.getTouchUIPos();
                    }
                }
                else {
                    if (Input.getTouchState(i) == Input.TOUCH_STATE.STAY
                            || Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                        transform.position = Input.getTouchUIPos(i);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
