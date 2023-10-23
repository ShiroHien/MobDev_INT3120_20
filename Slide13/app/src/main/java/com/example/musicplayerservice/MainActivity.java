package com.example.musicplayerservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start, stop, pause;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://freemusicarchive.org/music/Brylie_Christopher_Oxley/renewed-energy/contemplation-1/";
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(this, "Error initializing media player", Toast.LENGTH_SHORT).show();
        }

        start = findViewById(R.id.startButton);
        pause = findViewById(R.id.pauseButton);
        stop = findViewById(R.id.stopButton);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.startButton) {
            mediaPlayer.start();
        } else if (view.getId() == R.id.pauseButton) {
            mediaPlayer.pause();
        } else if (view.getId() == R.id.stopButton) {
            mediaPlayer.stop();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
