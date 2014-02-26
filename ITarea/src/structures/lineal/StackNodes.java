/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.lineal;


/**
 *
 * @author joseph
 */
public class StackNodes <T1> implements StackStructure <T1> {
    private Node<T1> _top;                
    
    @Override
    public void push(T1 pValue) {
        Node<T1> newNode = new Node<>(pValue);        
        if (_top != null){
            newNode.setNext(_top);
        } 
        _top = newNode;                
    }

    @Override
    public T1 pop() {
        if (_top == null){
            return null;
        } else {
            Node<T1> tmp = _top;
            _top = _top.getNext();
            return tmp.getValue();
        }
    }

    @Override
    public T1 top() {
        if (_top == null){
            return null;
        } else {
            return _top.getValue();
        }        
    }
    
    public void printMe(){
        if (_top != null){
            Node<T1> iNode = _top;
            System.out.print("[");
            while (iNode != null){
                System.out.print(iNode.getValue().toString());
                if (iNode.getNext() != null){
                    System.out.print(", ");
                }
                iNode = iNode.getNext();
            }
            System.out.println("]");
        }        
    }
       
    @Override
    public boolean isEmpty() {
        return _top == null;
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        _top = null;
    }
}
