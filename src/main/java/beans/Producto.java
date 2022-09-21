/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author Juancho_o
 */
public class Producto {
    
    private int id_producto;
    private int id_categoria;
    private String nombre_producto;
    private String descripcion;
    private double valor;
    private String marca;

    public Producto(int id_producto, int id_categoria, String nombre_producto, String descripcion, double valor, String marca) {
        this.id_producto = id_producto;
        this.id_categoria = id_categoria;
        this.nombre_producto = nombre_producto;
        this.descripcion = descripcion;
        this.valor = valor;
        this.marca = marca;
    }
    
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "\nProducto{" + "id_producto=" + id_producto + ", id_categoria=" + id_categoria + ", nombre_producto=" + nombre_producto + ", descripcion=" + descripcion + ", valor=" + valor + ", marca=" + marca + '}';
    }
    
    
}
