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
    private String _readFile;
    private String _alphabetString = "";
    private String[] _alphabetSplit;
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
    private LoadFile _automataFile = new LoadFile();
    private SaveFile _generateFile = new SaveFile();
    
    AssessProductions(String pDirectionAutomata, String pDirectionEntry, String pDirectionExit, String pDirectionProductions)
    {
         _readFile = _automataFile.readFile(pDirectionAutomata);
        _entryString = _entryFile.readFile(pDirectionEntry);
        _exitString = _exitFile.readFile(pDirectionExit);
        _productions = _productionsFile.readFile(pDirectionProductions);
        _splitProductions = _productions.split("/");
        //NO
        System.out.println(_entryString);
        System.out.println(_exitString);
        System.out.println(_productions);
        System.out.println(_splitProductions[1]);
        //NO
    }
    
    public void start()
    {
        createInputString();
        createAlphabet();
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
            int after = 0;
            for(int j = 0; j < _inputSplitString[i].length(); j++)
            {
                int nextindex = giveMeAWord(_inputSplitString[i],j);
                //NO
                System.out.println("INDEX");
                System.out.println(nextindex);
                System.out.println(j);
                //NO
                int advancement = 0;
                String character = "";
                character = _inputSplitString[i].substring(j, nextindex);
                advancement = nextindex - j;
                j = nextindex;
                System.out.println(advancement);
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
                    String finalproduction = "";
                    if(auxcharacter.length() == 1 && !isInAlphabet(auxcharacter))
                    {
                        finalproduction = "";
                    }
                    else
                    {
                        finalproduction = extractProductionArray(_temporaryProductions);
                    }
                    if(!"".equals(finalproduction))
                    {
                        System.out.println("J VALE: " + j);
                        String subauxcharacter = _inputSplitString[i].substring(after, j - advancement);
                        System.out.println("SUBCHARACTER: " + subauxcharacter);
                        System.out.println("PRODUCCION ENVIADDA: " + finalproduction);
                        if(subauxcharacter.length() < giveMeProduction(finalproduction).length())
                        {
                            System.out.println("ENTRO CARAJO");
                            finalproduction = limitControl(subauxcharacter);
                        }
                        if("e".equals(finalproduction))
                        {
                             auxgenerate = auxgenerate + finalproduction;
                             break;
                        }
                        else
                        {
                             auxgenerate = auxgenerate + finalproduction;
                        }
                        j = j - advancement;
                        after = j;
                        System.out.println("AFTER: " + after);
                    }
                    else
                    {
                        System.out.println("TEST: " + auxgenerate);
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
                j--;
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
            int counter = approachTheCharacter(_splitProductions[i],">");
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
        for(int j = 0; j < pArray.length; j++)
        {
            pArray[j] = giveMeProduction(pArray[j]);
        }
        String aux = pArray[0];
        for(int i = 0; i < pArray.length; i++)
        {
            if(pArray[i].length() < aux.length())
            {
                aux = pArray[i];
            }
        }
        return giveMeValueProduction(aux);
    }
    
    private String ultimateProduction(String pLine)
    {
        String finalproduction = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            String aux = _splitProductions[i].substring(counter, _splitProductions[i].length());
            if(aux.equals(pLine))
            {
                finalproduction = _splitProductions[i].substring(0, counter - 2);
                break;
            }
        }
        return finalproduction;   
    }
    
    private void createAlphabet()
    {
        int counter = 3;
        String alphabet = "";
        while(!"{".equals(Character.toString(_readFile.charAt(counter))))
        {
            counter++;
        }
        counter++;
        while (!"}".equals(Character.toString(_readFile.charAt(counter))))
        {
            if (",".equals(Character.toString(_readFile.charAt(counter))))
            {
                _alphabetString = _alphabetString + alphabet + "/";
                alphabet = "";
                counter++;
            }
            else
            {
                alphabet = alphabet + Character.toString(_readFile.charAt(counter));
                counter++;
            }
        }
        _alphabetString = _alphabetString + alphabet + "/";
        System.out.println("ALFABETO FINAL: " + _alphabetString);
        _alphabetSplit = _alphabetString.split("/");
    }
    
    private int giveMeAWord(String pChain, int pIndex)
    {
        System.out.println("ENTRO CON " + pIndex);
        System.out.println("LA LINEA ES: " + pChain + " Con una longitud: " + pChain.length());
        String aux = "";
        int index = 0;
        for(int i = pIndex; i <= pChain.length(); i++)
        {
            if(isInAlphabet(aux))
            {
                index = i;
                break;
            }
            else if(i < pChain.length())
            {
                aux = aux + Character.toString(pChain.charAt(i));
            }
            System.out.println("EL AUX ES: " + aux);
        }
        if(!isInAlphabet(aux))
        {
                index = pIndex + 1;
        }
        return index;
    }
    
    private boolean isInAlphabet(String pWord)
    {
        boolean aux = false;
        for(int i = 0; i < _alphabetSplit.length; i++)
        {
            if(_alphabetSplit[i].equals(pWord))
            {
                aux = true;
                break;
            }
        }
        return aux;
    }
    
    private String limitControl(String pCharacter)
    {
        System.out.println("ESTAMOS EN LIMIT CONTROL CON: " + pCharacter);
        String finalproduction = "";
        String midproduction = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            if(_splitProductions[i].length() < counter + pCharacter.length())
            {
                String assess = pCharacter.substring(0,giveMeAWord(pCharacter, 0));
                System.out.println("ASSESS: " + assess);
                String production = _splitProductions[i];
                System.out.println("PRODUCTION: " + production);
                boolean serieflag = true;
                int repeatcounter = 0;
                for(int j = counter; j<_splitProductions[i].length();j++)
                {
                    System.out.println("COUNTEEER: " + counter);
                    if(_splitProductions[i].substring(counter, giveMeAWord(production, counter)).equals(assess))
                    {
                        repeatcounter++;
                        counter = giveMeAWord(production, counter);
                        j = counter;
                        System.out.println("J ESSSSSS: " + j);
                    }
                    else
                    {
                        counter = giveMeAWord(production, counter);
                        j= counter;
                        System.out.println("J ESSSSSS: " + j);
                        serieflag = false;
                        break;
                    }
                    j--;
                }
                if(serieflag == true)
                {
                    midproduction = midproduction + _splitProductions[i].substring(0, approachTheCharacter(production,">")-2) + "=" + Integer.toString(repeatcounter) + "/";
                }
            }
            System.out.println("MID PRODUCTION");
            System.out.println(midproduction);
        }
        String[] midproductionsplit = midproduction.split("/");
        System.out.println(midproductionsplit.length);
        if(midproductionsplit.length == 1 && "".equals(midproductionsplit[0]))
        {
            return "e";
        }
        else
        {
            midproductionsplit = accommodateWords(midproductionsplit);
            System.out.println("PALABRAS ACOMODADAS: " + midproductionsplit[0]);
            int numberofwords = numberOfWords(pCharacter);
            finalproduction = aplyModule(midproductionsplit, numberofwords);
            return finalproduction; 
        }  
    }
    
    public int approachTheCharacter(String pProduction, String pCharacter)
    {
        int counter = 0;
        for(int j = 0; j < pProduction.length(); j++)
        {
            if(pCharacter.equals(Character.toString(pProduction.charAt(j))))
            {
                counter++;
                break;
            }
            else
            {
                counter++;
            }
        }
        return counter;
    }
    
    private String giveMeProduction(String pProduction)
    {
        String finalproduction = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            if(_splitProductions[i].substring(0, counter-2).equals(pProduction))
            {
                finalproduction = _splitProductions[i].substring(counter, _splitProductions[i].length());
            }
        }
        System.out.println("FINAL PRODUCTION: " + finalproduction);
        return finalproduction;
    }
    
    private String giveMeValueProduction(String pValue)
    {
        String finalvalue = "";
        for(int i = 0; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            if(_splitProductions[i].substring(counter, _splitProductions[i].length()).equals(pValue))
            {
                finalvalue = _splitProductions[i].substring(0, counter-2);
            }
        }
        return finalvalue;
    }
    
    private String[] accommodateWords(String[] pWords)
    {
        int[] intarray = new int[pWords.length];
        for(int i = 0; i < pWords.length; i++)
        {
            int initiation = approachTheCharacter(pWords[i],"=");
            String value = pWords[i].substring(initiation,pWords[i].length());
            int valueint = Integer.parseInt(value);
            intarray[i] = valueint;
            
        }
        intarray = investArray(sortArray(intarray));
        
        return makeTupla(intarray, pWords).split("/");
    }
    
    private int[] investArray(int[] pArray)
    {
        int[]auxArray = new int[pArray.length];
        for(int i = 0; i < pArray.length; i++)
        {
            auxArray[(pArray.length - 1) - i] = pArray[i];
        }
        return auxArray;
    }
    
    private String makeTupla(int[] pArrayInt, String[] pArrayString)
    {
        String aux = "";
        for(int i = 0; i < pArrayInt.length; i++)
        {
            int value = pArrayInt[i];
            for(int j = 0; j< pArrayString.length; j++)
            {
                int index = approachTheCharacter(pArrayString[i], "=");
                String valuestring = pArrayString[i].substring(index, pArrayString[i].length());
                if(valuestring.equals(Integer.toString(value)))
                {
                    aux = aux + pArrayString[i].substring(0, index-1) + "=" + Integer.toString(value) + "/";
                    break;
                }
            }
        }
        return aux;
    }
    
    public int[] sortArray(int[] pArray)
    {
        int aux;
        for (int i = 0; i < pArray.length - 1; i++) 
        {
            for (int x = i + 1; x < pArray.length; x++) 
            {
                if (pArray[x] > pArray[i]) 
                {
                    aux = pArray[i];
                    pArray[i] = pArray[x];
                    pArray[x] = aux;
                }
            }
        }
        return pArray;
    }
    
    private int numberOfWords(String pCharacter)
    {
        int counter = 0;
        for(int i = 0; i < pCharacter.length(); i++)
        {
            i = giveMeAWord(pCharacter, i);
            counter++;
            i--;
        }
        return counter;
    }
    
    private boolean hasProhibitedCharacters(String pWord)
    {
        boolean flag = false;
        for(int i = 0; i < pWord.length(); i++)
        {
            if(!isInAlphabet(Character.toString(pWord.charAt(i))))
            {
                flag = true;
            }
        }
        return flag;
    }
    
    private String aplyModule(String[] pModules, int pNumber)
    {
        String auxfinal = "";
        for(int i = 0; i < pModules.length; i++)
        {
            int index = approachTheCharacter(pModules[i], "=");
            int value = Integer.parseInt(pModules[i].substring(index, pModules[i].length()));
            System.out.println("pNumber: " + pNumber + " Y value: " + value);
            int amount = pNumber/value;
            System.out.println("AMOUNT: " + amount);
            pNumber = pNumber - (value * amount);
            for(int j = 0; j < amount; j++)
            {
                auxfinal = auxfinal + pModules[i].substring(0,index-1);
            }
        }
        System.out.println("AUXFINAL:  " + auxfinal);
        return auxfinal;
        
    }
    
    private void writeFile()
    {
        _generateFile.writeFile("/root/Desktop/ITarea/generado.txt", _finalGenerate);
    }
    
}
