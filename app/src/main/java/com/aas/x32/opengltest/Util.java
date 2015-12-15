package com.aas.x32.opengltest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by 32 on 2015/11/16.
 */
public class Util
{
    public static IntBuffer getIntBuffer(int [] vertexes)
    {
        IntBuffer buffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(vertexes.length * 4);
        qbb.order(ByteOrder.nativeOrder());

        buffer = qbb.asIntBuffer();
        buffer.put(vertexes);
        buffer.position(0);
        return buffer;
    }

    public static FloatBuffer getFloatBuffer(float [] vertexes)
    {
        FloatBuffer buffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(vertexes.length * 4);
        qbb.order(ByteOrder.nativeOrder());

        buffer = qbb.asFloatBuffer();
        buffer.put(vertexes);
        buffer.position(0);
        return buffer;
    }

    public static ByteBuffer getByteBuffer(byte[] vertexes)
    {
        ByteBuffer buffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(vertexes.length);
        buffer = qbb;
        buffer.put(vertexes);
        buffer.position(0);

        return buffer;
    }
}
