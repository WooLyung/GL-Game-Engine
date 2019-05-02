package com.example.glesgameengine.Scene;

import android.util.Log;

import com.example.glesgameengine.Camera;
import com.example.glesgameengine.GameObject.GameObject;
import com.example.glesgameengine.Main.Game;

import java.util.ArrayList;

abstract public class Scene
{
    ArrayList<GameObject> objs = new ArrayList<GameObject>();
    Camera mainCamera = null;

    abstract public void start();

    public void update()
    {
        for (GameObject gameObject : objs)
        {
            gameObject.update();
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