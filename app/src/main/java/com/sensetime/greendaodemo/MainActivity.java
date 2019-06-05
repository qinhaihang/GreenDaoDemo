package com.sensetime.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sensetime.greendaodemo.db.entity.FaceInfo;
import com.sensetime.greendaodemo.db.manage.FaceInfoDbManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<FaceInfo> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDB();

    }

    private void initDB() {
        FaceInfoDbManager.getInstance().initDB(getApplicationContext());
    }

    public void creatData(View view) {
        mDatas = new ArrayList<>();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 24000; i++) {
            FaceInfo faceInfo = new FaceInfo(null, i + "", i + 10, i % 2, "image", time,true);
            mDatas.add(faceInfo);
        }
        Log.v("qhh","end creatData = " + mDatas.size());
    }

    public void insertData(View view) {
        FaceInfoDbManager.getInstance().insert(mDatas);
    }

    public void read(View view) {
        long start = System.currentTimeMillis();
        List<FaceInfo> faceInfos = FaceInfoDbManager.getInstance().querySended(true);
        long time = System.currentTimeMillis() - start;
        Log.i("qhh","read time = " + time+", size " + faceInfos.size());
    }

    public void del(View view) {
        long start = System.currentTimeMillis();
        FaceInfoDbManager.getInstance().deleteAll();
        long time = System.currentTimeMillis() - start;
        Log.i("qhh","del time = " + time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FaceInfoDbManager.getInstance().closeFaceInfoDb();
    }

}
