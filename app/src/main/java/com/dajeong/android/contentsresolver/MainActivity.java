package com.dajeong.android.contentsresolver;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

   //내가 사용할 주소 데이터를 저장하는 저장소
    List<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한 체
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkedPermission();
        }else{
            init();
        }



    }

    public static final int REQ_PERM = 1;
    @TargetApi(Build.VERSION_CODES.M)
    public void checkedPermission(){
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            String permarr[] = {Manifest.permission.READ_CONTACTS ,Manifest.permission.CALL_PHONE};
            requestPermissions(permarr, REQ_PERM);
        }else{
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQ_PERM){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] ==PackageManager.PERMISSION_GRANTED ){
                init();
            }else{
                Toast.makeText(getBaseContext(), "권한을 허용하지 않으시면 프로그램을 실행 시킬 수 없습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void init(){
        //0. 데이터 가져옴.
        Loader loader = new Loader();
        data = loader.getData(this);

        //1.리사이클러뷰 화면 연결
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //2.아답터 생성
        CustomAdapter adapter = new CustomAdapter();
        adapter.setDate(data);
        //3.리사이클러뷰와 아답터 연결
        recyclerView.setAdapter(adapter);
        //4.리사이클러뷰매니저
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }





}
