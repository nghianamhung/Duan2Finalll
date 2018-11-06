package com.example.khangit.project_group3.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.model.Danhgia;

import java.util.ArrayList;

public class DanhgiaAdapter extends BaseAdapter {
    ArrayList<Danhgia> arrayListDanhgia;
    Context context;

    public DanhgiaAdapter(Context context, ArrayList<Danhgia> arrayListDanhgia ) {
        this.context = context;
        this.arrayListDanhgia = arrayListDanhgia;

    }

    @Override
    public int getCount() {
        return arrayListDanhgia.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListDanhgia.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txtdanhgia;
        TextView txtusernamedg;


    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DanhgiaAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new DanhgiaAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_danhgia, null);
            viewHolder.txtdanhgia = (TextView) view.findViewById(R.id.tvdanhgia);
            viewHolder.txtusernamedg = (TextView) view.findViewById(R.id.tvusernamedg);

            view.setTag(viewHolder);
        }
        else{
            viewHolder = (DanhgiaAdapter.ViewHolder) view.getTag();

        }
        Danhgia danhgia = (Danhgia) getItem(i);
        viewHolder.txtdanhgia.setText(danhgia.getDanhgiasanpham());
        viewHolder.txtusernamedg.setText(danhgia.getUsername());


        return  view;
    }


}
