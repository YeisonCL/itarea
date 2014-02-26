/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.trees;
/**
 *
 * @author joseph
 */
public class Node<T1 extends Comparable> {
    private Node<T1> _parent;
    private Node<T1> _rightChild;
    private Node<T1> _leftChild;
    private T1 _value;
       
    private boolean _color;    
    private boolean _readed;    
    
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    
    public Node(T1 pValue, Node<T1> pParent, Node<T1> pRightChild, Node<T1> pLeftChild, boolean pColor){
        _value = pValue;
        _parent = pParent;
        _rightChild = pRightChild;
        _leftChild = pLeftChild;
        _color = pColor;
        _readed = false;        
    }
    
    public Node(T1 pValue){
        this(pValue, null, null, null, Node.RED);
    }
    
    public boolean isBlack(){
        return _color == BLACK;
    }
    
    public boolean isRed(){
        return _color == RED;
    }
    
    public boolean isRightSon(){
        if (_parent == null){
            return false;
        } else if (_parent._rightChild == this){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isLeftSon(){
        if (_parent == null){
            return false;
        } else if (_parent._leftChild == this){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isLeaf(){
        if (_rightChild == null && _leftChild == null){
            return true;
        } else {
            return false;
        }
    }
    
    public Node<T1> getUncle(){
        if (_parent != null){       
            if (_parent.isLeftSon()){
                return getGrandfather().getRightChild();
            } else if (_parent.isRightSon()){
                return getGrandfather().getLeftChild();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }    
    
    public boolean getReaded(){
        return _readed;
    }
    
    public void setReaded(){
        _readed = true;
    }
    
    public void setUnreaded(){
        _readed = false;
    }
    
    public T1 getValue(){
        return _value;
    }        
    
    public Node<T1> getRightChild(){
        return _rightChild;
    }
    
    public Node<T1> getLeftChild(){
        return _leftChild;
    }
    
    public Node<T1> getGrandfather(){
        if (_parent != null){
            return _parent.getParent();
        } else {
            return null;
        }
    }
    
    public Node<T1> getParent(){
        return _parent;
    }
    
    public boolean getColor(){
        return _color;
    }
    
    public void setValue(T1 pValue){
        _value = pValue;
    }
    
    public void setRightChild(Node<T1> pChild){
        _rightChild = pChild;
    }
    
    public void setLeftChild(Node<T1> pChild){
        _leftChild = pChild;
    }
    
    public void setParent(Node<T1> pParent){
        _parent = pParent;
    }
    
    public void setColor(boolean pColor){
        _color = pColor;
    }
    
    
    
}
