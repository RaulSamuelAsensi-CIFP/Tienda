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
public class MetodosAux implements Serializable {
            
   public static boolean esInt(String s) {
        int n;
        try{
            n=Integer.parseInt(s);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
    
    public static boolean esDouble(String s) {
        double n;
        try {
            n = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false; 
        }
    }
    
    /* EJEMPLOS DE UTILIZACION esInt() y esDouble() :
        
        //INTRODUCIR LAS EXISTENCIAS DE UN LIBRO EN UNA VARIABLE DE TIPO INT
        String exT;
	do {          
            System.out.println("EXISTENCIAS:");
            exT=sc.next(); //Se lee la entrada de EXISTENCIAS como un String
        } while(!esInt(exT)); //Se sigue pidiendo la entrada si no es int
        
        // INTRODUCIR EL PVP DE UN ARTÍCULO EN UNA VARIABLE DE TIPO DOUBLE
        String pvpT;
	do {          
            System.out.println("PVP:");
            pvpT=sc.next(); //Se lee la entrada del PVP como un String
        } while(!esDouble(pvpT)); /
    */
    
    
    
    public static boolean validarDNI(String dni) {
        // Verificar que el DNI tiene un formato válido
        if (dni.isBlank() || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            return false;
        }

        // Extraer el número y la letra del DNI
        String numeroStr = dni.substring(0, 8);
        char letra = dni.charAt(8);

        // Calcular la letra correspondiente al número del DNI
        char letraCalculada = calcularLetraDNI(Integer.parseInt(numeroStr));

        // Comparar la letra calculada con la letra proporcionada y devolver
        // el resultado de la comparación TRUE/FALSE
                      
        return letra == letraCalculada; 
        
        /* devuelve TRUE si la letra del DNI y la calculada según la fórmula COINCIDEN 
        SE PUEDE DEVOLVER ASI EL RESULTADO DE UNA COMPARACIÓN. Se devuelve TRUE si la comparación
        se cumple y FALSE sino. Es equivalente a poner:
          if (letra == letraCalculada) {
              return TRUE;
           } else {
              return FALSE;   
           }
        */
    }

    private static char calcularLetraDNI(int numero) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letras.charAt(numero % 23);
    } 
    
    
    /* EJEMPLO DE UTILIZACIÓN validarDNI()
        
        do{
            System.out.println("DNI CLIENTE:");
            dniT=sc.nextLine().toUpperCase();
        }while (!validarDNI(dniT));
    */


}
