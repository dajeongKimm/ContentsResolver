package com.dajeong.android.contentsresolver;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<Holder> {

    //1.사용할 데이터 정의
    List<Data> data;

    public void setDate(List<Data> data){
        this.data = data;
    }

    //3.레이아웃을 객체로 변경
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);

        return new Holder(view);
    }

    //4.데이터를 화면에 세팅
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        //4.1데이터를 꺼낸다
        Data contact = data.get(position);
        //4.2 홀더에 넣어준다.
        holder.textId.setText(contact.Id);
        holder.textName.setText(contact.Name);
        holder.textPhoneNum.setText(contact.PhoneNum);

    }

    //2. 리스트의 개수를 정의
    @Override
    public int getItemCount() {
        return data.size();
    }
}
