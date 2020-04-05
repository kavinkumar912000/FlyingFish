package com.kavin.flyingfish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread=new Thread()
        {
              @Override
              public void run()
              {
                  try
                  {
                      sleep(3000);

                  }
                  catch(Exception e)
                  {
                      e.printStackTrace();
                  }
                  finally
                  {
                      Intent intent=new Intent(splashActivity.this,start_activity.class);
                      startActivity(intent);
                  }
              }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
