package com.example.glesgameengine.GameSystem.Scene;

import com.example.glesgameengine.GameSystem.Camera;
import com.example.glesgameengine.GameSystem.GameObject.GameObject;

import java.util.ArrayList;

abstract public class Scene
{
    public ArrayList<GameObject> objs = new ArrayList<GameObject>();
    public Camera camera = new Camera();

    abstract public void start();

    public void update()
    {
        for (GameObject gameObject : objs)
        {
            gameObject.update();
        }
    }

    public void render()
    {
        for (GameObject gameObject : objs)
        {
            gameObject.render();
        }
    }

    public void finish()
    {
        for (GameObject gameObject : objs)
        {
            gameObject.finish();
        }
    }
}