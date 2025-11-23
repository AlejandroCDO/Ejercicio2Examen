package alejandrocompany.ejercicio2examen.models;

import java.io.Serializable;

public class DesconocidoModel implements Serializable {

    //SI PIDE CALCULAR HAY QUE PONER EN EL CONTRUCTOR LA CANTIDAD DE LO QUE SEA POR EL PRECIO
    //THIS.TOTAL = THIS.PRECIO * THIS.CANTIDAD;


    //ACTUALIZAR TOTAL(){
    //     //THIS.TOTAL = THIS.PRECIO * THIS.CANTIDAD;}

    private String nombre;
    private  int cantidad;
    private float precio;
    private float total;

    public DesconocidoModel(String nombre, int cantidad, float precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = cantidad * precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void actualizarTotal(){
        this.total = this.cantidad * this.precio;
    }
    public float getTotal() {
        return total;
    }


    @Override
    public String toString() {
        return "DesconocidoModel{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", total=" + total +
                '}';
    }
}
