package com.kavin.flyingfish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class flyingFishview extends View  {

    private Bitmap fish[]=new Bitmap[2];
    private int fishx=10;
    private int fishy;
    private int fishspeed;

    private int canvasheight,canvaswidth;

    private boolean touch=false;

    private Bitmap backgroundimage;

    private int score,lifecounteroffish,level,i;

    private Paint scorepoint=new Paint();

    private Paint sp1=new Paint();

    private Bitmap life[]=new Bitmap[2];

    private int yellowx,yellowy,yellowspeed=16;
    private Paint yellowpaint=new Paint();

    private int greenx,greeny,greenspeed=20;
    private Paint greenpaint =new Paint();

    private int redx,redy,redspeed=25;
    private Paint redpaint =new Paint();





    public flyingFishview(Context context) {
        super(context);

        fish [0]= BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish [0]= BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        backgroundimage =BitmapFactory.decodeResource(getResources(), R.drawable.water3);

        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);

        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);

        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);


        scorepoint.setColor(Color.WHITE);
        scorepoint.setTextSize(70);
        scorepoint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepoint.setAntiAlias(true);

        sp1.setColor(Color.WHITE);
        sp1.setTextSize(70);
        sp1.setTypeface(Typeface.DEFAULT_BOLD);
        sp1.setAntiAlias(true);

        life[0]= BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1]= BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        fishy =550;

        score =0;

        level =1;

        i=0;

        lifecounteroffish =3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvaswidth = canvas.getWidth();
        canvasheight = canvas.getHeight();

        canvas.drawBitmap(backgroundimage, 0, 0, null);

        int minfishy = fish[0].getHeight();
        int maxfishy = canvasheight - fish[0].getHeight() * 3;
        fishy = fishy + fishspeed;
        if (fishy < minfishy) {
            fishy = minfishy;
        }
        if (fishy > maxfishy) {
            fishy = maxfishy;
        }
        fishspeed = fishspeed + 2;
        if (touch) {
            canvas.drawBitmap(fish[0], fishx, fishy, null);
            touch = false;
        } else {
            canvas.drawBitmap(fish[0], fishx, fishy, null);
        }

        yellowx = yellowx - yellowspeed;

        if (hitballchecker(yellowx, yellowy)) {
            score = score + 10;
            yellowx = -100;
        }

        if (yellowx < 0) {
            yellowx = canvaswidth + 21;
            yellowy = (int) Math.floor(Math.random() * (maxfishy - minfishy)) + minfishy;
        }
        canvas.drawCircle(yellowx, yellowy, 25, yellowpaint);

        greenx = greenx - greenspeed;

        if (hitballchecker(greenx, greeny)) {
            score = score + 20;
            greenx = -100;
        }

        if (greenx < 0) {
            greenx = canvaswidth + 21;
            greeny = (int) Math.floor(Math.random() * (maxfishy - minfishy)) + minfishy;
        }
        canvas.drawCircle(greenx, greeny, 25, greenpaint);


        redx = redx - redspeed;

        if (hitballchecker(redx, redy)) {
            redx = -100;
            lifecounteroffish--;

            if (lifecounteroffish == 0) {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                Intent gintent=new Intent(getContext(),GameOverActivity.class);
                gintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gintent.putExtra("score",score);
                getContext().startActivity(gintent);
            }
        }

        if (redx < 0) {
            redx = canvaswidth + 21;
            redy = (int) Math.floor(Math.random() * (maxfishy - minfishy)) + minfishy;
        }
        canvas.drawCircle(redx, redy, 30, redpaint);

        canvas.drawText("Score:"+score,20,60,scorepoint);


        if(score >= 200 && score <400)
        {
            if(i == 0)
            {
                backgroundimage =BitmapFactory.decodeResource(getResources(), R.drawable.background);
                i++;
            }
            level =2;
            yellowspeed =26;
            greenspeed =30;
            redspeed =35;
        }
        else if(score >=400 && score <600)
        {
            if(i == 1)
            {
                backgroundimage =BitmapFactory.decodeResource(getResources(), R.drawable.waterne);
                i++;
            }
            level =3;
            yellowspeed =36;
            greenspeed =40;
            redspeed =45;
        }
        else if(score >=600 && score <800)
        {
            if(i == 2)
            {
                backgroundimage =BitmapFactory.decodeResource(getResources(), R.drawable.water3);
                i++;
            }
            level =4;
            yellowspeed =46;
            greenspeed =50;
            redspeed =55;
        }
        else if(score >=800 && score <1000)
        {
            if(i == 3)
            {
                backgroundimage =BitmapFactory.decodeResource(getResources(), R.drawable.background);
                i++;
            }
            level =5;
            yellowspeed =56;
            greenspeed =60;
            redspeed =65;
        }

        canvas.drawText("Level :"+level,20,140,sp1);





        for (int i = 0; i < 3; i++) {
            int x = (int) (580 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < lifecounteroffish) {
                canvas.drawBitmap(life[0], x, y, null);
            } else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
        }


    public boolean hitballchecker(int x,int y)
    {
        if(fishx < x && x < (fishx +fish[0].getWidth()) && fishy < y && y < (fishy +fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         if(event.getAction() == MotionEvent.ACTION_DOWN )
         {
             touch=true;

             fishspeed =-35;
         }
         return true;
    }
}
