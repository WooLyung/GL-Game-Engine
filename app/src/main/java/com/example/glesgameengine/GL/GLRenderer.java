package com.example.glesgameengine.GL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.Log;

import com.example.glesgameengine.GameObject.GameObject;
import com.example.glesgameengine.ImageData;
import com.example.glesgameengine.Main.Game;
import com.example.glesgameengine.R;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements GLSurfaceView.Renderer
{
    public static ArrayList<ImageData> textureName;
    public static int[] textureCode = new int[100];
    private Context context;

    private void addTexture(GL10 gl, int texture)
    {
        Bitmap bitmap;
        gl.glGenTextures(100, textureCode, 0);

        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureCode[0]);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        bitmap = BitmapFactory.decodeResource(context.getResources(), texture);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();

        textureName.add(new ImageData());

        Log.i("addTexture", "Done");
    }

    public GLRenderer(Context context)
    {
        textureName = new ArrayList<ImageData>();
        this.context = context;
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        Game.engine.render(gl);
        gl.glLoadIdentity();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig arg1)
    {
        gl.glClearColor(1f, 1f, 1f, 0.5f);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);

        addTexture(gl, R.drawable.image);
    }
}