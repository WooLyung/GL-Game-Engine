package com.example.glesgameengine.GameSystem.Component.Components.TransformComponent;

import com.example.glesgameengine.GameSystem.Component.Component;
import com.example.glesgameengine.Types.Vector;

abstract public class TransformComponent extends Component
{
    public Vector position = new Vector();
    public Vector scale = new Vector(1, 1);
    public float angle = 0;
}
