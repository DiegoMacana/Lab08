package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import java.sql.Date;
import java.util.List;


public interface ClienteDAO {

    public Cliente load (long id) throws PersistenceException;
    List<ItemRentado> loadItemsCliente(int idCliente) throws PersistenceException;
    public void agregarItemRentado(long idcli, int id, Date fechainicio, Date fechafin) throws PersistenceException;
    public List<Cliente> consultarClientes() throws PersistenceException;
    public void save(Cliente cli) throws PersistenceException;
    public void vetarCliente(long idCliente,int estado) throws PersistenceException;
}
