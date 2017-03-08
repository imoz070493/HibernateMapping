package com.imoz.pojos;

public class Curso {
    
    private String id;
    private String nombre;
    private int creditos;
    private int prueba;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", nombre=" + nombre + 
                ", creditos=" + creditos + '}';
    }
    
    
    
}
