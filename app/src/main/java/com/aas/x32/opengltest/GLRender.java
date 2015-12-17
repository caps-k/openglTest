package com.aas.x32.opengltest;


import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by 32 on 2015/11/10.
 */
public class GLRender implements GLSurfaceView.Renderer
{
    float xrot, yrot, zrot;

//    private RectanglePlane _rectanglePlane = new RectanglePlane(10.0f, 10.0f, 10, 10);
//    private CirclePlane _circlePlane = new CirclePlane(5.0f, 10, 10);
//    private Cone _cone = new Cone(3.0f, 5.0f, 10, 10);
//    private Cylinder _cylinder = new Cylinder(3.0f, 5.0f, 50, 50);
//    private Sphere _sphere = new Sphere(2.0f, 20, 20);
//    private Ring _ring = new Ring(1.0f, 2.0f, 50, 50);
//    private Cube _cube = new Cube(4, 4);
    private Cube _base = new Cube(4.0f, 2);
    private Cube _lowerArm = new Cube(4.0f, 2);
    private Cube _upperArm = new Cube(4.0f, 2);

    private float _angle01 = 0.0f;
    private float _angle02 = 0.0f;
    private float _angle03 = 0.0f;

    private float _sign01 = 1.0f;
    private float _sign02 = 1.0f;

    private FloatBuffer lightAmbient = FloatBuffer.wrap(new float[]{0.5f, 0.5f, 0.5f,1.0f});

    private FloatBuffer lightDiffuse = FloatBuffer.wrap(new float[]{1.0f, 1.0f, 1.0f, 1.0f});

    private FloatBuffer lightPosition = FloatBuffer.wrap(new float[]{0.0f, 0.0f, 2.0f, 1.0f});

    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        gl.glClearColor(0, 0, 0.0f, 0.0f);

        gl.glShadeModel(GL10.GL_SMOOTH);

        gl.glEnable(GL10.GL_DEPTH_TEST);

        gl.glClearDepthf(1.0f);

        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, lightAmbient);
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, lightDiffuse);
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, lightPosition);

        gl.glEnable(GL10.GL_LIGHT1);
        gl.glEnable(GL10.GL_LIGHTING);
    }

    public void onDrawFrame(GL10 gl)
    {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -6.0f);

        gl.glRotatef(xrot, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(zrot, 0.0f, 0.0f, 1.0f);

//        this._rectanglePlane.draw(gl);
//        this._circlePlane.draw(gl);
//        this._cone.draw(gl);
//        this._cylinder.draw(gl);
//        this._sphere.draw(gl);
//        this._ring.draw(gl);
//        this._cube.draw(gl);

        if(this._angle01 >= 360.0f)
        {
            this._angle01 = 0.0f;
        }
        else
        {
            this._angle01 += 1;
        }

        if(this._angle02 >= 0)
        {
            this._sign01 = -0.5f;
        }
        if(this._angle02 < -20)
        {
            this._sign01 = 0.5f;
        }
        this._angle02 += this._sign01;

        if(this._angle03 >= 0)
        {
            this._sign02 = -0.5f;
        }
        if(this._angle03 < -20)
        {
            this._sign02 = 0.5f;
        }
        this._angle03 += this._sign02;

        gl.glRotatef(this._angle01, 0.0f, 1.0f, 0.0f);
        gl.glPushMatrix();
        gl.glScalef(1.0f, 0.5f, 1.0f);
        gl.glTranslatef(0.0f, -2.0f, 0.0f);
        this._base.draw(gl);
        gl.glPopMatrix();

        gl.glRotatef(this._angle02, 0.0f, 0.0f, 1.0f);
        gl.glPushMatrix();
        gl.glScalef(0.2f, 1.2f, 0.2f);
        gl.glTranslatef(0.0f, 1.0f, 0.0f);
        this._lowerArm.draw(gl);
        gl.glPopMatrix();

        gl.glRotatef(this._angle03, 0.0f, 0.0f, 1.0f);
        gl.glPushMatrix();
        gl.glScalef(0.7f, 0.2f, 0.2f);
        gl.glTranslatef(1.0f, 15.0f, 0.0f);
        this._upperArm.draw(gl);
        gl.glPopMatrix();

        gl.glLoadIdentity();

    }

    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        float ratio = (float) width / height;

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_PROJECTION);

        gl.glLoadIdentity();

        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();
    }


}
