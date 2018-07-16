package com.example.yanglei.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalThankYou extends AppCompatActivity {

    Button nextBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_thank_you);

        nextBarcode = findViewById(R.id.end_button);
        nextBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalThankYou.this,EnterPage.class);
                startActivity(intent);
            }
        });
    }
}
