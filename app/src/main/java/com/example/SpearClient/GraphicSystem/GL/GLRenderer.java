package com.example.SpearClient.GraphicSystem.GL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.GraphicSystem.ImageData;
import com.example.SpearClient.GraphicSystem.RenderTarget;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements GLSurfaceView.Renderer {

    public static ArrayList<RenderTarget> renderTargets;
    public static ArrayList<ImageData> imageDatas;
    public static int[] imageCode = new int[100];
    private Context context;

    // 이미지에 대한 정보를 저장
    private void addImage(int image, String name) {
        ImageData imgData = new ImageData();
        imgData.setName(name);
        imgData.setImgCode(image);
        imageDatas.add(imgData);
    }

    // 이미지 정보 목록에서 이름에 해당하는 이미지의 인덱스값을 반환
    public static int findImage(String name) {
        int index = 0;

        for (ImageData imgData : imageDatas)
        {
            if (imgData.getName().equals(name))
                return index;
            index++;
        }
        return -1;
    }

    public GLRenderer(Context context) {
        // 변수 초기화
        imageDatas = new ArrayList<ImageData>();
        renderTargets = new ArrayList<RenderTarget>();
        this.context = context;

        // 이미지 정보 목록에 각 이미지들을 미리 추가함
        addImage(R.drawable.image, "img1");
        addImage(R.drawable.test1, "img2");
        addImage(R.drawable.circle2, "circle");
        addImage(R.drawable.circle, "circle2");
        addImage(R.drawable.stick, "stick");
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // 렌더 버퍼를 지움
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // 카메라의 정보를 행렬 스택에 담음
        gl.glLoadIdentity();
        gl.glScalef(Game.engine.nowScene.camera.getZoomX() / (float)GLView.defaultWidth, Game.engine.nowScene.camera.getZoomY() / (float)GLView.defaultHeight, 1);
        gl.glRotatef(Game.engine.nowScene.camera.angle, 0, 0, 1);
        gl.glTranslatef(-Game.engine.nowScene.camera.position.x, -Game.engine.nowScene.camera.position.y, 0);
        gl.glPushMatrix();
        gl.glLoadIdentity();

        // 렌더 타겟에 렌더 대상들을 담음
        renderTargets.clear();
        Game.engine.render(gl);

        // 행렬 스택 초기화
        gl.glPopMatrix();
        gl.glLoadIdentity();

        // 렌더 타겟을 z-index에 따라 정렬
        Collections.sort(renderTargets, new Comparator<RenderTarget>() {
            @Override
            public int compare(RenderTarget t1, RenderTarget t2) {
                return t1.z_index - t2.z_index;
            }
        });

        // 렌더링
        for (RenderTarget renderTarget : renderTargets) {
            // 행렬 초기화
            gl.glLoadIdentity();

            // 블렌드 허용
            gl.glEnable(GL10.GL_BLEND);

            // 버텍스, 텍스쳐, 컬러, 인덱스 버퍼를 적용시킴
            if (renderTarget.fill == 1 && renderTarget.anchor.x == 0.5f && renderTarget.anchor.y == 0.5f) { // fill과 앵커에 따라 버텍스 조절
                gl.glVertexPointer(2, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(renderTarget.imageCode).getVertexBuffer());
                gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(renderTarget.imageCode).getTextureBuffer());
            }
            else {
                float[] vertex = new float[8], texture = new float[8];

                if (renderTarget.dir == RendererComponent.DIRECTION.RIGHT) {
                    vertex = new float[]{
                            renderTarget.fill * -2 + 1, 1,
                            1, 1,
                            1, -1,
                            renderTarget.fill * -2 + 1, -1
                    };
                    texture = new float[]{
                            1 - renderTarget.fill, 0,
                            1, 0,
                            1, 1,
                            1 - renderTarget.fill, 1
                    };
                }
                else if (renderTarget.dir == RendererComponent.DIRECTION.LEFT) {
                    vertex = new float[]{
                            -1, 1,
                            renderTarget.fill * 2 - 1, 1,
                            renderTarget.fill * 2 - 1, -1,
                            -1, -1
                    };
                    texture = new float[]{
                            0, 0,
                            renderTarget.fill, 0,
                            renderTarget.fill, 1,
                            0, 1
                    };
                }
                else if (renderTarget.dir == RendererComponent.DIRECTION.UP) {
                    vertex = new float[]{
                            -1, 1,
                            1, 1,
                            1, renderTarget.fill * -2 + 1,
                            -1, renderTarget.fill * -2 + 1
                    };
                    texture = new float[]{
                            0, 0,
                            1, 0,
                            1, renderTarget.fill,
                            0, renderTarget.fill
                    };
                }
                else if (renderTarget.dir == RendererComponent.DIRECTION.DOWN) {
                    vertex = new float[]{
                            -1, renderTarget.fill * 2 - 1,
                            1, renderTarget.fill * 2 - 1,
                            1, -1,
                            -1, -1
                    };
                    texture = new float[]{
                            0, 1 - renderTarget.fill,
                            1, 1 - renderTarget.fill,
                            1, 1,
                            0, 1
                    };
                }

                for (int i = 0; i < 8; i++) {
                    vertex[i] += ((i % 2 == 1) ? (renderTarget.anchor.y - 0.5f) : (renderTarget.anchor.x - 0.5f)) * 2;
                    vertex[i] *= (i % 2 == 1) ? GLRenderer.imageDatas.get(renderTarget.imageCode).getHeight() : GLRenderer.imageDatas.get(renderTarget.imageCode).getWidth();
                    vertex[i] /= 100f;
                }

                // 버퍼 설정
                ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertex.length * 4);
                byteBuf.order(ByteOrder.nativeOrder());
                FloatBuffer vertexBuffer = byteBuf.asFloatBuffer();
                vertexBuffer.put(vertex);
                vertexBuffer.position(0);

                byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
                byteBuf.order(ByteOrder.nativeOrder());
                FloatBuffer textureBuffer = byteBuf.asFloatBuffer();
                textureBuffer.put(texture);
                textureBuffer.position(0);

                // 버퍼 적용
                gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
                gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
            }
            gl.glBindTexture(GL10.GL_TEXTURE_2D, GLRenderer.imageCode[renderTarget.imageCode]);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(renderTarget.imageCode).getColorBuffer());

            // 행렬을 불러옴
            gl.glMultMatrixf(renderTarget.matrix);

            // 버퍼들을 허용
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

            // 알파 블렌드
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

            // 실제 렌더링
            gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, GLRenderer.imageDatas.get(renderTarget.imageCode).getIndex().length, GL10.GL_UNSIGNED_SHORT, GLRenderer.imageDatas.get(renderTarget.imageCode).getIndexBuffer());

            // 허용된 것을 다시 비허용
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glDisable(GL10.GL_BLEND);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
        // 렌더러의 여러 값들을 초기화
        gl.glClearColor(1f, 1f, 1f, 0.5f);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glGenTextures(100, imageCode, 0);

        Bitmap bitmap;

        for (int i = 0; i < imageDatas.size(); i++) {
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
            for (int j = 0; j < vertices.length; j++) {
                if (j % 2 == 0) {
                    vertices[j] *= imageDatas.get(i).getWidth() / 100f;
                }
                else {
                    vertices[j] *= imageDatas.get(i).getHeight() / 100f;
                }
            }
            imageDatas.get(i).setVertices(vertices);
        }
    }
}