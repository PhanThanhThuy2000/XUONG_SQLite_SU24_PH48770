package com.example.xuong_sqlite_su24_ph48770.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.xuong_sqlite_su24_ph48770.Adapter.NhanVienAdapter;
import com.example.xuong_sqlite_su24_ph48770.Adapter.PhongBanAdapter;
import com.example.xuong_sqlite_su24_ph48770.DAO.NhanVienDAO;
import com.example.xuong_sqlite_su24_ph48770.DAO.PhongBanDAO;
import com.example.xuong_sqlite_su24_ph48770.Model.NhanVienEntity;
import com.example.xuong_sqlite_su24_ph48770.Model.PhongBanEntity;
import com.example.xuong_sqlite_su24_ph48770.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QLNhanVienFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QLNhanVienFragment extends Fragment {
    NhanVienDAO nhanVienDAO;
    NhanVienAdapter nhanVienAdapter;
    ArrayList<NhanVienEntity> listNV;
    RecyclerView recyclerView;
    Button btnAdd_NV;
    NhanVienEntity nhanVienEntity;
    String TAG = "nhanvien";
//    private ArrayList<HashMap<String, Object>> getHM;
    private ArrayList<HashMap<String, Object>> listHM;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QLNhanVienFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QLNhanVienFragment newInstance(String param1, String param2) {
        QLNhanVienFragment fragment = new QLNhanVienFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public QLNhanVienFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_q_l_nhan_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nhanVienDAO = new NhanVienDAO(this.getContext());
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listNV = new ArrayList<>();
        listNV = nhanVienDAO.getList();
        nhanVienAdapter = new NhanVienAdapter(getContext(), listNV, getDSPhongBan(), nhanVienDAO);
        recyclerView.setAdapter(nhanVienAdapter);

        // ánh xạ
        btnAdd_NV = view.findViewById(R.id.btnAdd_NV);
        btnAdd_NV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                showDialogCreate();
            }
        });

        nhanVienAdapter.setInterfaceClick(new NhanVienAdapter.InterfaceClick() {
            @Override
            public void onCreate(NhanVienEntity nhanVienEntity) {

            }

            @Override
            public void onEditClick(NhanVienEntity nhanVienEntity) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.nhan_vien_dialog, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                // ánh xạ
                EditText dialog_ho_dem = v.findViewById(R.id.dialog_ho_dem);
                EditText dialog_ten = v.findViewById(R.id.dialog_ten);
                EditText dialog_dia_chi = v.findViewById(R.id.dialog_dia_chi);
                Spinner spnPhongBan = v.findViewById(R.id.spnPhongBan);
                Button btndialog_ok = v.findViewById(R.id.btndialog_ok);
                Button btndialog_no = v.findViewById(R.id.btndialog_no);
                // fill form lay thong tin găn vào from hiển thị lên cho ng dùng để sửa
                dialog_ho_dem.setText(nhanVienEntity.getHo_dem());
                dialog_ten.setText(nhanVienEntity.getTen());
                dialog_dia_chi.setText(nhanVienEntity.getDia_chi());
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), getDSPhongBan(), android.R.layout.simple_list_item_1, new String[]{"tenPB"}, new int[]{android.R.id.text1});
                spnPhongBan.setAdapter(simpleAdapter);
                int index = 0;
                int postion = -1;
                for (HashMap<String, Object> item : getDSPhongBan()) {
                    if ((int) item.get("maPB") == nhanVienEntity.getMaPB()) {
                        postion = index;
                    }
                    index++;
                }
                spnPhongBan.setSelection(postion);
                // xử lý buttom
                btndialog_ok.setOnClickListener(vd -> {
                    // lưu dữ liệu lấy đước vào các biên
                    String ho = dialog_ho_dem.getText().toString();
                    String ten = dialog_ten.getText().toString();
                    String dia_chi = dialog_dia_chi.getText().toString();
                    HashMap<String, Object> hs = (HashMap<String, Object>) spnPhongBan.getSelectedItem();
                    int maPB = (int) hs.get("maPB");

                    nhanVienDAO.updateRow( nhanVienEntity.getMa_nv(),ho,  ten,  dia_chi,maPB);
                    listNV.clear();
                    listNV.addAll(nhanVienDAO.getList());
                    nhanVienAdapter.notifyDataSetChanged();
                    dialog.dismiss();

                });
                btndialog_no.setOnClickListener(v2 -> {
                    dialog.dismiss();
                });
                dialog.show();
            }

            @Override
            public void onItemDelete(NhanVienEntity nhanVienEntity) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Bạn muốn xoá chứ ?");
                builder.setPositiveButton("Ok Xoá", (dialog1, i) -> {
                    nhanVienDAO.deleteRow(nhanVienEntity.getMa_nv());
                    listNV.clear();
                    listNV.addAll(nhanVienDAO.getList());
                    nhanVienAdapter.notifyDataSetChanged();
                });
                builder.setNegativeButton("Hủy", (dialog2, i) -> {
                    dialog2.dismiss();
                });
                builder.create().show();
            }
        });
    }

    public void showDialogCreate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.nhan_vien_dialog, null);
        builder.setView(v);
        AlertDialog dialog = builder.create();
        // ánh xạ
        EditText dialog_ho_dem = v.findViewById(R.id.dialog_ho_dem);
        EditText dialog_ten = v.findViewById(R.id.dialog_ten);
        EditText dialog_dia_chi = v.findViewById(R.id.dialog_dia_chi);
        Spinner spnPhongBan = v.findViewById(R.id.spnPhongBan);
        Button btndialog_ok = v.findViewById(R.id.btndialog_ok);
        Button btndialog_no = v.findViewById(R.id.btndialog_no);

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), getDSPhongBan(), android.R.layout.simple_list_item_1, new String[]{"tenPB"}, new int[]{android.R.id.text1});
        spnPhongBan.setAdapter(simpleAdapter);


        // xử lý buttom
        btndialog_ok.setOnClickListener(vd -> {
            String ho = dialog_ho_dem.getText().toString();
            String ten = dialog_ten.getText().toString();
            String dia_chi = dialog_dia_chi.getText().toString();
            HashMap<String, Object> hs = (HashMap<String, Object>) spnPhongBan.getSelectedItem();
            int maPB = (int) hs.get("maPB");
            nhanVienDAO.addRow( ho,  ten,  dia_chi,maPB);
            listNV.clear();
            listNV.addAll(nhanVienDAO.getList());
            nhanVienAdapter.notifyDataSetChanged();
//            loadData();
            Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        btndialog_no.setOnClickListener(v2 -> {
            dialog.dismiss();
        });
        dialog.show();
    }
    private ArrayList<HashMap<String, Object>> getDSPhongBan() {
        PhongBanDAO phongBanDAO = new PhongBanDAO(getContext());
        ArrayList<PhongBanEntity> list = phongBanDAO.getList();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (PhongBanEntity phongBanEntity : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maPB", phongBanEntity.getMaPB());
            hs.put("tenPB", phongBanEntity.getTenPB());
            listHM.add(hs);
        }

        return listHM;
    }


}