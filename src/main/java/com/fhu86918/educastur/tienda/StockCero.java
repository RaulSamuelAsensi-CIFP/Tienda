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
public class StockCero extends Exception implements Serializable {
    public  StockCero(String mensaje){
        super(mensaje);
    }
}
