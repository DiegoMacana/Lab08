package edu.eci.cvds.samples.services.impl;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.*;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import java.sql.Date;
import java.util.List;


@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {


    @Inject
    private ItemDAO itemDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private TipoItemDAO tipoItemDAO;

    @Inject
    private ItemRentadoDAO itemRentadoDAO;

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try {
            return clienteDAO.load(docu);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar cliente", e);
        }

    }
    @Override public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try{
            return clienteDAO.consultarClientes();
        }catch(PersistenceException ex){
            throw new UnsupportedOperationException("Error al consultar la lista de clientes");
        }

    }
    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item " + id, ex);
        }
    }
    @Override public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.consultarItemsDisponibles();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar la lista de items disponibles", e);
        }
    }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {

        throw new ExcepcionServiciosAlquiler("El item"+iditem+"no se encuentra rentado");
    }
    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return tipoItemDAO.load(id);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el tipo de item "+id,e);
        }
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        try {
            return tipoItemDAO.loadTiposItems();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los tipos de items ",e);
        }
    }
    @Override public List consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        Item item = consultarItem(iditem);
        if (item == null) throw new ExcepcionServiciosAlquiler("El item no existe");
        else if (numdias < 1) throw new ExcepcionServiciosAlquiler("el numero de dias debe ser mayor o igual a 1");
        else return consultarItem(iditem).getTarifaxDia() * numdias;
    }



    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        itemRentadoDAO.registrarAlquiler(docu, item, date, numdias);

    }

    @Override
    public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
        try {
            clienteDAO.save(c);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar el cliente", ex);
        }
    }
    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.save(i);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar el item " + i, ex);
        }
    }
    @Override
    public void registrarTipoItem(TipoItem tipoItem) throws ExcepcionServiciosAlquiler {
        try {
            tipoItemDAO.save(tipoItem);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al insertar el tipo de Item " + tipoItem.getID(), e);
        }
    }

    @Override
    public int valorMultaRetrasoxDia(int itemId) throws PersistenceException {
        return itemDAO.valorMultaDia(itemId);
    }


    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.actualizarTarifaItem(id, tarifa);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al actualizar el item " + id, e);
        }
    }



    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        try {
            clienteDAO.vetarCliente(docu, estado ? 1 : 0);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al actualizar la informacion del cliente" + docu, e);
        }
    }


}