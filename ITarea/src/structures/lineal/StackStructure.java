/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.lineal;


/**
 *
 * @author joseph
 */
public interface StackStructure <T1> {
    
    /**
     * Inserta un valor en la ultima posición de la estructura
     *
     * @param pValue - Valor a insertar
     */
    public void push(T1 pValue);
    
    /**
     * Saca de la estructura el último elemento insertado
     *
     * @return Valor extraído
     */
    public T1 pop();
    
    /**
     * Devuelve el ultimo valor de la estructura sin borrarlo
     *
     * @return Ultimo valor insertado sin borrarlo
     */
    public T1 top();
    
    /**
     * Verifica si la estructura está vacía    
     *
     * @return True si está vacía o false de caso contrario
     */
    public boolean isEmpty();
    
    /**
     * Verifica se la etructura está lleno
     *
     * @return True si esta lleno o false en el caso contrarrio
     */
    public boolean isFull();
    
    /**
     * Borra todos los elementos de la pila
     */
    public void clear();
}
