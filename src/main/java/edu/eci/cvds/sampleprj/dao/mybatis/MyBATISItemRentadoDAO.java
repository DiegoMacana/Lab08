package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;

import java.util.List;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {

    @Inject
    private ItemRentadoMapper itemRentadoMapper;

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

    public List<ItemRentado> totalItemsRentados() throws PersistenceException {
        try{
            return itemRentadoMapper.TotalRentado();
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el itemRentado ",e);
        }
    }
}