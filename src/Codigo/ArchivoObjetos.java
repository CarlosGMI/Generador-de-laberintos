package Codigo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArchivoObjetos 
{
    public void crearArchivoAños(Proyecto proyectos) throws FileNotFoundException, IOException
    {
        FileOutputStream fOutput = new FileOutputStream ("proyectos.DAT");
        ObjectOutputStream Ooutput = new ObjectOutputStream (fOutput);
        Ooutput.writeObject(proyectos);
        Ooutput.close();
    }
    
    public Object capturarArchivoAños() throws Exception
    {
        FileInputStream fInput = new FileInputStream ("proyectos.DAT");
        ObjectInputStream oInput = new ObjectInputStream (fInput);
        Proyecto proyectos = (Proyecto) oInput.readObject();
        oInput.close();
        return proyectos;
    }
}
