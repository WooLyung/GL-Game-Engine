package com.example.glesgameengine.Main;

import com.example.glesgameengine.GameSystem.Scene.*;
import com.example.glesgameengine.GameIO.Input;

import javax.microedition.khronos.opengles.GL10;

public class Engine {

    public Scene nowScene;

    public Engine() {
        changeScene(new TestScene());
        start();
    }

    public void start() {

    }

    public void update() {
        nowScene.update();
        Input.update();
    }

    public void render(GL10 gl) {
        nowScene.render(gl);
    }

    public void finish() {

    }

    public void changeScene(Scene newScene) {
        if (nowScene != null)
            nowScene.finish();
        nowScene = newScene;
        newScene.start();
    }
}
