/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automata;

import structures.lineal.SimpleLinkedList;
import structures.lineal.StackNodes;
import structures.trees.BinarySearchTree;

/**
 * Considerations. This class use the singleton model, so, to instance it you
 * have to use the getInstance method. Also, it is thread safe.
 * 
 * @author jmloaiza
 */

public class Automata {
    private StackNodes<StateContainer> _evaluationStack;    //Stack where all the posibles paths in an evaluation are going to be save
    private BinarySearchTree<State> _states;                //All the automata states
    private BinarySearchTree<String> _alphabet;             //The automata alphabet
    private State _initialState;                            //Initial state of the automata
    
    
    private static Automata _instance;
    
    public static Automata getInstance(){
        if (_instance == null){
            synchronized(Automata.class){
                if (_instance == null){
                    _instance = new Automata();
                }
            }
        }
        return _instance;
    }
    
    private Automata(){
        _evaluationStack = new StackNodes<>();
        _states = new BinarySearchTree<>();
        _alphabet = new BinarySearchTree<>();
        _initialState = null;
    }
    
    /**
     * Description. This method only creates a new state.
     * 
     * @param pID Name of the new state
     */
    public synchronized void newState(String pID){
        State newState = new State(pID);
        _states.insert(newState);
        System.out.println("Inserted:"+ pID);
    }
    
    /**
     * Description. Use this method to set the initial state. Just one start state
     * can exist, if you set another state it will override the previous one.
     * Considerations. The initial state has to exist before set it
     * 
     * @param pID Name of the state to set initial
     */
    public synchronized void setInitialState(String pID){
        State dummy = new State(pID);
        structures.trees.Node<State> searched = _states.search(dummy);
        if (searched == null){
            System.out.println("Initial state not found: " + pID);
        } else {
            _initialState = searched.getValue();
            _initialState.setInitial(true);
        }
    }
    
    /**
     * Description. This method add a word or key to the alphabet of the automata
     * 
     * @param pWord 
     */
    public synchronized void addWordToAlphabet(String pWord){
        _alphabet.insert(pWord);
    }
    
    /**
     * Descripition. This method returns the index+1 of the end char of a word
     * that belongs to the alphabet and starts in the start index specified.
     * If the given chain doesn't contains a word of the alphabet this method
     * will return -1.
     * Example:
     *          Alphabet: {hello,house,car}
     *          Chain: ahellobc
     *          StartIndex: 1 , this is the 'h' char index
     *          Return: 6 , this is the 'o' char index + 1
     * 
     * @param pChain 
     * @param pStartIndex
     * @return The index+1 of the final char of a word in the alphabet
     */
    public synchronized int findNextKey(String pChain, int pStartIndex){
        for (int i = pChain.length(); i > pStartIndex; i--){
            if (_alphabet.search(pChain.substring(pStartIndex, i)) != null){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Description. Use this method to set final states. It can be more
     * than one final state.
     * Considerations. The state has to exist before set it.
     * 
     * @param pID Name of the state to set final
     */
    public synchronized void setFinalState(String pID){
        State dummy = new State(pID);
        structures.trees.Node<State> searched = _states.search(dummy);
        if (searched == null){
            System.out.println("Final state not found: " + pID);
        } else {
            searched.getValue().setFinal(true);
        }
    }
    
    /**
     * Description. Use this method to set the transitions between states.
     * Considerations. The states have to exist before use them
     * 
     * @param pStartID Goes from here
     * @param pEndID To here
     * @param pValue When read this value
     */
    public synchronized void addTransition(String pStartID, String pEndID, String pValue){
        State dummyStart = new State(pStartID);
        State dummyFinal = new State(pEndID);
        structures.trees.Node<State> start = _states.search(dummyStart);
        structures.trees.Node<State> end = _states.search(dummyFinal);
        if (start == null || end == null){
            System.out.println("State(s) not found in transition: " + pStartID + "->"+pEndID);
        } else {
            start.getValue().addRelation(end.getValue(), pValue);
        }
        
    }
    
    /**
     * Description. Use this method to validate a chain of characters in the automata.
     * It will return true if exist a path between states that satisfies the chain and
     * finish in a final state.
     * 
     * @param pChain
     * @return Boolean 
     */
    public synchronized boolean evaluate(String pChain){
        
        //We add the start node to the evaluation stack:
        StateContainer container = new StateContainer(0, _initialState);
        _evaluationStack.push(container);        
        
        int index;
        while (!_evaluationStack.isEmpty()){ //If evaluation stack is empty is because all the elements have been checked
            
            container = _evaluationStack.pop(); //We get the next element to be evaluated
            index = container.getIndex(); //This element have the corresponding index in the chain
            if (index == pChain.length()){ //If the index+1 is equals to the length means all chain have been evaluated
                if (container.getState().isFinal()){ //If the state is final the chain is correct :D
                    _evaluationStack.clear();
                    return true;
                }
                
            } else { //If we haven't check al chain add relations of the actual state to the stack to be evaluated
                int finalIndex = findNextKey(pChain, index);
                
                if (finalIndex != -1){ //If finalIndex == -1 is because there is no correspondency between nextKey and the alphabet
                    
                    String key = pChain.substring(index, finalIndex);
                    ConnectionHandler connection = container.getState().getConnection(key);
                    if (connection != null){
                        addRelationsToStack(connection.getRelations(), finalIndex);
                    }
                    
                } else {
                    
                    return false;
                    
                }
                    
            }
        }
        return false;
    }
    
    /**
     * Description. Internal manage method, used in evaluate method. It adds a list of states to the _evaluationStack
     * variable that satisfies a character of the chain.
     * 
     * @param pList List of states that are going to be added
     * @param pIndex Index of the current character
     */
    private void addRelationsToStack(SimpleLinkedList<State> pList, int pIndex){
        structures.lineal.Node<State> iNode = pList.getHead();
        StateContainer iContainer;
        while (iNode != null){
            iContainer = new StateContainer(pIndex, iNode.getValue());
            _evaluationStack.push(iContainer);
            iNode = iNode.getNext();
        }
    }
    
//    public static void main(String[] args) {
//        Automata a = Automata.getInstance();
//        
//        a.newState("s1");
//        a.newState("s2");
//        a.newState("s3");
//        a.newState("s4");
//        a.newState("s5");
//        a.newState("s6");
//        a.newState("s7");
//        
//        a.setInitialState("s7");
//        a.setFinalState("s1");
//        a.setFinalState("s3");
//        
//        a.addWordToAlphabet("a");
//        a.addWordToAlphabet("l");
//        a.addWordToAlphabet("s");
//        a.addWordToAlphabet("casa");
//        
//        a.addTransition("s7", "s1", "a");
//        a.addTransition("s7", "s2", "a");
//        a.addTransition("s7", "s7", "a");
//        a.addTransition("s7", "s5", "casa");
//        a.addTransition("s5", "s1", "a");
//        a.addTransition("s1", "s3", "s");
//        
//        
//        System.out.println(a.evaluate("acasaas4"));
//    }
}
