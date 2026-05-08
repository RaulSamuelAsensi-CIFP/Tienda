/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.fhu86918.educastur.tienda;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 1dawd
 */    
public class Tienda2026Test {
    
    public Tienda2026Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
       t.cargaDatos();
    }
    @AfterEach
    public void tearDown() {
    }
    
    //Creamos un objeto Tienda2026 t para las pruebas
    Tienda t=new Tienda();
    
    
    @Test
    public void testCargaDatos() {
        assertAll(
            () -> assertEquals(10, t.getArticulos().size()),
            () -> assertEquals(4, t.getClientes().size()),
            () -> assertEquals(6, t.getPedidos().size())
        );
    }
   
    @Test
    public void testGeneraIdPedido() {
        assertAll(
            () -> assertEquals("80580845T-004/2026",t.generaIdPedido("80580845T")),
            () -> assertEquals("36347775R-003/2026",t.generaIdPedido("36347775R")),
            () -> assertEquals("63921307Y-002/2026",t.generaIdPedido("63921307Y"))
        );
    }
    
    @Test
    public void testTotalPedido() {
        assertAll(
            () -> assertEquals(585,t.totalPedido(t.getPedidos().get(0))),
            () -> assertEquals(2980,t.totalPedido(t.getPedidos().get(1))),
            () -> assertEquals(240,t.totalPedido(t.getPedidos().get(2))),
            () -> assertEquals(390,t.totalPedido(t.getPedidos().get(3))),
            () -> assertEquals(1980,t.totalPedido(t.getPedidos().get(4)))
        );
    }  
    
    @Test
    public void testTotalPorCliente() {
        assertAll(
            () -> assertEquals(3805,t.totalCliente(t.getClientes().get("80580845T"))),
            () -> assertEquals(2370,t.totalCliente(t.getClientes().get("36347775R"))),
            () -> assertEquals(2160,t.totalCliente(t.getClientes().get("63921307Y"))),
            () -> assertEquals(0,t.totalCliente(t.getClientes().get("02337565Y")))
        );        
    }
    
    @Test
    public void testStock() {
      assertAll(
        () -> assertThrows(StockCero.class, ()-> {t.stock(t.getArticulos().get("1-11"),5);}),
        () -> assertThrows(StockCero.class, ()-> {t.stock(t.getArticulos().get("2-33"),1);}),
        () -> assertThrows(StockInsuficiente.class, ()-> {t.stock(t.getArticulos().get("3-11"),5);}),
        () -> assertThrows(StockInsuficiente.class, ()-> {t.stock(t.getArticulos().get("3-22"),10);})
      ); 
    }

}
