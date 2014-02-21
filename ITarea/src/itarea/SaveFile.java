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
public class SaveFile 
{
    private File _file;
    private FileWriter _fileWriter;
    private BufferedWriter _writerBuffer;
    private PrintWriter _writer; 
            
    public void writeFile(String pDirection, String pText)
    {
        try
        {
            _file = new File(pDirection);
            _fileWriter = new FileWriter(_file);
            _writerBuffer = new BufferedWriter(_fileWriter);
            _writer = new PrintWriter(_writerBuffer);
            _writer.write(pText);
            _writer.close();
            _writerBuffer.close();
        }
        catch (IOException e)
        {
            System.out.println("Error:" + e.getMessage());
        }  
        
    }
}
