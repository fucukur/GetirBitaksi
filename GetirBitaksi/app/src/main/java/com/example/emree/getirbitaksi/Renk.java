package com.example.emree.getirbitaksi;

/**
 * Created by Emree on 23.3.2017.
 */

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class Renk implements Renderer
{
    private Cizim cizim;    // çizim çizilir:)
    public Renk()
    {
        this.cizim = new Cizim();
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        // sayfa temizlenir ve tanımlama yapılır
        gl.glClear(GL10.GL_COLOR_MATERIAL | GL10.GL_DEPTH_BUFFER_BIT);

        // Modelview matris sıfırlanır
        gl.glLoadIdentity();

        // Çizim Bölümü
        gl.glTranslatef(0.8f, 0.0f, -5.0f);
        //gl.glScalef(0.5f, 0.5f, 0.5f);

        // üçgeni ölçeklemek için kullanılır
        cizim.ciz(gl);   // CİZ çizilir
    }
    @Override

    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        if(height == 0)
        {
            height = 1;
        }

        gl.glViewport(0, 0, width, height); //Sıfırlamak için kullanılır
        gl.glMatrixMode(GL10.GL_PROJECTION);    //GL_PROJECTİON matrisini seçmek için kullanılır
        gl.glLoadIdentity();   //GL_PROJECTİON matrisini sıfırlamak için kullanılır

        //Pencere oranı ayarı
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW); //GL_MODELVIEW seçilir
        gl.glLoadIdentity();    //GL_MODELVIEW sıfırlanır.
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
    }
}