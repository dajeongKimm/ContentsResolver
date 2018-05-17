package com.dajeong.android.contentsresolver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Holder extends RecyclerView.ViewHolder {

    //2. 리스트 아이템에 있는 사용할 위젯 정의
    TextView textId, textName, textPhoneNum;
    Button btnCall;

    //1. 생성자
    public Holder(View v) {
        super(v);

        //3. findViewById로 연결
        textId = v.findViewById(R.id.textId);
        textName = v.findViewById(R.id.textName);
        textPhoneNum = v.findViewById(R.id.textPhoneNum);
        btnCall = v.findViewById(R.id.btnCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                //전화번호를 가져다가 전화를 건다.
                String tel = textPhoneNum.getText().toString();
                Uri uri = Uri.parse("tel:"+tel); //Uri형태로 바꿔준 tel
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(uri);
                v.getContext().startActivity(intent);
            }
        });
    }
}
