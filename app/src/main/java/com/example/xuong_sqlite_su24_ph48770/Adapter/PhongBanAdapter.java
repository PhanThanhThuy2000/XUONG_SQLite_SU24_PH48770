package com.example.xuong_sqlite_su24_ph48770.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xuong_sqlite_su24_ph48770.Model.PhongBanEntity;
import com.example.xuong_sqlite_su24_ph48770.R;

import java.util.ArrayList;

public class PhongBanAdapter extends RecyclerView.Adapter<PhongBanAdapter.ViewHolder> {
    String TAG = "adapter";
    Context context;
    ArrayList<PhongBanEntity> listPB;
    InterfaceClick interfaceClick;


    public PhongBanAdapter(Context context, ArrayList<PhongBanEntity> listPB) {
        this.context = context;
        this.listPB = listPB;
    }
    public interface InterfaceClick {
        void onEditClick(PhongBanEntity objPhongBan);
        void onItemDelete(PhongBanEntity phongBanEntity);
    }
    public void setInterfaceClick(InterfaceClick interfaceClick) {
        this.interfaceClick = interfaceClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context ).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_list, parent, false);
        Log.i(TAG, "onCreateViewHolder: 1");
        return  new ViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhongBanEntity phongBanEntity = listPB.get(position);
        holder.row_name_PB.setText(phongBanEntity.getTenPB()+"");
        holder.img_update_PB.setOnClickListener(v2 -> {
            interfaceClick.onEditClick(phongBanEntity);
        });
        holder.img_delete_PB.setOnClickListener(v2 -> {
            interfaceClick.onItemDelete(phongBanEntity);
        });

    }

    @Override
    public int getItemCount() {
        return listPB.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView row_name_PB;
        ImageView img_update_PB,img_delete_PB;

        public ViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            // ánh xạ
            row_name_PB = itemView.findViewById(R.id.row_name_PB);
            img_update_PB = itemView.findViewById(R.id.img_update_PB);
            img_delete_PB = itemView.findViewById(R.id.img_delete_PB);
        }
    }
}
