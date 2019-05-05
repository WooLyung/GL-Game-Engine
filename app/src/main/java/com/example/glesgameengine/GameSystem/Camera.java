package com.example.glesgameengine.GameSystem;

import com.example.glesgameengine.GraphicSystem.GLView;
import com.example.glesgameengine.Types.Vector;

public class Camera
{
    public Vector position = new Vector();
    public float angle = 0;
    private Vector zoom = new Vector(1, 1);

    public void setZoomX(float x) {
        this.zoom.x = x;
        GLView.nowWidth = GLView.defaultWidth / x;
    }

    public void setZoomY(float y) {
        this.zoom.y = y;
        GLView.nowHeight = GLView.defaultHeight / y;
    }


    public float getZoomX() {
        return zoom.x;
    }

    public float getZoomY() {
        return zoom.y;
    }
}
