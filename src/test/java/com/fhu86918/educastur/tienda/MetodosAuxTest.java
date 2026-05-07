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
public class MetodosAuxTest {
    
    public MetodosAuxTest() {
    }
    
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    
    @BeforeEach
    public void setUp() {
    }
    
    
    @AfterEach
    public void tearDown() {
    }
    
    
    @Test
    public void testEsInt() {
        
        //asertos sueltos si falla algún caso de prueba no sabré cual falló
        /*assertTrue(MetodosAux.esInt("5"));
        assertTrue(MetodosAux.esInt("-5"));
        assertFalse(MetodosAux.esInt("555555555555555555555555"));
        assertFalse(MetodosAux.esInt("5.5"));
        assertFalse(MetodosAux.esInt("sdfgh"));*/
        
        
        //aseris dentro de un asertAll, si falla alguno sabré cuál es
        assertAll(
        () -> assertTrue(MetodosAux.esInt("5")),
        () -> assertTrue(MetodosAux.esInt("-5")),
        () -> assertFalse(MetodosAux.esInt("5.5")),
        () -> assertFalse(MetodosAux.esInt("sdfgh"))
        );
    }
    
    
    @Test
    public void testEsDouble() {
        
        assertAll(
            () -> assertTrue(MetodosAux.esDouble("5")),
            () -> assertTrue(MetodosAux.esDouble("-5")),
            () -> assertTrue(MetodosAux.esDouble("5.5")),
            () -> assertFalse(MetodosAux.esDouble("sdfgh"))
        );
        
    }
    
    
    @Test
    public void testValidarDNI() {

        
        assertAll(
            () -> assertTrue(MetodosAux.validarDNI("80580845T")),
            () -> assertTrue(MetodosAux.validarDNI("36347775R")),
            () -> assertFalse(MetodosAux.validarDNI("63921307Z")),
            () -> assertFalse(MetodosAux.validarDNI("02337565Z"))
        );

    }
    
}
