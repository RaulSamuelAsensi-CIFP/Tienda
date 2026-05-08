/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.fhu86918.educastur.tienda;

//<editor-fold defaultstate="fold" desc="IMPORTS">
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ContentHandlerFactory;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import jdk.management.jfr.FlightRecorderMXBean;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//</editor-fold>

/**
 *
 * @author 1dawd
 */
// <editor-fold defaultstate="collapsed" desc="CLASE TIENDA 2026">
public class Tienda implements Serializable {
    //Para poder serializar la tienda hay que borrar la declaración de Scanner de debajo, y ponerla en cada metodo y menu que necesite de uno
    //porque si no la tienda no se podrá serializar correctamente.

    private static transient Scanner sc = new Scanner(System.in);
    public ArrayList <Pedido> pedidos;
    public HashMap <String, Articulo> articulos;
    public HashMap <String, Cliente> clientes;

    public static Scanner getSc() {
        return sc;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public HashMap<String, Articulo> getArticulos() {
        return articulos;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }
    // </editor-fold>

    public Tienda() {
        pedidos = new ArrayList();
        articulos = new HashMap();
        clientes = new HashMap();
    }

    //<editor-fold defaultstate="collapsed" desc="MAIN">
    public static void main(String[] args) {    

        //La primera vez que ejecute esto con el Backup de Tienda Completa, utilizar el carga datos, llamar al cargadatos, luego al menú, y luego al backup.
        //La segunda vez, no ejecutaremos el cargadatos, sino el metodo importar tienda, luego el menú, y luego el backup.
        Tienda t = new Tienda();
        //t.cargaDatos();
        //t.leeClientes();
        //t.guardaClientes();
        //t.guardaArticulosPorSeccion1();
        //t.leeArticulosPorSeccion();
        //t.menu();
        t.jdbcLeeArticulos();
        t.jdbcGuardaArticulos();

    }

//</editor-fold>
    
 private void jdbcLeeArticulos(){
        Statement sentencia;
        ArrayList <Articulo> articulosAux = new ArrayList();
        /*LEER ARTICULOS DESDE LA BD */
        
        String consultaSQL ="SELECT * FROM articulos";      
        try {
            /* Usando la clase Conexion SE CREA UN OBJETO DE TIPO Statement sobre el que se van 
            a lanzar consultas SQL.
               Despues se lanza la consulta SQL a traves del Statement y se recogen los registros
            en un ResultSet (Conjunto de registros resultado de una consulta SQL)
            */ 
            sentencia = Conexion.obtener().createStatement();
            ResultSet rs=sentencia.executeQuery(consultaSQL);
            while (rs.next())
            {
                articulosAux.add(new Articulo(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDouble(4)));
            }
            System.out.println("ARTICULOS importados desde MySQL correctamente");
        }catch (SQLException e) {
                System.out.println(e.toString());
        } catch (ClassNotFoundException ex) {
            System.getLogger(Tienda.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        articulosAux.stream().forEach(System.out::println);
}
 
 private void jdbcGuardaArticulos(){
        String consulta;
        
        for (Articulo a:articulos.values()){
            consulta = "INSERT INTO `articulos` (`idArticulo`, `descripcion`, `existencias`, `pvp`)"
                    + " VALUES ('" + a.getIdArticulo()+"', '"+a.getDescripcion()+"', '"+a.getExistencias()+"', '" + a.getPvp()+"')";
            try {
                PreparedStatement ps = Conexion.obtener().prepareStatement(consulta);
                ps.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("ARTICULOS exportados a MySQL correctamente");
    }
    
   private void jdbcGuardaClientes(){
        String consulta;
        
        for (Cliente c:clientes.values()){
            consulta= "INSERT INTO `clientes` (`idCliente`, `nombre`, `telefono`, `email`)"
                    + " VALUES ('" + c.getIdCliente()+"', '"+c.getNombre()+"','"+c.getTelefono()+"' , '" +c.getEmail()+"')";
            try {
                PreparedStatement ps = Conexion.obtener().prepareStatement(consulta);
                ps.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("CLIENTES exportados a MySQL correctamente");
    }
    
    private void jdbcLeeClientes(){
        Statement sentencia;
        ArrayList <Cliente> clientesAux = new ArrayList();
      
        
        String consultaSQL ="SELECT * FROM clientes";      
        try {
            sentencia = Conexion.obtener().createStatement();
            ResultSet rs=sentencia.executeQuery(consultaSQL);
            while (rs.next())
            {
                clientesAux.add(new Cliente(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            System.out.println("CLIENTES importados desde MySQL correctamente");
        }catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.toString());
        }
        
        clientesAux.stream().forEach(System.out::println);
    }

    
    private void jdbcGuardaPedidos(){
        String consulta;
        
        for (Pedido p : pedidos){
            consulta = "INSERT INTO `pedidos` (`idPedido`, `clientePedido`, `fechaPedido`)"
                    + " VALUES ('" + p.getIdPedido()+"', '"+p.getClientePedido().getIdCliente() +"', '"+p.getFechaPedido();
            for (LineaPedido lp : ) {
                
            }
            try {
                PreparedStatement ps = Conexion.obtener().prepareStatement(consulta);
                
                ps.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("ARTICULOS exportados a MySQL correctamente");
    } 
    
        
    //<editor-fold defaultstate="fold" desc="MENUS">
    public void menu() { 
        int Opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU PRINCIPAL\n");
            System.out.println("\t\t\t\t1 - ARTICULOS");
            System.out.println("\t\t\t\t2 - CLIENTES");
            System.out.println("\t\t\t\t3 - PEDIDOS");
            System.out.println("\t\t\t\t9 - CERRAR EL PROGRAMA");

            Opcion = sc.nextInt();

            switch (Opcion) {
                case 1 ->
                    menuArticulos();

                case 2 ->
                    menuClientes();

                case 3 ->
                    menuPedidos();

            }
        } while (Opcion != 9);
        System.out.println("Saliendo del programa...");
    }

    public void menuArticulos() {
        int Opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU PRINCIPAL\n");
            System.out.println("\t\t\t\t1 - ALTA");
            System.out.println("\t\t\t\t2 - BAJA");
            System.out.println("\t\t\t\t3 - REPOSICION");
            System.out.println("\t\t\t\t4 - LISTADOS");
            System.out.println("\t\t\t\t9 - MENU PRINCIPAL");

            Opcion = sc.nextInt();

            switch (Opcion) {
                case 1 ->
                    altaArticulos();

                case 2 ->
                    bajaArticulos();

                case 3 ->
                    reposicionArticulos();

                case 4 ->
                    listarArticulos();

                case 9 ->
                    menu();
            }
        } while (Opcion != 9);
        System.out.println("Saliendo del programa...");
    }

    public void menuClientes() {
        int Opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU PRINCIPAL\n");
            System.out.println("\t\t\t\t1 - ALTA");
            System.out.println("\t\t\t\t2 - BAJA");
            System.out.println("\t\t\t\t3 - MODIFICACION");
            System.out.println("\t\t\t\t4 - LISTADO");
            System.out.println("\t\t\t\t9 - MENU PRINCIPAL");

            Opcion = sc.nextInt();

            switch (Opcion) {
                case 1 ->
                    altaClientes();

                case 2 ->
                    bajaClientes();

                case 3 ->
                    modificacionClientes();

                case 4 ->
                    listaClientes();
                case 9 ->
                    menu();
            }
        } while (Opcion != 9);
        System.out.println("Saliendo del programa...");
    }

    public void menuPedidos() {
        int Opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU PRINCIPAL\n");
            System.out.println("\t\t\t\t1 - NUEVO PEDIDO");
            System.out.println("\t\t\t\t2 - LISTADO PEDIDOS");
            System.out.println("\t\t\t\t9 - MENU PRINCIPAL");

            Opcion = sc.nextInt();

            switch (Opcion) {
                case 1 ->
                    nuevoPedido();

                case 2 ->
                    listadoPedidos();

                case 9 ->
                    menu();
            }
        } while (Opcion != 9);
        System.out.println("Saliendo del programa...");
    }
//</editor-fold>

    // <editor-fold defaultstate="fold" desc="ARTICULOS"> 
    private void altaArticulos() {
        String idArticulo, descripcion, existencias, pvp; //Todas las

        sc.nextLine();
        System.out.println("ALTA DE NUEVO ARTICULO");

        //idArticulo validado con EXPRESION REGULAR SENCILLA
        do {
            System.out.println("IdArticulo (IDENTIFICADOR):");
            idArticulo = sc.next();
        } while (!idArticulo.matches("[1-5][-][0-9][0-9]") || articulos.containsKey(idArticulo));
        //CONTROLAR FORMATIO id Y QUE NO EXISTA ESE ID PREVIAMENTE

        //Entrada descripcion sin ningun tipo de validacion
        System.out.println("DESCRIPCION");
        descripcion = sc.nextLine();

        //EXISTENCIAS CON VALIDACION TIPO int
        do {
            existencias = sc.nextLine();
        } while (!MetodosAux.esInt(existencias));

        //PVP CON VALIDACION TIPO double
        do {
            System.out.println("PVP: ");
            pvp = sc.nextLine();
        } while (!MetodosAux.esDouble(pvp));

        //AÑADO OBJETO ARTICULO A LA COLECCION PARSEANDO A int y double
        Articulo a = new Articulo(idArticulo, descripcion,
                Integer.parseInt(existencias), Double.parseDouble(pvp));
        articulos.put(idArticulo, a);
        System.out.println("- Articulo añadido -");
        /*podría hacerse con una única instrucción: 
        articulos.put(idArticulo, new Articulo (idArtiulo,descripcion,+
         */
    }

    private void bajaArticulos() {

    }

    private void reposicionArticulos() {

    }

    private void listarArticulos() {
        System.out.println("");
        for (Articulo a : articulos.values()) {//Al trabajar con Hashmaps, hay que añadir el método ".values()" para que en este caso me entregue toda la información de los artículos que pedí con el toString en la clase Articulos
            System.out.println(a);
        }
        //articulos.keySet(); //Te imprime los datos que aparecen justo detrás de "articulos.put" en el cargaDatos

        System.out.println("");
        articulos.values().stream().forEach(a -> System.out.println(a));//imprime todos los articulos 
        /*para convertir los articulos y los clientes en stream:debemos 
        seleccionar qué queremos convertir en stream de las clases, porque no podemos convertir directamente los hashmaps en stream por ello 
        debemos seleccionar o los values o los keySet.
        Ejemplo:
            articulos.values().stream().forEach(a->System.out.println(a));
            articulos
         */

        //este metodo sirve para pasar elementos de una colección a otra, se puede hacer de cualquier tipo de coleccion a cualquier otra
        //hay que meter articulos.values() porque es un hashmap, si "articulos" fuera un arraylist, valdría con meter:
        //ArrayList<Articulo> articulosAux = new ArrayList(articulos);
        ArrayList<Articulo> articulosAux = new ArrayList(articulos.values());
        System.out.println("");
        articulosAux.stream().forEach(a -> System.out.println(a));//imprime todos los articulos

        //Este codigo sirve para pasar la informacion del metodo articulosAux a articulosAux2.
        ArrayList<Articulo> articulosAux2 = new ArrayList(articulosAux);
        System.out.println("");
        articulosAux2.stream()
                .filter(a -> a.getPvp() < 100)
                .sorted(Comparator.comparing(Articulo::getPvp))
                .forEach(a -> System.out.println(a));
    }

    // </editor-fold>
    
    //<editor-fold defaultstate="fold" desc="CLIENTES">
    private void altaClientes() {

    }

    private void bajaClientes() {

    }

    private void modificacionClientes() {

    }

    private void listaClientes() {
        System.out.println("DNI clientes:");
        for (String dni : clientes.keySet()) {
            System.out.println(dni);
        }
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="PEDIDOS">
    String generaIdPedido(String idCliente) {
        String nuevoId;//variable String para ir construyendo un nuevo idPedido
        //Calculamos en la variable contador cuantos pedidos tiene el cliente asociado
        int contador = 0;

        for (Pedido p : pedidos) {
            if (p.getClientePedido().getIdCliente().equalsIgnoreCase(idCliente)) {
                contador++;
            }
        }
        contador++; //sumamos a contador 1 para el nuevo pedido
        nuevoId = idCliente + "-" + String.format("%03d", contador) + "/" + LocalDate.now().getYear();
        return nuevoId;
    }

    public void stock(Articulo a, int unidades) throws StockCero, StockInsuficiente {
        //SE ACTUALIZA EL MÉTODO DE STOCK ENVIANDO DIRECTAMENTE EL ARTICULO COMO OBJETO ENTERO EN LUGAR DEL ID, SE SIMPLIFICA EL CÓDIGO Y SE HACE MÁS ELEGANTE (24/02/2026)
        //Cuando no quedan unidades lanza esta alarma, dando la info del throw
        if (a.getExistencias() == 0) {
            throw new StockCero("0 unidades disponibles de:"
                    + a.getDescripcion());
        }

        //Cuando nos piden más unidades de las que tenemos lanzamos esta excepción
        if (a.getExistencias() < unidades) {
            throw new StockInsuficiente("Sólo hay " + a.getExistencias() + " unidades disponibles de: "
                    + a.getDescripcion());
        }

    }

    //metodo que calcula el total de un pedido
    public double totalPedido(Pedido p) {
        double totalPedido = 0;
        for (LineaPedido l : p.getCestaCompra()) {
            totalPedido += l.getUnidades() * l.getArticulo().getPvp();//el metodo "+="; te va acumulando el precio de los articulos
        }

        //ANTES: 
        //AHORA: l.getArticulo().getPvp()
        return totalPedido;
    }

    public double totalCliente(Cliente c) {

        return pedidos.stream().filter(p -> p.getClientePedido().equals(c)).mapToDouble(p -> totalPedido(p)).sum();
    }

    private void nuevoPedido() {
        sc.nextLine();
        String idCliente;
        do {
            System.out.println("DNI (id) CLIENTE:");
            idCliente = sc.nextLine().toUpperCase();
            if (!clientes.containsKey(idCliente)) {
                System.out.println("No es cliente de la tienda."
                        + " Desea darse de alta o comprar como invitado");
            }
        } while (!MetodosAux.validarDNI(idCliente));

        //el new ArrayList (); sirve para inicializar la variable cestaCompra.add 
        ArrayList<LineaPedido> cestaCompra = new ArrayList();
        String idArticulo;
        int unidades = 0;
        System.out.print("\nTecle el ID del artículo deseado (FIN para terminar la compra)");
        idArticulo = sc.next();
        while (!idArticulo.equalsIgnoreCase("FIN")) {
            System.out.print("\nTeclea las unidades deseadas: ");
            unidades = sc.nextInt();

            try {
                stock(idArticulo, unidades);
                cestaCompra.add(new LineaPedido(articulos.get(idArticulo), unidades));
                //si todo va bien, no pasará nada
            } catch (StockCero ex) {
                System.out.println(ex.getMessage());
            } catch (StockInsuficiente ex) {
                System.out.println(ex.getMessage());
                System.out.println("Las quieres (SI/NO)");
                String respuesta = sc.next();
                if (respuesta.equalsIgnoreCase("SI")) {
                    cestaCompra.add(new LineaPedido(articulos.get(idArticulo), articulos.get(idArticulo).getExistencias()));
                }
            }
            System.out.print("\nTecle el ID del artículo deseado (FIN para terminar la compra)");
            idArticulo = sc.next();
        }

        if (!cestaCompra.isEmpty()) {
            System.out.println("\nESTE ES SU PEDIDO:\n");
            double totalPedido = 0;
            double totalLinea = 0;
            for (LineaPedido l : cestaCompra) {
                totalLinea = l.getUnidades() * l.getArticulo().getPvp();
                totalPedido += totalLinea;
                System.out.println(l.getArticulo() + "-"
                        + " - " + l.getUnidades()
                        + " - " + totalLinea);
            }
            System.out.println("\t\t\t\tTotal:" + totalPedido);
            System.out.println("\nProcedemos con la compra (SI/NO) ");
            String respuesta = sc.next();
            if (respuesta.equalsIgnoreCase("SI")) {
                pedidos.add(new Pedido(generaIdPedido(idCliente), clientes.get(idCliente),
                        LocalDate.now(), cestaCompra));
                for (LineaPedido l : cestaCompra) {
                    l.getArticulo().setExistencias(l.getArticulo().getExistencias() - l.getUnidades());
                }
            }
        }
    }

//IMPORTANTE REPASAR STREAMS PARA EL EXAMEN DEL JUEVES:  FILTER(REPASAR EN LA BIBLIOTECA), SORTED, FOREACH
    private void listadoPedidos() {
        System.out.println("");
        for (Pedido p : pedidos) {
            System.out.println(p + "- Total: " + totalPedido(p));
        }

        /*
        
        System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p->totalPedido(p)))//El metodo sorted ordena por defecto://Letras: a - z;  Numeros: menor a mayor
                .forEach(p->System.out.println(p + "- Total: " + totalPedido(p))); //el forEach sirve para que te dé el resultado del método que pides ()
    
        System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p->totalPedido((Pedido)p)).reversed())
                .forEach(p->System.out.println(p + "- Total: " + totalPedido(p)));
    
        System.out.println("\n");
        pedidos.stream().filter(p-> totalPedido(p)>1000)                            //filtra los pedidos y te muestra los de más de 1000 euros
                .sorted(Comparator.comparing(Pedido::getFechaPedido))      //ordena los pedidos de más de mil euros por fecha de pedido
                .forEach(p->System.out.println(p + "- Total: " + totalPedido(p)));
         */
    }

//libros.stream().sorted(Comparator.comparing(Libro::getTitulo)).forEach(l-> System.out.println(l));
//Ejemplo de ordenación en biblioteca, si tenemos definida e inicializada la variable. En el caso de la tienda: "p"; en el metodo al que se llama para la ordenacion (totalPedido)
//</editor-fold>
    
    // <editor-fold defaultstate="fold" desc="CARGA DATOS"> 
    public void cargaDatos() {
        clientes.put("80580845T", new Cliente("80580845T", "ANA ", "658111111", "ana@gmail.com"));
        clientes.put("36347775R", new Cliente("36347775R", "LOLA", "649222222", "lola@gmail.com"));
        clientes.put("63921307Y", new Cliente("63921307Y", "JUAN", "652333333", "juan@gmail.com"));
        clientes.put("02337565Y", new Cliente("02337565Y", "EDU", "634567890", "edu@gmail.com"));

        articulos.put("1-11", new Articulo("1-11", "RATON LOGITECH ST ", 0, 15));
        articulos.put("1-22", new Articulo("1-22", "TECLADO STANDARD  ", 5, 18));
        articulos.put("2-11", new Articulo("2-11", "HDD SEAGATE 1 TB  ", 15, 80));
        articulos.put("2-22", new Articulo("2-22", "SSD KINGSTOM 256GB", 9, 70));
        articulos.put("2-33", new Articulo("2-33", "SSD KINGSTOM 512GB", 0, 200));
        articulos.put("3-11", new Articulo("3-11", "HP LASERJET HP800 ", 2, 200));
        articulos.put("3-22", new Articulo("3-22", "EPSON PRINT XP300 ", 5, 80));
        articulos.put("4-11", new Articulo("4-11", "ASUS  MONITOR  22 ", 5, 100));
        articulos.put("4-22", new Articulo("4-22", "HP MONITOR LED 28 ", 5, 180));
        articulos.put("4-33", new Articulo("4-33", "SAMSUNG ODISSEY G5", 12, 580));

        LocalDate hoy = LocalDate.now();
        pedidos.add(new Pedido("80580845T-001/2025", clientes.get("80580845T"), hoy.minusDays(1), new ArrayList<>(List.of(new LineaPedido(articulos.get("1-11"), 3), new LineaPedido(articulos.get("4-22"), 3)))));
        pedidos.add(new Pedido("80580845T-002/2025", clientes.get("80580845T"), hoy.minusDays(2), new ArrayList<>(List.of(new LineaPedido(articulos.get("4-11"), 3), new LineaPedido(articulos.get("4-22"), 2), new LineaPedido(articulos.get("4-33"), 4)))));
        pedidos.add(new Pedido("36347775R-001/2025", clientes.get("36347775R"), hoy.minusDays(3), new ArrayList<>(List.of(new LineaPedido(articulos.get("4-22"), 1), new LineaPedido(articulos.get("2-22"), 3)))));
        pedidos.add(new Pedido("36347775R-002/2025", clientes.get("36347775R"), hoy.minusDays(5), new ArrayList<>(List.of(new LineaPedido(articulos.get("4-33"), 3), new LineaPedido(articulos.get("2-11"), 3)))));
        pedidos.add(new Pedido("63921307Y-001/2025", clientes.get("63921307Y"), hoy.minusDays(4), new ArrayList<>(List.of(new LineaPedido(articulos.get("2-11"), 5), new LineaPedido(articulos.get("2-33"), 3), new LineaPedido(articulos.get("4-33"), 2)))));
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="EJERCICIOS DE FILTRADO">
    private void articulosCeroExistencias() {
        articulos.values().stream()
                .filter(a -> a.getExistencias() < 1);

    }

    private void ejerciciosDeFiltrado() {

        System.out.println("Articulos de menos de 100 euros, ordenados por precio");
        articulos.values().stream()
                .filter(a -> a.getPvp() < 100)
                .sorted(Comparator.comparing(a -> a.getPvp()))
                .forEach(a -> System.out.println(a));

        System.out.println("Filtrar artículos con stock superior a un valor dado");
        int n = sc.nextInt();
        articulos.values().stream()
                
                .filter(a -> a.getExistencias() > n) //para un valor inferior, habría que usar:".filter(a-> a.getExistencias() < n)"
                .forEach(a -> System.out.println(a));

        System.out.println("Filtrar artículos cuyo PVP esté entre dos valores");
        int q = sc.nextInt();
        int w = sc.nextInt();
        articulos.values().stream()
                .filter(a -> a.getPvp() < q && a.getPvp() > w)
                .forEach(a -> System.out.println(a));

        System.out.println("Filtrar artículos cuyo ID empiece por un número concreto"); //te salen por pantalla los articulos cuyo id empieze por 4-
        int tipoProducto = sc.nextInt();
        String prefijo = tipoProducto + "4-";
        articulos.values().stream()
                .filter(a -> a.getIdArticulo().startsWith(prefijo))
                .forEach(a -> System.out.println(a));

        System.out.println("");
        ArrayList<Articulo> articulosAux = new ArrayList(articulos.values());
        String idArticulo;
        System.out.println("Articulos por ID");
        System.out.print("\nTecle el primer digito del ID del artículo:");
        idArticulo = sc.next();
        articulosAux.stream()
                .filter(a -> String.valueOf(a.getIdArticulo()).startsWith(idArticulo))
                .sorted(Comparator.comparing(Articulo::getIdArticulo))
                .forEach(System.out::println);

        //EJERCICIOS CON METODOS DEL API DE STREAMS     --->    count(); collect(); mapToInt(); .collect(Collectors); 
        long numPedidos = pedidos.stream()
                .filter(p -> p.getClientePedido().getIdCliente().equalsIgnoreCase("80580845T"))
                .count();
        System.out.println("\n" + numPedidos + "\n");
        //Las funciones tipo "count()" / "counting()" almacenan resultados en variables de tipo long 

        //CONTABILIZAR CUANTOS PEDIDOS HAY POR CLIENTE - PARA LAS AGRUPACIONES SON IDEALES
        Map<Cliente, Long> numPedidosPorCliente
                = pedidos.stream()
                        .collect(Collectors.groupingBy(Pedido::getClientePedido, Collectors.counting()));

        /*for (Cliente c::numPedidosPorCliente.keySet()){
    System.out.println(c + " - " + numPedidosPorCliente.get(c));
}*/
        System.out.println("\n");
        for (Articulo a : articulos.values()) {
            int total = 0;
            for (Pedido p : pedidos) {
                total += p.getCestaCompra().stream().filter(l -> l.getArticulo().equals(a))
                        .mapToInt(LineaPedido::getUnidades).sum();
            }
            System.out.println(a + " - " + total);
        }

        //TODOS LOS ARTICULOS VENDIDOS SIN REPETICIONES     ------>     Set no admite elementos repetidos por lo que no imprimira por pantalla más de una vez el mismo artículo vendido
        System.out.println("\n");
        Set<Articulo> articulosVendidos = pedidos.stream()
                .flatMap(p -> p.getCestaCompra().stream())
                .map(LineaPedido::getArticulo)
                .collect(Collectors.toSet());

        articulosVendidos.stream().forEach(System.out::println);

        /*
        //TOTAL DE UNIDADES VENDIDAS DE TODOS LOS ARTÍCULOS usando flatMap()
        System.out.println("\n");
        Map <Articulo, Integer> unidadesPorArticulo = 
        pedidos.stream()
                .flatMap(p -> p.getCestaCompra());
         */
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Ejercicio hecho en clase 12/02/2026">
    /*
        METODOS PARA CALCULAR LAS UNIDADES VENDIDAS DE UN ARTICULO 
        EN TODOS LOS PEDIDOS DE LA TIENDA
     */
    //VERSION CLASEICA
    private int unidadesVendidas1Clasica(Articulo a) {
        int c = 0;
        for (Pedido p : pedidos) {
            for (LineaPedido l : p.getCestaCompra()) {
                if (l.getArticulo().equals(a)) {
                    c += l.getUnidades();
                }
            }
        }
        return c;
    }

    //VERSION SEMI-CLASICA, UTILIZANDO STREAMS SOLO PARA LA cestaCompra de cada pedido de la tienda
    private int unidadesVendidas2Clasica(Articulo a) {
        int total = 0;
        for (Pedido p : pedidos) {
            total += p.getCestaCompra().stream().filter(l -> l.getArticulo().equals(a))
                    .mapToInt(LineaPedido::getUnidades).sum();
        }
        return total;
    }

    //VERSION DE PROGRAMACION FUNCIONAL CON flatMap() para aplanar los streams
    //Importante el flatMap ----- Nos "aplana" la cesta de la compra para poder convertirla en stream
    private int unidadesVendidas3Clasica(Articulo a) {
        return pedidos.stream().flatMap(p -> p.getCestaCompra().stream())//Muy importante esta primera línea. Le pedimos que nos procese todas las lineasPedido de la cestaCompra de cada Pedido de la tienda. Saltamos de cestaCompra a Pedidos. Tenemos cada pedido de las lineas de pedido de la tienda
                .filter(l -> l.getArticulo().equals(a))
                .mapToInt(LineaPedido::getUnidades).sum();
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="SOLUCION EXAMEN 5/2/2026">
    /*private void uno(){
        String[] secciones={"","PERIFERICOS","ALMACENAMIENTO","IMPRESORAS","MONITORES"};
        System.out.println("SECCION A LISTAR:");
        String sec=sc.next();
        System.out.println("ARTICULOS DE LA SECCION " + secciones[Integer.parseInt(sec)] +":");
        
        //CON Bucle FOR-EACH 
        for(Articulo a:articulos.values()){
            if (a.getIdArticulo().startsWith(sec)){
                System.out.println(a);
            }
        }
        System.out.println("");
        
        //CON STREAMS
        articulos.values().stream()
                .filter(a->a.getIdArticulo().startsWith(sec))
                .forEach(a->System.out.println(a));
        
    }
    
    //LISTADO DE TODOS LOS ARTICULOS CLASIFICADOS POR SECCIONES
    private void dos() {
        String[] secciones={"","PERIFERICOS","ALMACENAMIENTO","IMPRESORAS","MONITORES"};
        for (int i=1; i<=4;i++){
            System.out.println(secciones[i]);
            String prefijo = Integer.toString(i);
            
            
            articulos.values().stream().filter(a -> a.getIdArticulo().startsWith(prefijo)).forEach(System.out::println);
        }
    }
    
    
    //Dado un cliente mostrar sus pedidos junto con el importe de cada uno
    //y también el total gastado por el usuario en la tienda
    //validar si el dni introducido es correcto y si es cliente de la tienda
    private void tres(){
        String dni;
        do{
            System.out.println("DNI CLIENTE:");
            dni=sc.nextLine().toUpperCase();
        }while (!MetodosAux.validarDNI(dni));
        if (clientes.containsKey(dni)){
            float total=0;
            System.out.println("PEDIDOS DEL CLIENTE " + clientes.get(dni).getNombre() +":");
            for (Pedido p:pedidos){
                if (p.getClientePedido().getIdCliente().equals(dni)){
                    System.out.println(p + "\tTOTAL: " + totalPedidoExamen(p));
                    total += totalPedidoExamen(p);
                }
            }
            System.out.println("\nTOTAL GASTADO: " + total);
        }else{
            System.out.println("ESE CLIENTE NO EXISTE");
        }
    }
    
    public double totalPedidoExamen (Pedido p){
        double totalPedidoExamen=0;
        for (LineaPedido l: p.getCestaCompra()){
            totalPedidoExamen+= l.getUnidades()* l.getArticulo().getPvp();//el metodo "+="; te va acumulando el precio de los articulos
        }
        
        
                
        //ANTES: 
        //AHORA: l.getArticulo().getPvp()

        
        return totalPedidoExamen;
    }
    
    //LISTADO DE LOS ARTICULOS ORDENADOS DE MAYOR A MENOR POR UNIDADES VENDIDAS. SE APOYA EN EL METODO unidadesVendidas()
    private void cuatro() {
        System.out.println("LISTADO ARTICULOS - UNIDADES VENDIDAS:\n"); 
        articulos.values().stream()
            .sorted(Comparator.comparing(a -> unidadesVendidas((Articulo) a)).reversed())
            .forEach(a -> System.out.println("\t" + a.getDescripcion() + "\tVENDIDAS: " + unidadesVendidas(a))); 
    }

   
    private int unidadesVendidas (Articulo a){
        int c=0;
        for (Pedido p:pedidos){
            for (LineaPedido l:p.getCestaCompra()){
                if (l.getArticulo().equals(a)){
                    c+=l.getUnidades();
                }
            }
        }
        return c;
    }

    //Crear un ArrayList ClientesSIN con los clientes que no han hecho ningún pedido e imprimirlo
    private void cinco() {
        ArrayList<Cliente> clientesSIN=new ArrayList();
       
        for (Cliente c:clientes.values()){
            int cont=0;
            for (Pedido p:pedidos){
                if (p.getClientePedido().equals(c)){
                    cont++;
                    break;
                }
            }
            if (cont==0){
                clientesSIN.add(c);
            }
        }
        System.out.println("LISTADO CLIENTES SIN PEDIDOS:\n");
        for (Cliente c:clientesSIN){
            System.out.println(c);
        }
    }
     */
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="EXAMEN /2/2026">
    /*    ArrayList<Articulo> articulosAux = new ArrayList(articulos.values());
    
    
    private void uno() {
        System.out.println("");
        String idArticulo;
        idArticulo = sc.next();
        articulosAux.stream()
                .filter(a -> String.valueOf(a.getIdArticulo()).startsWith(idArticulo))
                .sorted(Comparator.comparing(Articulo::getIdArticulo))
                .forEach(a-> System.out.println(a));
    }    
    

    private void dos() {
        int perifericos = 1;        
        String familia1 = perifericos + "1-";
        int almacenamiento = 2;
        String familia2 = almacenamiento + "2";
        int impresoras = 3;
        String familia3 = impresoras + "3-";
        int monitores = 4;
        String familia4 = monitores + "4-";

                
        System.out.println("PERIFERICOS");
        articulosAux.stream()
                .filter(b-> b.getIdArticulo().startsWith(familia1, perifericos))
                .forEach(b->System.out.println(b));
        
        System.out.println("ALMACENAMIENTO");
        articulosAux.stream()
                .filter(c-> c.getIdArticulo().startsWith(familia2, almacenamiento))
                .forEach(c->System.out.println(c)); 
        
        System.out.println("IMPRESORAS");
        articulosAux.stream()
                .filter(d-> d.getIdArticulo().startsWith(familia3, impresoras))
                .forEach(d->System.out.println(d)); 
        
        System.out.println("MONITORES");
        articulosAux.stream()
                .filter(e-> e.getIdArticulo().startsWith(familia4, monitores))
                .forEach(e->System.out.println(e));    }


    public void tres() { //Pedidos de un cliente y total gastado
        
        int totalGastado = 0;
        
        for (Pedido p : pedidos) {
            if (p.getClientePedido().getIdCliente().equalsIgnoreCase(idCliente)){
                System.out.println("PEDIDOS DEL  CLIENTE " + p.getIdPedido());
            }
        }

        System.out.println("\t\t\t\tTOTAL:" + totalPedido);

    }        

    


    private static void cuatro() {//Listado de todos los articulos oredenados de mas a menos unidades vendidas
        
    }

 
    ArrayList<Cliente> clientesAux = new ArrayList(clientes.values());
    private static void cinco() { //= Listado de clientes sin pedidos
        for (Object object : col) {
            
        }
    }*/
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Ejercicio extra">
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="EJERCICIOS EN CONJUNTO DE METODOS Y API DE STREAMS BASICOS">
    private void ejercicio1(HashMap<String, Articulo> articulos1) {

        // Mostrar por pantalla todos los artículos (uno por línea) usando articulos.values().stream().forEach(...)
        System.out.println("");
        articulos.values().stream().forEach(a -> System.out.println(a));

    }

    private void ejercicio2(HashMap<String, Articulo> articulos2) {

        //Listar artículos con existencias 0 (o <=0 si lo prefieres) usando filter.
        System.out.println("");

        articulos.values().stream()
                .filter(a -> a.getExistencias() < 1)
                .forEach(a -> System.out.println(a));
    }

    private void ejercicio3(HashMap<String, Articulo> articulos3) {

        //Listar artículos con PVP < 100 ordenados por PVP ascendente (sorted(Comparator.comparing(...))).
        System.out.println("");

        articulos.values().stream()
                .filter(a -> a.getPvp() < 100)
                .sorted(Comparator.comparing(Articulo::getPvp))
                .forEach(a -> System.out.println(a));
    }

    private void ejercicio4(HashMap<String, Articulo> articulos4) {

        //Listar artículos con PVP < 100 ordenados por PVP descendente (reversed).
        System.out.println("");

        articulos.values().stream()
                .filter(a -> a.getPvp() < 100)
                .sorted(Comparator.comparing(Articulo::getPvp).reversed())
                .forEach(a -> System.out.println(a));
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void ejercicio5(HashMap<String, Articulo> articulos5) {
        //Pedir un número n y listar artículos con existencias > n (solo filter + forEach).
        System.out.println("");

        int n = sc.nextInt();

        articulos.values().stream()
                .filter(a -> a.getExistencias() > n)
                .forEach(a -> System.out.println(a));
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void ejercicio6(HashMap<String, Articulo> articulos6) {
        //Pedir min y max y listar artículos cuyo PVP esté entre ambos (incluidos).
        System.out.println("");

        int max = sc.nextInt();
        int min = sc.nextInt();

        articulos.values().stream()
                .filter(a -> a.getExistencias() > min)
                .filter(a -> a.getExistencias() < max)
                .forEach(a -> System.out.println(a));
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void ejercicio7(HashMap<String, Articulo> articulos7) {
        //Pedir un “prefijo de sección” (por ejemplo "4-") y listar artículos cuyo id empieza por ese prefijo (startsWith).
        System.out.println("");

        String sec = sc.next();

        articulos.values().stream()
                .filter(a -> a.getIdArticulo().startsWith(sec))
                .forEach(a -> System.out.println(a));
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void ejercicio8(HashMap<String, Articulo> articulos8) {
        System.out.println("");

        //Contar cuántos clientes hay en la tienda (stream sobre clientes.values() + count).
        long ejercicio8 = clientes.values().stream()
                .count();
        System.out.println("\n" + ejercicio8 + "\n");

    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void ejercicio9(HashMap<String, Articulo> articulos9) {
        System.out.println("");

//Listar los DNIs de clientes usando clientes.keySet() (sin streams) y luego repetirlo con streams sobre keySet().stream().
/*        System.out.println("DNI clientes con .keySet: ");
            for (String dni : clientes.keySet()) {
                System.out.println(dni);
            }
         */
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Con streams: ");

        clientes.values().stream()
                .forEach(c -> System.out.println(c.getIdCliente()));
    }

    /*
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
            //EJERCICIOS CON METODOS DEL API DE STREAMS     --->    count(); collect(); mapToInt(); .collect(Collectors); 
        long numPedidos= pedidos.stream()
                .filter(p -> p.getClientePedido().getIdCliente().equalsIgnoreCase("80580845T"))
                .count();
        System.out.println("\n" + numPedidos + "\n");
        //Las funciones tipo "count()" / "counting()" almacenan resultados en variables de tipo long 

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //CONTABILIZAR CUANTOS PEDIDOS HAY POR CLIENTE - PARA LAS AGRUPACIONES SON IDEALES
        Map<Cliente, Long> numPedidosPorCliente = 
                pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getClientePedido, Collectors.counting()));
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

     */
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="EJERCICIOS EN CONJUNTO DE METODOS Y API DE STREAMS MEDIO">
    private void ejercicioMedio0() {
        //Contar cuántos pedidos ha hecho un cliente concreto (filtra por p.getClientePedido().getIdCliente() y count)
        System.out.println("");

        String dni = sc.next();

        long numeroPedidosDeUnClienteConcreto
                = pedidos.stream()
                        .filter(p -> p.getClientePedido().getIdCliente().equalsIgnoreCase(dni))
                        .count();
        System.out.println("\n" + numeroPedidosDeUnClienteConcreto + "\n");

    }

    private void ejercicioMedio1() {
//Sacar el conjunto (Set) de artículos vendidos (sin repetidos) y de los que aún queden unidades disponibles
//a partir de todos los pedidos usando flatMap + map(LineaPedido::getArticulo) + toSet.
        System.out.println("");

        Set<Articulo> ejercicioMedio1
                = pedidos.stream()
                        .flatMap(p -> p.getCestaCompra().stream())
                        .map(LineaPedido::getArticulo)
                        .collect(Collectors.toSet());

    }

    private void ejercicioMedio2() {
//Sacar el conjunto (Set) de clientes que han realizado al menos un pedido 
//(a partir de pedidos.stream().map(Pedido::getClientePedido).collect(toSet())).
        System.out.println("");

    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="AlGUNOS MÉTODOS VISTOS YA PERO REALIZADOS AHORA CON STREAMS">
    //Total gastado por un Cliente
    public double totalCliente2(Cliente c) {
        return pedidos.stream().filter(p -> p.getClientePedido().equals(c))
                .mapToDouble(p -> totalPedido(p)).sum();
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Importe Total de un Pedido
    public double totalPedido2(Pedido p) {
        return p.getCestaCompra().stream()
                .mapToDouble(l -> l.getArticulo().getPvp()
                * l.getUnidades()).sum();
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /* METODO PARA CALCULAR LAS UNIDADES VENDIDAS DE UN ARTÁCULO
       EN TODOS LOS PEDIDOS DE LA TIENDA - VERSIÁN CLÁSICA
     */
    private int unidadesVendidas1(Articulo a) {
        int c = 0;
        for (Pedido p : pedidos) {
            for (LineaPedido l : p.getCestaCompra()) {
                if (l.getArticulo().equals(a)) {
                    c += l.getUnidades();
                }
            }
        }
        return c;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /* VERSIÓN SEMI-CLÁSICA - UTILIZANDO STREAMS SOLO PARA LA cestaCompra de cada Pedido 
     */
    private int unidadesVendidas2(Articulo a) {
        int total = 0;
        for (Pedido p : pedidos) {
            total += p.getCestaCompra().stream().filter(l -> l.getArticulo().equals(a))
                    .mapToInt(LineaPedido::getUnidades).sum();
        }
        return total;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /* VERSIÓN PROGRAMACIÁN FUNCIONAL CON flatMap() para aplanar los streams */
    private int unidadesVendidas3(Articulo a) {
        return pedidos.stream().flatMap(p -> p.getCestaCompra().stream())
                .filter(l -> l.getArticulo().equals(a))
                .mapToInt(LineaPedido::getUnidades).sum();
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="EJERCICIOS CON STREAMS">
    private void listadosStreams() {

        //EJEMPLOS SENCILLOS CON filter() - sorted() - forEach()
        // ARTICULOS DE MENOS DE 100€ ORDENADOS POR PRECIO DE - A +
        articulos.values().stream()
                .filter(a -> a.getPvp() < 100)
                .sorted(Comparator.comparing(Articulo::getPvp))
                .forEach(a -> System.out.println(a));

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------        
        //PEDIDOS ORDENADOS POR EL IMPORTE TOTAL DEL PEDIDO DE - A + (usamos el método auxiliar totalPedido()) 
        System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p -> totalPedido(p)))
                .forEach(p -> System.out.println(p + "- Total: " + totalPedido(p)));

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //PEDIDOS ORDENADOS POR EL IMPORTE TOTAL DEL PEDIDO DE + A - (usamos el método auxiliar totalPedido()) 
        System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p -> totalPedido((Pedido) p)).reversed())
                .forEach(p -> System.out.println(p + "- Total: " + totalPedido(p)));

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //PEDIDOS DE MÁS DE 1000€ (filter) ORDENADOS POR LA FECHA DEL PEDIDO DE - A + 
        System.out.println("\n");
        pedidos.stream().filter(p -> totalPedido(p) > 1000)
                .sorted(Comparator.comparing(Pedido::getFechaPedido))
                .forEach(p -> System.out.println(p + "- Total: " + p.getFechaPedido()));

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //EJERCICIOS CON MÉTODOS DEL API PARA REALIZAR CALCULOS count() map() mapToInt() .collect(Collectors.groupingBy) ...
        //CONTABILIZAR LOS PEDIDOS DE UN DETERMINADO CLIENTE - PODRÍA PEDIR NOMBRE O DNI POR TECLADO PERO LO HARÉ PARA UNO CONCRETO
        long numPedidos = pedidos.stream()
                .filter(p -> p.getClientePedido().getIdCliente().equalsIgnoreCase("80580845T"))
                .count();
        System.out.println("\n" + numPedidos + "\n");
        //LAS FUNCIONES TIPO count() counting() almacenan resultados en variables de tipo long 

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //CONTABILIZAR CUANTOS PEDIDOS HAY POR CLIENTE - PARA LAS AGRUPACIONES SON IDEALES LOS MAPAS PORQUE PUEDEN CONTENER 2 DATOS
        Map<Cliente, Long> numPedidosPorCliente
                = pedidos.stream()
                        .collect(Collectors.groupingBy(Pedido::getClientePedido, Collectors.counting()));

        for (Cliente c : numPedidosPorCliente.keySet()) {
            System.out.println(c + " - " + numPedidosPorCliente.get(c));
        }

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // TOTAL DE UNIDADES VENDIDAS DE UN ARTICULO EN TODOS LOS PEDIDOS. PODEMOS APLICARLO AL 
        // MÉTODO UNIDADES VENDIDAS QUE HABÍA QUE HACER EN EL EJERCICIO 4 DE LA ÚLTIMA PRUEBA
        System.out.println("\n");
        for (Articulo a : articulos.values()) {
            int total = 0;
            for (Pedido p : pedidos) {
                total += p.getCestaCompra().stream().filter(l -> l.getArticulo().equals(a))
                        .mapToInt(LineaPedido::getUnidades).sum();
            }
            System.out.println(a + " - " + total);
        }

        /**
         * **************************************************************************************
         * EJERCICIOS CON flatMap() para colecciones anidadas, nuestro caso pues
         * pedidos es un ArrayList de <Pedido> y dentro de cada Pedido hay una
         * cestaCompra, que es un ArrayList de <Lineapedido>
         *
         ****************************************************************************************
         */
        //USUARIOS QUE HAN COMPRADO UN ARTÍCULO DETERMINADO incluyendo CUANTAS UNIDADES HAN COMPRADO 
        //probamos con el artículo articulos.get("4-22") 
        System.out.println("\n");
        for (Cliente c : clientes.values()) {
            int unidades = pedidos.stream().filter(p -> p.getClientePedido().equals(c))
                    .flatMap(p -> p.getCestaCompra().stream()).filter(l -> l.getArticulo().equals(articulos.get("4-22")))
                    .mapToInt(LineaPedido::getUnidades).sum();

            System.out.println(c.getNombre() + ": " + unidades + " de " + articulos.get("4-22").getDescripcion());
        }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //TODOS LOS ARTICULOS VENDIDOS, LOS ALMACENAMOS EN UN SET PARA EVITAR REPETICIONES
        System.out.println("\n");

        Set<Articulo> articulosVendidos
                = pedidos.stream()
                        .flatMap(p -> p.getCestaCompra().stream())
                        .map(LineaPedido::getArticulo)
                        .collect(Collectors.toSet());

        articulosVendidos.stream().forEach(a -> System.out.println(a));

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------        
        //TOTAL DE UNIDADES VENDIDAS DE TODOS LOS ARTÍCULOS usando flatMap()
        System.out.println("\n");
        Map<Articulo, Integer> unidadesPorArticulo
                = pedidos.stream()
                        .flatMap(p -> p.getCestaCompra().stream())
                        .collect(Collectors.groupingBy(LineaPedido::getArticulo, Collectors.summingInt(LineaPedido::getUnidades)
                        ));
        for (Articulo a : unidadesPorArticulo.keySet()) {
            System.out.println(a.getDescripcion() + " - " + unidadesPorArticulo.get(a));
        }
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="EXAMEN 20/02/2026">
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void uno() {
//        ArrayList<Pedido> pedidosAux = new ArrayList(pedidos);

        System.out.println("\n");

        pedidos.stream().filter(p -> totalPedido(p) > 1000)
                .sorted(Comparator.comparing(Pedido::getFechaPedido))
                .forEach(p -> System.out.println(p + "- Total: " + totalPedido(p)));
    }

    private void dos() {

        System.out.println("\n");

        String[] secciones = {"", "PERIFERICOS", "ALMACENAMIENTO", "IMPRESORAS", "MONITORES"};
        System.out.println("SECCION A LISTAR:");
        String sec = sc.next();
        System.out.println("ARTICULOS DE LA SECCION " + secciones[Integer.parseInt(sec)] + ":");

        articulos.values().stream()
                .filter(a -> a.getIdArticulo().startsWith(sec) && a.getExistencias() > 0)
                .sorted(Comparator.comparing(Articulo::getPvp))
                .forEach(a -> System.out.println(a));

    }

    private void tres() {
        ArrayList<Articulo> articulosSIN = new ArrayList();

        System.out.println("\n");
        for (Articulo a : articulos.values()) {
            int cont = 0;
            for (Pedido p : pedidos) {
                if (p.getClientePedido().equals(a)) {
                    cont++;
                    break;
                }
            }
            if (cont == 0) {
                articulosSIN.add(a);
            }
        }

        for (Articulo a : articulosSIN) {
            System.out.println(a);
        }
    }

    private void cuatro() {

        System.out.println("\n");
        LocalDate hoy = LocalDate.now();
        LocalDate fechaLimite = LocalDate.now().minusDays(5);
        double total = pedidos.stream()
                .filter(p -> p.getFechaPedido().isAfter(fechaLimite) || p.getFechaPedido().equals(fechaLimite))
                .mapToDouble(p -> totalPedido(p))
                .sum();

        System.out.println("Total facturado entre " + fechaLimite + " y " + hoy + " : " + total + "€");

    }

    private void cinco() {

    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="EXAMEN 20/02/2026 RESUELTO">
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /*    
     //1. LISTADO DE LOS CLIENTES ORDENADOS DE Mayor a Menor por su GASTO
    private void uno(){
        clientes.values().stream()
                .sorted(Comparator.comparing(c-> totalCliente((Cliente) c)).reversed())
                .forEach(c-> System.out.println(c + "\t\t Total GASTADO: " +  totalCliente(c))); 
    }
    
    //2. LISTADO DE LOS ARTICULOS DE UNA SECCIÓN CON existencias >0 y ordenados por Pvp
    private void dos() {
        System.out.println("SECCION A LISTAR:");
        String sec=sc.next();
       
        articulos.values().stream()
                .filter(a->a.getIdArticulo().startsWith(sec)&&a.getExistencias()>0)
                .sorted(Comparator.comparing(Articulo::getPvp).reversed())
                .forEach(a->System.out.println(a));
    }
    
    //3. CREAR Y LISTAR NUEVA COLECCION articulosNoVendidos
    private void tres(){
        Set<Articulo> vendidos
                = pedidos.stream()
                        .flatMap(p -> p.getCestaCompra().stream())
                        .map(LineaPedido::getArticulo)
                        .collect(Collectors.toSet());

        List<Articulo> noVendidos
                = articulos.values().stream()
                        .filter(a -> !vendidos.contains(a))
                        .toList();
        System.out.println("\n");
        noVendidos.stream().forEach(a -> System.out.println(a));
    }
    
   
    //4. TOTAL FACTURADO EN LA TIENDA EN LOS ÚLTIMOS 5 DÍAS
    private void cuatro() {
    
        LocalDate fecha1 = LocalDate.now().minusDays(5);
        double total5dias = pedidos.stream()
            .filter(p -> p.getFechaPedido().isAfter(fecha1))
               .flatMap(p -> p.getCestaCompra().stream())
                .mapToDouble(lp -> lp.getArticulo().getPvp() * lp.getUnidades()).sum();
        System.out.println("\nTotal facturado los ultimos 5 dias: " + total5dias);
    }   
   
    //5. IMPORTE MEDIO POR PEDIDO EN NUESTRA TIENDA
    private void cinco() {
        double importePedidos = pedidos.stream()
            .flatMap(p -> p.getCestaCompra().stream())
              .mapToDouble(lp -> lp.getArticulo().getPvp() * lp.getUnidades()).sum();
        
        System.out.println("\nImporte Medio Pedidos TIENDA: " + importePedidos/pedidos.size());  
    }
     */
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="STREAMS, LISTAS, TREEMAPS, HASHMAPS, ETC.  -  ULTIMOS EJERCICIOS">
    private void pedidosOrdenadosPorFecha() {
//Dame los pedidos ordenados por fecha y envíalos a una nueva collección        
        List<Pedido> pedidosOrdenadosPorFecha
                = pedidos.stream()
                        .sorted(Comparator.comparing(Pedido::getFechaPedido).reversed())
                        .collect(Collectors.toList());

        pedidos.stream().forEach(p -> System.out.println(p.getIdPedido() + " - " + p.getFechaPedido()));
        System.out.println("\n");
        pedidosOrdenadosPorFecha.stream().forEach(p -> System.out.println(p.getIdPedido() + " - " + p.getFechaPedido()));

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("\n");
        //Calcula el total de cada pedido, con eso voy a un mapa con el valor del total como key, y con el pedido en sí como value

        /*        List <Pedido> pedidosOrdenadosPorFecha = 
        pedidos.stream()
                .sorted(Comparator.comparing(Pedido::getFechaPedido).reversed())
                .collect(Collectors.toMap(totalPedido(p), Pedido));
         */
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        /*
        HashMap <Double, Pedido> pedidosConTotales = new HashMap();
        for (Pedido p : pedidos) {
            pedidosConTotales.put(totalPedido(p), p);
        }
        
        for (Double total : pedidosConTotales.keySet()) {
            System.out.println(pedidosConTotales.get(total) + " - " + total);
        }
         */
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //CREAR UN MAPA ORDENADO (TreeMap) CON LOS PEDIDOS (values) Y EL TOTAL DE CADA PEDIDO (key) ORDENADOS POR FECHA
        TreeMap<Double, Pedido> pedidosConTotales = new TreeMap(); //Los TreeMaps te ordenan por sí solos por las claves que tu le indiques, en este caso por el Double 
        for (Pedido p : pedidos) {
            pedidosConTotales.put(totalPedido(p), p);
        }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //CREAR UN MAPA ORDENADO (TreeMap) CON LOS CLIENTES (values) Y EL TOTAL GASTADO POR CADA CLIENTE
        TreeMap<Double, Cliente> ventasPorCliente = new TreeMap();
        for (Cliente c : clientes.values()) {
            ventasPorCliente.put(totalCliente(c), c);
        }

        //COMPROBACIÓN DE  AMBOS RESULTADOS
        for (Double total : pedidosConTotales.descendingKeySet()) {                 //El "descendingKeySet" te ordena tal y como explico arriba, pero a la inversa
            System.out.println(pedidosConTotales.get(total).getIdPedido() + " - " + total);
        }

        for (Double totCli : ventasPorCliente.keySet()) {
            System.out.println(ventasPorCliente.get(totCli).getNombre() + " - " + totCli);
        }

        //Crear 4 listas de cada seccion (Monitores, almacenamiento, etc), en la que cada una incluya solamente sus respectivos productos; en versión clásica deben utilizarse ArrayList
        ArrayList<Articulo> perifericos1 = new ArrayList();
        ArrayList<Articulo> almacenamiento1 = new ArrayList();
        ArrayList<Articulo> impresoras1 = new ArrayList();
        ArrayList<Articulo> monitores1 = new ArrayList();

        for (Articulo a : articulos.values()) {
            switch (a.getIdArticulo().charAt(0)) {
                case '1':
                    perifericos1.add(a);
                    break;

                case '2':
                    almacenamiento1.add(a);
                    break;

                case '3':
                    impresoras1.add(a);
                    break;

                case '4':
                    monitores1.add(a);
                    break;

            }
        }

        //Crear 4 listas de cada seccion (Monitores, almacenamiento, etc), en la que cada una incluya solamente sus respectivos productos; en versión moderna deben utilizarse List
        List<Articulo> perifericos2, almacenamiento2, impresoras2, monitores2;

        perifericos2 = articulos.values().stream().filter(p -> p.getIdArticulo().startsWith("1"))
                .collect(Collectors.toList());

        almacenamiento2 = articulos.values().stream().filter(p -> p.getIdArticulo().startsWith("2"))
                .collect(Collectors.toList());

        impresoras2 = articulos.values().stream().filter(p -> p.getIdArticulo().startsWith("3"))
                .collect(Collectors.toList());

        monitores2 = articulos.values().stream().filter(p -> p.getIdArticulo().startsWith("4"))
                .collect(Collectors.toList());

        //COMPROBACIÓN DE RESULTADOS
        System.out.println("\n" + perifericos1);
        System.out.println("\n" + almacenamiento1);
        System.out.println("\n" + impresoras1);
        System.out.println("\n" + monitores1);

        System.out.println("\n");

        System.out.println("\n" + perifericos2);
        System.out.println("\n" + almacenamiento2);
        System.out.println("\n" + impresoras2);
        System.out.println("\n" + monitores2);
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //BORRA ARTICULOS DE LA SECCION IMPRESORAS
        articulos.values().removeIf(a -> a.getIdArticulo().startsWith("3"));
        System.out.println("\n");
        articulos.values().forEach(a -> System.out.println(a));

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //LAS COLECCIONES DE TIPO LIST NO ADMITEN removeIf
        //BORRA LOS PEDIDOS DE MAS DE 3 DÍAS DE ANTIGÜEDAD
        List<Pedido> pedidosAntiguos = pedidos.stream()
                .filter(p -> p.getFechaPedido().isBefore(LocalDate.now().minusDays(3)))
                .collect(Collectors.toList());
        pedidos.removeAll(pedidosAntiguos);
        System.out.println(pedidos);

    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="LECTURA DE ARCHIVOS">
    
    
    
    private void leeClientes() {
    
    HashMap<String,Cliente> clientesAux = new HashMap();
    try (Scanner scClientes = new Scanner(new File("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/clientes.txt"))){
        while (scClientes.hasNextLine()){
            String [] atributos = scClientes.nextLine().split("[,]");
            Cliente c = new Cliente (atributos[0],atributos[1],atributos[2],atributos[3]);
            clientesAux.put(atributos[0], c);
        }    
    }   catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nListado de Clientes del nuevo HashMap clientesAux\n");
        clientesAux.values().forEach(System.out::println);
    }
        
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
/*
    //          COMO SE LEEN ESOS ARCHIVOS? ------> 
    
    private void leeArticulosPorSeccion (){    
        try (BufferedReader brPerifericos = new BufferedReader(new FileReader("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Perifericos.csv")); 
            BufferedReader brAlmacenamiento = new BufferedReader(new FileReader("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Almacenamiento.csv")) ;
            BufferedReader brImpresoras = new BufferedReader(new FileReader("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Impresoras.csv")) ;
            BufferedReader brMonitores = new BufferedReader(new FileReader("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Monitores.csv"))) {
            String cadenaPerifericos = brPerifericos.readLine();
            while (cadenaPerifericos != null) {
                System.out.println(cadenaPerifericos);
                cadenaPerifericos = brPerifericos.readLine();
            }
            System.out.println("\n\n");
            String cadenaAlmacenamiento = brAlmacenamiento.readLine();
            while (cadenaAlmacenamiento != null) {
                System.out.println(cadenaAlmacenamiento);
                cadenaAlmacenamiento = brAlmacenamiento.readLine();
            }
            System.out.println("\n\n");
            String cadenaImpresoras = brImpresoras.readLine();
            while (cadenaImpresoras != null) {
                System.out.println(cadenaImpresoras);
                cadenaImpresoras = brImpresoras.readLine();
            }
            System.out.println("\n\n");
            String cadenaMonitores = brMonitores.readLine();
            while (cadenaMonitores != null) {
                System.out.println(cadenaMonitores);
                cadenaMonitores = brMonitores.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
*/

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void leeArticulosPorSeccion() {

        //SIMPLEMENTE LEER LAS LÍNEAS DEL ARCHIVO clientes.txt Y MOSTRARLAS POR PANTALLA
        System.out.println("\nListado de Clientes directamente desde los articulos por secciones\n");
        try (Scanner scPerifericos = new Scanner(new File("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Perifericos.csv"));
        Scanner scAlmacenamiento = new Scanner(new File("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Almacenamiento.csv"));
        Scanner scImpresoras = new Scanner(new File("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Impresoras.csv"));
        Scanner scMonitores = new Scanner(new File("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Monitores.csv"))) 
        
        {
            while (scPerifericos.hasNextLine()) {
                System.out.println(scPerifericos.nextLine());
            }
            System.out.println("\n\n");
            while (scAlmacenamiento.hasNextLine()) {
                System.out.println(scAlmacenamiento.nextLine());
            }
            System.out.println("\n\n");
            while (scImpresoras.hasNextLine()) {
                System.out.println(scImpresoras.nextLine());
            }
            System.out.println("\n\n");
            while (scMonitores.hasNextLine()) {
                System.out.println(scMonitores.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ESCRITURA DE ARCHIVOS">
    
    
    private void guardaClientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("E:/1dawd/Curso/PROGRAMACIÓN/NetBeansProjects/Tienda1/clientes.txt"))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
                bw.newLine();
            }System.out.println("Archivo creado correctamente");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("E:/1dawd/Curso/PROGRAMACIÓN/NetBeansProjects/Tienda1/clientes.csv"))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
                bw.newLine();
            }System.out.println("Archivo creado correctamente");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void guardaArticulosPorSeccion() {
        try (BufferedWriter bwPerifericos = new BufferedWriter(new FileWriter("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Perifericos.csv"));
            BufferedWriter bwAlmacenamiento = new BufferedWriter(new FileWriter("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Almacenamiento.csv"));
            BufferedWriter bwImpresoras = new BufferedWriter(new FileWriter("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Impresoras.csv"));
            BufferedWriter bwMonitores = new BufferedWriter(new FileWriter("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Monitores.csv"))) {
            
            for (Articulo a : articulos.values()) {
                switch (a.getIdArticulo().charAt(0)) {
                    case '1':
                        bwPerifericos.write(a.getIdArticulo() + "," + a.getDescripcion() + "," + a.getExistencias() + "," + a.getPvp());
                        bwPerifericos.newLine();
                        break;

                    case '2':
                        bwAlmacenamiento.write(a.getIdArticulo() + "," + a.getDescripcion() + "," + a.getExistencias() + "," + a.getPvp());
                        bwAlmacenamiento.newLine();
                        break;

                    case '3':
                        bwImpresoras.write(a.getIdArticulo() + "," + a.getDescripcion() + "," + a.getExistencias() + "," + a.getPvp());
                        bwImpresoras.newLine();
                        break;

                    case '4':
                        bwMonitores.write(a.getIdArticulo() + "," + a.getDescripcion() + "," + a.getExistencias() + "," + a.getPvp());
                        bwMonitores.newLine();
                        break;

                }
            }System.out.println("Archivos creados correctamente");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
            File f = new File ("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Perifericos.csv");
            f.delete();
            File f1 = new File ("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Almacenamiento.csv");
            f1.delete();
            File f2 = new File ("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Impresoras.csv");
            f2.delete();
            File f3 = new File ("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/Monitores.csv");
            f3.delete();
            
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("E:/1dawd/Curso/Programacion/NetBeansProjects/Tienda/clientes.csv"))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
                bw.newLine();
            }System.out.println("Archivo creado correctamente");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void guardaPedido() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("E:/1dawd/Curso/PROGRAMACIÓN/NetBeansProjects/Tienda1/pedidos.txt"))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
                bw.newLine();
            }System.out.println("Archivo creado correctamente");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("E:/1dawd/Curso/PROGRAMACIÓN/NetBeansProjects/Tienda1/pedidos.csv"))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
                bw.newLine();
            }System.out.println("Archivo creado correctamente");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    private void guardaArticulo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("E:/1dawd/Curso/PROGRAMACIÓN/NetBeansProjects/Tienda1/articulo.txt"))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
                bw.newLine();
            }System.out.println("Archivo creado correctamente");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("E:/1dawd/Curso/PROGRAMACIÓN/NetBeansProjects/Tienda1/articulo.csv"))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
                bw.newLine();
            }System.out.println("Archivo creado correctamente");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    private void guardaArticulosPorSeccion1(){
        //GUARDAMOS LOS 
        try (BufferedWriter bwPerifericos = new BufferedWriter(new FileWriter("E:/perifericos.csv"));
             BufferedWriter bwAlmacenamiento = new BufferedWriter(new FileWriter("E:/almacenamiento.csv"));
             BufferedWriter bwImpresoras = new BufferedWriter(new FileWriter("E:/impresoras.csv"));
             BufferedWriter bwMonitores = new BufferedWriter(new FileWriter("E:/monitores.csv")))
        {
            for (Articulo a : articulos.values()) {
                switch (a.getIdArticulo().charAt(0)) {
                case '1':
                    bwPerifericos.write(a.getIdArticulo()+","+a.getDescripcion()+","+a.getExistencias()+","+a.getPvp()+"\n");
                    break;
                case '2':
                    bwAlmacenamiento.write(a.getIdArticulo()+","+a.getDescripcion()+","+a.getExistencias()+","+a.getPvp()+"\n");
                    break;
                case '3':
                    bwImpresoras.write(a.getIdArticulo()+","+a.getDescripcion()+","+a.getExistencias()+","+a.getPvp()+"\n");
                    break;
                case '4':
                    bwMonitores.write(a.getIdArticulo()+","+a.getDescripcion()+","+a.getExistencias()+","+a.getPvp()+"\n");
                    break;
                }
            }
            System.out.println("Archivos creados correctamente");                              
        } catch (IOException e) {
            System.out.println("No se han podido crear los archivos");
            File f=new File("D:/perifericos.csv");
            f.delete();
            f=new File("D:/almacenamiento.csv");
            f.delete();
            f=new File("D:/impresoras.csv");
            f.delete();
            f=new File("D:/monitores.csv");
            f.delete();
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void leeArticulosPorSeccionNuevo() {
        
         
        
    }

//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="COMIENZO DE ARCHIVOS BINARIOS (.dat)">
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="EJERCICIOS CLASE PARTICULAR">
    /*
        public void listaArticulosPorPrecio() {
        ArrayList<Articulo> aux = new ArrayList<Articulo>();
        Scanner sc = new Scanner(System.in);
        System.out.println("SECCION A LISTAR:");
        int seccion = sc.nextInt();
        String seccionStr = "";
        switch (seccion) {
            case 1:
                seccionStr = "PERIFERICOS";
                break;
            case 2:
                seccionStr = "ALMACENAMIENTO";
                break;
            case 3:
                seccionStr = "IMPRESORAS";
                break;
            case 4:
                seccionStr = "MONITORES";
                break;        
        }
    
        System.out.println("ARTICULOS DE LA SECCION " + seccionStr + ":");
        for (Map.Entry<String, Articulo> a : articulos.entrySet()) {
            Articulo art = a.getValue();
            if (art.getIdArticulo().startsWith(seccion + "")) {
                aux.add(art);
            }
        }
        aux.sort(Comparator.comparingDouble(Articulo::getPvp).reversed());
        for(Articulo art : aux) {
            System.out.println(art.toString());
        }

    }

    public void listarPedidosPorId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ID ARTICULO A COMPROBAR:");
        String idArticulo = sc.nextLine();
        Articulo art = articulos.get(idArticulo);
        if (art != null) {
            String artName = art.getDescripcion();
            System.out.println("Pedidos en los que se ha vendido MAS de 1 unidad de " + artName);
            System.out.println("PEDIDO            \tFECHA     \tUNIDADES");
            for (Pedido p : pedidos) {
                for (LineaPedido lp : p.getCestaCompra()) {
                    if (lp.getArticulo().equals(idArticulo) && lp.getUnidades()> 1) {
                        System.out.println(p.getIdPedido() + "\t" + p.getFechaPedido()+ "\t" + lp.getUnidades());
                    }
                }
            }
        }
    }

        public void getArticulosVendidosPorSeccion() {
            System.out.println("Total de articulos vendidos por SECCION:");
            System.out.println("----------------------------------------");
            int contPer = 0;
            int contAlm = 0;
            int contImp = 0;
            int contMon = 0;
            for (Pedido p : pedidos) {
                for (LineaPedido lp : p.getCestaCompra()) {
                    if (lp.getIdArticulo().startsWith("1")) {
                        contPer++;
                    }
                    if (lp.getIdArticulo().startsWith("2")) {
                        contAlm++;
                    }
                    if (lp.getIdArticulo().startsWith("3")) {
                        contImp++;
                    }
                    if (lp.getIdArticulo().startsWith("4")) {
                        contMon++;
                    }
                }
            }
            System.out.println("PERIFERICOS: " + contPer);
            System.out.println("ALMACENAMIENTO: " + contAlm);
            System.out.println("IMPRESORAS: " + contImp);
            System.out.println("MONITORES: " + contMon);
        }

    

    public void getImportesPorSeccion() {
        System.out.println("Total total vendido por SECCION:");
        System.out.println("--------------------------------");
        double contPer = 0.0;
        double contAlm = 0.0;
        double contImp = 0.0;
        double contMon = 0.0;
        for (Pedido p : pedidos) {
            for (LineaPedido lp : p.getCestaCompra()) {
                Articulo art = articulos.get(lp.getArticulo());
                if (lp.getArticulo().startsWith("1")) {
                    contPer += art.getPvp()* lp.getUnidades();
                }
                if (lp.getArticulo().startsWith("2")) {
                    contAlm += art.getPvp()* lp.getUnidades();
                }
                if (lp.getArticulo().startsWith("3")) {
                    contImp += art.getPvp()* lp.getUnidades();
                }
                if (lp.getArticulo().startsWith("4")) {
                    contMon += art.getPvp()* lp.getUnidades();
                }
            }
        }
        System.out.println("PERIFERICOS: " + contPer);
        System.out.println("ALMACENAMIENTO: " + contAlm);
        System.out.println("IMPRESORAS: " + contImp);
        System.out.println("MONITORES: " + contMon);
    }
    
    
    
    
    
    //Obtén los clientes que tengan un artículo con el nombre 'Bola', y con una fecha de pedido de entre hoy y hace 15 días
    
    public ArrayList<Cliente> getClientesFiltrados (String input, String output){ 
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        //Leer los pedidos desde un fichero input en el que están serializados en un ArrayList de Pedidos
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(input))) {
            
            ArrayList <Pedido> pedidos = (ArrayList<Pedido>)ois.readObject();
            for (Pedido p : pedidos) {
                for (LineaPedido l : p.getCestaCompra()) {
                    if (l.getArticulo().getDescripcion().equals("Bola")){
                        LocalDate hoy = LocalDate.now();
                        LocalDate hace15dias = hoy.minusDays(15);
                        if (p.getFechaPedido().isAfter(hace15dias)){
                            clientes.add(p.getClientePedido());
                            bw.write(p.getClientePedido().getNombre() + "," + l.getArticulo().getIdArticulo()
                                                                      + "," + p.getFechaPedido().toString());
                        }
                    }
                }
            }
            clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getEdad, 
                Comparator.reverseOrder())); //al estar el reverse dentro del segundo paréntesis solo ordena del revés la edad
//(ten en cuenta que en el caso de esta tienda, los clientes no tienen edad)
        } catch (IOException ex) {
            System.err.println();
        } catch (ClassNotFoundException ex) {
            System.err.println();
        }
        
        return clientes;
    }
    
    //</editor-fold>
    */
}
 