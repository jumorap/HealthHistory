package com.health;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<HolderHistory> {

    private List<History> listMensaje = new ArrayList<>();
    private Context c;

    public AdapterHistory(Context c) {
        this.c = c;
    }

    public void addMessage(History m) {
        listMensaje.add(m);
        notifyItemInserted(listMensaje.size());
    }


    @NonNull
    @Override
    public HolderHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_history,parent,false);
        return new HolderHistory(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderHistory holder, int position) {
        String newName = listMensaje.get(2).getNombre();

        holder.getHora().setText(listMensaje.get(position).getHora());
        holder.getMensaje().setText(listMensaje.get(position).getMensaje());
        holder.getNombre().setText(listMensaje.get(position).getNombre());
        if(listMensaje.get(position).getType_mensaje().equals("2")){
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setTextColor(127);//COLOR TRANSPARENTE
            Glide.with(c).load(listMensaje.get(position).getUrlFoto()).into(holder.getFotoMensaje());
        } else if(listMensaje.get(position).getType_mensaje().equals("1")) {
            holder.getFotoMensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}
