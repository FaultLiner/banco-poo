package Banco;

import Bolsa.BolsaDeValores;

import java.util.Iterator;
import Bolsa.*;

public class GestorDeInversiones extends Persona {
    private BolsaDeValores bolsa;

    public GestorDeInversiones(String nombre, String dni, BolsaDeValores bolsa) {
        super(nombre, dni);
        this.bolsa = bolsa;
    }

    public String consultaDeInversiones() {

        if (bolsa.getEmpresas().size() == 0) {
            System.out.println("No hay empresas en la bolsa");
            return "1";
        } else {
            Empresa empresa1 = null;
            float variacionMaxima =-100;
            Iterator iterador = bolsa.getEmpresas().iterator(); // creo un objeto Iterator para recorrer la coleccion
            while (iterador.hasNext()) {
                Empresa empresa = (Empresa) iterador.next();

                if(empresa.calculaVariacion() >variacionMaxima ){
                    variacionMaxima = empresa.calculaVariacion();
                    empresa1 = empresa;
                }
            }
            return empresa1.getNombre();
        }
    }
}



