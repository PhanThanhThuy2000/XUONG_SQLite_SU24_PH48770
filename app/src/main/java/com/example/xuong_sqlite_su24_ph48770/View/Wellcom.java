package com.example.xuong_sqlite_su24_ph48770.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.xuong_sqlite_su24_ph48770.R;
import com.example.xuong_sqlite_su24_ph48770.Fragment.QuanLyFragment;

public class Wellcom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wellcom);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Sử dụng CountDowTimmer chuyển màn hình tự động
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished) {// trong vòng 3s thì thực hiện công việc

            }
            @Override
            public void onFinish() {// sau khi kết thúc 3s sẽ chuyển sang login
                // Tạo intent để chuyển màn hinh
                Intent intent = new Intent(Wellcom.this, QuanLyFragment.class);
                // Chay
                startActivity(intent);
            }
        }.start();
    }
}