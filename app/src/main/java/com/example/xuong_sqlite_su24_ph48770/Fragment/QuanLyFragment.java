package com.example.xuong_sqlite_su24_ph48770.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xuong_sqlite_su24_ph48770.Adapter.QuanLyFragmentAdapter;
import com.example.xuong_sqlite_su24_ph48770.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyFragment extends Fragment {
    ViewPager2 pager2;
    TabLayout tabLayout;
    QuanLyFragmentAdapter quanLyFragmentAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuanLyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuanLyFragment newInstance(String param1, String param2) {
        QuanLyFragment fragment = new QuanLyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Ánh xạ
        pager2 = view.findViewById(R.id.page1);
        tabLayout = view.findViewById(R.id.tab);
        // tạo adapter
        quanLyFragmentAdapter = new QuanLyFragmentAdapter(this);
        // gắn adapter cho view page
        pager2.setAdapter(quanLyFragmentAdapter);
        /// --- hiẻn thị tab
        TabLayoutMediator mediator = new TabLayoutMediator(
                tabLayout,
                pager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                        // cấu hình cho tab viết ở đây
                        switch (i){
                            case 0:
                                tab.setText("Phòng Ban");
                                break;
                            case 1:
                                tab.setText("Nhân Viên");
                                break;
                        }
                    }
                }
        );
        mediator.attach();// hển thị tab
    }
}