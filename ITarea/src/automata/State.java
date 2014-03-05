/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automata;

import structures.trees.BinarySearchTree;

/**
 * Description. A state of the automata. It can be see also like a node of the "adjacency table"
 * of the automata.
 * 
 * @author jmloaiza
 */
public class State implements Comparable<State>{
    
    private boolean                             _initial;               //If it is an initial state
    private boolean                             _final;                 //If it is a final state
    private final String                        _id;                    //The name of the state
    private BinarySearchTree<ConnectionHandler> _connectionsTree;       //The connections to other states by keys
    private BinarySearchTree<ConnectionHandler> _inverseConnections;    //The connections from other states
    
    /**
     * Description. A state of an automata
     * 
     * @param pID Name of the state
     */
    public State(String pID){
        _connectionsTree = new BinarySearchTree<>();
        _inverseConnections = new BinarySearchTree<>();
        _initial = false;
        _final = false;
        _id = pID;
    }
    
    /**
     * Description. A state an automata
     * 
     * @param pID Name of the state
     * @param pIsInitial
     * @param pIsFinal 
     */
    public State(String pID, boolean pIsInitial, boolean pIsFinal){
        _initial = pIsInitial;
        _final = pIsFinal;
        _id = pID;
    }
    
    /**
     * Description. This method relation by a key this state with another. 
     * The transition is from this state to the other but not vice versa.
     * 
     * @param pState Key-relationed state
     * @param pKey Relational key
     */
    public void addRelation(State pState, String pKey){
        ConnectionHandler newConnection = new ConnectionHandler(pKey);
        structures.trees.Node<ConnectionHandler> connection = _connectionsTree.search(newConnection);
        if (connection != null){
            connection.getValue().addState(pState);
        } else {
            _connectionsTree.insert(newConnection);
            newConnection.addState(pState);
        }
    }
    
    /**
     * Description. Use this method to add a connection from other state.
     * 
     * @param pState 
     * @param pKey 
     */
    public void addInverseRelation(State pState, String pKey){
        ConnectionHandler newConnection = new ConnectionHandler(pKey);
        structures.trees.Node<ConnectionHandler> connection = _inverseConnections.search(newConnection);
        if (connection != null){
            connection.getValue().addState(pState);
        } else {
            _inverseConnections.insert(newConnection);
            newConnection.addState(pState);
        }
    }
    
    public void eraseConnection(State pState, String pKey){
        ConnectionHandler dummyHandler = new ConnectionHandler(pKey);
        structures.trees.Node<ConnectionHandler> handler = _connectionsTree.search(dummyHandler);
        if (handler != null){
            handler.getValue().eraseState(pState);
            pState.eraseInverseConnection(this, pKey);
        }
    }
    
    public void eraseInverseConnection(State pState, String pKey){
        ConnectionHandler dummyHandler = new ConnectionHandler(pKey);
        structures.trees.Node<ConnectionHandler> handler = _inverseConnections.search(dummyHandler);
        if (handler != null){
            handler.getValue().eraseState(pState);
        }
    }
    
    public void eraseMe(){
        structures.trees.Node<ConnectionHandler> connections = _connectionsTree.getRoot();
        structures.trees.Node<ConnectionHandler> inverse = _inverseConnections.getRoot();
        if (connections != null){
            eraseMeConnections(connections);
        }
        if (inverse != null){
            eraseMeInverseConnections(inverse);
        }
        
        
    }
       
    private void eraseMeConnections (structures.trees.Node<ConnectionHandler> pNode){
        if (pNode == null){
            return;
        }
        structures.lineal.Node<State> iNode = pNode.getValue().getRelations().getHead();
        while (iNode != null){
            iNode.getValue().eraseInverseConnection(this, pNode.getValue().getKey());
            iNode = iNode.getNext();
        }
        eraseMeConnections(pNode.getLeftChild());
        eraseMeConnections(pNode.getRightChild());
    }
    
    private void eraseMeInverseConnections (structures.trees.Node<ConnectionHandler> pNode){
        if (pNode == null){
            return;
        }
        structures.lineal.Node<State> iNode = pNode.getValue().getRelations().getHead();
        while (iNode != null){
            iNode.getValue().eraseConnection(this, pNode.getValue().getKey());
            iNode = iNode.getNext();
        }
        eraseMeInverseConnections(pNode.getLeftChild());
        eraseMeInverseConnections(pNode.getRightChild());
    }
    
    
    /**
     * Description. Set the initial property of the state
     * 
     * @param pIsInitial 
     */
    public void setInitial(boolean pIsInitial){
        _initial = pIsInitial;
    }
    
    /**
     * Description. Set the final property of the state
     * 
     * @param pIsFinal 
     */
    public void setFinal(boolean pIsFinal){
        _final = pIsFinal;
    }
    
    /**
     * Description. Returns if the state is initial or not
     * 
     * @return 
     */
    public boolean isInitial(){
        return _initial;
    }
    
    /**
     * Description. Returns if the state is final or not
     * 
     * @return 
     */
    public boolean isFinal(){
        return _final;
    }
    
    /**
     * Description. Returns the state name
     * 
     * @return 
     */
    public String getID(){
        return _id;
    }
    
    /**
     * Description. Returns a connection handler of a specific key
     * 
     * @param pKey
     * @return 
     */
    public ConnectionHandler getConnection(String pKey){
        ConnectionHandler dummy = new ConnectionHandler(pKey);
        structures.trees.Node<ConnectionHandler> searched = _connectionsTree.search(dummy);
        return (searched!=null)?searched.getValue():null;
    }
    
    public ConnectionHandler getInverseConnection(String pKey){
        ConnectionHandler dummy = new ConnectionHandler(pKey);
        structures.trees.Node<ConnectionHandler> searched = _inverseConnections.search(dummy);
        return (searched!=null)?searched.getValue():null;
    }
    
    @Override
    public int compareTo(State pState) {
        return _id.compareTo(pState.getID());
    }
    
}
