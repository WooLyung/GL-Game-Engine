package com.example.glesgameengine.Component.RendererComponent;

import com.example.glesgameengine.Component.Component;

import javax.microedition.khronos.opengles.GL10;

abstract public class RendererComponent extends Component
{
    public int[] image;
    abstract public void render(GL10 gl);
}
