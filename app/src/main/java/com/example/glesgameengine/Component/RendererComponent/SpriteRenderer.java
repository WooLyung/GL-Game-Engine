package com.example.glesgameengine.Component.RendererComponent;

import android.util.Log;

import com.example.glesgameengine.GL.GLRenderer;
import com.example.glesgameengine.Main.Game;

import javax.microedition.khronos.opengles.GL10;

public class SpriteRenderer extends RendererComponent
{
    @Override
    public void render(GL10 gl)
    {
        gl.glEnable(GL10.GL_BLEND);

        gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(image[0]).getVertexBuffer());
        gl.glBindTexture(GL10.GL_TEXTURE_2D, GLRenderer.imageCode[image[0]]);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(image[0]).getColorBuffer());
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(image[0]).getTextureBuffer());

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, GLRenderer.imageDatas.get(image[0]).getIndex().length, GL10.GL_UNSIGNED_SHORT, GLRenderer.imageDatas.get(image[0]).getIndexBuffer());

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL10.GL_BLEND);
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
