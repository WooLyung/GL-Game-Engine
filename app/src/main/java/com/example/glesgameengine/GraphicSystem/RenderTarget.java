package com.example.glesgameengine.GraphicSystem;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.glesgameengine.Types.Vector;

import java.nio.FloatBuffer;

public class RenderTarget {

    public FloatBuffer matrix;
    public float fill;
    public int z_index;
    public int imageCode;
    public Vector anchor;
    public RendererComponent.DIRECTION dir;
}
