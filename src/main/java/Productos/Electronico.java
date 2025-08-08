package Productos;

import jakarta.persistence.Entity;

/**
 * Clase que modela objetos de tipo de Electrónico que hereda de la clase padre Producto
 */
@Entity
public class Electronico extends Producto{
    /**
     * Marca del producto
     */
    private String marca;
    /**
     * Garantia (EN MESES) del producto
     */
    private int garantia;

    /**
     * Constructor vacio
     */
    public Electronico() {}

    /**
     * Constructor Parametrizado de la clase
     * @param nombre Nombre del producto
     * @param tipo Tipo de producto
     * @param precio Precio del Producto
     * @param stock Cantidad disponible de producto
     * @param marca Marca del producto
     * @param garantia Garantia del producto
     */
    public Electronico(String nombre, String tipo, double precio, int stock, String marca, int garantia) {
        super(nombre,"Electrónico", precio, stock);
        this.marca = marca;
        this.garantia = garantia;
    }

    /**
     * Método que devuelve la marca del producto
     * @return Marca del producto
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Método que establece  la marca del producto
     * @param marca Marca del producto
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Método que devuelve  la garantia del producto
     * @return garantia del producto en MESES
     */
    public int getGarantia() {
        return garantia;
    }

    /**
     *  Método que establece  la garantia del producto
     * @param garantia garantia del producto en MESES
     */
    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    /**
     * Método para mostrar información
     */
    @Override
    void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
    }
}
