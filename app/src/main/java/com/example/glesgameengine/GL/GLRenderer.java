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

    // 이미지에 대한 정보를 저장
    private void addImage(int image, String name)
    {
        ImageData imgData = new ImageData();
        imgData.setName(name);
        imgData.setImgCode(image);
        imageDatas.add(imgData);
    }

    // 이미지 정보 목록에서 이름에 해당하는 이미지의 인덱스값을 반환
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
        // 변수 초기화
        imageDatas = new ArrayList<ImageData>();
        this.context = context;

        // 이미지 정보 목록에 각 이미지들을 미리 추가함
        addImage(R.drawable.image, "img1");
        addImage(R.drawable.test1, "img2");
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        // 렌더 버퍼를 지움
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // 렌더 타겟에 모든 렌더링이 될 오브젝트의 렌더러를 받아옴
        renderTargets.clear();
        Game.engine.render();
        // 렌더러들의 z-index에 따라 정렬
        Collections.sort(renderTargets, new Comparator<RendererComponent>() {
            @Override
            public int compare(RendererComponent r1, RendererComponent r2) {
                return r1.getZ_index() - r2.getZ_index();
            }
        });
        // 정렬된 순서에 따라서 실제로 렌더링함
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
        // 렌더러의 여러 값들을 초기화
        gl.glClearColor(1f, 1f, 1f, 0.5f);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glGenTextures(100, imageCode, 0);

        Bitmap bitmap;

        for (int i = 0; i < imageDatas.size(); i++)
        {
            // 이미지 정보 목록에 실제 이미지를 비트맵으로 저장
            gl.glBindTexture(GL10.GL_TEXTURE_2D, imageCode[i]);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            bitmap = BitmapFactory.decodeResource(context.getResources(), imageDatas.get(i).getImgCode());
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
            bitmap.recycle();

            // 이미지 정보의 버텍스 버퍼를 화면 크기에 맞도록 조절
            float[] vertices = imageDatas.get(i).getVertices();
            for (int j = 0; j < vertices.length; j++)
            {
                if (j % 2 == 0)
                    vertices[j] /= GLView.defaultWidth;
                else
                    vertices[j] /= GLView.defaultHeight;
            }
            imageDatas.get(i).setVertices(vertices);
        }
    }
}