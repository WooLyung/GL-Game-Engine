package com.example.glesgameengine.GL;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements GLSurfaceView.Renderer
{
    public GLRenderer(Context context)
    {

    }

    @Override public void onDrawFrame(GL10 gl)
    {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
    }

    @Override public void onSurfaceChanged(GL10 gl, int width, int height)
    {

    }

    @Override public void onSurfaceCreated(GL10 gl, EGLConfig arg1)
    {
        gl.glClearColor(0, 1, 0, 0.5f);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
    }
}