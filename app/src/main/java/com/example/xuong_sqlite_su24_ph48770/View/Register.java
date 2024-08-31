package com.example.xuong_sqlite_su24_ph48770.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.xuong_sqlite_su24_ph48770.DAO.UserDAO;
import com.example.xuong_sqlite_su24_ph48770.Model.UserEntity;
import com.example.xuong_sqlite_su24_ph48770.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    UserDAO userDAO;
    ArrayList<UserEntity> listUser;
    EditText edtEmailReg,edtPassReg,edtConfirmPassReg;
    Button btnRegisterReg,btnLoginReg;
    TextInputLayout txtEmail,txtPass,txtConfirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // anh xa
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        txtConfirmPass = findViewById(R.id.txtConfirmPass);
        edtEmailReg = findViewById(R.id.edtEmailReg);
        edtPassReg = findViewById(R.id.edtPassReg);
        edtConfirmPassReg = findViewById(R.id.edtConfirmPassReg);
        btnRegisterReg = findViewById(R.id.btnRegisterReg);
        btnLoginReg = findViewById(R.id.btnLoginReg);
        userDAO = new UserDAO(this);

        btnLoginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });
        // validate b2
        edtEmailReg.addTextChangedListener(new TextWatcher() {
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
        edtPassReg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // đang thay đổi
                if(s.length() == 0){
                    txtPass.setError("vui lòng nhập mật khẩu");
                } else {
                    txtPass.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtConfirmPassReg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // đang thay đổi
                if(s.length() == 0){
                    txtConfirmPass.setError("vui lòng nhập lại mật khẩu");
                } else {
                    txtConfirmPass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnRegisterReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailReg.getText().toString();
                String pass = edtPassReg.getText().toString();
                String confim = edtConfirmPassReg.getText().toString();
                String confirmPassReg = edtConfirmPassReg.getText().toString();
                if(!pass.equals(confirmPassReg)) {
                    Toast.makeText(Register.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
//                    boolean check = userDAO.Register(email,pass,confirmPassReg);

                    //validate b1
                    if (TextUtils.isEmpty(email)) {
                        edtEmailReg.setError("Email không được để trống");
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        edtEmailReg.setError("Địa chỉ email không hợp lệ");
                    }
                    if (pass.length() < 8) {
                        edtPassReg.setError("Mật khẩu phải có ít nhất 8 ký tự");
                    }
                    if (confim.length() < 8) {
                        edtConfirmPassReg.setError("Mật khẩu phải có ít nhất 8 ký tự");
                    }
//                    if (!pass.matches("[a-zA-Z0-9]+")) {
//                        edtPassReg.setError("Mật khẩu phải bao gồm cả chữ hoa, chữ thường và số");
//                    }
//                    if (pass.contains("[^a-zA-Z0-9]")) {
//                        edtPassReg.setError("Mật khẩu phải bao gồm ít nhất một ký tự đặc biệt");
//                    }
//                    if (!confim.matches("[a-zA-Z0-9]+")) {
//                        edtConfirmPassReg.setError("Mật khẩu phải bao gồm cả chữ hoa, chữ thường và số");
//                    }
//                    if (confim.contains("[^a-zA-Z0-9]")) {
//                        edtConfirmPassReg.setError("Mật khẩu phải bao gồm ít nhất một ký tự đặc biệt");
//                    }
//                    if (pass.length() < 8) {
//                        edtPassReg.setError("Mật khẩu phải có ít nhất 8 ký tự");
//                    } else if (!pass.matches("[a-zA-Z0-9]+")) {
//                        edtPassReg.setError("Mật khẩu phải bao gồm cả chữ hoa, chữ thường và số");
//                    } else if (pass.contains("[^a-zA-Z0-9]")) {
//                        edtPassReg.setError("Mật khẩu phải bao gồm ít nhất một ký tự đặc biệt");
//                    }
//                    if (TextUtils.isEmpty(confim)) {
//                        edtConfirmPassReg.setError("Mật khẩu không được để trống");
//                    } else if (confim.length() < 8) {
//                        edtConfirmPassReg.setError("Mật khẩu phải có ít nhất 8 ký tự");
//                    } else if (!confim.matches("[a-zA-Z0-9]+")) {
//                        edtConfirmPassReg.setError("Mật khẩu phải bao gồm cả chữ hoa, chữ thường và số");
//                    } else if (confim.contains("[^a-zA-Z0-9]")) {
//                        edtConfirmPassReg.setError("Mật khẩu phải bao gồm ít nhất một ký tự đặc biệt");
//                    }

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
                        if (confim.equals("")) {
                            txtConfirmPass.setError("Vui lòng nhập password");
                        } else {
                            txtConfirmPass.setError(null);
                        }

                    }
                    boolean check = userDAO.Register(email,pass,confirmPassReg);

                    if(check){
                            Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,Login.class));
                    } else {
                        Toast.makeText(Register.this, "Đăng ký thất bại vui lòng nhập dữ liệu hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}