package Conexion;

import Modelo.Persona;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import javax.swing.table.DefaultTableModel;

public class Conexion {

    private ObjectContainer oc;

    private void open() {
        this.oc = Db4o.openFile("base.yap");
    }

    public boolean Insertar(Persona persona) {
        try {
            this.open();
            oc.set(persona);
            this.oc.close();
            return true;
        } catch (Exception e) {
            System.out.println("e" + e.getMessage());
            return false;
        }
    }

    public Persona[] Consultar(Persona objeto) {
        try {
            Persona[] personas = null;
            this.open();
            ObjectSet resultados = this.oc.get(objeto);
            int i = 0;
            if (resultados.hasNext()) {
                personas = new Persona[resultados.size()];
                while (resultados.hasNext()) {
                    personas[i] = (Persona) resultados.next();
                    i++;
                }
            }
            this.oc.close();
            return personas;

        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
            return null;
        }

        //this.oc.close()
    }

     public Persona buscarPersona(Persona objeto) {
        this.open();   
        Persona encontrado = null;
        ObjectSet resultados = this.oc.get(objeto);
        if (resultados.hasNext()) {
            encontrado = (Persona) resultados.next();
        }
        return encontrado;
    }
      public boolean Eliminar(Persona objeto) {
        try {
            //CONSULTAMOS LOS OBJETOS ALMACENADOS EN LA BASE DE DATOS Y SI EXISTE UNA COINCIDENCIA LA ELIMINAMOS            
            this.open();
            ObjectSet resultados = this.oc.get(objeto);
            if (resultados.size() > 0) {
                Persona persona = (Persona) resultados.next();
                this.oc.delete(persona);
                this.oc.close();
                return true;
            } else {
                this.oc.close();
                return false;
            }
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("bdoo.Controlador.insertarPersona() : " + e);
            return false;
        }
    }

}
