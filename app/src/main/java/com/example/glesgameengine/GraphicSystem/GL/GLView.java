package com.example.glesgameengine.GraphicSystem.GL;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GLView extends GLSurfaceView
{
    public static double defaultWidth;
    public static double defaultHeight;
    public static double nowWidth;
    public static double nowHeight;

    public GLView(Context context, Renderer renderer)
    {
        super(context);
        setRenderer(renderer);
        setRenderMode(RENDERMODE_CONTINUOUSLY);
        setFocusableInTouchMode(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        Log.i("event","onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        Log.i("event","onTouchEvent");
        return super.onTouchEvent(event);
    }
}