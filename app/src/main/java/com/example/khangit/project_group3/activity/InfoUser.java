package com.example.khangit.project_group3.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.model.KhachHang;

public class InfoUser extends AppCompatActivity {

    private TextView txtEmail;
    private TextView txtHoten;
    private TextView txtPhone;
    private TextView txtNgaysinh;
    private TextView txtDiachi;
    private Button btnCapnhap;
    private Button btnCapnhap1;
    Toolbar toolbaruser;


    private KhachHang khachHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_login);
        Intent intent = getIntent();
        khachHang = new KhachHang();
        khachHang = (KhachHang) intent.getSerializableExtra("login");
        addControl();
        EventButton1();
        ActionToolbar();
    }

    private void addControl() {
        btnCapnhap = (Button) findViewById(R.id.btnCapnhap) ;
//        btnCapnhap1 = (Button) findViewById(R.id.btnCapnhap1) ;

        txtHoten = (TextView) findViewById(R.id.txtHoten);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtDiachi = (TextView) findViewById(R.id.txtDiachi);
        txtNgaysinh = (TextView) findViewById(R.id.txtNgaysinh);
        toolbaruser = (Toolbar) findViewById(R.id.toolbaruser);


        /**Set value*/
        txtHoten.setText(khachHang.getHoten()+"");
        txtEmail.setText(khachHang.getEmail()+"");
        txtPhone.setText(khachHang.getPhone()+"");
        txtNgaysinh.setText(khachHang.getNgaysinh()+"");
        txtDiachi.setText(khachHang.getDiachi()+"");

    }
    private void EventButton1() {
        btnCapnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateUser.class);
                intent.putExtra("login", khachHang);
                startActivity(intent);
            }
        });


    }
    private void ActionToolbar() {
        setSupportActionBar(toolbaruser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbaruser.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("login", khachHang);
                startActivity(intent);
                finish();
            }
        });
    }
}