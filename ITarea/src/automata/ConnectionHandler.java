/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automata;

import structures.lineal.SimpleLinkedList;

/**
 * Description. This class is used to handle the connections to multiple states
 * that satisfies an specific key.
 * 
 * @author jmloaiza
 */
public class ConnectionHandler implements Comparable<ConnectionHandler>{
    private final SimpleLinkedList<State> _relations; //List of states that are linked    
    private final String _key; //Key that satisfies the relation
    
    /**
     * 
     * @param pKey Key that satisfies the relation
     */
    public ConnectionHandler(String pKey){
        _key = pKey;
        _relations = new SimpleLinkedList<>();
    }
    
    /**
     * Description. Add a state that correspond with this key
     * 
     * @param pState 
     */
    public void addState(State pState){
        _relations.insertBeginning(pState);
    }
    
    /**
     * Description. This method returns the list of the key-relationed states
     * 
     * @return State list
     */
    public SimpleLinkedList<State> getRelations(){
        return _relations;
    }
    
    /**
     * Description. This method returns the key that links the states
     * 
     * @return Relational key
     */
    public String getKey(){
        return _key;
    }
    
    @Override
    public int compareTo(ConnectionHandler pEntry) {
        return _key.compareTo(pEntry.getKey());
    }
}
