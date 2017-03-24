package com.example.emree.getirbitaksi;

/**
 * Created by Emree on 23.3.2017.
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Cizim
{
    private FloatBuffer kosee;  // matristampn tanımlanıyor
    private float kose[] = {

            -0.5f, -0.5f,  0.8f,  // V1 - birinci köşe (x,y,z)

            0.5f, -0.5f,  0.0f,      // V2 - İkinci köşe

            0.0f,  0.5f,  0.0f       // V3 - Üçüncü köşe



    };


    public Cizim(int width, int genisik)
    {
        // koordinatlar tanımlanıyor
        ByteBuffer kosetanim = ByteBuffer.allocateDirect(kose.length * 5);

        kosetanim.order(ByteOrder.nativeOrder());

        kosee = kosetanim.asFloatBuffer();  // Belleğe atılıyor
        kosee.put(kose);    // köşeleri koordinat yerleştirmesi yapılıyor
        kosee.position(0);  // Koordinat başlangıç pozisyonu belirtiliyor
    }

    /* Üçgen çizme */
    public void ciz(GL10 gl)
    {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Rengi göstermek için (ekran boya) biz renk arabelleğini temizlemek gerekir
        // gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        // Üçgenin rengi ayarlanıyor

        gl.glColor4f(8.0f, 1.0f, 8.0f, 6.5f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, kosee);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, kose.length / 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    }
}