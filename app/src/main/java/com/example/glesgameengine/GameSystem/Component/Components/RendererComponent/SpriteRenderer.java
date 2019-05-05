package com.example.glesgameengine.GameSystem.Component.Components.RendererComponent;

import android.util.Log;

import com.example.glesgameengine.GraphicSystem.GLRenderer;
import com.example.glesgameengine.GraphicSystem.GLView;

import javax.microedition.khronos.opengles.GL10;

public class SpriteRenderer extends RendererComponent
{
    @Override
    public void render(GL10 gl)
    {
        super.render(gl);

        // 블렌드와 뎁스 버퍼 허용
        gl.glEnable(GL10.GL_BLEND);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        // 버텍스, 텍스쳐, 컬러, 인덱스 버퍼를 적용시킴
        gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(image[0]).getVertexBuffer());
        gl.glBindTexture(GL10.GL_TEXTURE_2D, GLRenderer.imageCode[image[0]]);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(image[0]).getColorBuffer());
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(image[0]).getTextureBuffer());

        // 버퍼들을 허용
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // 알파 블렌드
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // 실제 렌더링
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, GLRenderer.imageDatas.get(image[0]).getIndex().length, GL10.GL_UNSIGNED_SHORT, GLRenderer.imageDatas.get(image[0]).getIndexBuffer());

        // 허용된 것을 다시 막음
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_BLEND);

        // 렌더링 마무리
        gl.glPopMatrix();
        gl.glLoadIdentity();
    }

    @Override
    public void start()
    {
        setName("spriteRenderer");
        image = new int[1];
    }

    @Override
    public void update()
    {
    }

    @Override
    public void finish()
    {

    }
}
