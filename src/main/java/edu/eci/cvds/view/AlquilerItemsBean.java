package edu.eci.cvds.view;


import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ApplicationScoped
@ManagedBean(name="aplicationBean")
public class AlquilerItemsBean extends BasePageBean {
    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    private String nombre;
    private long documento;
    private String telefono;
    private String direccion;
    private String email;
    private boolean vetado;

    public List<Cliente> consultarclientes() throws ExcepcionServiciosAlquiler{
        return serviciosAlquiler.consultarClientes();
    }

    public void registrarcliente(){
        Cliente cliente=new Cliente(nombre,documento,telefono,direccion,email,vetado,null);
        try{

           serviciosAlquiler.registrarCliente(cliente);
        }catch(ExcepcionServiciosAlquiler e){
            e.printStackTrace();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVetado() {
        return vetado;
    }

    public void setVetado(boolean vetado) {
        this.vetado = vetado;
    }
}

