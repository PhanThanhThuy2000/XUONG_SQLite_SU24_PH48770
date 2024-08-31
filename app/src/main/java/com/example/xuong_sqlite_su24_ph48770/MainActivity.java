package com.example.xuong_sqlite_su24_ph48770;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.xuong_sqlite_su24_ph48770.Fragment.QLNhanVienFragment;
import com.example.xuong_sqlite_su24_ph48770.Fragment.QLPhongBanFragment;
import com.example.xuong_sqlite_su24_ph48770.Fragment.ThongTin;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar mToolbar;
    NavigationView nav;
    FragmentManager fm; // quản lý fragment
    QLPhongBanFragment frag01;
    QLNhanVienFragment frag02;
    ThongTin frag03;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });fm = getSupportFragmentManager();
        frag01 = new QLPhongBanFragment();
        frag02 = new QLNhanVienFragment();
        frag03 = new ThongTin();

        // ánh xạ view
        drawerLayout = findViewById(R.id.main);
        mToolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav_drawer);
// gắn toolbar vào giao diện
        setSupportActionBar( mToolbar );

///--> viết code đóng mở toolbar
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                mToolbar,
                R.string.chuoi_open,
                R.string.chuoi_close
        );
// vào file values/strings.xml khai báo 2 string chuoi_open, chuoi_close
// cài đặt cập nhật trạng thái đóng mở
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener( drawerToggle );

// gán măcj định hiê thị 1 fragment khi vào ứng dụng
// có thể làm màn hình home và hiển thị ở đây
        fm.beginTransaction().add(R.id.frag_container, frag01).commit();
// xử lý sự kiện tương tác bấm vào menu
// khi vào ứng dụng thì mặc định hiển thị 1 fragment
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.menu_thong_tin){
                    fm.beginTransaction().replace(R.id.frag_container, frag03).commit();
                    mToolbar.setTitle("Màn hình Thông tin");
                }else if(menuItem.getItemId() ==R.id.menu_phong_ban){
                    fm.beginTransaction().replace(R.id.frag_container, frag01).commit();
                    mToolbar.setTitle("Màn hình phòng phòng ban");
                }else{
                    // có thể làm màn hình home và hiển thị ở đây
                    fm.beginTransaction().replace(R.id.frag_container, frag02).commit();
                    mToolbar.setTitle("Màn hình nhân viên");
                }
                drawerLayout.close();// đóng drawer
                return true;
            }
        });
    }
}