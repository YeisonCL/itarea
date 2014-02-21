/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package structures;

/**
 *
 * @author root
 */
public class QueueNode 
{
    private QueueNode _next;
    private String _value;
    
    public QueueNode(String pValue)
    {
        this._value = pValue;
    }
    
    public String getValue()
    {
        return _value;
    }
    
    public void setValue(String pValue)
    {
        this._value = pValue;
    }

    public QueueNode getNext() 
    {
        return _next;
    }
    
    public void setNext(QueueNode pNext) 
    {
        this._next = pNext;
    }
}
