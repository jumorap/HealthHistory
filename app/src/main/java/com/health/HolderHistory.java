package com.health;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderHistory extends RecyclerView.ViewHolder {

    private TextView hora;
    private TextView nombre;
    private TextView mensaje;
    private TextView pesAcc;
    private TextView tallAcc;
    private TextView tempAcc;
    private TextView tensiAcc;
    private TextView satAcc;
    private TextView sintAcc;
    private TextView obsAcc;
    private TextView presAcc;

    private TextView pesA;
    private TextView tallA;
    private TextView tempA;
    private TextView tensiA;
    private TextView satA;
    private TextView sintA;
    private TextView obsA;
    private TextView presA;


    private ImageView fotoMensaje;

    public HolderHistory(@NonNull View itemView) {
        super(itemView);

        hora = itemView.findViewById(R.id.hora);
        nombre = itemView.findViewById(R.id.nombre);
        mensaje = itemView.findViewById(R.id.mensaje);
        pesAcc = itemView.findViewById(R.id.pesAcc);
        tallAcc = itemView.findViewById(R.id.tallAcc);
        tempAcc = itemView.findViewById(R.id.tempAcc);
        tensiAcc = itemView.findViewById(R.id.tensiAcc);
        satAcc = itemView.findViewById(R.id.satAcc);
        sintAcc = itemView.findViewById(R.id.sintAcc);
        obsAcc = itemView.findViewById(R.id.obsAcc);
        presAcc = itemView.findViewById(R.id.presAcc);

        mensaje = itemView.findViewById(R.id.mensaje);
        pesA = itemView.findViewById(R.id.pesA);
        tallA = itemView.findViewById(R.id.tallA);
        tempA = itemView.findViewById(R.id.tempA);
        tensiA = itemView.findViewById(R.id.tensiA);
        satA = itemView.findViewById(R.id.satA);
        sintA = itemView.findViewById(R.id.sintA);
        obsA = itemView.findViewById(R.id.obsA);
        presA = itemView.findViewById(R.id.presA);

        fotoMensaje = itemView.findViewById(R.id.mensajeFoto);
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getPesAcc() {
        return pesAcc;
    }

    public void setPesAcc(TextView pesAcc) {
        this.pesAcc = pesAcc;
    }

    public TextView getTallAcc() {
        return tallAcc;
    }

    public void setTallAcc(TextView tallAcc) {
        this.tallAcc = tallAcc;
    }

    public TextView getTempAcc() {
        return tempAcc;
    }

    public void setTempAcc(TextView tempAcc) {
        this.tempAcc = tempAcc;
    }

    public TextView getTensiAcc() {
        return tensiAcc;
    }

    public void setTensiAcc(TextView tensiAcc) {
        this.tensiAcc = tensiAcc;
    }

    public TextView getSatAcc() {
        return satAcc;
    }

    public void setSatAcc(TextView satAcc) {
        this.satAcc = satAcc;
    }

    public TextView getSintAcc() {
        return sintAcc;
    }

    public void setSintAcc(TextView sintAcc) {
        this.sintAcc = sintAcc;
    }

    public TextView getObsAcc() {
        return obsAcc;
    }

    public void setObsAcc(TextView obsAcc) {
        this.obsAcc = obsAcc;
    }

    public TextView getPresAcc() {
        return presAcc;
    }

    public void setPresAcc(TextView presAcc) {
        this.presAcc = presAcc;
    }

    public ImageView getFotoMensaje() {
        return fotoMensaje;
    }

    public void setFotoMensaje(ImageView fotoMensaje) {
        this.fotoMensaje = fotoMensaje;
    }


    public TextView getPesA() {
        return pesA;
    }

    public void setPesA(TextView pesA) {
        this.pesA = pesA;
    }

    public TextView getTallA() {
        return tallA;
    }

    public void setTallA(TextView tallA) {
        this.tallA = tallA;
    }

    public TextView getTempA() {
        return tempA;
    }

    public void setTempA(TextView tempA) {
        this.tempA = tempA;
    }

    public TextView getTensiA() {
        return tensiA;
    }

    public void setTensiA(TextView tensiA) {
        this.tensiA = tensiA;
    }

    public TextView getSatA() {
        return satA;
    }

    public void setSatA(TextView satA) {
        this.satA = satA;
    }

    public TextView getSintA() {
        return sintA;
    }

    public void setSintA(TextView sintA) {
        this.sintA = sintA;
    }

    public TextView getObsA() {
        return obsA;
    }

    public void setObsA(TextView obsA) {
        this.obsA = obsA;
    }

    public TextView getPresA() {
        return presA;
    }

    public void setPresA(TextView presA) {
        this.presA = presA;
    }
}
