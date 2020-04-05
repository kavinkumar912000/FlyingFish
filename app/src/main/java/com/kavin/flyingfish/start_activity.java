package com.kavin.flyingfish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start_activity extends AppCompatActivity {

    public Button g1,g2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        g1=findViewById(R.id.game1);
        g2=findViewById(R.id.game2);

        g2.setEnabled(false);

        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(start_activity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent(start_activity.this,Main2Activity.class);
                startActivity(intent2);
            }
        });
    }


}
