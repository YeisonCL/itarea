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
public class AssessProductions 
{
    private String _entryString = "";
    private String[] _entrySplitString;
    private String _exitString = "";
    private String[] _exitSplitString;
    private String _inputString = "";
    private String[] _inputSplitString;
    private String _productions = "";
    private String[] _splitProductions;
    private String[] _temporaryProductions;
    String _finalGenerate = "";
    private LoadFile _entryFile = new LoadFile();
    private LoadFile _exitFile = new LoadFile();
    private LoadFile _productionsFile = new LoadFile();
    private SaveFile _generateFile = new SaveFile();
    
    AssessProductions(String pDirectionEntry, String pDirectionExit, String pDirectionProductions)
    {
        _entryString = _entryFile.readFile(pDirectionEntry);
        _exitString = _exitFile.readFile(pDirectionExit);
        _productions = _productionsFile.readFile(pDirectionProductions);
        _splitProductions = _productions.split("/");
        //NO
        System.out.println(_entryString);
        System.out.println(_exitString);
        System.out.println(_productions);
        //NO
    }
    
    public void start()
    {
        createInputString();
        startAssess();
        writeFile();
        _finalGenerate = "";
    }
    
    private void createInputString()
    {
        _entrySplitString = _entryString.split("/");
        _exitSplitString = _exitString.split("/");
        for(int i = 0; i < _entrySplitString.length; i++)
        {
            if("OK".equals(_exitSplitString[i]))
            {
                _inputString = _inputString + _entrySplitString[i] + "/";
            }
        }
        _inputSplitString = _inputString.split("/");
        //NO
        System.out.println(_inputString);
        //NO
    }
    
    private void startAssess()
    {
        for(int i = 0; i < _inputSplitString.length; i++)
        {
            String auxgenerate = "";
            String auxcharacter = "";
            for(int j = 0; j < _inputSplitString[i].length(); j++)
            {
                String character = Character.toString(_inputSplitString[i].charAt(j));
                auxcharacter = auxcharacter + character;
                //NO
                System.out.println("AUX");
                System.out.println(auxcharacter);
                System.out.println("AUX");
                //NO
                String[] auxsplit = null;
                auxsplit = extractProduction(auxcharacter);
                if (isEmptyArray(auxsplit))
                {
                    String finalproduction = extractProductionArray(_temporaryProductions);
                    if(!"".equals(finalproduction))
                    {
                        auxgenerate = auxgenerate + finalproduction;
                        j--;
                    }
                    else
                    {
                        auxgenerate = auxgenerate + "e";
                        break;
                    }
                    auxcharacter = "";
                }
                _temporaryProductions = auxsplit;
                //NO
                System.out.println("SEMIFINAL");
                System.out.println(auxgenerate);
                System.out.println("SEMIFINAL");
                //NO
            }
            auxgenerate = auxgenerate + ultimateProduction(auxcharacter);
            //NO
            System.out.println(auxcharacter);
            //NO
            _finalGenerate = _finalGenerate + auxgenerate + "\n";
        }
        //NO
        System.out.println("FINAL");
        System.out.println(_finalGenerate);
        System.out.println("FINAL");
        //NO
    }
    
    private String[] extractProduction(String pCharacter)
    {
        String[] splitproductions = null;
        String productions = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = 0;
            for(int j = 0; j < _splitProductions[i].length(); j++)
            {
                if(">".equals(Character.toString(_splitProductions[i].charAt(j))))
                {
                    counter++;
                    break;
                }
                else
                {
                    counter++;
                }
            }
            //NO
            System.out.println(pCharacter);
            System.out.println(counter);
            //NO
            String auxcomparision = "";
            if(counter + pCharacter.length() <= _splitProductions[i].length())
            {
                auxcomparision = _splitProductions[i].substring(counter, counter + pCharacter.length());
            }
            //NO
            System.out.println(auxcomparision);
            //NO
            if(auxcomparision.equals(pCharacter))
            {
                productions = productions + _splitProductions[i].substring(0 , counter - 2) + "/";
            }
            //NO
            System.out.println("PRODU");
            System.out.println(productions);
            System.out.println("PRODU");
            //NO
        }
        splitproductions = productions.split("/");
        return splitproductions;
    }
    
    private boolean isEmptyArray(String[] pArray)
    {
        boolean text = false;
        int i=0;
        while (!text && ( i < pArray.length))
        {
            if ((pArray[i]!= null) && (!pArray[i].equals("")))
            {
                text = true;
            }
        
            i++;
        }
        return !text;
    }
    
    private String extractProductionArray(String[] pArray)
    {
        String aux = pArray[0];
        for(int i = 0; i < pArray.length; i++)
        {
            if(pArray[i].length() < aux.length())
            {
                aux = pArray[i];
            }
        }
        return aux;
    }
    
    private String ultimateProduction(String pLine)
    {
        String finalproduction = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = 0;
            for(int j = 0; j < _splitProductions[i].length(); j++)
            {
                if(">".equals(Character.toString(_splitProductions[i].charAt(j))))
                {
                    counter++;
                    break;
                }
                else
                {
                    counter++;
                }
            }
            String aux = _splitProductions[i].substring(counter, _splitProductions[i].length());
            if(aux.equals(pLine))
            {
                finalproduction = _splitProductions[i].substring(0, counter - 2);
                break;
            }
        }
        return finalproduction;   
    }
    
    private void writeFile()
    {
        _generateFile.writeFile("/root/Desktop/ITarea/generado.txt", _finalGenerate);
    }
}
