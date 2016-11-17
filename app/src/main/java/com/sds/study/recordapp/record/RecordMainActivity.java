package com.sds.study.recordapp.record;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sds.study.recordapp.FileListActivity;
import com.sds.study.recordapp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by student on 2016-11-17.
 */

public class RecordMainActivity extends AppCompatActivity {
    String TAG;
    //녹음하는 객체
    MediaRecorder recorder;
    static final int REQUEST_RECORD_PERMISSION = 1;
    boolean flag = false;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();

        setContentView(R.layout.record_main);//layout선택시 sds껄로 하자!
        img = (ImageView) findViewById(R.id.img);
        init();
    }

    public void init() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//api참조
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//api참조
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);//api참조
    }

    //저장 파일 구하기!!
    public String getSaveFile() {
        File dir = new File(Environment.getExternalStorageDirectory(), "iot_record");

        //현재 시간 구하기!!(파일명)
        Date d = new Date();//날짜로 이름 뽑기
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HHmmss").format(d);//ex)2016-11-14

        Log.d(TAG, "현재시간은?" + currentTime);

        File saveFile = new File(dir, currentTime + ".mp4");

        return saveFile.getAbsolutePath();


    }

    //녹음파일 화면 띄우기!!
    public void showList() {

        Intent intent=new Intent(this, FileListActivity.class);
        startActivity(intent);
    }

    public void startRecord() {
        if (flag) {
            recorder.stop();
            recorder.reset();//재녹음을 위한 초기화
            img.setImageResource(R.drawable.recordstop);
            flag = false;

            //녹임이 완료된 화면을 보여주기!
            showList();
        } else {
            try {
                recorder.setOutputFile(getSaveFile());//api참조
                recorder.prepare();
                recorder.start();
                flag = true;
                img.setImageResource(R.drawable.record);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //유저의 선택!(처리결과 받기)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "requestCode: " + requestCode + ", grantResults[0]: " + grantResults[0] + ", grantResults[1]: " + grantResults[1]);
        switch (requestCode) {
            case REQUEST_RECORD_PERMISSION:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "앱 사용을 위해서는 미디어접근 권한을 주셔야 합니다.", Toast.LENGTH_SHORT).show();
                } else if (permissions.length > 0 && grantResults[1] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "앱 사용을 위해서는 오디오 권한을 주셔야 합니다.", Toast.LENGTH_SHORT).show();
                }

        }
    }

    //각종 권한을 체크하자!!버튼을 눌렀을때 권한체크!
    public void btnClick(View view) {
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int recordPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        // 퍼미션이 있는지 없는지..
        if (writePermission == PackageManager.PERMISSION_DENIED || recordPermission == PackageManager.PERMISSION_DENIED) {
            //수락여부 묻기!
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
            }, REQUEST_RECORD_PERMISSION);
        } else {
            startRecord();//조건에 맞는 사람만 권한을 준다!
        }

    }

}
