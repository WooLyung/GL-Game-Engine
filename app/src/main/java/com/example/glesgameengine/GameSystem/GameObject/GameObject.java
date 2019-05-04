package com.example.glesgameengine.GameSystem.GameObject;

import com.example.glesgameengine.GameSystem.Component.Component;
import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.glesgameengine.GameSystem.Component.Components.TransformComponent.TransformComponent;
import com.example.glesgameengine.GraphicSystem.GLRenderer;

import java.util.ArrayList;

abstract public class GameObject
{
    protected RendererComponent renderer;
    protected TransformComponent transform;
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

        if (newComponent.getName() == "transform"
            || newComponent.getName() == "GUITransform")
        {
            transform = (TransformComponent) newComponent;
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

    public void render()
    {
        if (renderer != null)
            GLRenderer.renderTargets.add(renderer);
    }

    public void finish()
    {
        for (Component component : components)
        {
            component.finish();
        }
    }
}