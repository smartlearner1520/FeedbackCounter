package com.example.yanglei.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class SetPassword extends AppCompatActivity {
    private EditText t;
    private Button reset,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_password);

        t = (EditText) findViewById(R.id.et1);
        reset = (Button) findViewById(R.id.reset);
        exit = (Button) findViewById(R.id.exit);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resetpw = t.getText().toString();
                if(!resetpw.equals("")){
                    try {
                        EnterPage.setPassword(resetpw);
                        Intent intent = new Intent(SetPassword.this,MainActivity.class);
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
            }
        });
    }

    public void Logout(){
        Intent intent = new Intent(SetPassword.this,MainActivity.class);
        startActivity(intent);
    }
}
