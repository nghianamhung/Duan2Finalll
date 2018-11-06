package com.example.khangit.project_group3.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.adapter.DanhgiaAdapter;
import com.example.khangit.project_group3.adapter.ModelProductAdapter;
import com.example.khangit.project_group3.adapter.SanphamAdapter;
import com.example.khangit.project_group3.model.Danhgia;
import com.example.khangit.project_group3.model.Giohang;
import com.example.khangit.project_group3.model.KhachHang;
import com.example.khangit.project_group3.model.ModelProduct;
import com.example.khangit.project_group3.model.Sanpham;
import com.example.khangit.project_group3.ultil.CheckConnection;
import com.example.khangit.project_group3.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewMain;
    NavigationView navigationView;
    ListView listViewMain;
    DrawerLayout drawerLayoutMain;
    ArrayList<ModelProduct> arrayListmodelPro;
    ModelProductAdapter modelProductAdapter;
    int id = 0;
    String tenloaisp = "";
    String hinhanhloaisp ="";
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    Context context;
    public static  ArrayList<Giohang> manggiohang;
    private KhachHang khachHang;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        khachHang = new KhachHang();
        khachHang = (KhachHang) intent.getSerializableExtra("login");
        initControl();
        
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaisp();
            GetDuLieuSanPham();
            CatchOnItemListView();
        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Check connecttion Agian..");
            finish();
        }
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

    private void CatchOnItemListView() {
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0 :
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            if (KhachHang.Trangthai == "ok") {
                                Intent intent = new Intent(getApplicationContext(), InfoUser.class);
                                intent.putExtra("login", khachHang);
                                startActivity(intent);
                            }else {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.putExtra("login", khachHang);
                                startActivity(intent);
                            }

                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayoutMain.closeDrawer(GravityCompat.START);
                        break;
                    case 1 :
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, DienThoaiActivity.class);
                            intent.putExtra("idloaisanpham",arrayListmodelPro.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayoutMain.closeDrawer(GravityCompat.START);
                        break;
                    case 2 :
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LaptopActivity.class);
                            intent.putExtra("idloaisanpham",arrayListmodelPro.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayoutMain.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, PcMaytinhActivity.class);
                            intent.putExtra("idloaisanpham",arrayListmodelPro.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayoutMain.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, PcMaytinhActivity.class);
                            intent.putExtra("idloaisanpham",arrayListmodelPro.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayoutMain.closeDrawer(GravityCompat.START);
                        break;
                    case 10 :
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            intent.putExtra("idloaisanpham",arrayListmodelPro.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayoutMain.closeDrawer(GravityCompat.START);
                        break;
                    case 11 :
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                            intent.putExtra("idloaisanpham",arrayListmodelPro.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayoutMain.closeDrawer(GravityCompat.START);
                        break;


                }
            }
        });
    }

    private void GetDuLieuSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansanphamoinhat,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null){
                            int ID = 0;
                            String Tensanpham= "";
                            Integer Giasanpham = 0;
                            String Hinhanhsanpham = "";
                            String Motasanpham = "";
                            int IDsanpham = 0;
                            int Soluongsanpham = 0;
                            String Usernamedg;
                            String Danhgiasanpham;
                            try {
                                for(int i = 0; i < response.length(); i++){
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    ID = jsonObject.getInt("id");
                                    Tensanpham = jsonObject.getString("tensp");
                                    Giasanpham = jsonObject.getInt("giasp");
                                    Hinhanhsanpham = jsonObject.getString("hinhanhsp");
                                    Motasanpham = jsonObject.getString("motasp");
                                    IDsanpham = jsonObject.getInt("idsanpham");
                                    Soluongsanpham = jsonObject.getInt("soluongsanpham");
                                    mangsanpham.add(new Sanpham(ID,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,IDsanpham,Soluongsanpham));
                                    sanphamAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanLoaisp,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    try {
                        for(int i = 0; i < response.length(); i++){
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaisp = jsonObject.getString("tenloaisp");
                            hinhanhloaisp = jsonObject.getString("hinhanhloaisp");
                            arrayListmodelPro.add(new ModelProduct(id,tenloaisp,hinhanhloaisp));
                            modelProductAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
//                arrayListmodelPro.add(5, new ModelProduct(0,"Tài khoản","https://png.icons8.com/office/40/000000/administrator-male.png"));
//                arrayListmodelPro.add(6, new ModelProduct(0,"Liên Hệ","https://i.imgur.com/BT9ShMC.png"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }




    protected void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn.tgdd.vn/qcao/08_10_2018_09_17_53_HuaweiY9-800-300.png");
        mangquangcao.add("https://cdn.tgdd.vn/qcao/09_10_2018_11_20_08_Oppo-f9-tim-800-300.png");
        mangquangcao.add("https://cdn.tgdd.vn/qcao/12_10_2018_11_12_53_Hot-sale-Galaxy-A6+-800-300.png");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            Picasso.with(MainActivity.this).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        Animation in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setAutoStart(true);
        }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

//        getSupportActionBar().setTitle("Trang Chính");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutMain.openDrawer(GravityCompat.START);
            }
        });

    }

    private void initControl() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper_activity_main);
        recyclerViewMain = (RecyclerView) findViewById(R.id.recyclerview_activity_main);
        navigationView = (NavigationView) findViewById(R.id.navigationview_activity_main);
        listViewMain = (ListView) findViewById(R.id.listview_activity_main);

        drawerLayoutMain = (DrawerLayout) findViewById(R.id.drawerlayout_activity_main);
        arrayListmodelPro = new ArrayList<>();
        arrayListmodelPro.add(0, new ModelProduct(0, "Tài Khoản","https://img.icons8.com/ios/100/e67e22/guest-male-filled.png"));

        modelProductAdapter = new ModelProductAdapter(arrayListmodelPro,getApplicationContext());
        listViewMain.setAdapter(modelProductAdapter);
        mangsanpham = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(), mangsanpham);
        recyclerViewMain.setHasFixedSize(true);
        recyclerViewMain.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewMain.setAdapter(sanphamAdapter);

        if(manggiohang != null){

        }else{
            manggiohang = new ArrayList<>();
        }

    }


    @Override
    public void onClick(View view) {

    }
}
