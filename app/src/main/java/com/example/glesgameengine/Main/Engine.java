package com.example.glesgameengine.Main;

import com.example.glesgameengine.Scene.*;

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
