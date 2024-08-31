package com.example.xuong_sqlite_su24_ph48770.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.xuong_sqlite_su24_ph48770.Fragment.QuanLyFragment;
import com.example.xuong_sqlite_su24_ph48770.MainActivity;
import com.example.xuong_sqlite_su24_ph48770.util.SendMail;

import com.example.xuong_sqlite_su24_ph48770.DAO.UserDAO;
import com.example.xuong_sqlite_su24_ph48770.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    String TAG = "login";
    EditText edtEmailLog,edtPassLog,edtForgotEmail;
    TextView txtForgot;
    TextInputLayout txtEmail,txtPass;
    UserDAO userDAO;
    SendMail sendMail;

    Button btnLoginLog,btnRegisterLog,btnSend,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // anh xa
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        edtEmailLog = findViewById(R.id.edtEmailLog);
        edtPassLog = findViewById(R.id.edtPassLog);
        btnLoginLog = findViewById(R.id.btnLoginLog);
        btnRegisterLog = findViewById(R.id.btnRegisterLog);
        txtForgot = findViewById(R.id.txtForgot);
        userDAO = new UserDAO(this);
        sendMail = new SendMail();
        btnRegisterLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        // validate b2
        edtEmailLog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // đang thay đổi
                if(s.length() == 0){
                    txtEmail.setError("vui lòng nhập email");
                } else {
                    txtEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnLoginLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmailLog.getText().toString();
                String pass = edtPassLog.getText().toString();
                //validate b1
                if (TextUtils.isEmpty(email)) {
                    edtEmailLog.setError("Email không được để trống");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edtEmailLog.setError("Địa chỉ email không hợp lệ");
                }
                //validate b1
                if (email.equals("") || pass.equals("")) {
                    if (email.equals("")) {
                        txtEmail.setError("Vui lòng nhập email");
                    } else {
                        txtEmail.setError(null);
                    }
                    if (pass.equals("")) {
                        txtPass.setError("Vui lòng nhập password");
                    } else {
                        txtPass.setError(null);
                    }
                }
                boolean check = userDAO.CheckLogin(email,pass);

                if (check) {
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));

                } else {
                    Toast.makeText(Login.this, "Sai tài khoản hoặc mật khẩu vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogForgot();
            }
        });

    }

    private void showDialogForgot(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_forgot,null);
        builder.setView(view);

        // hiển thị
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false); // không cho tắt dailog khi ấn bất kì
        alertDialog.show();

        // anh xa
        edtForgotEmail = view.findViewById(R.id.edtForgotEmail);
        btnSend = view.findViewById(R.id.btnSend);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmailLog.getText().toString();
                String pass = userDAO.ForgotPassword(email);
                Log.d(TAG, "onClick: "+email);
                Toast.makeText(Login.this, pass, Toast.LENGTH_SHORT).show();

                if(pass.equals("")){
                    Toast.makeText(Login.this, "Không tìm thấy tai khoản", Toast.LENGTH_SHORT).show();
                } else {
                    sendMail.Send(Login.this,email,"Lấy lại mật khẩu","Mật khẩu của bạn là"+pass);
                }
            }
        });
    }
}