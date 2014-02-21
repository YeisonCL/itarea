/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package structures;

/**
 *
 * @author Yeison
 */
public class Queue 
{
    private QueueNode _top;
    private QueueNode _tail;
    
    public Queue()
    {
        _top = null;
        _tail = null;
    }
    
    public void clear()
    {
        _top = null;
    }
    
    public boolean isEmpty()
    {
        return _top == null;
    }
    
    public void enqueue(String pValue)
    {
        QueueNode newnode = new QueueNode(pValue);
        newnode.setNext(null);
        if(_top == null && _tail == null)
        {
            _top = newnode;
            _tail = newnode;
        }
        else
        {
            _tail.setNext(newnode);
            _tail = newnode;
        }
    }
    
    public String dequeue()
    {
        if(_top == null)
        {
            return null;
        }
        else
        {
            String value = _top.getValue();
            _top = _top.getNext();
            return value;
        }
    }
}
