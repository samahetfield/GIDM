package com.example.gidm.db;

import android.arch.persistence.room.Embedded;

public class Operaciones_Gastos {
    @Embedded
    Operaciones operaciones;

    @Embedded
    Usuario_hace_Operaciones usuario_hace_operaciones;

    public Operaciones getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(Operaciones operaciones) {
        this.operaciones = operaciones;
    }

    public Usuario_hace_Operaciones getUsuario_hace_operaciones() {
        return usuario_hace_operaciones;
    }

    public void setUsuario_hace_operaciones(Usuario_hace_Operaciones usuario_hace_operaciones) {
        this.usuario_hace_operaciones = usuario_hace_operaciones;
    }
}
