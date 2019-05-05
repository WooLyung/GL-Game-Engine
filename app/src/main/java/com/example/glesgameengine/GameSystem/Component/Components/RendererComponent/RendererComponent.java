package com.example.glesgameengine.GameSystem.Component.Components.RendererComponent;

import com.example.glesgameengine.GameSystem.Component.Component;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;
import com.example.glesgameengine.GraphicSystem.GL.GLView;
import com.example.glesgameengine.Main.Game;

import javax.microedition.khronos.opengles.GL10;

abstract public class RendererComponent extends Component {

    public int[] image;
    private int z_index = 0;

    public void render(GL10 gl) {
        if (object.getTransform().getName() == "transform") {
            // transform 컴포넌트가 일반 transform이라면 상대적인 위치 적용
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(object.getTransform().position.x, object.getTransform().position.y, 0);
            gl.glScalef(object.getTransform().scale.x, object.getTransform().scale.y, 1);
            gl.glRotatef(object.getTransform().angle, 0, 0, 1);
        }
        else {
            // transform 컴포넌트가 GUI용 transform일 경우 절대적인 위치 사용
            gl.glLoadIdentity();
            gl.glTranslatef(object.getTransform().position.x / (float)GLView.defaultWidth, object.getTransform().position.y / (float)GLView.defaultHeight, getZ_index() / 100.f);
            gl.glScalef(object.getTransform().scale.x / (float)GLView.defaultWidth, object.getTransform().scale.y / (float)GLView.defaultHeight, 1);
            gl.glRotatef(object.getTransform().angle, 0, 0, 1);
        }
        gl.glPushMatrix();

        for (GameObject child : object.getChilds()) {
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
