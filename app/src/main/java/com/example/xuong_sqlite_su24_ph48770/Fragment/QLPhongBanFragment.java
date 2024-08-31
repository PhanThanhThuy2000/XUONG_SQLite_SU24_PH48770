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
import android.widget.Toast;

import com.example.xuong_sqlite_su24_ph48770.Adapter.PhongBanAdapter;
import com.example.xuong_sqlite_su24_ph48770.DAO.PhongBanDAO;
import com.example.xuong_sqlite_su24_ph48770.Model.PhongBanEntity;
import com.example.xuong_sqlite_su24_ph48770.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QLPhongBanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QLPhongBanFragment extends Fragment {
String TAG = "fm";
    PhongBanDAO phongBanDAO;
    PhongBanAdapter phongBanAdapter;
    ArrayList<PhongBanEntity> listPB;
    RecyclerView recyclerView;
    EditText edtName,edtMaPB;
    Button btnAdd;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QLPhongBanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QLPhongBanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QLPhongBanFragment newInstance(String param1, String param2) {
        QLPhongBanFragment fragment = new QLPhongBanFragment();
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
        return inflater.inflate(R.layout.fragment_q_l_phong_ban, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phongBanDAO = new PhongBanDAO(this.getContext());
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listPB = new ArrayList<>();
        listPB = phongBanDAO.getList();
        phongBanAdapter = new PhongBanAdapter(getContext(),listPB);
        recyclerView.setAdapter(phongBanAdapter);
        // ánh xạ
        edtName = view.findViewById(R.id.edtName);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                PhongBanEntity phongBanEntity = new PhongBanEntity(0,name);
                phongBanDAO.addRow(phongBanEntity);
                listPB.clear();
                listPB.addAll(phongBanDAO.getList());
                phongBanAdapter.notifyDataSetChanged();
                edtName.setText("");
            }
        });

        phongBanAdapter.setInterfaceClick(new PhongBanAdapter.InterfaceClick() {
            @Override
            public void onEditClick(PhongBanEntity phongBanEntity) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.activity_custom_dialog, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                EditText editText = v.findViewById(R.id.dialog_name);
                Button btnEdit = v.findViewById(R.id.btndialog_ok);
                editText.setText(phongBanEntity.getTenPB());

                btnEdit.setOnClickListener(vd -> {
                    String phongBan = editText.getText().toString();
                    if(phongBan.trim().length() >3) {
                        phongBanEntity.setTenPB(phongBan);
                        phongBanDAO.updateRow(phongBanEntity);
                        reload();
                        dialog.dismiss();
                        return;
                    }
                    Toast.makeText(getContext(), "Phòng ban  cần lớn hơn 3", Toast.LENGTH_SHORT).show();
                });
                dialog.show();
            }

            @Override
            public void onItemDelete(PhongBanEntity phongBanEntity) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Bạn muốn xoá chứ ?");
                builder.setPositiveButton("Ok Xoá", (dialog1, i) -> {
                    phongBanDAO.deleteRow(phongBanEntity.getMaPB());
                    listPB.clear();
                    listPB.addAll(phongBanDAO.getList());
                    phongBanAdapter.notifyDataSetChanged();
                });
                builder.setNegativeButton("Hủy", (dialog2, i) -> {
                    dialog2.dismiss();
                });
                builder.create().show();
            }
        });


    }
    private void reload() {
        listPB.clear();
        listPB.addAll(phongBanDAO.getList());
        phongBanAdapter.notifyDataSetChanged();
    }
}