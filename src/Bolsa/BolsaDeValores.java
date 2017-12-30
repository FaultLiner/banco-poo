package Bolsa;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import Utilidades.*;
import General.*;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class BolsaDeValores {

    //ZONA DE VARIABLES
    private String nombre;
    private HashSet<Empresa> empresas;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    //constructor que solo recibe el nombre de la bolsa
    public BolsaDeValores(String nombre) {
        this.nombre = nombre;
        empresas = new HashSet<Empresa>();
    }
    //constructor que recibe el nombre de la bolsa y una empresa que añade a la lista de empresas
    public BolsaDeValores(String nombre, Empresa empresa) {
        this.nombre = nombre;
        empresas = new HashSet<Empresa>();
        empresas.add(empresa);
    }
    //constructor que recibe el nombre de la bolsa y una lista de empresas que añade a la lista de empresas
    public BolsaDeValores(String nombre, HashSet<Empresa> empresas2) {
        this.nombre = nombre;
        empresas = new HashSet<Empresa>();
        empresas.addAll(empresas2);
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public String getNombre() {
        return nombre;
    }

    public HashSet<Empresa> getEmpresas() {
        return empresas;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PUBLICOS

    /*Nombre método: addEmpresa
      Entradas: Objeto de tipo Empresa
      Salidas: nada
      Excepciones: ninguna
      Descripción: añade una nueva empresa a la lista solo si la empresa no estaba en la lista
      */
    public void addEmpresa(Empresa empresa){
        if(!this.empresas.add(empresa)) {
            System.out.println("La empresa que ha intendado añadir YA esta presente en la bolsa");
        }
        else System.out.println("Empresa añadida con exito!");
    }

    /*Nombre método: addEmpresas
      Entradas: Objeto de tipo HashSet<Empresa>, es decir, una lista de Empresas
      Salidas: nada
      Excepciones: ninguna
      Descripción: añade una lista de empresa a la lista solo si ninguna de las empresas que se intentan añadir estaban ya en la lista
      */
    public void addEmpresas(HashSet<Empresa> empresas1){
        if(!empresas.addAll(empresas1)) System.out.println("Las empresas que ha intendado añadir, tiene alguna/s empresa que ya esta presente en la bolsa");
        else System.out.println("Empresas añadidas con exito!");
    }

    /*Nombre método: removeEmpresa
      Entradas: Objeto de String, el nombre de una empresa.
      Salidas: nada
      Excepciones: ninguna
      Descripción: elimina una empresa de la lista.
      */
    public void removeEmpresa (String nombreEmpresa){
        Empresa empresa = new Empresa(nombreEmpresa,0);
        if(!empresas.remove(empresa)) System.out.println("La empresa que ha intendado borrar NO esta presente en la bolsa");
        else System.out.println("Empresa eliminada con exito!");

    }

    /*Nombre método: actualizarValoresEmpresas
      Entradas: Objeto de String, el nombre de una empresa.
      Salidas: nada
      Excepciones: ninguna
      Descripción: elimina una empresa de la lista.
      */
    public void actualizarValoresEmpresas (){
        HashSet<Empresa> empresas1 = new  HashSet<Empresa>();
        Utilidades aleatorio = new Utilidades();
        Iterator iterador = empresas.iterator();
        while (iterador.hasNext()) {
            Empresa empresa = (Empresa) iterador.next();
            empresa.setValorTituloPrevio(empresa.getValorTituloActual());
            empresa.setValorTituloActual(empresa.getValorTituloActual()+empresa.getValorTituloActual() * aleatorio.generaAleatorio());
            empresas1.add(empresa);
        }
        empresas.clear();
        empresas.addAll(empresas1);



    }
    /*Nombre método: showEmpresas
      Entradas: nada
      Salidas: nada
      Excepciones:
      Descripción: Muestra todas las empresas que hay contenidas en la lista de empresas.
      */
    public void showEmpresas(){
        if(empresas.size()==0) System.out.println("No hay empresas en la bolsa!");
        else {
                System.out.println(empresas.toString());
            }
    }



    /*Nombre método: copiaSeguridadEmpresas
      Entradas: String path del fichero, objeto de tipo Output
      Salidas: nada
      Excepciones: IOException
      Descripción: Serializa la información de las empresas presentes en la bolsa y las uarda en disco
      */
    public void copiaSeguridadEmpresas(String path, Output serializa)throws IOException {

        serializa.abrir(path);
        Iterator iterador = empresas.iterator();
        System.out.println("Copiando...");
        System.out.println();
        while (iterador.hasNext()) {
            Empresa empresa = (Empresa) iterador.next();
            serializa.escribirEmpresa(empresa);
        }
        serializa.cerrar();
    }

    /*Nombre método: restaurarCopiaSeguridadEmpresas
      Entradas: String path del fichero
      Salidas: nada
      Excepciones: IOException, ClassNotFoundException
      Descripción: Deserializa la información de las empresas presentes en la bolsa y las guarda en disco
      */
    public void restaurarCopiaSeguridadEmpresas(String path, Input deserializa)throws IOException, ClassNotFoundException {
        float aux = 0;
        Empresa empresa;
        deserializa.abrir(path);
        System.out.println("Restaurando...");
        System.out.println();
        empresas.clear();//borramos toda la lista antes de cargar desde disco la nueva lista de la que dispondremos
        do{
            empresa = deserializa.leerEmpresa();
            empresas.add(empresa);
        } while (empresa !=null) ;
        deserializa.cerrar();
        empresas.remove(null);
        //Comprobamos que no hay empresas en la lista que tengan un valor de titulo actual de cero y si lo tiene la quitamos de la lista.
        Iterator iterador = empresas.iterator();
        while (iterador.hasNext()) {
            Empresa empresa1 = (Empresa) iterador.next();
            if(empresa1.getValorTituloActual() == aux){
                empresas.remove(empresa1);
            }
        }
    }
}








