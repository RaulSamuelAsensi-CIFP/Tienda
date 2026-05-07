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
    
    Tienda2026 t = new Tienda2026();

    @Test
    public void testTotalPedido() {
        assertAll(
                () -> assertEquals(585, t.totalPedido(t.getPedidos().get(0))),
                () -> assertEquals(2980, t.totalPedido(t.getPedidos().get(1))),
                () -> assertEquals(390, t.totalPedido(t.getPedidos().get(2))),
                () -> assertEquals(1980, t.totalPedido(t.getPedidos().get(3)))
        );
    }
    
}
