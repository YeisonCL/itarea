/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.lineal;


/**
 *
 * @author joseph
 */
public class SimpleLinkedList <T1 extends Comparable> implements ListStructure<T1> {
    private Node<T1> _head;
    private Node<T1> _tail;
  
    public SimpleLinkedList(){
        _head = _tail = null;
    }
    
    public Node<T1> getHead(){
        return _head;
    }
    
    public void setHead(Node<T1> pNode){
        _head = pNode;        
    }
    
    @Override
    public void insert(T1 pValue){
        Node<T1> newNode = new Node<>(pValue);
        if (_head == null){
            _head = _tail = newNode;
            newNode.setNext(null);
        } else if (pValue.compareTo(_head.getValue()) <= 0){
            newNode.setNext(_head);
            _head = newNode;
        } else if (pValue.compareTo(_tail.getValue()) >= 0){
            _tail.setNext(newNode);
            newNode.setNext(null);
            _tail = newNode;
        } else {
            Node<T1> iNode = _head;
            while (pValue.compareTo(iNode.getNext().getValue()) > 0){
                iNode = iNode.getNext();
            }
            newNode.setNext(iNode.getNext());
            iNode.setNext(newNode);                      
        }
    }
    
    @Override
    public Node<T1> erase (T1 pValue){
        if (_head == null)        {
            return null;
        } else if (_head.getValue().compareTo(pValue) == 0){
            Node<T1> tmp = _head;
            if (_head == _tail){                
                _head = _tail = null;
                return tmp;
            } else {
                _head = _head.getNext();
                return tmp;
            }            
        } else {
            Node<T1> iNode = _head;
            while (iNode.getNext() != null && iNode.getNext().getValue().compareTo(pValue) != 0){
                iNode = iNode.getNext();
            }
            if (iNode.getNext() == null){
                return null;
            } else if (iNode.getNext() == _tail){
                Node<T1> tmp = _tail;
                iNode.setNext(null);
                _tail = iNode;
                return tmp;
            } else {
                Node<T1> tmp = iNode.getNext();
                iNode.setNext(iNode.getNext().getNext());
                return tmp;
            }            
        }        
    }
    
    @Override
    public Node<T1> search(T1 pValue){
        if (_head == null){
            return null;
        } else if (_head.getValue().compareTo(pValue) == 0){
            return _head;
        } else if (_tail.getValue().compareTo(pValue) == 0){
            return _tail;
        } else {
            Node<T1> iNode = _head.getNext();
            while (iNode != null && iNode.getValue().compareTo(pValue) != 0){
                iNode = iNode.getNext();
            }                                     
            return iNode;            
        }
    }

    @Override
    public void insertBeginning(T1 pValue) {
        Node<T1> newNode = new Node<>(pValue);
        if (_head == null){
            _head = _tail = newNode;
        } else {
            newNode.setNext(_head);
            _head = newNode;
        }        
    }

    @Override
    public void insertEnd(T1 pValue) {
        Node<T1> newNode = new Node<>(pValue);
        if (_head == null){
            _head = _tail = newNode;
        } else {
            _tail.setNext(newNode);
            _tail = newNode;
        }        
    }
    
    @Override
    public Node<T1> eraseLastOne() {
        return erase(_tail.getValue());
    }

    @Override
    public Node<T1> eraseFirstOne() {
        return erase(_head.getValue());
    }
    
    public void printMe(){
        System.out.print("[");
        Node<T1> iNode = _head;
        while (iNode != null){
            System.out.print(iNode.getValue().toString());
            if (iNode.getNext() != null){
                System.out.print(", ");
            }
            iNode = iNode.getNext();
        }
        System.out.println("]");
    }
    
    public void printMe(Node<T1> pLimit){
        System.out.print("[");
        Node<T1> iNode = _head;
        while (iNode != pLimit.getNext()){
            System.out.print(iNode.getValue().toString());
            if (iNode.getNext() != null){
                System.out.print(", ");
            }
            iNode = iNode.getNext();
        }
        System.out.println("]");
    }
}
