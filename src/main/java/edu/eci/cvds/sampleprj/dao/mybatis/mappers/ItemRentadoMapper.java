package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface ItemRentadoMapper {

    public List<ItemRentado> consultarItemsRentados(@Param("idcli") long id);

    public List<ItemRentado> TotalRentado();

    public ItemRentado consultarItemRentado( @Param("idit") int idItem );

    public void registrarAlquilerCliente(@Param("idcl")long docu, @Param("idit")int id, @Param("fechainicio")Date date,@Param("fechafin") Date valueOf);
}
