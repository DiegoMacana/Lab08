package edu.eci.cvds.sampleprj.dao.mybatis;
import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

import java.sql.Date;
import java.util.List;

public class MyBATISClienteDAO implements ClienteDAO{

    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public Cliente load(long id) throws PersistenceException {
        try {
            return clienteMapper.consultarCliente((int)id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el Cliente con el id: " + id, e);
        }
    }

    @Override
    public void agregarItemRentado(long idcli, int id, java.sql.Date fechainicio, Date fechafin) throws PersistenceException {
        try {
            clienteMapper.agregarItemRentadoACliente((int) idcli, id, fechainicio, fechafin);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al añadir el nuevo cliente", e);
        }

    }

    @Override
    public List<Cliente> consultarClientes() throws PersistenceException {
        try {
            return clienteMapper.consultarClientes();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar todos los clientes", e);
        }
    }

    @Override
    public List<ItemRentado> loadItemsCliente(int idCliente) throws PersistenceException {
        try {
            return (List<ItemRentado>) clienteMapper.consultarCliente(idCliente);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar los items de los clientes", e);
        }
    }
    @Override
    public void save(Cliente c) throws PersistenceException {
        try {
            clienteMapper.agregarCliente(c);
        } catch (Exception e) {
            throw new PersistenceException(e.toString(), e);
        }

    }
    @Override
    public void vetarCliente(long idCliente, int estado) throws PersistenceException {
        try{
            clienteMapper.vetarCliente(idCliente,estado);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException(PersistenceException.V_CLIENTE);
        }

    }
}
