package Banco;

public class ClientePremium extends Cliente {
    private Persona gestorDeInversiones;

    public ClientePremium(String nombre, String dni, float saldo, Persona gestorDeInversiones) {
        super(nombre, dni, saldo);
        this.gestorDeInversiones = gestorDeInversiones;
    }

    public ClientePremium(Cliente clienteOrigen, Persona gestorDeInversiones) {
        super(clienteOrigen.nombre, clienteOrigen.dni, clienteOrigen.saldo);
        this.gestorDeInversiones = gestorDeInversiones;
        clienteOrigen = null; // Borra el cliente original
    } // Esta clase permite a un cliente normal hacerle premium
}
