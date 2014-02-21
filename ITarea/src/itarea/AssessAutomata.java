/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itarea;

import structures.Queue;

/**
 *
 * @author Yeison
 */

public class AssessAutomata 
{
    private LoadFile _entryFile = new LoadFile();
    private SaveFile _exitFile = new SaveFile();
    private Queue _queue = new Queue();
    private String _readFile;
    private int _counter = 1;
    
    AssessAutomata(String pDirection)
    {
        _readFile = _entryFile.readFile(pDirection);
        System.out.println(_readFile);
    }
    
    public void start()
    {
        startAssess(_readFile);
        writeExitFile();
        _queue.clear();
        _counter = 1;
    }
    
    public void startAssess(String pRead)
    {
        String assess = "";
        while (!"*".equals(Character.toString(pRead.charAt(_counter))))
        {
            if ("/".equals(Character.toString(pRead.charAt(_counter))))
            {
                boolean send = false;
                //send = Automata.lineAsses(asses); SI
                if (send == true)
                {
                    _queue.enqueue("OK");
                }
                else
                {
                    _queue.enqueue(":-c");
                }
                //NO
                System.out.println(assess);
                //NO
                assess = "";
                _counter++;
            }
            else
            {
                assess = assess + Character.toString(pRead.charAt(_counter));
                _counter++;
            }
        }
    }
    
    public void writeExitFile()
    {
        String finalassess = "";
        while(!_queue.isEmpty())
        {
            finalassess = finalassess + "\n" + _queue.dequeue();
        }
        _exitFile.writeFile("/root/Desktop/ITarea/salida.txt", finalassess);
        System.out.println(finalassess);
    }
}
