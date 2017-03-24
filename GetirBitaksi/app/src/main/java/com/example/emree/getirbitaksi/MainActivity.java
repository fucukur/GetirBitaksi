package com.example.emree.getirbitaksi;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;//Opengl kütüphanesi
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity
{
    /* OpenGL görünümü */
    private GLSurfaceView gorunum;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("email", "emre.ayy@yandex.com");
        params.put("name", "Emre AY");
        params.put("gsm","0531 218 31 51");
        //client.put()
        client.post("http://getir-bitaksi-hackathon.herokuapp.com/getElements",params , new AsyncHttpResponseHandler() {

                        @Override
                        public void onStart() {
                            // called before request is started nette arıyorum +
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                            // called when response HTTP status is "200 OK"
                            JSONObject json;
                            try {
                                json = new JSONObject(new String(response));
                                JSONArray elements = json.getJSONArray("elements");
                                int boyut = elements.length();
                                for (int i = 0; i < boyut; i++) {
                                    JSONObject obje = elements.getJSONObject(i);
                                    Log.d("metin2","Gelen tip: "+ obje.getString("type"));
                                        String type = obje.getString("type");
                                    int r = 0, width = 0, height = 0;
                                    if (type.equals("circle")) {
                                        r = obje.getInt("r");
                                    } else if (type.equals("rectangle")) {
                                        width = obje.getInt("width");
                                        height = obje.getInt("height");
                                    }
                                    int xPosition = obje.getInt("xPosition");
                                    int yPosition = obje.getInt("yPosition");

                                    String color = obje.getString("color");


                                   // Cizim cizim =new Cizim(width,yPosition); //Cizim.java ya gönderme





                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                            // caller when response HTTP status is "4XX" (eg. 401, 403, 404)
                            Log.d("hata","hataaa: "+ (new String(errorResponse))+" ve kod: "+ statusCode);

                        }

                        @Override
                        public void onRetry(int retryNo) {
                            // called when request is retried
                        }

                    });

        // Kapatma koyma
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// Full screen

        gorunum = new GLSurfaceView(this);// Opengl görünümü oluşturuluyor

        // Renk sınıfından derle
        gorunum.setRenderer(new Renk());// Renk sınıfı belirtiyor.
        setContentView(gorunum);
    }
    /*
     * glSurface gösterim
     */

    @Override
    protected void onResume()
    {
        super.onResume();
        gorunum.onResume();
    }


    protected void onDraw(Canvas canvas)
    {

        Paint paint = new Paint();
        int x = 20;
        int y = 20;
        int radius;
        radius = 100;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(x / 2, y / 2, radius, paint);
    }

    /*
     * glsurface durdur
     */
    @Override
    protected void onPause()
    {
        super.onPause();
        gorunum.onPause();
    }

}