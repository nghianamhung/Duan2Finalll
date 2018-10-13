package com.example.khangit.project_group3.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PcMaytinhAdater extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayPc;

    public PcMaytinhAdater(Context context, ArrayList<Sanpham> arrayPc) {
        this.context = context;
        this.arrayPc = arrayPc;
    }

    @Override
    public int getCount() {
        return  arrayPc.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayPc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txttenpc , txtgiapc, txtmotapc;
        public ImageView imgpc;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PcMaytinhAdater.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new PcMaytinhAdater.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_pc, null );
            viewHolder.txttenpc = (TextView) view.findViewById(R.id.textviewtenPc);
            viewHolder.txtgiapc = (TextView) view.findViewById(R.id.textviewgiaPc);
            viewHolder.txtmotapc = (TextView) view.findViewById(R.id.textviewmotaPc);
            viewHolder.imgpc = (ImageView) view.findViewById(R.id.imageviewPc);
            view.setTag(viewHolder);
        }else{
            viewHolder = (PcMaytinhAdater.ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenpc.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiapc.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        viewHolder.txtmotapc.setMaxLines(2);
        viewHolder.txtmotapc.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotapc.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.errorimg)
                .into(viewHolder.imgpc);
        return view;
    }
}
