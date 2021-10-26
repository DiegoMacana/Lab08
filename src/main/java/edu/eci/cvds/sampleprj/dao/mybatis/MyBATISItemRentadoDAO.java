package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {

    @Inject
    private ItemRentadoMapper itemRentadoMapper;

    @Inject
    private ItemMapper itemMapper;

    @Override
    public List<ItemRentado> consultarItemsRentados(long idC) throws PersistenceException {
        try{
            return itemRentadoMapper.consultarItemsRentados(idC);
        } catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los itemsRentados de el cliente "+idC,e);
        }
    }

    @Override
    public ItemRentado consultarItemRentado(int idI) throws PersistenceException {
        try{
            return itemRentadoMapper.consultarItemRentado(idI);
        } catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el itemRentado "+idI,e);
        }
    }

    @Override
    public void registrarAlquiler(long docu, Item item, Date date, int numdias) throws ExcepcionServiciosAlquiler {
        LocalDate fechaInicial=date.toLocalDate();
        LocalDate fechaFinal = fechaInicial.plusDays(numdias);
        Item it=itemMapper.consultarItem(item.getId());
        if(numdias>1){
            try{
                itemRentadoMapper.registrarAlquilerCliente(docu,it.getId(),date,Date.valueOf(fechaFinal));
            }catch(NullPointerException e){
                throw new ExcepcionServiciosAlquiler("Error al registrar Alquiler",e);
            }

        }
    }

    public List<ItemRentado> totalItemsRentados() throws PersistenceException {
        try{
            return itemRentadoMapper.TotalRentado();
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el itemRentado ",e);
        }
    }
}