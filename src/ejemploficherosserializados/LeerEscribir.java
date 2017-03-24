/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploficherosserializados;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author oracle
 */
public class LeerEscribir {
    
    private static ArrayList<Alumnos> listaAprobados = new ArrayList<Alumnos>();
    
    public static void escribir() {
        try {
            FileOutputStream fos = new FileOutputStream("notas.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < 5; i++) {
                Alumnos a = new Alumnos(JOptionPane.showInputDialog("Escribe el nombre"), Integer.parseInt(JOptionPane.showInputDialog("Indica la nota")));
                oos.writeObject(a);
            }
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public static void leer() {
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream("notas.dat");
            ois = new ObjectInputStream(fis);
            //Escribir aprobados
            FileOutputStream fosa = new FileOutputStream("aprobados.dat");
            ObjectOutputStream oosa = new ObjectOutputStream(fosa);
            //Escribir suspenso
            FileOutputStream foss = new FileOutputStream("suspensos.dat");
            ObjectOutputStream ooss = new ObjectOutputStream(foss);
            //Comproacion de nota
            for (int i=0;i<5;i++) {
                Alumnos a = (Alumnos) ois.readObject();
                if (a.getNota() >= 5) {
                    listaAprobados.add(a);
                    oosa.writeObject(a);
                } else {
                    ooss.writeObject(a);
                }
            }
            for(Alumnos al:listaAprobados){
                System.out.println(al.getNombre()+" saco un "+al.getNota());
            }
            
            
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (ClassNotFoundException exc) {
            System.out.println(exc.toString());
        }
    }
    
    
}
