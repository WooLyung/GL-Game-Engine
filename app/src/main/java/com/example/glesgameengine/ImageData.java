package com.example.glesgameengine;

import android.media.Image;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class ImageData
{
    private String name;

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;
    private FloatBuffer textureBuffer;
    private FloatBuffer colorBuffer;

    private float[] vertices = {
            -1, 1,
            1, 1,
            1, -1,
            -1, -1};
    private float[] colors = {
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1 };
    private short[] index = { 0, 1, 2, 2, 3, 0 };
    private float[] texture = {
            0, 0,
            1, 0,
            1, 1,
            0, 1};

    public ImageData()
    {
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        byteBuf = ByteBuffer.allocateDirect(colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        colorBuffer = byteBuf.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuf.asFloatBuffer();
        textureBuffer.put(texture);
        textureBuffer.position(0);

        byteBuf = ByteBuffer.allocateDirect(index.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        indexBuffer = byteBuf.asShortBuffer();
        indexBuffer.put(index);
        indexBuffer.position(0);
    }

    public FloatBuffer getColorBuffer() {
        return colorBuffer;
    }

    public FloatBuffer getTextureBuffer() {
        return textureBuffer;
    }

    public FloatBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    public ShortBuffer getIndexBuffer() {
        return indexBuffer;
    }

    public void setTextureBuffer(FloatBuffer textureBuffer) {
        this.textureBuffer = textureBuffer;
    }

    public void setIndexBuffer(ShortBuffer indexBuffer) {
        this.indexBuffer = indexBuffer;
    }

    public void setColorBuffer(FloatBuffer colorBuffer) {
        this.colorBuffer = colorBuffer;
    }

    public void setVertexBuffer(FloatBuffer vertexBuffer) {
        this.vertexBuffer = vertexBuffer;
    }

    public void setColors(float[] colors) {
        this.colors = colors;
    }

    public void setIndex(short[] index) {
        this.index = index;
    }

    public void setTexture(float[] texture) {
        this.texture = texture;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    public float[] getColors() {
        return colors;
    }

    public float[] getTexture() {
        return texture;
    }

    public float[] getVertices() {
        return vertices;
    }

    public short[] getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
