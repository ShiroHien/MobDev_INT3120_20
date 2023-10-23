package com.example.slide7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.week7.R;

public class GreetingActivity extends AppCompatActivity {
    Button btnBack;
    TextView tvFb;
    String fullName;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greeting_activity);

        btnBack = (Button) findViewById(R.id.btnBack);
        tvFb = (TextView) findViewById(R.id.tvFb);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        Intent intent = this.getIntent();
        fullName = intent.getStringExtra("fullName");
        message = intent.getStringExtra("message");
        tvFb.setText(message);
    }

    public void goBack() {
        this.onBackPressed();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        String feedback = "Hello " + fullName;
        data.putExtra("feedback", feedback);
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}