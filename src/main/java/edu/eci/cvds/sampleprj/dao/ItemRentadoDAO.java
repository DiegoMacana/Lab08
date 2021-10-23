package edu.eci.cvds.sampleprj.dao;


import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.List;



public interface ItemRentadoDAO {

    public List<ItemRentado> totalItemsRentados()throws PersistenceException;

    public List<ItemRentado> consultarItemsRentados(long idCliente) throws PersistenceException;

    public ItemRentado consultarItemRentado( int  idItem ) throws PersistenceException;
}
