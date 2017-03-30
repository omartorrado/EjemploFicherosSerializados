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
public class Alumnos implements Serializable, Comparable<Alumnos>{
    
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

    @Override
    public int compareTo(Alumnos a) {
        //No puedo usar el compareTo pq solo funciona con objetos y no con 
        //datos primitivos. serviria para string. Si keremos ordenar por nombre
        /*
        return this.getNombre().compareTo(a.getNombre());
        */
        //Hay que tener cuidado al usar este metodo, en el caso de que los 
        //valores a comparar puedan llegar a salir del rango de ints (o el primitivo k sea)
        return this.getNota()-a.getNota();                     
    }
    
    
    
}
