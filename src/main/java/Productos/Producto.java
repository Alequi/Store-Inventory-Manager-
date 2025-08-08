package Productos;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * La clase abstracta Productos.Producto  modela un producto con su nombre, precio,tipo,stock, getters y setters
 *
 * @author Alejandro Quivera - frachaqui@alu.edu.gva.es
 * @version 1.0
 *
 */

@Entity
@NamedQuery(name = "Producto.buscarTodos", query = "SELECT p FROM Producto p")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Producto implements Serializable {
    /**
     * ID que identifica de forma única a le Entidad
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Auto generado
    private int id;
    /**
     * Nombre del producto
     */
    private String nombre;

    /**
     * Tipo de producto. Electrico o Ropa
     */
    private String tipo;

    /**
     * Precio del producto
     */
    private double precio;

    /**
     * Cantidad disponible del producto
     */
    private int stock;

    /**
     * Constructor vacio de la clase
     */
    public Producto() {}

    /**
     * Constructor parametrizado
     * @param nombre Nombre del producto
     * @param tipo Tipo del Productos.Producto
     * @param precio Precio del Productos.Producto
     * @param stock Cantidad disponible del producto
     */
    public Producto(String nombre, String tipo, double precio, int stock) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Método que devuelve el ID del producto
     * @return ID del producto
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo que devuelve el nombre del producto
     * @return Nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre
     * @param nombre Nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que devuelve el tipo del producto
     * @return Tipo de Productos.Producto
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método que establece el tipo del producto
     * @param tipo Tipo de producto
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que devuelve el precio del producto
     * @return Precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Método que estrablece un precio
     * @param precio Precio del producto
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método que devuelve la cantidad disponible de producto
     * @return Cantidad disponible del producto
     */
    public int getStock() {
        return stock;
    }

    /**
     *  Método que establece la cantidad de producto disponible.
     *  Si es 0 lo actualiza al valor dado, en caso contrario se lo suma a la cantidad actual disponible
     * @param stock Cantidad disponible del producto
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Método abstracto para mostrar información
     */
    abstract void mostrarInformacion();




}
