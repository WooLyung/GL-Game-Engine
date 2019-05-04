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
    private GameObject parent = null;
    private ArrayList<GameObject> childs = new ArrayList<GameObject>();

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

    public RendererComponent getRenderer() {
        return renderer;
    }

    public TransformComponent getTransform() {
        return transform;
    }

    public void appendChild(GameObject child)
    {
        childs.add(child);
        child.parent = this;
    }

    public void setParent(GameObject parent)
    {
        parent.appendChild(this);
    }

    public void removeParent()
    {
        parent.childs.remove(this);
        parent = null;
    }

    public void removeChild(GameObject child)
    {
        child.removeParent();
    }

    public void attachComponent(Component newComponent)
    {
        components.add(newComponent);
        newComponent.object = this;

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

    public ArrayList<GameObject> getChilds() {
        return childs;
    }

    public GameObject getParent() {
        return parent;
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

        for (GameObject child : childs)
        {
            child.update();
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

        for (GameObject child : childs)
        {
            child.finish();
        }
    }
}