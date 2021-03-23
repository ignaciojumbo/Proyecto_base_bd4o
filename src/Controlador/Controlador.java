package Controlador;

import Conexion.Conexion;
import Modelo.Persona;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Controlador extends Conexion {

    public Controlador() {
    }

    public boolean insertarPersona(int id, String nombre, String apellidos,String telefono) {
        Persona persona = new Persona(nombre, apellidos,telefono, id);
        return this.Insertar(persona);
    }
     public boolean ActualizarPersona(int id, String nombre, String apellidos,String telefono) {
        Persona persona = new Persona(nombre, apellidos,telefono, id);
        return this.Actualizar(persona);
    }
    

    public DefaultTableModel personas() {
        String titulos[] = {"ID", "NOMBRE", "APELLIDO","TELÉFONO"};
        DefaultTableModel dtm = new DefaultTableModel(null, titulos);
        Persona persona = null;
        Persona[] p = this.Consultar(persona);
        if (p != null) {
            for (Persona per : p) {
                Object[] cli = new Object[4];
                cli[0] = per.getId();
                cli[1] = per.getNombre();
                cli[2] = per.getApellido();
                cli[3] = per.getTelefono();
                dtm.addRow(cli);
            }
        }
        return dtm;
    }
     public boolean eliminarPersona(int id) {
        if (id > 0) {
            Persona persona = new Persona(null, null,null, id);
            return this.Eliminar(persona);
        } else {
            return false;
        }
    }
     
    

}
