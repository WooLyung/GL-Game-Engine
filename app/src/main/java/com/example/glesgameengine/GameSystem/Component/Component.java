package com.example.glesgameengine.GameSystem.Component;

abstract public class Component
{
    String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Component()
    {
        start();
    }

    abstract public void start();
    abstract public void update();
    abstract public void finish();
}