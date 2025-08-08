package Productos;

import jakarta.persistence.Entity;

/**
 * Clase que modela un objeto de tipo ROPA y que hereda de la clase padre Producto
 */

@Entity
public class Ropa extends Producto {
    /**
     * Talla del Producto (S,M,L,XL)
     */
    private String talla;

    /**
     * Color de la prenda de ropa
     */
    private String color;

    /**
     * Material de la prenda de ropa
     */
    private String material;

    /**
     * Constructor vacio de la clase
     */
    public Ropa() {}

    /**
     * Constructor Parametrizado de la clase
     * @param nombre Nombre del producto
     * @param tipo Tipo de Producto
     * @param precio Precio del Producto
     * @param stock Cantidad disponible del producto
     * @param talla Talla del Producto
     * @param color Color del Producto
     * @param material Material de elaboración del Producto
     */

    public Ropa(String nombre, String tipo, double precio, int stock, String talla, String color, String material) {
        super(nombre, "Ropa", precio, stock);
        this.talla = talla;
        this.color = color;
        this.material = material;
    }

    /**
     * Método que deuvelve la talla
     * @return Talla del producto
     */
    public String getTalla() {
        return talla;
    }

    /**
     * Metodo que establece la talla
     * @param talla Talla del Producto
     */
    public void setTalla(String talla) {
        this.talla = talla;
    }

    /**
     * Método que devuelve el color del producto
     * @return Color del producto
     */
    public String getColor() {
        return color;
    }

    /**
     * Método que establece el color del producto
     * @param color Color del producto
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Método que devuelve el material del producto
     * @return Material de fabricación del producto
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Método que establece el material de fabricación
     * @param material Material de elaboración
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Método para mostrar información del producto
     */
    @Override
    void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre() + "\nPrecio: " + getPrecio() + "\nCantidad: " + getStock() + "\nTalla: " + getTalla() + "\nColor: " + getColor() + "\nMaterial: " + getMaterial());
    }
}
