package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import java.sql.Date;
import java.util.List;


public interface ClienteDAO {

    public Cliente load(int id) throws PersistenceException;

}
