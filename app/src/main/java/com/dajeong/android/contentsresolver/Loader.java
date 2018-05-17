package com.dajeong.android.contentsresolver;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class Loader { //데이터를 가져올 함수.


    public List<Data> getData(Context context){
        //1. contents resolver 불러오기
        ContentResolver resolver = context.getContentResolver();

        //2. contents Provider(연락처)에서 미리 만들어 놓은 주소를 Uri 형태로 정의.
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //3. 가져올 컬럼명들을 정의.
        String calums[] = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        //4. 컨텐츠리졸버로 데이터 요청. - Query로 받음.(ContentsProvider의 주소, 칼럼명, 조건절, 조건절, 조건절)
        //cursor 단위로 받음.
        Cursor cursor= resolver.query(uri, calums,null,null,null);

        //5. cursor에 담긴 데이터를 반복문을 통해 꺼낸다.
        List<Data> list = new ArrayList<>();
        if(cursor != null){

            while(cursor.moveToNext()){ //데이터를 다 읽었으면 false가 됨.
                //5.1 컬럼인덱스를 가져온다. DB에 저장된 데이커가 몇번째있는지 보려고.
                //5.2 컬럼 인덱스로 값을 가져온다.
                Data data = new Data(); //data에 String으로 변수 설정함.
                int idx = cursor.getColumnIndex(calums[0]);
                data.Id = cursor.getString(idx);

                idx = cursor.getColumnIndex(calums[1]);
                data.Name = cursor.getString(idx);

                idx = cursor.getColumnIndex(calums[2]);
                data.PhoneNum = cursor.getString(idx);

                list.add(data);
            }

        }

        return list;

    }
}
