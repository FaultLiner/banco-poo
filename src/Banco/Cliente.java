package Banco;

import java.util.HashSet;

public class Cliente extends Persona {
    protected float saldo;
    protected HashSet<PaqueteDeAcciones> paquetesAcciones;

    public Cliente(String nombre, String dni, float saldo) {
        super(nombre, dni);
        this.saldo = saldo;
    }

    public float getSaldo() {
        return this.saldo;
    }

    @Override
    public boolean equals (Object o) {
        if(o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            return this.dni.equals(cliente.dni);
        }
        else return false;

    }
    @Override
    public int hashCode(){
        return this.dni.length();
    }
}
