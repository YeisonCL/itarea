/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.lineal;


/**
 * Nodos. Estructura general en la que se basan las listas, estos almacenan un dato
 * y permiten tener nodos asociados - nodo anterior y nodo siguiente.
 *
 * @author joseph
 */
public class Node<T1>{
    private T1 _value;
    private Node<T1> _next;
    private Node<T1> _previous;        
    
    /**
     * Constructor. Crea una instancia de un nodo
     *
     * @param pValue - Valor que se desea dar al nodo
     */
    public Node(T1 pValue){
        this (pValue, null, null);
    }
    
    /**
     * Constructor. Crea una instancia de un nodo
     *
     * @param pValue - Valor que se desea dar al nodo
     * @param pPrevious - Nodo anterior
     * @param pNext - Nodo siguiente
     */
    public Node(T1 pValue, Node<T1> pPrevious, Node<T1> pNext){
        _value = pValue;
        _previous = pPrevious;
        _next = pNext;        
    }
    
    /**
     * Obtiene el valor del nodo
     *
     * @return Valor contenido en el nodo
     */
    public T1 getValue(){
        return _value;
    }
    
    /**
     * Obtiene el siguiente nodo
     *
     * @return Referencia al siguiente nodo
     */
    public Node<T1> getNext(){
        return _next;
    }
    
    /**
     * Obtiene el nodo anterior
     *
     * @return Referencia al nodo anterior
     */
    public Node<T1> getPrevious(){
        return _previous;
    }
    
    /**
     * Cambia el valor del nodo
     *
     * @param pValue - Nuevo valor
     */
    public void setValue(T1 pValue){
        _value = pValue;
    }        
    
    /**
     * Establece el proximo nodo
     *
     * @param pNode - Proximo nodo
     */
    public void setNext(Node<T1> pNode){
        _next = pNode;
    }
    
    /**
     * Establece el nodo anterior
     *
     * @param pNode - Nodo anterior
     */
    public void setPrevious(Node<T1> pNode){
        _previous = pNode;
    }
    
}
