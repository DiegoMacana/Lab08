package edu.eci.cvds.test;


import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

    public class ServiciosAlquilerTest {

        @Inject
        private SqlSession sqlSession;

        ServiciosAlquiler serviciosAlquiler;

        public ServiciosAlquilerTest() {
            serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
        }

        @Before
        public void setUp() {
        }
        @Test
        public void consultarClienteTest(){
            try{
                Assert.assertEquals(serviciosAlquiler.consultarCliente(1234).getNombre(),"edwin");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        @Test
        public void consultarItemTest(){
            try{
                Assert.assertEquals(serviciosAlquiler.consultarItem(1).getNombre(),"BichoMilk");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        @Test
        public void consultarItemsTest(){
            try{
                serviciosAlquiler.consultarItemsDisponibles();

            }catch(ExcepcionServiciosAlquiler e){
                Assert.assertEquals("Error al consultar la lista de items disponibles",e.getMessage());
            }
        }
        @Test
        public void consultarTipoItemTest(){
            try{
                Assert.assertEquals(serviciosAlquiler.consultarTipoItem(2).getDescripcion(),"Accion");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        @Test
        public void consultarTiposItemsTest(){
            try{
                serviciosAlquiler.consultarTiposItem();

            }catch(ExcepcionServiciosAlquiler e){
                Assert.assertEquals("Error al consultar los tipos de items",e.getMessage());
            }
        }
        @Test
        public void emptyDB() {
            for(int i = 0; i < 100; i += 10) {
                boolean r = false;
                try {
                    Cliente cliente = serviciosAlquiler.consultarCliente(1234);
                } catch(ExcepcionServiciosAlquiler e) {
                    r = true;
                } catch(IndexOutOfBoundsException e) {
                    r = true;
                }
                // Validate no Client was found;
                Assert.assertFalse(r);
            };

        }
}

