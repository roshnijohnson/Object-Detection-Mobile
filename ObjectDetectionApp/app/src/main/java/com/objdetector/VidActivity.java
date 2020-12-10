package com.objdetector;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VidActivity extends AppCompatActivity {

    Button pick;
    VideoView videoView;
    MediaController mc;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vid);

        pick = findViewById(R.id.pick);
        videoView = findViewById(R.id.video);
        mc = new MediaController(VidActivity.this);
        videoView.setMediaController(mc);

        pick.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent pickIntent = new Intent(Intent.ACTION_PICK);
                pickIntent.setType("video/*");
                startActivityForResult(pickIntent,1);
            }
        });

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VidActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            Uri videoUri = data.getData();
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }

}