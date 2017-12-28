package Banco;

import ExcepcionesPropias.*;

import java.util.HashSet;
import java.util.Iterator;

public class Banco {
    //ZONA DE VARIABLES
    private String nombre;
    private AgenteDeInversiones broker;
    private HashSet<Cliente> clientes;
    //FIN ZONA VARIABLES

    //ZONA CONSTRUCTORES
    //constructor que solo recibe el nombre del banco
    public Banco(String nombre) {
        this.nombre = nombre;
        this.clientes = new HashSet<Cliente>();
    }

    //constructor que solo recibe el nombre del banco y un cliente
    public Banco(String nombre, Cliente cliente) {
        this.nombre = nombre;
        this.clientes = new HashSet<Cliente>();
        this.clientes.add(cliente);
    }

    //constructor que solo recibe el nombre del banco y un Agente de inversiones
    public Banco(String nombre, AgenteDeInversiones broker) {
        this.nombre = nombre;
        this.broker = broker;
        this.clientes = new HashSet<Cliente>();
    }

    //constructor que solo recibe el nombre del banco y un Agente de inversiones y una lista de clientes
    public Banco(String nombre, AgenteDeInversiones broker, HashSet<Cliente> clientes) {
        this.nombre = nombre;
        this.broker = broker;
        this.clientes = new HashSet<Cliente>();
        this.clientes.addAll(clientes);
    }

    //FIN ZONA CONSTRUCTORES

    //ZONA DE GETTERS

    public String getNombre() {
        return nombre;
    }

    public AgenteDeInversiones getBroker() {
        return broker;
    }

    public HashSet<Cliente> getClientes() {
        return clientes;
    }

    //FIN ZONA GETTERS

    //ZONA DE SETTERS

    public void setBroker(AgenteDeInversiones broker) {
        this.broker = broker;
    }


    //FIN ZONA SETTERS

    //ZONA DE METODOS PUBLICOS

    /*Nombre método: addCliente
      Entradas: Objeto de tipo Cliente
      Salidas: nada
      Excepciones: ninguna
      Descripción: añade un nuevo cliente a la lista solo si el cliente no estaba en la lista
      */
    public void addCliente(Cliente cliente) {
        if (!this.clientes.add(cliente)) {
            System.out.println("El cliente que ha intendado añadir YA esta presente en el banco");
        } else System.out.println("Cliente añadido con exito!");
    }

    /*Nombre método: addClientes
      Entradas: Objeto de tipo HashSet<Cliente>, es decir, una lista de Clientes
      Salidas: nada
      Excepciones: ninguna
      Descripción: añade una lista de clientes a la lista solo si ningun de los clientes que se intentan añadir estaban ya en la lista
      */
    public void addClientes(HashSet<Cliente> cliente1) {
        if (!clientes.addAll(cliente1))
            System.out.println("Las empresas que ha intendado añadir, tiene alguna/s empresa que ya esta presente en la bolsa");
        else System.out.println("Empresas añadidas con exito!");
    }

    /*Nombre método: removeClientes
      Entradas: Objeto de tipo String, el dni de un cliente.
      Salidas: nada
      Excepciones: ninguna
      Descripción: elimina un cliente de la lista.
      */
    public void removeCliente(String dniCliente) {
        Cliente cliente = new Cliente("sdsd", dniCliente, 32);
        if (!clientes.remove(cliente))
            System.out.println("El cliente que ha intendado borrar NO esta presente en el banco");
        else System.out.println("Cliente eliminado con exito!");

    }

    /*Nombre método: showClientes
      Entradas: nada
      Salidas: nada
      Excepciones:
      Descripción: Muestra todas las empresas que hay contenidas en la lista de empresas.
      */
    public void showClientes() {
        if (clientes.size() == 0) System.out.println("No hay clientes en la banco!");
        else {
            Iterator iterador = clientes.iterator();
            while (iterador.hasNext()) {
                Cliente cliente = (Cliente) iterador.next();
                System.out.println(cliente.toString());
            }
        }
    }

    /*Nombre método: promocionAClientePremium
      Entradas: dni cliente
      Salidas: nada
      Excepciones:
      Descripción: Promociona a un cleinte ya existente a premium asignandole a un gestor
      */
    public void promocionAClientePremium(String dniCliente) throws BancoNoTieneGestor {
        boolean encontrado;
        try {
            Cliente cliente = new Cliente("sdsd", dniCliente, 32); //creamos este objeto auxiliar para comparar con los elemntos de la lista de clientes

            if (this.broker == null) { // miro a ver si el banco tiene un gestor asociado
                System.out.println("El bancco no tiene asignado ningún gestor");
            } else {
                encontrado = false;
                Iterator iterador = clientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
                while (iterador.hasNext() && !encontrado) {
                    Cliente cliente1 = (Cliente) iterador.next();
                    if (cliente1.isEsPremium()) {
                        System.out.println("El cliente con dni: " + dniCliente + " ya tiene categoria Premium y su gestor es: " + ((ClientePremium) cliente1).getNombreGestorDeInversiones());
                    } else if (cliente.equals(cliente1)) {
                        encontrado = true;
                        cliente = cliente1;//meto en la varible cliente al objeto de la colleccion cliente1 que coincide con el en el dni
                    } else {
                        System.out.println("El cliente con dni: " + dniCliente + " no existe");
                    }
                }
                if (encontrado) {
                    Cliente clientePremium = new ClientePremium(cliente, this.broker.getNombre());// creo un nuevo objeto de tipo cliente como tipo estatico pero como tipo dinamico de tipo cliente premium utilizando el constructor que recibe a un cliente antiguo y el nombre del gestor
                    clientes.remove(cliente); // borro de la coleccion al cliente antiguo, es decir el que no era premium
                    clientes.add(clientePremium); // añado a la coleccion al cliente ya promocionado a premium
                    System.out.println("El cliente con dni: " + dniCliente + " se le ha otorgado la categoria Premium y su gestor asignado es: " + this.broker.getNombre());

                }
            }
        } catch (NullPointerException bancoNotieneGestor) {
            throw new BancoNoTieneGestor("Se ha intentado promocionar a un cliente a premium sin que el banco tenga un gestor de inversiones");
        }


    }
}


