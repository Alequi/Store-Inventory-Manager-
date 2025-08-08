package Inventario;

import Productos.Electronico;
import Productos.Producto;
import Productos.Ropa;
import jakarta.persistence.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Aplicación para la gestión de inventario de una tienda con control de Stock
 * @author Alejandro Quivera - frachaqui@alu.edu.gva.es
 * @version 1.0
 */
public class GestionInventario {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;


    public static int leerEnteroPositivo(String mensaje, Scanner sc) {
        int valor = -1;

        do {
            System.out.print(mensaje);
            try {
                valor = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                if (valor < 0) {
                    System.out.println("❌ El valor no puede ser negativo.");
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Introduzca un número entero válido.");
                sc.nextLine(); // limpiar el valor incorrecto
                valor = -1; // forzar que se repita
            }
        } while (valor < 0);

        return valor;
    }


    // FUNCIONALIDAD PARA AÑADIR PRODUCTOS
    private static void CrearProducto() {

        Scanner sc = new Scanner(System.in);

        String nombre;
        String tipo;
        double precio;
        int stock;
        Producto p;

        System.out.println("\nALTA DE NUEVO PRODUCTO");
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Tipo (Electronico / Ropa): ");
        tipo = sc.nextLine().trim().toLowerCase();
        System.out.print("Precio: ");
        String precioTexto = sc.nextLine().replace(",", "."); //SE PIDE UN STRING PARA MAYOR ROBUSTIDAD Y LUEGO SE CONVIERTE A DOUBLE
        precio = Double.parseDouble(precioTexto);

        //BUCLE PARA ASEGURARNOS QUE EL STOCK INTRODUCIDO NO SEA NEGATIVO
        stock = leerEnteroPositivo("Stock: ", sc);

        //USO DE SWITCH PARA CREAR UNA OBJETO ELECTRONICO O ROPA SEGUN ELIJA EL USUARIO
        switch (tipo) {
            case "electronico":
                System.out.print("Marca: ");
                String marca = sc.nextLine();
                System.out.print("Garantia (EN MESES): ");
                int garantia = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                p = new Electronico(nombre, tipo, precio, stock, marca, garantia);
                break;

            case "ropa":
                System.out.print("Talla (S/M/L/XL): ");
                String talla = sc.nextLine();
                System.out.print("Color: ");
                String color = sc.nextLine().trim().toLowerCase();
                System.out.print("Material: ");
                String material = sc.nextLine().trim().toLowerCase();

                p = new Ropa(nombre, tipo, precio, stock, talla, color, material);
                break;

            default:
                System.out.println("Tipo no válido. Solo se admite 'Electronico' o 'Ropa'.");
                return;
        }
        tx.begin();
        em.persist(p);
        tx.commit();
        System.out.println("Producto creado Correctamente.");
    }

    //FUNCIONALIDAD PARA VENTA PRODUCTOS (CON ACTUALIZACION DE STOCK)
    private static void VenderProducto() {
        Scanner sc = new Scanner(System.in);

        int id;

        System.out.print("\n === VENTA DE  PRODUCTO ===");
        System.out.print("\nID: ");

        id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Producto p = em.find(Producto.class, id); //BUSQUEDA DE PRODUCTO POR ID

        int unidades;

        //SI NO SE ENCUENTRA NINGUN PRODUCTO CON UN ID ARROJA UNA ADVERTENCIA. CASO CONTRARIO, SE VENDE Y ACTUALIZA STOCK
        if (p == null) {
            System.out.println("No se encontro el producto.");
            return;
        } else {
            System.out.println("Producto encontrado: " + p.getNombre() +  " | " + " Stock disponible: " + p.getStock());
            System.out.println("¿Cuantas unidades se van a vender?");
            unidades = sc.nextInt();
            sc.nextLine(); // limpiar buffer
        }
        //CONTROL DE EXISTENCIAS.
         if (unidades <= 0) {
         System.out.println(" Cantidad no válida.");
         return;
         }

         if (unidades > p.getStock()) {
         System.out.println(" No hay suficiente stock.");
         return;
         }

        tx.begin();
        p.setStock(p.getStock() - unidades);
        tx.commit();

        System.out.println("Venta Efectuada Correctamente. Quedan " + p.getStock() + " unidades.");

    }
    //FUNCIONALIDAD PARA REPONER PRODUCTOS Y ACTUALIZAR STOCK
    private static void ReponerProducto() {
        Scanner sc = new Scanner(System.in);

        int id;

        System.out.print("\n === REPOSICIÓN DE PRODUCTO ===");
        System.out.print("\nID: ");
        id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Producto p = em.find(Producto.class, id); //BUSQUEDA DE PRODUCTO POR ID
        int unidades;

        // ALTERNATIVAS DISPONIBLES EN CASO DE ENCONTRAR O NO EL PRODUCTO EN LA BBDD
        if (p == null) {
            System.out.println("No se encontro el producto.");
            return;
        } else {
            System.out.println("Producto encontrado: " + p.getNombre() + " | " + " Stock disponible: " + p.getStock());
            System.out.println("¿Cuantas unidades quieres reponer?");
            unidades = sc.nextInt();
            sc.nextLine(); // limpiar buffer
        }
        tx.begin();
        p.setStock(p.getStock() + unidades);
        tx.commit();

        System.out.println("Reposición correcta. Stock Actual " + p.getStock() + " unidades.");

    }

    //FUNCIONALIDAD PARA VER EL STOCK DE INVETARIO Y SU PRECIO TOTAL
    private static void MostrarInventario() {
        System.out.println("\n=== INVENTARIO GENERAL ===");

        TypedQuery<Producto> query = em.createNamedQuery("Producto.buscarTodos", Producto.class);
        List<Producto> productos = query.getResultList();//LOS TRAEMOS TODOS Y LOS GUARDAMOS EN UNA LISTA
        double valorTotal = 0;

        //BUCLE FOR EACH PARA RECORRER TODA LA LISTA Y OBTENER EL INVENTARIO, VALOOR TOTAL POR PRODUCTO Y VALOR DEL INVETARIO
        for (Producto p : productos) {
            double subtotal = p.getStock() * p.getPrecio(); //VALOR DEL INVENTARIO DE CADA PRODUCTO
            valorTotal += subtotal; //VALOR TOTAL DEL INVENTARIO

            System.out.printf("ID: %d |  Nombre: %s | Stock: %d | Precio: %.2f € | Subtotal: %.2f €\n",
                    p.getId(), p.getNombre(), p.getStock(), p.getPrecio(), subtotal);

        }

        System.out.printf("\n Valor total del inventario: %.2f €\n", valorTotal);
    }


    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        int opcion;
        System.out.println("\n======= APP GESTIÓN DE INVENTARIO =======");
        do {
            System.out.println("\n=== MENÚ ===");
            System.out.println("1. Añadir producto al inventario");
            System.out.println("2. Vender producto");
            System.out.println("3. Reponer producto");
            System.out.println("4. Mostrar Inventario y valor total");
            System.out.println("5. Guardar y Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> CrearProducto();
                case 2 -> VenderProducto();
                case 3 -> ReponerProducto();
                case 4 -> MostrarInventario();
                case 5 -> {
                    System.out.println(" Guardando cambios...");
                    em.close();
                    emf.close();
                    System.out.println(" Aplicación cerrada correctamente.");
                }
                default -> System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);
    }
}
