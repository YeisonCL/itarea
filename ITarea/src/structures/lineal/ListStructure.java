/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.lineal;


/**
 *
 * @author joseph
 */
public interface ListStructure <T1 extends Comparable> {
    
    /**
     * Inserta un valor en orden en la lista
     * 
     * @param pValue - Valor a insertar
     */
    public void insert(T1 pValue);
    
    /**
     * Borra el valor igual al dado de la lista
     * 
     * @param pValue - Valor a borrar
     * @return Nodo que contiene el valor borrado por movimiento de referencias o null si no existe el dato
     */
    public Node<T1> erase(T1 pValue);
    
    /**
     * Borra el último elemento de la lista
     *
     * @return Nodo eliminado
     */
    public Node<T1> eraseLastOne();
    
    /**
     * Borra el primer elemento de la lista
     *
     * @return Nodo eliminado
     */
    public Node<T1> eraseFirstOne();
    
    /**
     * Busca un valor dado en la lista
     *
     * @param pValue - Valor a buscar
     * @return Nodo con el valor buscado o null si no existe un nodo que contenga el valor
     */
    public Node<T1> search(T1 pValue);
    
    /**
     * Inserta un valor al inicio de la lista
     *
     * @param pValue - Valor a insertar
     */
    public void insertBeginning(T1 pValue);
    
    /**
     * Inserta un valor al final de la lista
     *
     * @param pValue - Valor a insertar
     */
    public void insertEnd(T1 pValue);
    
}
