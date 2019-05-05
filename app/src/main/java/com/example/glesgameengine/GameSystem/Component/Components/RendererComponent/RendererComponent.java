package com.example.glesgameengine.GameSystem.Component.Components.RendererComponent;

import android.util.Log;

import com.example.glesgameengine.GameSystem.Component.Component;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.GraphicSystem.GLView;
import com.example.glesgameengine.Main.Game;

import javax.microedition.khronos.opengles.GL10;

abstract public class RendererComponent extends Component
{
    public int[] image;
    private int z_index = 0;

    public void render(GL10 gl)
    {
        // transform에 따른 변환 행렬을 행렬 스택에 쌓음
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslatef(object.getTransform().position.x, object.getTransform().position.y, getZ_index() / 100.f);
        gl.glScalef(object.getTransform().scale.x, object.getTransform().scale.y, 1);
        gl.glRotatef(object.getTransform().angle, 0, 0, 1);
        gl.glPushMatrix();

        for (GameObject child : object.getChilds())
        {
            if (child.getRenderer() != null)
            {
                child.getRenderer().render(gl);
            }
        }

        gl.glPopMatrix();
        gl.glPushMatrix();
    };

    public int getZ_index() {
        return z_index;
    }

    public void setZ_index(int z_index) {
        this.z_index = z_index;
    }
}
