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
    LoadFile _entryFile = new LoadFile();
    String _readFile;
    int _counter = 2;
    
    LoadAutomata(String pDireccion)
    {
        _readFile = _entryFile.readFile(pDireccion);
    }
    
    public void cargar()
    {
        createBeen(_readFile);
        setFinalBeen(_readFile);
        setTransition(_readFile);
        setAcceptBeen(_readFile);
    }
    
    public void createBeen(String pLectura)
    {
        String estate = "";
        while (!"}".equals(Character.toString(pLectura.charAt(_counter))))
        {
            if (",".equals(Character.toString(pLectura.charAt(_counter))))
            {
                //Automata.crearEstado(estado); SI
                System.out.println(estate);
                estate = "";
                _counter++;
            }
            else
            {
                estate = estate + Character.toString(pLectura.charAt(_counter));
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
    
    public void setFinalBeen(String pLectura)
    {
        String initialstate = "";
        while(!"}".equals(Character.toString(pLectura.charAt(_counter))))
        {
            _counter++;
        }
        _counter = _counter + 2;
        while(!",".equals(Character.toString(pLectura.charAt(_counter))))
        {
            initialstate = initialstate + Character.toString(pLectura.charAt(_counter));
            _counter++;
        }
        //Automata.setEstadoInicial(estadoinicial); SI
        //NO
        System.out.println(initialstate);
        System.out.println(_counter);
        //NO
    }
    
    public void setTransition(String pLectura)
    {
        String beenone = "";
        String beentwo = "";
        String value = "";
        
        while(!"}".equals(Character.toString(pLectura.charAt(_counter))))
        {
            if ("(".equals(Character.toString(pLectura.charAt(_counter))))
            {
                _counter++;
                while(!",".equals(Character.toString(pLectura.charAt(_counter))))
                {
                    beenone = beenone + Character.toString(pLectura.charAt(_counter));
                    _counter++;
                }
                _counter++;
                while(!")".equals(Character.toString(pLectura.charAt(_counter))))
                {
                    value = value + Character.toString(pLectura.charAt(_counter));
                    _counter++;
                }
                _counter = _counter + 2;
                while(!",".equals(Character.toString(pLectura.charAt(_counter))) && !"}".equals(Character.toString(pLectura.charAt(_counter))))
                {
                    beentwo = beentwo + Character.toString(pLectura.charAt(_counter));
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
            if(!"}".equals(Character.toString(pLectura.charAt(_counter))))
            {
                _counter++;
            }
        }
    }
    
    public void setAcceptBeen(String pLectura)
    {
        _counter = _counter + 3;
        String beenaccept = "";
        while (!"}".equals(Character.toString(pLectura.charAt(_counter))))
        {
            if (",".equals(Character.toString(pLectura.charAt(_counter))))
            {
                //Automata.crearEstado(estado); SI
                System.out.println(beenaccept);
                beenaccept = "";
                _counter++;
            }
            else
            {
                beenaccept = beenaccept + Character.toString(pLectura.charAt(_counter));
                _counter++;
            }
        }
        //Automata.crearEstado(estado); SI
        //NO
        System.out.println(beenaccept);
        //NO
    }
}
