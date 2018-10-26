package com.example.khangit.project_group3.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.model.KhachHang;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.khangit.project_group3.ultil.Server;
import java.util.HashMap;
import java.util.Map;

import static com.example.khangit.project_group3.ultil.Server.Duongdanregister;


public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    private EditText edtUserName;
    private EditText edtPassWord;
    private EditText edtHoten;
    private EditText edtPhone;
    private EditText edtEmail;
    private EditText edtNgaysinh;
    private EditText edtDiachi;

    private Button btnRegister;
    private Button btnLogin;
    private ProgressDialog pDialog;


    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_HOTEN = "hoten";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NGAYSINH = "ngaysinh";
    public static final String KEY_DIACHI = "diachi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get data input
                String username = edtUserName.getText().toString().trim();
                String password = edtPassWord.getText().toString().trim();
                String hoten = edtHoten.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String ngaysinh = edtNgaysinh.getText().toString().trim();
                String diachi = edtDiachi.getText().toString().trim();


                //Call method register
                registerUser(username, password,hoten,phone, email,ngaysinh,diachi);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {

        edtUserName = (EditText) findViewById(R.id.editUsername);
        edtPassWord = (EditText) findViewById(R.id.editPassword);
        edtHoten = (EditText) findViewById(R.id.editHoten);
        edtPhone = (EditText) findViewById(R.id.editPhone);
        edtEmail = (EditText) findViewById(R.id.editEmail);
        edtNgaysinh = (EditText) findViewById(R.id.editNgaysinh);
        edtDiachi = (EditText) findViewById(R.id.editDiachi);


        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng ký...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * Method register
     *
     * @param username
     * @param password
     * @param hoten
     * @param phone
     * @param email
     * @param ngaysinh
     * @param diachi    result json
     */
    private void registerUser(final String username, final String password,final String hoten, final String phone, final String email, final String ngaysinh, final String diachi) {

        if (checkEditText(edtUserName) && checkEditText(edtPassWord) && checkEditText(edtHoten) && checkEditText(edtPhone) && checkEditText(edtEmail) && checkEditText(edtNgaysinh) &&checkEditText(edtDiachi) && isValidEmail(email))  {
            pDialog.show();
            StringRequest registerRequest = new StringRequest(Request.Method.POST, Duongdanregister,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("success") == 1) {
                                    KhachHang khachHang = new KhachHang();
                                    khachHang.setUserName(jsonObject.getString("username"));
                                    khachHang.setPassword(jsonObject.getString("password"));
                                    message = jsonObject.getString("message");
                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                                    //Start LoginActivity
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    message = jsonObject.getString("message");
                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException error) {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                            }
                            pDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                            pDialog.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put(KEY_USERNAME, username);
                    params.put(KEY_PASSWORD, password);
                    params.put(KEY_HOTEN, hoten);
                    params.put(KEY_PHONE, phone);
                    params.put(KEY_EMAIL, email);
                    params.put(KEY_NGAYSINH, ngaysinh);
                    params.put(KEY_DIACHI, diachi);


                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(registerRequest);
        }
    }

    /**
     * Check Input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

    /**
     * Check Email
     */
    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            return true;
        else {
            edtEmail.setError("Email sai định dạng!");
        }
        return false;
    }
}
