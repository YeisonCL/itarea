/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itarea;

import java.io.*;


/**
 *
 * @author Yeison
 */
public class LoadFile 
{
    private File _file;
    private FileReader _fileRead;
    private BufferedReader _readBuffer;
    private String _read = "";
    
    public String readFile(String pDirection)
    {
        try
        {
            //Creamos el objeto con el archivo que leeremos.
            _file = new File(pDirection);

            //Creamos el objeto FileReader que nos dara el flujo de datos para poder leer.
            _fileRead = new FileReader(_file);

            //Creamos un lector en buffer para recopilar datos a travez del flujo de FileReader.
             _readBuffer = new BufferedReader(_fileRead);

            String aux = "";/*variable auxiliar*/

            while(aux != null)
            //Este repite el proceso de lectura por si el archivo viene en varias lineas.
            {
                _read = _read + aux+ "/";
                aux = _readBuffer.readLine();
                //Si la variable aux aún tiene datos se guardan sino se sale del ciclo.
            }
            _read = _read + "*";
            //Se cierran los buffers.
            _readBuffer.close();
            _fileRead.close();

        }
        catch(IOException e)
        {
            System.out.println("Error:" + e.getMessage());
        }  
        return _read;
    }
}
