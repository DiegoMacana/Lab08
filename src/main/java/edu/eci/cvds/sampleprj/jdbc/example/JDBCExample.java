package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 2));
            
            List<String> prodsPedido=nombresProductosPedido(con, 2);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            //int suCodigoECI=2156504;
            //registrarNuevoProducto(con, suCodigoECI, "Angélica", 5000000);   
            //con.commit();
                        
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los par�metros dados
     * @param con la conexi�n JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
    	
        //Crear preparedStatement
    	PreparedStatement insertProduct = null;
    	String insertValues = "INSERT INTO ORD_PRODUCTOS VALUES (?,?,?);"; 
    	insertProduct = con.prepareStatement (insertValues);
    	
        //Asignar par�metros
    	insertProduct.setInt(1, codigo);
    	insertProduct.setString(2, nombre);
    	insertProduct.setInt(3, precio);
    	
        //usar 'execute'
    	insertProduct.executeUpdate();
        con.commit();
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexi�n JDBC
     * @param codigoPedido el c�digo del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido){
        List<String> np=new LinkedList<>();
        
        //Crear prepared statement
        PreparedStatement consultProduct = null;
        String consultValues = "SELECT nombre FROM ORD_PRODUCTOS pro, ORD_DETALLE_PEDIDO ped WHERE ?=ped.pedido_fk AND ped.producto_fk=pro.codigo;"; 
        try {
        	consultProduct = con.prepareStatement (consultValues);
        	
        	//asignar par�metros
        	consultProduct.setInt(1, codigoPedido);
        	
        	//usar executeQuery
        	ResultSet rs = consultProduct.executeQuery();
        	
        	//Sacar resultados del ResultSet y llenar la lista
        	while(rs.next()) {
        		String nombreProducto=rs.getString("nombre");
                np.add(nombreProducto);   		
        	}
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido c�digo del pedido cuyo total se calcular�
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido){
    	int totalPedido = 0;
    
    	//Crear prepared statement
        PreparedStatement calculatePedido = null;
        String calculateValues = "SELECT SUM(precio*cantidad) AS total FROM ORD_DETALLE_PEDIDO ped, ORD_PRODUCTOS pro WHERE ped.pedido_fk=? AND ped.producto_fk=pro.codigo;"; 
        try {
        	calculatePedido = con.prepareStatement (calculateValues);
        	
        	//asignar par�metros
        	calculatePedido.setInt(1, codigoPedido);
        	
        	//usar executeQuery
        	ResultSet rs = calculatePedido.executeQuery();
        	
        	//Sacar resultados del ResultSet y llenar la lista
        	while(rs.next()) {
        		totalPedido=rs.getInt("total");   		
        	}
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        
        return totalPedido;
    }
}