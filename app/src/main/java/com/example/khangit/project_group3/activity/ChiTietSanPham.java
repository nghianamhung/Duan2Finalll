package com.example.khangit.project_group3.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.adapter.DanhgiaAdapter;
import com.example.khangit.project_group3.model.Danhgia;
import com.example.khangit.project_group3.model.Giohang;
import com.example.khangit.project_group3.model.KhachHang;
import com.example.khangit.project_group3.model.Sanpham;
import com.example.khangit.project_group3.ultil.CheckConnection;
import com.example.khangit.project_group3.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.khangit.project_group3.ultil.Server.Duongdansetdanhgia;

public class ChiTietSanPham extends AppCompatActivity {
    public static final String TAG = ChiTietSanPham.class.getSimpleName();


    Toolbar toolbarchitiet;
    ImageView imageViewchitiet;
    TextView txtten,txtgia,txtmota,txtdanhgia;
    Spinner spinner;
    Button btndatmua;

    String iddanhgia ;
    String username ="";
    String danhgiasanpham ="";
    String masanpham ;
    ArrayList<Danhgia> arrayListDanhgia;
    DanhgiaAdapter danhgiaAdapter;
    ListView lvdanhgia;


    int id = 0;
    String Tenchitiet = "";
    int Giachitiet =0;
    String Hinhanhchitiet = "";
    String Motachitiet = "";
    int Idsanpham;
    int Soluongsanpham=0;

    private EditText edtdanhgia;
    private Button btndanhgia;
    private ProgressDialog pDialog;

    public static final String KEY_USERNAME = "username";
    public static final String KEY_IDDANHGIA = "iddanhgia";
    public static final String KEY_MASANPHAM = "masanpham";
    public static final String KEY_DANHGIASANPHAM = "danhgiasanpham";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActionToolbar();
        }
        Intent intent = this.getIntent();
        masanpham= intent.getStringExtra("masanpham");

        GetInfomation();
        CatchEventSpinner();
        Getdanhgia1();
//      Getdanhgia();
        Setdanhgia(danhgiasanpham);
        initEvent();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.khangit.project_group3.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initEvent() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size() > 0){
                    int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for(int i = 0; i < MainActivity.manggiohang.size(); i ++){
                        if(MainActivity.manggiohang.get(i).getIdsp() == id){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() +sl);
                            if(MainActivity.manggiohang.get(i).getSoluongsp() >= Soluongsanpham){
                                MainActivity.manggiohang.get(i).setSoluongsp(Soluongsanpham);
                                Toast.makeText(getApplicationContext(), " Sản phẩm không đủ",
                                        Toast.LENGTH_LONG).show();
                            }
                            MainActivity.manggiohang.get(i).setGiasp(Giachitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * Giachitiet;
                        MainActivity.manggiohang.add(new Giohang(id, Tenchitiet, Giamoi, Hinhanhchitiet, soluong));
                    }
                }else{
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * Giachitiet;
                    MainActivity.manggiohang.add(new Giohang(id, Tenchitiet, Giamoi, Hinhanhchitiet, soluong));

                }
                Intent intent = new Intent(getApplicationContext(), com.example.khangit.project_group3.activity.Giohang.class);
                startActivity(intent);
            }
        });

    }

    private void CatchEventSpinner() {

        Integer[] soluong = new Integer[]{1};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInfomation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        Tenchitiet = sanpham.getTensanpham();
        Giachitiet = sanpham.getGiasanpham();
        Hinhanhchitiet = sanpham.getHinhanhsanpham();
        Idsanpham = sanpham.getIDSanpham();
        Soluongsanpham = sanpham.getSoluongsanpham();
        Motachitiet = sanpham.getMotasanpham();
        txtten.setText(Tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText(decimalFormat.format(Giachitiet)+"Đ");
        txtmota.setText(Motachitiet);
        Picasso.with(getApplicationContext()).load(Hinhanhchitiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.errorimg)
                .into(imageViewchitiet);
    }

    private void Getdanhgia1() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdandanhgia,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if (response != null){
                            try {
                                for(int i = 0; i < response.length(); i++){
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    username = jsonObject.getString("username");
                                    danhgiasanpham = jsonObject.getString("danhgiasanpham");
                                    masanpham = jsonObject.getString("masanpham");
                                    arrayListDanhgia.add(new Danhgia(iddanhgia,username,danhgiasanpham,masanpham));
                                    danhgiaAdapter.notifyDataSetChanged();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
//
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void Setdanhgia(String danhgiasanpham) {

            StringRequest requestLogin = new StringRequest(Request.Method.POST, Duongdansetdanhgia,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("success") == 1) {
                                    Danhgia danhgia = new Danhgia(iddanhgia,username, ChiTietSanPham.this.danhgiasanpham,masanpham);
                                    danhgia.setIddanhgia(jsonObject.getString("iddanhgia"));
                                    danhgia.setDanhgiasanpham(jsonObject.getString("danhsachsanpham"));
                                    danhgia.setMasanpham(jsonObject.getString("masanpham"));
                                    danhgia.setUsername(jsonObject.getString("username"));
                                    message = jsonObject.getString("message");
                                    Toast.makeText(ChiTietSanPham.this, message, Toast.LENGTH_SHORT).show();


                                    Intent intent = new Intent();
                                    intent.putExtra("masanpham", masanpham);

                                    startActivity(intent);
                                    finish();

                                } else {
                                    message = jsonObject.getString("message");
                                    Toast.makeText(ChiTietSanPham.this, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            pDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                        }
                    }) {
                /**
                 * set paramater
                 * */
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    params.put(KEY_USERNAME, username);
                    params.put(KEY_MASANPHAM, masanpham);
                    params.put(KEY_IDDANHGIA, iddanhgia);
                    params.put(KEY_DANHGIASANPHAM, ChiTietSanPham.this.danhgiasanpham);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(requestLogin);
        }



//    private void Getdanhgia() {
//        Danhgia danhgia = (Danhgia) getIntent().getSerializableExtra("danhgia");
//        iddanhgia = danhgia.getIddanhgia()+"";
//        danhgiasanpham = danhgia.getDanhgiasanpham()+"";
//        username = danhgia.getUsername()+"";
//        masanpham = danhgia.getMasanpham()+"";
//
//    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ActionToolbar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarchitiet = (Toolbar) findViewById(R.id.toolbarchitietsanpham);
        imageViewchitiet = (ImageView) findViewById(R.id.imageviewchitietsanpham);
        txtgia = (TextView) findViewById(R.id.textviewgiachitietsanpham);
        txtten = (TextView) findViewById(R.id.textviewtenchitietsanpham);
        txtmota = (TextView) findViewById(R.id.textviewmotachitietsanpham);
        spinner = (Spinner) findViewById(R.id.spinner);
        btndatmua = (Button) findViewById(R.id.buttondatmua);
        btndanhgia = (Button) findViewById(R.id.btndanhgia) ;
        txtdanhgia = (TextView) findViewById(R.id.tvdanhgia);

            lvdanhgia = findViewById(R.id.listviewdanhgia);
            arrayListDanhgia = new ArrayList<>();
            danhgiaAdapter = new DanhgiaAdapter(getApplicationContext(), arrayListDanhgia);
            lvdanhgia.setAdapter(danhgiaAdapter);

        }

    }

