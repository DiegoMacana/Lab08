package edu.eci.cvds.samples.services.client;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

import java.sql.SQLException;
import java.util.List;


public class ServiceExample {
    public static void main(String args[]) throws SQLException, ExcepcionServiciosAlquiler {
        ServiciosAlquiler servicios = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
        List<Cliente> clientes=servicios.consultarClientes();
        System.out.println(clientes);
        System.out.println("---- Cliente -----");
        Cliente cliente = servicios.consultarCliente(1234);
        System.out.println(cliente.getNombre());
    }
}