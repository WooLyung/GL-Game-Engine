package com.example.glesgameengine.GL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.Log;

import com.example.glesgameengine.Component.RendererComponent.RendererComponent;
import com.example.glesgameengine.GameObject.GameObject;
import com.example.glesgameengine.ImageData;
import com.example.glesgameengine.Main.Game;
import com.example.glesgameengine.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements GLSurfaceView.Renderer
{
    public static ArrayList<ImageData> imageDatas;
    public static int[] imageCode = new int[100];
    public static ArrayList<RendererComponent> renderTargets = new ArrayList<RendererComponent>();
    private Context context;

    private void addImage(int image, String name)
    {
        ImageData imgData = new ImageData();
        imgData.setName(name);
        imgData.setImgCode(image);
        imageDatas.add(imgData);
    }

    public static int findImage(String name)
    {
        int index = 0;
        Log.i("findImage", "" + imageDatas.size() );

        for (ImageData imgData : imageDatas)
        {
            Log.i("findImage", imgData.getName());

            if (imgData.getName().equals(name))
                return index;
            index++;
        }
        return -1;
    }

    public GLRenderer(Context context)
    {
        imageDatas = new ArrayList<ImageData>();
        this.context = context;

        addImage(R.drawable.image, "img1");
        addImage(R.drawable.test1, "img2");
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        renderTargets.clear();
        Game.engine.render();
        Collections.sort(renderTargets, new Comparator<RendererComponent>() {
            @Override
            public int compare(RendererComponent r1, RendererComponent r2) {
                return r1.getZ_index() - r2.getZ_index();
            }
        });
        for(RendererComponent rendererComponent : renderTargets)
        {
            rendererComponent.render(gl);
        }
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
        gl.glGenTextures(100, imageCode, 0);

        Bitmap bitmap;

        int index = 0;
        for (ImageData imgData : imageDatas)
        {
            gl.glBindTexture(GL10.GL_TEXTURE_2D, imageCode[index]);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            bitmap = BitmapFactory.decodeResource(context.getResources(), imgData.getImgCode());
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
            bitmap.recycle();

            index++;
        }
    }
}