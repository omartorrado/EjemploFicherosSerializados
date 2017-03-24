/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploficherosserializados;

import java.io.Serializable;

/**
 *
 * @author oracle
 */
public class Alumnos implements Serializable{
    
    private String nombre;
    private int nota;

    public Alumnos(String nombre, int nota) {
        this.nombre = nombre;
        this.nota = nota;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    
}
