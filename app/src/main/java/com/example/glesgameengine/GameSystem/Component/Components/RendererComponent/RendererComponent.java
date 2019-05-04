package com.example.glesgameengine.GameSystem.Component.Components.RendererComponent;

import com.example.glesgameengine.GameSystem.Component.Component;

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
