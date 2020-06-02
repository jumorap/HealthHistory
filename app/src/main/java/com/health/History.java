package com.health;

import com.google.firebase.database.DatabaseReference;

public class History {

    private String hora;
    private String nombre;
    private String mensaje;
    private String pesoH;
    private String tallaH;
    private String tempH;
    private String tensiH;
    private String satH;
    private String sintH;
    private String obsH;
    private String presH;
    private String urlFoto;
    private String type_mensaje;

    public History(){
    }

    public History(String hora, String nombre, String mensaje, String pesoH, String tallaH, String tempH, String tensiH, String satH, String sintH, String obsH, String presH, String urlFoto, String type_mensaje) {
        //public Mensaje(String nombre, String mensaje, String urlFoto, String type_mensaje) {

        mensaje = mensaje.trim();
        pesoH = pesoH.trim();
        tallaH = tallaH.trim();
        tempH = tempH.trim();
        tensiH = tensiH.trim();
        satH = satH.trim();
        sintH = sintH.trim();
        obsH = obsH.trim();
        presH = presH.trim();

        if (!mensaje.equals("")){
            this.hora = hora;
            this.nombre = nombre;
            this.mensaje = mensaje;
            this.pesoH = pesoH;
            this.tallaH = tallaH;
            this.tempH = tempH;
            this.tensiH = tensiH;
            this.satH = satH;
            this.sintH = sintH;
            this.obsH = obsH;
            this.presH = presH;
            this.urlFoto = urlFoto;
            this.type_mensaje = type_mensaje;
        }else{
            //
        }
    }

    /*public History(String hora, String nombre, String mensaje, String urlFoto, String type_mensaje) {
        //public Mensaje(String nombre, String mensaje, String urlFoto, String type_mensaje) {

        mensaje = mensaje.trim();

        if (!mensaje.equals("")){
            this.hora = hora;
            this.nombre = nombre;
            this.mensaje = mensaje;
            this.urlFoto = urlFoto;
            this.type_mensaje = type_mensaje;
        }else{
            //
        }
    }*/

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getType_mensaje() {
        return type_mensaje;
    }

    public void setType_mensaje(String type_mensaje) {
        this.type_mensaje = type_mensaje;
    }

    public String getPesoH() {
        return pesoH;
    }

    public void setPesoH(String pesoH) {
        this.pesoH = pesoH;
    }

    public String getTallaH() {
        return tallaH;
    }

    public void setTallaH(String tallaH) {
        this.tallaH = tallaH;
    }

    public String getTempH() {
        return tempH;
    }

    public void setTempH(String tempH) {
        this.tempH = tempH;
    }

    public String getTensiH() {
        return tensiH;
    }

    public void setTensiH(String tensiH) {
        this.tensiH = tensiH;
    }

    public String getSatH() {
        return satH;
    }

    public void setSatH(String satH) {
        this.satH = satH;
    }

    public String getSintH() {
        return sintH;
    }

    public void setSintH(String sintH) {
        this.sintH = sintH;
    }

    public String getObsH() {
        return obsH;
    }

    public void setObsH(String obsH) {
        this.obsH = obsH;
    }

    public String getPresH() {
        return presH;
    }

    public void setPresH(String presH) {
        this.presH = presH;
    }
}
