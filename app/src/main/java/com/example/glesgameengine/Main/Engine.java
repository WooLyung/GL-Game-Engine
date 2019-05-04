package com.example.glesgameengine.Main;

import com.example.glesgameengine.GameSystem.Scene.*;

public class Engine
{
    public Scene nowScene;

    public Engine()
    {
        changeScene(new TestScene());
        start();
    }

    public void start()
    {

    }

    public void update()
    {
        nowScene.update();
    }

    public void render()
    {
        nowScene.render();
    }

    public void finish()
    {

    }

    public void changeScene(Scene newScene)
    {
        if (nowScene != null)
            nowScene.finish();
        nowScene = newScene;
        newScene.start();
    }
}
