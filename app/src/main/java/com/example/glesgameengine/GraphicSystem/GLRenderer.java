package com.example.glesgameengine.GraphicSystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.Log;

import com.example.glesgameengine.GameSystem.Component.Components.RendererComponent.RendererComponent;
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

        for (ImageData imgData : imageDatas)
        {
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
        addImage(R.drawable.circle2, "circle");
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        // 렌더 버퍼를 지움
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // 카메라의 정보를 행렬 스택에 담음
        gl.glLoadIdentity();
        gl.glScalef(Game.engine.nowScene.camera.getZoomX() / (float)GLView.defaultWidth, Game.engine.nowScene.camera.getZoomY() / (float)GLView.defaultHeight, 1);
        gl.glRotatef(Game.engine.nowScene.camera.angle, 0, 0, 1);
        gl.glTranslatef(-Game.engine.nowScene.camera.position.x, -Game.engine.nowScene.camera.position.y, 0);
        gl.glPushMatrix();
        gl.glLoadIdentity();

        // 렌더링
        Game.engine.render(gl);

        // 렌더링 마무리
        gl.glPopMatrix();
        gl.glLoadIdentity();
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

            // 이미지 크기를 저장
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), imageDatas.get(i).getImgCode(), options);
            imageDatas.get(i).setWidth(options.outWidth);
            imageDatas.get(i).setHeight(options.outHeight);

            bitmap.recycle();

            // 이미지 정보의 버텍스 버퍼를 화면 크기와 이미지 크기에 맞도록 조절
            float[] vertices = imageDatas.get(i).getVertices();
            for (int j = 0; j < vertices.length; j++)
            {
                if (j % 2 == 0) {
                    //vertices[j] /= GLView.defaultWidth;
                    vertices[j] *= imageDatas.get(i).getHeight() / 100f;
                }
                else {
                    //vertices[j] /= GLView.defaultHeight;
                    vertices[j] *= imageDatas.get(i).getWidth() / 100f;
                }
            }
            imageDatas.get(i).setVertices(vertices);
        }
    }
}