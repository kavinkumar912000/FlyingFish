package com.kavin.flyingfish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gameover2 extends AppCompatActivity {
    private Button statragain,home;
    private TextView gscore;
    private String gmscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover2);

        gmscore =getIntent().getExtras().get("score").toString();

        statragain =(Button) findViewById(R.id.playagain);
        gscore =(TextView) findViewById(R.id.ggscore);
        home=(Button) findViewById(R.id.home);

        statragain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent=new Intent(gameover2.this,Main2Activity.class);
                startActivity(mintent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent=new Intent(gameover2.this,start_activity.class);
                startActivity(mintent);
            }
        });
        gscore.setText("Score   "+gmscore);
    }
}
