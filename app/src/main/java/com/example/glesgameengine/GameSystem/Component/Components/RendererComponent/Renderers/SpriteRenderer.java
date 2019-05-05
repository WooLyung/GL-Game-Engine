package com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.Renderers;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.glesgameengine.GraphicSystem.GL.GLRenderer;
import com.example.glesgameengine.GraphicSystem.RenderTarget;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class SpriteRenderer extends RendererComponent {

    @Override
    public void render(GL10 gl) {
        super.render(gl);

        // 렌더 타겟을 추가
        RenderTarget renderTarget = new RenderTarget();
        renderTarget.imageCode = image[0];
        renderTarget.matrix = FloatBuffer.allocate(4 * 9);
        ((GL11)gl).glGetFloatv(GL11.GL_MODELVIEW_MATRIX, renderTarget.matrix);
        renderTarget.z_index = getZ_index();
        GLRenderer.renderTargets.add(renderTarget);

        // 렌더 타겟 추가 마무리
        gl.glPopMatrix();
        gl.glLoadIdentity();
    }

    @Override
    public void start() {
        setName("spriteRenderer");
        image = new int[1];
    }

    @Override
    public void update() {
    }

    @Override
    public void finish() {
    }
}