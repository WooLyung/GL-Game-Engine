package com.example.glesgameengine.GameObject;

import android.util.Log;

import com.example.glesgameengine.Component.Component;

import java.util.ArrayList;

abstract public class GameObject
{
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

    public void finish()
    {
        for (Component component : components)
        {
            component.finish();
        }
    }
}