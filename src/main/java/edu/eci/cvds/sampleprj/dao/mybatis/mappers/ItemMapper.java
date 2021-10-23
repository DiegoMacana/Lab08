package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(@Param("itemId")int id);
    
    public void insertarItem(@Param("itemI")Item it);
    public void actualizarTarifaItem(@Param("id") int id, @Param("tarifa") long tarifa);

    public List<Item> consultarItemsDisponibles();

        
}
