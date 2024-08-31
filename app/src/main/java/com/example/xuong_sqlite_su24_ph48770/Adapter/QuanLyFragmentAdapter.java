package com.example.xuong_sqlite_su24_ph48770.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.xuong_sqlite_su24_ph48770.Fragment.QLNhanVienFragment;
import com.example.xuong_sqlite_su24_ph48770.Fragment.QLPhongBanFragment;
import com.example.xuong_sqlite_su24_ph48770.Fragment.ThongTin;

public class QuanLyFragmentAdapter extends FragmentStateAdapter {
    QLPhongBanFragment QLPhongBanFragment1;
    QLNhanVienFragment QLNhanVienFragment2;
    ThongTin thongTin;
    public QuanLyFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
        QLPhongBanFragment1 = new QLPhongBanFragment();
        QLNhanVienFragment2 = new QLNhanVienFragment();
        thongTin = new ThongTin();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return QLNhanVienFragment2;
            case 2: return QLPhongBanFragment1;
            default: return thongTin;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
