/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fhu86918.educastur.tienda;

import java.io.Serializable;

/**
 *
 * @author 1dawd
 */
public class LineaPedido implements Serializable {
    private Articulo articulo;
    private int unidades;

    public LineaPedido(Articulo idArticulo, int unidades) {
        this.articulo = articulo;
        this.unidades = unidades;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo Articulo) {
        this.articulo = Articulo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    
    
    

    @Override
    public String toString() {
        return "LineaPedido{" + "idArticulo=" + articulo + ", unidades=" + unidades + '}';
    }
    
    
            
}
