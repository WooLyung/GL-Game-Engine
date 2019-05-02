package com.example.glesgameengine.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.glesgameengine.GL.GLRenderer;
import com.example.glesgameengine.GL.GLView;

public class Game extends AppCompatActivity
{
    GLView view;
    GLRenderer renderer;
    Thread thread;
    public static Engine engine;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); // 조명 항상켜기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        renderer = new GLRenderer(this);
        view = new GLView(this, renderer);
        thread = new Thread(new MainLoop());
        engine = new Engine();

        setContentView(view);
        thread.start();
    }
}