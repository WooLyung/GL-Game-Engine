package com.example.glesgameengine.GameObject;

import android.opengl.GLSurfaceView;
import android.util.Log;

import com.example.glesgameengine.Component.Component;
import com.example.glesgameengine.Component.RendererComponent.RendererComponent;
import com.example.glesgameengine.Component.RendererComponent.SpriteRenderer;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

abstract public class GameObject
{
    protected RendererComponent renderer;
    private ArrayList<Component> components = new ArrayList<Component>();
    private String tag = null;
    private String name = null;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameObject()
    {
        start();
    }

    public void attachComponent(Component newComponent)
    {
        components.add(newComponent);

        if (newComponent.getName() == "spriteRenderer")
        {
            renderer = (RendererComponent) newComponent;
        }
    }

    public Component getComponent(String componentName)
    {
        for (Component component : components)
        {
            if (component.getName().equals(componentName))
            {
                return component;
            }
        }

        return null;
    }

    abstract public void start();

    public void update()
    {
        for (Component component : components)
        {
            component.update();
        }
    }

    public void render(GL10 gl)
    {
        renderer.render(gl);
    }

    public void finish()
    {
        for (Component component : components)
        {
            component.finish();
        }
    }
}