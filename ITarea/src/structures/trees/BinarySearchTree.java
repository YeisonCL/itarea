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

    @Override
    public void insert(T1 pValue){
        insertAux(pValue);
    }
    
    @Override
    public Node<T1> erase (T1 pValue){        
        Node toErase = search(pValue);
        eraseAux(toErase);
        return toErase;
    }        
    
    @Override
    public Node<T1> search(T1 pValue){
        return searchAux(pValue);
    }           
    
}
