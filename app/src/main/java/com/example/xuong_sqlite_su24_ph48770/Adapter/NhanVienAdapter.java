package com.example.xuong_sqlite_su24_ph48770.Adapter;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xuong_sqlite_su24_ph48770.DAO.NhanVienDAO;
import com.example.xuong_sqlite_su24_ph48770.Model.NhanVienEntity;
import com.example.xuong_sqlite_su24_ph48770.Model.PhongBanEntity;
import com.example.xuong_sqlite_su24_ph48770.R;

import java.util.ArrayList;
import java.util.HashMap;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {
    String TAG = "adapter";
    Context context;
    ArrayList<NhanVienEntity> listNV;
    private NhanVienDAO nhanVienDAO;

    private ArrayList<HashMap<String, Object>> listHM;

    InterfaceClick interfaceClick;


    public NhanVienAdapter(Context context, ArrayList<NhanVienEntity> listNV, ArrayList<HashMap<String, Object>> listHM, NhanVienDAO nhanVienDAO) {
        this.context = context;
        this.listNV = listNV;
        this.listHM = listHM;
        this.nhanVienDAO = nhanVienDAO;

    }

    public interface InterfaceClick {
        void onCreate(NhanVienEntity nhanVienEntity);

        void onEditClick(NhanVienEntity nhanVienEntity);

        void onItemDelete(NhanVienEntity nhanVienEntity);
    }

    public void setInterfaceClick(InterfaceClick interfaceClick) {
        this.interfaceClick = interfaceClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_list_nhan_vien, parent, false);
        return new ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NhanVienEntity nhanVienEntity = listNV.get(position);
        holder.row_ho_NV.setText("Mã nhân viên: " + nhanVienEntity.getHo_dem() + "");
        holder.row_ten_NV.setText("Tên nhân viên: " + nhanVienEntity.getTen() + "");
        holder.row_dia_chi_NV.setText("Địa chỉ nhân viên: " + nhanVienEntity.getDia_chi() + "");
        holder.row_maPB.setText("Mã phòng ban: " + nhanVienEntity.getMaPB() + "");
        holder.row_tenPB.setText("Tên phòng ban:" + listNV.get(position).getTenPB());
        holder.img_update_MV.setOnClickListener(v2 -> {
//            showDialogUpdate(listNV.get(holder.getAdapterPosition()));
            interfaceClick.onEditClick(nhanVienEntity);

        });
        holder.img_delete_NV.setOnClickListener(v2 -> {
            interfaceClick.onItemDelete(nhanVienEntity);
        });

    }

    @Override
    public int getItemCount() {
        return listNV.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView row_ho_NV, row_ten_NV, row_dia_chi_NV, row_maPB, row_tenPB;
        ImageView img_update_MV, img_delete_NV;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            // ánh xạ
            row_ho_NV = itemView.findViewById(R.id.row_ho_NV);
            row_ten_NV = itemView.findViewById(R.id.row_ten_NV);
            row_dia_chi_NV = itemView.findViewById(R.id.row_dia_chi_NV);
            row_maPB = itemView.findViewById(R.id.row_maPB);
            row_tenPB = itemView.findViewById(R.id.row_tenPB);
            img_update_MV = itemView.findViewById(R.id.img_update_NV);
            img_delete_NV = itemView.findViewById(R.id.img_delete_NV);
        }
    }

    private void showDialogUpdate(NhanVienEntity nhanVienEntity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
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
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, listHM, android.R.layout.simple_list_item_1, new String[]{"tenPB"}, new int[]{android.R.id.text1});
        spnPhongBan.setAdapter(simpleAdapter);
        int index = 0;
        int postion = -1;
        for (HashMap<String, Object> item : listHM) {
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

            nhanVienDAO.updateRow(nhanVienEntity.getMa_nv(), ho, ten, dia_chi, maPB);
            listNV.clear();
            listNV.addAll(nhanVienDAO.getList());
            notifyDataSetChanged();
            dialog.dismiss();

        });
        btndialog_no.setOnClickListener(v2 -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}
