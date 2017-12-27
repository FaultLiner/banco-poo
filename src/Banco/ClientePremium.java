package Banco;

public class ClientePremium extends Cliente {
    private Persona gestorDeInversiones;

    public ClientePremium(String nombre, String dni, float saldo, Persona gestorDeInversiones) {
        super(nombre, dni, saldo,true);
        this.gestorDeInversiones = gestorDeInversiones;
    }
}
