package com.example.glesgameengine.GraphicSystem.GL;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.glesgameengine.Input;

public class GLView extends GLSurfaceView {
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
}