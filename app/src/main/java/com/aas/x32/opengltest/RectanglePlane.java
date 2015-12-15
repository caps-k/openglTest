package com.aas.x32.opengltest;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by 32 on 2015/11/17.
 */
public class RectanglePlane
{
    private int _row = 10;
    private int _col = 10;
    private float _width = 10.0f;
    private float _height = 10.0f;

    private FloatBuffer _planeBuffer;

    public RectanglePlane(float width, float height, int row, int col)
    {
        this._width = width;
        this._height = height;
        this._row = row;
        this._col = col;

        this.createGraphics();
    }

    private void createGraphics()
    {
        Vertex3f pos = new Vertex3f(0.0f, 0.0f, 0.0f);

        Vertex3f[][] vertexes = new Vertex3f[this._row][this._col];

        float sx = pos.x - this._width / 2;
        float sy = pos.y - this._height / 2;

        for(int i = 0; i < this._col; i++)
        {
            for(int j = 0; j < this._row; j++)
            {
                float nearX = sx + j * (this._width/((float)this._col-1));
                float nearY = sy + i * (this._height/((float)this._row -1));
                float nearZ = pos.z;

                vertexes[i][j] = new Vertex3f(nearX, nearY, nearZ);
            }
        }

        int len = (this._row-1)*(this._col-1) * 2 * 3;

        Vertex3f[] tri = new Vertex3f[len];

        int index = 0;

        for(int i = 0; i < this._row-1; i++)
        {
            for(int j = 0; j < this._col-1; j++)
            {
                //plane
                tri[index+0] = vertexes[i+1][j+1];
                tri[index+1] = vertexes[i+1][j];
                tri[index+2] = vertexes[i][j];

                tri[index+3] = vertexes[i][j];
                tri[index+4] = vertexes[i][j+1];
                tri[index+5] = vertexes[i+1][j+1];

                index += 6;
            }
        }

        int len2 = len * 3;

        float[] plane = new float[len2];

        int index2 = 0;

        for(int e = 0; e < len; e++)
        {
            plane[index2] = tri[e].x;
            plane[index2+1] = tri[e].y;
            plane[index2+2] = tri[e].z;

            index2 += 3;
        }

        this._planeBuffer = Util.getFloatBuffer(plane);
    }

    public void draw(GL10 gl)
    {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,this._planeBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, (this._row-1)*(this._col-1)*2*3);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glFinish();
    }


}
















