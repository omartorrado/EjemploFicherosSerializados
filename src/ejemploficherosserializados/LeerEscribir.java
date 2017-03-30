/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploficherosserializados;

import java.awt.HeadlessException;
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
            //e es el numero de aslumnos que queremos incluir, el programa sigue preguntando
            //hasta tener el numero indicado
            //numOK controla el bucle de e
            int e = 0;
            boolean numOK = false;
            while (numOK == false) {
                try {
                    e = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos alumnos hay?", 1));
                    numOK = true;
                } catch (Exception ex) {

                }
            }
            for (int i = 0; i < e; i++) {
                Alumnos a = null;
                try {
                    a = new Alumnos(JOptionPane.showInputDialog("Escribe el nombre"), Integer.parseInt(JOptionPane.showInputDialog("Indica la nota")));
                    oos.writeObject(a);
                } catch (HeadlessException headlessException) {
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("Valor no válido");
                    i--;
                }

            }
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound" + e.toString());
        } catch (IOException ex) {
            System.out.println("IO" + ex.toString());
        }
    }

    public static void leer() {
        FileInputStream fis;
        ObjectInputStream ois;
        boolean leiAlgo = false;
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
            Alumnos aux = (Alumnos) ois.readObject();
            while (aux != null) {
                if (aux.getNota() >= 5) {
                    listaAprobados.add(aux);
                    oosa.writeObject(aux);
                    leiAlgo = true;
                } else {
                    ooss.writeObject(aux);
                    leiAlgo = true;
                }
                aux = (Alumnos) ois.readObject();
            }

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound" + e.toString());
        } catch (IOException ex) {
            if (leiAlgo == false) {
                System.out.println("IO" + ex.toString());
            }
        } catch (ClassNotFoundException exc) {
            System.out.println("ClassNotFound" + exc.toString());
        } finally {
            //Muestra la lista de aprovados si hay alguno
            if (listaAprobados.size() > 0) {
                System.out.println("listaAprobados desordenada:");
                for (Alumnos al : listaAprobados) {                    
                    System.out.println(al.getNombre() + " saco un " + al.getNota());
                }
            } else {
                System.out.println("Nadie ha aprobado...");
            }
        }
    }

    public static void verArrayList() {
        listaAprobados.sort(null);
        if (listaAprobados.size() > 0) {
            System.out.println("listaAprobados ordenada:");
            for (Alumnos al : listaAprobados) {
                System.out.println(al.getNombre() + " saco un " + al.getNota());
            }
        } else {
            System.out.println("Nadie ha aprobado...");
        }
    }

    public static void leerFichero(String fichero) {
        boolean leiAlgo = false;
        try {
            FileInputStream fis = new FileInputStream(fichero);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Alumnos aux = (Alumnos) ois.readObject();
            //quitamos las 4 ultimas letras para que no salga el .dat
            System.out.println(fichero.substring(0, fichero.length() - 4) + ":");
            while (aux != null) {
                leiAlgo = true;
                System.out.println("Alumno:" + aux.getNombre() + " Nota:" + aux.getNota());
                aux = (Alumnos) ois.readObject();
            }
        } catch (IOException e) {
            if (leiAlgo == false) {
                System.out.println("IO" + e.toString());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound" + ex.toString());
        }
    }
}
