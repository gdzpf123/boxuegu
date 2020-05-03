package com.boxuegu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.boxuegu.R;

public class VideoPlayActivity extends AppCompatActivity {

	private MediaController controller;
    private VideoView videoView;
    private String videoPath;
    private int position;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        videoPath = getIntent().getStringExtra("videoPath");
        position = getIntent().getIntExtra("position",0);
        init();

    }

    private void init(){
        videoView = (VideoView)findViewById(R.id.videoView);
        controller = new MediaController(this);
        videoView.setMediaController(controller);
        play();
    }
    private void play(){
        if (TextUtils.isEmpty(videoPath)){
            Toast.makeText(this,"����û����Ƶ����ʱ�޷�����",Toast.LENGTH_SHORT).show();
            return;
        }
       
         String uri = "android.resource://" + getPackageName() + "/" + R.raw.video11;
        videoView.setVideoPath(uri);
        videoView.start();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //����Ƶ������洫�����ı������Ƶ��λ�ô��ݻ�ȥ
        Intent data = new Intent();
        data.putExtra("position",position);
        setResult(RESULT_OK,data);
        return  super.onKeyDown(keyCode,event);

    }
}