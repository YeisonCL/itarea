/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.trees;

/**
 *
 * @author joseph
 */
public class BinarySearchTree <T1 extends Comparable> extends AbstractBinaryTree<T1> {
    private int _count;
    
    public BinarySearchTree(){
        _count = 0;
    }
    
    @Override
    public void insert(T1 pValue){
        insertAux(pValue);
        _count++;
    }
    
    @Override
    public Node<T1> erase (T1 pValue){        
        Node toErase = search(pValue);
        if (toErase != null) _count--;
        eraseAux(toErase);
        return toErase;
    }        
    
    @Override
    public Node<T1> search(T1 pValue){
        return searchAux(pValue);
    }           
    
    public int getCount(){
        return _count;
    }
    
}
