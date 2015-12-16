package com.gl.Cube;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube 
{
	private int   _row 		= 4;		// 行数
	private float _side		= 3.0f;		// 边长
	private int   _numFace	= 6;		// 面数
	
	private FloatBuffer _planeBuffer;
	
	public Cube(float side, int row)
	{
		this._side = side;
		this._row  = row;
		
		// 创建图形数据
		createGraphics();
	}
	
	private void createGraphics()
	{
		// 设置原点
        Vertex3f pos = new Vertex3f(0.0f, 0.0f, 0.0f); 
        
        // 设置网格顶点
        Vertex3f nearVertex[][] 	= new Vertex3f[this._row + 1][this._row + 1]; // near
        Vertex3f rightVertex[][] 	= new Vertex3f[this._row + 1][this._row + 1]; // right
        Vertex3f farVertex[][] 		= new Vertex3f[this._row + 1][this._row + 1]; // far
        Vertex3f leftVertex[][] 	= new Vertex3f[this._row + 1][this._row + 1]; // left
        Vertex3f topVertex[][] 		= new Vertex3f[this._row + 1][this._row + 1]; // top
        Vertex3f bottomVertex[][] 	= new Vertex3f[this._row + 1][this._row + 1]; // bottom
        
        float sx = pos.x - this._side / 2;
        float sy = pos.y - this._side / 2;
        
        for(int i = 0; i < this._row + 1; i++)
        {
        	for(int j = 0; j < this._row + 1; j++)
        	{
        		// near
        		float nearX = sx + j * (this._side / ((float)this._row));
        		float nearY = sy + i * (this._side / ((float)this._row));
        		float nearZ = this._side / 2;
        			
        		nearVertex[i][j] = new Vertex3f(nearX, nearY, nearZ);
        		
        		// right near绕y轴逆时针旋转 pi/2
        		float rightX = (float) ((Math.cos(-Math.PI / 2) * nearX) - (Math.sin(-Math.PI / 2) * nearZ));
        		float rightY = nearY;
        		float rightZ = (float) (-1 * (Math.sin(-Math.PI / 2) * nearX) + (Math.cos(-Math.PI / 2) * nearZ));
        		
        		rightVertex[i][j] = new Vertex3f(rightX, rightY, rightZ);
        		
        		// far near绕y轴逆时针旋转 pi
        		float farX = (float) ((Math.cos(-Math.PI) * nearX) - (Math.sin(-Math.PI) * nearZ));
        		float farY = nearY;
        		float farZ = (float) (-1 * (Math.sin(-Math.PI) * nearX) + (Math.cos(-Math.PI) * nearZ));
        		
        		farVertex[i][j] = new Vertex3f(farX, farY, farZ);
        		
        		// right near绕y轴逆时针旋转 3pi/2
        		float leftX = (float) ((Math.cos(-1.5 * Math.PI) * nearX) - (Math.sin(-1.5 * Math.PI) * nearZ));
        		float leftY = nearY;
        		float leftZ = (float) (-1 * (Math.sin(-1.5 * Math.PI) * nearX) + (Math.cos(-1.5 * Math.PI) * nearZ));
        		
        		leftVertex[i][j] = new Vertex3f(leftX, leftY, leftZ);
        		
        		// top near绕y轴顺时针旋转 pi/2
        		float topX = nearX;
        		float topY = (float) ((Math.cos(Math.PI / 2) * nearY) - (Math.sin(Math.PI / 2) * nearZ));
        		float topZ = (float) ((Math.sin(Math.PI / 2) * nearY) - (Math.cos(Math.PI / 2) * nearZ));
        		
        		topVertex[i][j] = new Vertex3f(topX, topY, topZ);
        		
        		// bottom near绕y轴逆时针旋转 pi/2
        		float bottomX = nearX;
        		float bottomY = (float) ((Math.cos(-Math.PI / 2) * nearY) - (Math.sin(-Math.PI / 2) * nearZ));
        		float bottomZ = (float) ((Math.sin(-Math.PI / 2) * nearY) - (Math.cos(-Math.PI / 2) * nearZ));
        		
        		bottomVertex[i][j] = new Vertex3f(bottomX, bottomY, bottomZ);
        	}
        }
        
        // 设置三角形顶点
        int len = (this._row) * (this._row) * 2 * 3;
        
        Vertex3f tri[] = new Vertex3f[len * this._numFace];
        
        int index = 0;
        
        for(int i = 0; i < this._row; i++)
        {
        	for(int j = 0; j < this._row; j++)
        	{
        		// near
        		tri[index + 0] = nearVertex[i + 1][j + 1];
        		tri[index + 1] = nearVertex[i + 1][j];
        		tri[index + 2] = nearVertex[i][j];
        		
        		tri[index + 3] = nearVertex[i][j];
        		tri[index + 4] = nearVertex[i][j + 1];
        		tri[index + 5] = nearVertex[i + 1][j + 1];
        		
        		// right
        		tri[index + 0 + len] = rightVertex[i + 1][j + 1];
        		tri[index + 1 + len] = rightVertex[i + 1][j];
        		tri[index + 2 + len] = rightVertex[i][j];
        		
        		tri[index + 3 + len] = rightVertex[i][j];
        		tri[index + 4 + len] = rightVertex[i][j + 1];
        		tri[index + 5 + len] = rightVertex[i + 1][j + 1];
        		
        		// far
        		tri[index + 0 + len * 2] = farVertex[i + 1][j + 1];
        		tri[index + 1 + len * 2] = farVertex[i + 1][j];
        		tri[index + 2 + len * 2] = farVertex[i][j];
        		
        		tri[index + 3 + len * 2] = farVertex[i][j];
        		tri[index + 4 + len * 2] = farVertex[i][j + 1];
        		tri[index + 5 + len * 2] = farVertex[i + 1][j + 1];
        		
        		// left
        		tri[index + 0 + len * 3] = leftVertex[i + 1][j + 1];
        		tri[index + 1 + len * 3] = leftVertex[i + 1][j];
        		tri[index + 2 + len * 3] = leftVertex[i][j];
        		
        		tri[index + 3 + len * 3] = leftVertex[i][j];
        		tri[index + 4 + len * 3] = leftVertex[i][j + 1];
        		tri[index + 5 + len * 3] = leftVertex[i + 1][j + 1];
        		
        		// top
        		tri[index + 0 + len * 4] = topVertex[i + 1][j + 1];
        		tri[index + 1 + len * 4] = topVertex[i + 1][j];
        		tri[index + 2 + len * 4] = topVertex[i][j];
        		
        		tri[index + 3 + len * 4] = topVertex[i][j];
        		tri[index + 4 + len * 4] = topVertex[i][j + 1];
        		tri[index + 5 + len * 4] = topVertex[i + 1][j + 1];
        		
        		// bottom
        		tri[index + 0 + len * 5] = bottomVertex[i + 1][j + 1];
        		tri[index + 1 + len * 5] = bottomVertex[i + 1][j];
        		tri[index + 2 + len * 5] = bottomVertex[i][j];
        		
        		tri[index + 3 + len * 5] = bottomVertex[i][j];
        		tri[index + 4 + len * 5] = bottomVertex[i][j + 1];
        		tri[index + 5 + len * 5] = bottomVertex[i + 1][j + 1];
        		
        		index += 6;
        	}
        }
        
        // 设置顶点缓冲
        int len2 = len * 3;
        
        len2 *= this._numFace;
        
        float []plane = new float[len2];
        
        int index2 = 0;
        
        for(int e = 0; e < len * this._numFace; e++)
        {
        	plane[index2] = tri[e].x;
        	plane[index2 + 1] = tri[e].y;
        	plane[index2 + 2] = tri[e].z;

        	index2 += 3;
        }
        
        this._planeBuffer = Util.getFloatBuffer(plane);
	}
	
	public void draw(GL10 gl)
	{
		// 允许设置顶点
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    
		// 绘制平面
	    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this._planeBuffer);
	    gl.glDrawArrays(GL10.GL_TRIANGLES, 0, (this._row) * (this._row) * 2 * 3 * this._numFace);
	    
	    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
	    // 取消顶点设置
	    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	    
	    // 绘制结束
	    gl.glFinish();
	}
}
