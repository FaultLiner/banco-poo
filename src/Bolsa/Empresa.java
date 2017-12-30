package Bolsa;

import java.io.Serializable;



public class Empresa implements Serializable{
    private String nombre;
    private float valorTituloActual;
    private float valorTituloPrevio;

    public Empresa(String nombre, float valorTituloActual) {
        this.nombre = nombre;
        this.valorTituloActual = valorTituloActual;
        this.valorTituloPrevio = valorTituloActual;
    }

    public String getNombre() {
        return nombre;
    }

    public float getValorTituloActual() {
        return this.valorTituloActual;
    }

    public float getValorTituloPrevio() {
        return valorTituloPrevio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValorTituloActual(float valorTituloActual) {
        this.valorTituloActual = valorTituloActual;
    }

    public void setValorTituloPrevio(float valorTituloPrevio) {
        this.valorTituloPrevio = valorTituloPrevio;
    }


    public float calculaVariacion()  {

        if(valorTituloPrevio == 0){
            return 0;
        }
        return ((valorTituloActual-valorTituloPrevio)/valorTituloPrevio)*100;
    }

    public String toString(){
        return "Nombre Empresa: "+this.nombre + "  |||| Valor Actual Título: "+  this.valorTituloActual +"  |||| Variación: "+  this.calculaVariacion()+ " %" +"\n";
    }


    @Override
    public boolean equals (Object o) {
        if(o instanceof Empresa) {
            Empresa empresa = (Empresa) o;
            return this.nombre.equals(empresa.nombre);
        }
        else return false;

    }
    @Override
    public int hashCode(){
        return this.nombre.length();
    }

}
