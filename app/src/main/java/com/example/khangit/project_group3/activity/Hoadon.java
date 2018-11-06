package com.example.khangit.project_group3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.adapter.GiohangAdapter;

import java.text.DecimalFormat;

public class Hoadon extends AppCompatActivity {

    Toolbar toolbarhoadon;

    ListView lvgiohang;
    Button btntrove;
    static TextView txttongtien;
    GiohangAdapter giohangAdapter;
    static TextView TenNguoiNhan, DiaChi, SoDienThoai;
    String Ten, SDT, diachi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        Intent intent = this.getIntent();
        Anhxa();
        Ten= intent.getStringExtra("Ten");
        SDT = intent.getStringExtra("SDT");
        diachi = intent.getStringExtra("DiaChi");
        initEvent();
        getData();
        EventButton();
        ActionToolbar();
    }

    private void getData(){

    }

    private void EventButton() {
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.manggiohang.clear();
                Intent intent = new Intent(Hoadon.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initEvent() {
        TenNguoiNhan.setText(Ten);
        DiaChi.setText(diachi);
        SoDienThoai.setText(SDT);
        long tongtien = 0;
        for(int i = 0; i < MainActivity.manggiohang.size(); i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien) + " Đ");
    }

    private void Anhxa() {
        TenNguoiNhan = (TextView) findViewById(R.id.txtTenNguoiNhan);
        DiaChi = (TextView) findViewById(R.id.txtDiaChi);
        SoDienThoai = (TextView) findViewById(R.id.txtSoDienThoai);
        lvgiohang = (ListView) findViewById(R.id.listviewhoadon);
        btntrove = (Button) findViewById(R.id.btn_tiep_tuc_hoa_don);
        txttongtien = (TextView) findViewById(R.id.txtThanhTienHoaDon);
        giohangAdapter = new GiohangAdapter(Hoadon.this, MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
        toolbarhoadon = (Toolbar) findViewById(R.id.toolbarhoadon);


    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarhoadon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarhoadon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
