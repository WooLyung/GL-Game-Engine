package com.example.glesgameengine.Component.RendererComponent;

import com.example.glesgameengine.Component.Component;

import javax.microedition.khronos.opengles.GL10;

abstract public class RendererComponent extends Component
{
    public int[] image;
    private int z_index = 0;
    abstract public void render(GL10 gl);

    public int getZ_index() {
        return z_index;
    }

    public void setZ_index(int z_index) {
        this.z_index = z_index;
    }
}
