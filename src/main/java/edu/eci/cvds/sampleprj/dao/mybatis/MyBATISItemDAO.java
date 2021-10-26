
package edu.eci.cvds.sampleprj.dao.mybatis;
import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;

import java.util.List;

public class MyBATISItemDAO implements ItemDAO{

    @Inject
    private ItemMapper itemMapper;

    private Item it;

    @Override
    public void save(Item it) throws PersistenceException{
        try{
            itemMapper.insertarItem(it);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+it.toString(),e);
        }
    }


    @Override
    public Item load(int id) throws PersistenceException {
        try{
            return itemMapper.consultarItem(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }
    }

    @Override
    public List<Item> loadItems() throws PersistenceException {
        try {
            return itemMapper.consultarItems();
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar todos los items",e);
        }
    }



    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException {
        try{
            itemMapper.actualizarTarifaItem(id,tarifa);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al actualizar la tarifa del item",e);
        }
    }



    @Override
    public List<Item> consultarItemsDisponibles() throws PersistenceException {
        try{
            return itemMapper.consultarItemsDisponibles();
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los items disponibles",e);
        }
    }

    @Override
    public int valorMultaDia(int itemId) throws PersistenceException {
            it=load(itemId);
            return (int)it.getTarifaxDia();

    }
}