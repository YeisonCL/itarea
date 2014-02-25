/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itarea;

/**
 *
 * @author Yeison
 */
public class LoadAutomata 
{
    private LoadFile _automataFile = new LoadFile();
    private String _readFile;
    private int _counter = 3;
    
    LoadAutomata(String pDirection)
    {
        _readFile = _automataFile.readFile(pDirection);
    }
    
    public void load()
    {
        createBeen(_readFile);
        setFinalBeen(_readFile);
        setTransition(_readFile);
        setAcceptBeen(_readFile);
        _counter = 3;
    }
    
    private void createBeen(String pRead)
    {
        String estate = "";
        while (!"}".equals(Character.toString(pRead.charAt(_counter))))
        {
            if (",".equals(Character.toString(pRead.charAt(_counter))))
            {
                //Automata.crearEstado(estado); SI
                System.out.println(estate);
                estate = "";
                _counter++;
            }
            else
            {
                estate = estate + Character.toString(pRead.charAt(_counter));
                _counter++;
            }
        }
        //Automata.crearEstado(estado); SI
        _counter++;
        //NO
        System.out.println(estate);
        System.out.println(_counter);
        //NO
        
    }
    
    private void setFinalBeen(String pRead)
    {
        String initialstate = "";
        while(!"}".equals(Character.toString(pRead.charAt(_counter))))
        {
            _counter++;
        }
        _counter = _counter + 2;
        while(!",".equals(Character.toString(pRead.charAt(_counter))))
        {
            initialstate = initialstate + Character.toString(pRead.charAt(_counter));
            _counter++;
        }
        //Automata.setEstadoInicial(estadoinicial); SI
        //NO
        System.out.println(initialstate);
        System.out.println(_counter);
        //NO
    }
    
    private void setTransition(String pRead)
    {
        String beenone = "";
        String beentwo = "";
        String value = "";
        
        while(!"}".equals(Character.toString(pRead.charAt(_counter))))
        {
            if ("(".equals(Character.toString(pRead.charAt(_counter))))
            {
                _counter++;
                while(!",".equals(Character.toString(pRead.charAt(_counter))))
                {
                    beenone = beenone + Character.toString(pRead.charAt(_counter));
                    _counter++;
                }
                _counter++;
                while(!")".equals(Character.toString(pRead.charAt(_counter))))
                {
                    value = value + Character.toString(pRead.charAt(_counter));
                    _counter++;
                }
                _counter = _counter + 2;
                while(!",".equals(Character.toString(pRead.charAt(_counter))) && !"}".equals(Character.toString(pRead.charAt(_counter))))
                {
                    beentwo = beentwo + Character.toString(pRead.charAt(_counter));
                    _counter++;
                }
                //NO
                System.out.println(beenone);
                System.out.println(beentwo);
                System.out.println(value);
                //NO
                //Automata.crearTransicion(estado1,estado2,valor); SI
                beenone = "";
                beentwo = "";
                value = "";
            }
            if(!"}".equals(Character.toString(pRead.charAt(_counter))))
            {
                _counter++;
            }
        }
    }
    
    private void setAcceptBeen(String pRead)
    {
        _counter = _counter + 3;
        String beenaccept = "";
        while (!"}".equals(Character.toString(pRead.charAt(_counter))))
        {
            if (",".equals(Character.toString(pRead.charAt(_counter))))
            {
                //Automata.crearEstado(estado); SI
                System.out.println(beenaccept);
                beenaccept = "";
                _counter++;
            }
            else
            {
                beenaccept = beenaccept + Character.toString(pRead.charAt(_counter));
                _counter++;
            }
        }
        //Automata.crearEstado(estado); SI
        //NO
        System.out.println(beenaccept);
        //NO
    }
}
