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
        //String newName = listMensaje.get(2).getNombre();

        holder.getHora().setText(listMensaje.get(position).getHora());
        holder.getMensaje().setText(listMensaje.get(position).getMensaje());

        holder.getPesAcc().setText(listMensaje.get(position).getPesoH());
        holder.getTallAcc().setText(listMensaje.get(position).getTallaH());
        holder.getTempAcc().setText(listMensaje.get(position).getTempH());
        holder.getTensiAcc().setText(listMensaje.get(position).getTensiH());
        holder.getSatAcc().setText(listMensaje.get(position).getSatH());
        holder.getSintAcc().setText(listMensaje.get(position).getSintH());
        holder.getObsAcc().setText(listMensaje.get(position).getObsH());
        holder.getPresAcc().setText(listMensaje.get(position).getPresH());

        holder.getNombre().setText(listMensaje.get(position).getNombre());
        if(listMensaje.get(position).getType_mensaje().equals("2")){
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            holder.getPesAcc().setVisibility(View.GONE);
            holder.getTallAcc().setVisibility(View.GONE);
            holder.getTempAcc().setVisibility(View.GONE);
            holder.getTensiAcc().setVisibility(View.GONE);
            holder.getSatAcc().setVisibility(View.GONE);
            holder.getSintAcc().setVisibility(View.GONE);
            holder.getObsAcc().setVisibility(View.GONE);
            holder.getPresAcc().setVisibility(View.GONE);

            holder.getPesA().setVisibility(View.GONE);
            holder.getTallA().setVisibility(View.GONE);
            holder.getTempA().setVisibility(View.GONE);
            holder.getTensiA().setVisibility(View.GONE);
            holder.getSatA().setVisibility(View.GONE);
            holder.getSintA().setVisibility(View.GONE);
            holder.getObsA().setVisibility(View.GONE);
            holder.getPresA().setVisibility(View.GONE);

            holder.getMensaje().setTextColor(127);//COLOR TRANSPARENTE
            Glide.with(c).load(listMensaje.get(position).getUrlFoto()).into(holder.getFotoMensaje());
        } else if(listMensaje.get(position).getType_mensaje().equals("1")) {
            holder.getFotoMensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            holder.getPesAcc().setVisibility(View.VISIBLE);
            holder.getTallAcc().setVisibility(View.VISIBLE);
            holder.getTempAcc().setVisibility(View.VISIBLE);
            holder.getTensiAcc().setVisibility(View.VISIBLE);
            holder.getSatAcc().setVisibility(View.VISIBLE);
            holder.getSintAcc().setVisibility(View.VISIBLE);
            holder.getObsAcc().setVisibility(View.VISIBLE);
            holder.getPresAcc().setVisibility(View.VISIBLE);

            holder.getPesA().setVisibility(View.VISIBLE);
            holder.getTallA().setVisibility(View.VISIBLE);
            holder.getTempA().setVisibility(View.VISIBLE);
            holder.getTensiA().setVisibility(View.VISIBLE);
            holder.getSatA().setVisibility(View.VISIBLE);
            holder.getSintA().setVisibility(View.VISIBLE);
            holder.getObsA().setVisibility(View.VISIBLE);
            holder.getPresA().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}
