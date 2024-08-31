package com.example.xuong_sqlite_su24_ph48770.View;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.xuong_sqlite_su24_ph48770.Fragment.QLNhanVienFragment;
import com.example.xuong_sqlite_su24_ph48770.Fragment.QLPhongBanFragment;
import com.example.xuong_sqlite_su24_ph48770.R;

public class Home extends AppCompatActivity {
    FragmentManager fm;
    Fragment frag01;
    Fragment frag02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // khởi tao biến
        fm = getSupportFragmentManager();
        frag01 = new QLPhongBanFragment();
        frag02 = new QLNhanVienFragment();
        // gán mặc định khi vào activity sẽ hiển thị frag01
        fm.beginTransaction().add(R.id.frag_container01, frag01).commit();
    }
}