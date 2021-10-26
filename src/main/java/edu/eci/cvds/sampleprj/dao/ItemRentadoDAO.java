package edu.eci.cvds.sampleprj.dao;


import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

import java.sql.Date;
import java.util.List;



public interface ItemRentadoDAO {

    public List<ItemRentado> totalItemsRentados()throws PersistenceException;

    public List<ItemRentado> consultarItemsRentados(long idCliente) throws PersistenceException;

    public ItemRentado consultarItemRentado( int  idItem ) throws PersistenceException;

    public void registrarAlquiler(long docu, Item item, Date date, int numdias) throws ExcepcionServiciosAlquiler;
}
