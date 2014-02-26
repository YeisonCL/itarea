/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.trees;
/**
 *
 * @author joseph
 */
public abstract class AbstractBinaryTree <T1 extends Comparable>{    
    protected Node<T1> _root;
    public static int PRE_ORDER_TRAVERSAL = 0;
    public static int IN_ORDER_TRAVERSAL = 1;
    public static int POST_ORDER_TRAVERSAL = 2;    
    
    public abstract void insert(T1 pValue);
    
    public abstract Node<T1> erase(T1 pValue);
    
    public abstract Node<T1> search(T1 pValue);
    
    public Node<T1> getRoot(){
        return _root;
    }
    
    protected Node<T1> findGreater(Node<T1> pNode){        
        Node<T1> iNode = pNode;
        while (iNode.getRightChild() != null){
            iNode = iNode.getRightChild();
        }        
        return iNode;
    }
    
    private Node<T1> eraseLeaf(Node<T1> pToErase){
        if (pToErase.isLeftSon()){
            pToErase.getParent().setLeftChild(null);
        } else if (pToErase.isRightSon()){
            pToErase.getParent().setRightChild(null);
        } else {
            _root = null;
        }
        return pToErase.getParent();
    }
    
    private Node<T1> eraseWithLeftNull(Node<T1> pToErase){
        pToErase.getRightChild().setParent(pToErase.getParent());
        if (pToErase.isLeftSon()){
            pToErase.getParent().setLeftChild(pToErase.getRightChild());
        } else if (pToErase.isRightSon()){
            pToErase.getParent().setRightChild(pToErase.getRightChild());
        } else {
            _root = pToErase.getRightChild();
        }
        return pToErase.getRightChild();
    }
    
    private Node<T1> eraseWithRightNull(Node<T1> pToErase){
        pToErase.getLeftChild().setParent(pToErase.getParent());
        if (pToErase.isLeftSon()){
            pToErase.getParent().setLeftChild(pToErase.getLeftChild());
        } else if (pToErase.isRightSon()){
            pToErase.getParent().setRightChild(pToErase.getLeftChild());
        } else {
            _root = pToErase.getLeftChild();
        }
        return pToErase.getLeftChild();
    }
    
    private Node<T1> eraseWithChilds(Node<T1> pToErase){
        Node<T1> replacement = findGreater(pToErase.getLeftChild());
        Node<T1> toBalance = replacement.getParent();
        if (replacement.getParent() != pToErase){            
            
            if (replacement.getLeftChild() != null){
                replacement.getLeftChild().setParent(replacement.getParent());
            }            
            
            replacement.getParent().setRightChild(replacement.getLeftChild());
            replacement.setLeftChild(pToErase.getLeftChild());
            pToErase.getLeftChild().setParent(replacement);
        } else {
            toBalance = replacement;
        }
        
        if (pToErase.isLeftSon()){
            pToErase.getParent().setLeftChild(replacement);
        } else if (pToErase.isRightSon()){
            pToErase.getParent().setRightChild(replacement);
        }else{
            _root = replacement;
        }
        replacement.setParent(pToErase.getParent());
        replacement.setRightChild(pToErase.getRightChild());
        pToErase.getRightChild().setParent(replacement);
        return toBalance;
    }
    
    protected Node<T1> eraseAux(Node<T1> pToErase){
        Node<T1> toBalance;
        if (pToErase == null){            
            return null;
            
        } else if (pToErase.isLeaf()){            
            toBalance = eraseLeaf(pToErase);
            
        } else if (pToErase.getRightChild() == null){
            toBalance = eraseWithRightNull(pToErase);
            
        } else if (pToErase.getLeftChild() == null){
            toBalance = eraseWithLeftNull(pToErase);
            
        } else {            
            toBalance = eraseWithChilds(pToErase);            
        }      
        return toBalance;
    }
    
    protected Node<T1> searchAux(T1 pValue){
        Node<T1> iNode = _root;
        boolean find = false;
        while (!find && iNode != null){
            if (iNode.getValue().compareTo(pValue) == 0){
                find = true;
            } else if (pValue.compareTo(iNode.getValue()) < 0) {               
                iNode = iNode.getLeftChild();
            } else {
                iNode = iNode.getRightChild();
            }
        }        
        return iNode;
    }
    
    protected Node<T1> insertAux(T1 pValue){        
        Node<T1> newNode = new Node<>(pValue);
        if (_root == null){
            _root = newNode;
        } else {
            Node<T1> iNode = _root;
            boolean onInsertion = true;
            
            while (onInsertion){                
                
                if (pValue.compareTo(iNode.getValue()) < 0){
                    if (iNode.getLeftChild() != null){
                        iNode = iNode.getLeftChild();
                    } else {
                        iNode.setLeftChild(newNode);
                        newNode.setParent(iNode);
                        onInsertion = false;
                    }
                    
                } else {
                    if (iNode.getRightChild() != null){
                        iNode = iNode.getRightChild();
                    } else {
                        iNode.setRightChild(newNode);
                        newNode.setParent(iNode);
                        onInsertion = false;
                    }
                }
            }  
        }
        return newNode;
    }
    
    
    protected void leftLeftRotation(Node<T1> pNode){              
        pNode.getParent().setRightChild(pNode.getLeftChild());
        
        if (pNode.getLeftChild() != null){
            pNode.getLeftChild().setParent(pNode.getParent());
        }
        
        if (pNode.getParent().isLeftSon()){
            pNode.getGrandfather().setLeftChild(pNode);
        } else if (pNode.getParent().isRightSon()){
            pNode.getGrandfather().setRightChild(pNode);
        } else {
            _root = pNode;
        }
        
        pNode.setLeftChild(pNode.getParent());
        pNode.setParent(pNode.getGrandfather());
        pNode.getLeftChild().setParent(pNode);        
    }
    
    protected void rightRightRotation(Node<T1> pNode){
        pNode.getParent().setLeftChild(pNode.getRightChild());
        
        if (pNode.getRightChild() != null){
            pNode.getRightChild().setParent(pNode.getParent());
        }
        
        if (pNode.getParent().isLeftSon()){
            pNode.getGrandfather().setLeftChild(pNode);            
        } else if (pNode.getParent().isRightSon()){
            pNode.getGrandfather().setRightChild(pNode);
        } else {
            _root = pNode;
        }
        
        pNode.setRightChild(pNode.getParent());
        pNode.setParent(pNode.getGrandfather());
        pNode.getRightChild().setParent(pNode);        
    }
    
    public void printTree(int pTraversal){            
        if (_root == null){
            System.out.print("null tree");
        } else if (pTraversal == PRE_ORDER_TRAVERSAL){
            preOrderTraversal();
        } else if (pTraversal == IN_ORDER_TRAVERSAL){
            inOrderTraversal();           
        } else if (pTraversal == POST_ORDER_TRAVERSAL){
            postOrderTraversal();
        }
        System.out.println("");
    }       
    
    private void postOrderTraversal(){
        Node<T1> iNode = _root;
        boolean fromRight = false;
        boolean up = false;
        while (iNode != null){
            
            if (!up){
                if (iNode.getLeftChild() != null){
                    iNode = iNode.getLeftChild();
                } else if (iNode.getRightChild() != null){
                    iNode = iNode.getRightChild();                    
                } else {
                    System.out.print(iNode.getValue().toString());
                    //System.out.print(" parent:"); iNode.getParent().getValue().printValue();
                    System.out.print(" - ");
                    if (iNode.isRightSon()){
                        fromRight = true;
                    } else if (iNode.isLeftSon()) {
                        fromRight = false;
                    }
                    
                    iNode = iNode.getParent();
                    up = true;
                }
            } else if (up && !fromRight){
                if (iNode.getRightChild() != null){
                    iNode = iNode.getRightChild();
                    up = false;
                } else {
                    System.out.print(iNode.getValue().toString());
                    System.out.print(" - ");
                    
                    if (iNode.isRightSon()){
                        fromRight = true;
                    } else if (iNode.isLeftSon()) {
                        fromRight = false;
                    }                    
                    iNode = iNode.getParent();
                }
            } else if (up && fromRight) {
                System.out.print(iNode.getValue().toString());
                System.out.print(" - ");
                if (iNode.isRightSon()){
                    fromRight = true;
                } else if (iNode.isLeftSon()) {
                    fromRight = false;
                }
                iNode = iNode.getParent();
            }
        }
    }
    
    private void inOrderTraversal(){
        Node<T1> iNode = _root;
        boolean fromRight = false;
        boolean up = false;
        while (iNode != null){
            if (!up){
                if (iNode.getLeftChild() != null){
                    iNode = iNode.getLeftChild();
                } else if (iNode.getRightChild() != null) {
                    System.out.print(iNode.getValue().toString());
                    System.out.print(" - ");
                    iNode = iNode.getRightChild();
                } else {                    
                    System.out.print(iNode.getValue().toString());
                    System.out.print(" - ");
                    
                    if (iNode != _root && iNode.getParent().getRightChild() == iNode){
                        fromRight = true;
                    } else if (iNode != _root) {
                        fromRight = false;
                    }                    
                    iNode = iNode.getParent();
                    up = true;                    
                }
            } else if (up && !fromRight){
                System.out.print(iNode.getValue().toString());
                System.out.print(" - ");
                
                if (iNode.getRightChild() != null){
                    iNode = iNode.getRightChild();
                    up = false;
                } else {
                    if (iNode != _root && iNode.getParent().getRightChild() == iNode){
                        fromRight = true;
                    } else if (iNode != _root) {
                        fromRight = false;
                    }
                    iNode = iNode.getParent();
                }
            } else if (up && fromRight){
                if (iNode != _root && iNode.getParent().getRightChild() == iNode){
                    fromRight = true;
                } else if (iNode != _root) {
                    fromRight = false;
                }
                iNode = iNode.getParent();
            }
        }
    }
    
    private void inverseOrderTraversal(){
        Node<T1> iNode = _root;
        boolean fromLeft = false;
        boolean up = false;
        while (iNode != null){
            if (!up){
                if (iNode.getRightChild() != null){
                    iNode = iNode.getRightChild();
                } else {
                    System.out.print(iNode.getValue().toString());
                    System.out.print(" - ");
                    if (iNode.getLeftChild() != null){
                        iNode = iNode.getLeftChild();
                    } else {
                        if (iNode.isRightSon()){
                            fromLeft = false;
                        } else if (iNode.isLeftSon()){
                            fromLeft = true;
                        }
                        iNode = iNode.getParent();
                        up = true;
                    }
                }
            } else if (up && !fromLeft){
                System.out.print(iNode.getValue().toString());
                System.out.print(" - ");
                if (iNode.getLeftChild() != null){
                    iNode = iNode.getLeftChild();
                    up = false;                    
                } else {
                    if (iNode.isLeftSon()){
                        fromLeft = true;
                    }
                    iNode = iNode.getParent();
                }
            } else if (up && fromLeft){
                if (iNode.isRightSon()){
                    fromLeft = false;
                }
                iNode = iNode.getParent();
            }
        }
    }
    
    private void preOrderTraversal(){
        Node<T1> iNode = _root;
        boolean fromRight = false;        
        boolean up = false;
        while (iNode != null){
            if (!up){ 
                //If we are not going up we print
                System.out.print(iNode.getValue().toString());
                System.out.print(" - ");
                
                //Check if exists a left child
                if (iNode.getLeftChild() != null){
                    iNode = iNode.getLeftChild();
                    
                //if not, check if exist a right child
                } else if (iNode.getRightChild() != null){
                    iNode = iNode.getRightChild();
                
                //if not, check what node is this
                } else {
                    
                    if (iNode != _root && iNode.getParent().getRightChild() == iNode){
                        fromRight = true;
                    } else if (iNode != _root){
                        fromRight = false;
                    }                               
                    
                    up = true;
                    iNode = iNode.getParent();
                }
                
            // if we are going up ckeck if we already check the right subtree
            } else if (up && !fromRight){
                //Check if exist a right child
                if (iNode.getRightChild() != null){
                    iNode = iNode.getRightChild();
                    up = false;               
                    
                // If not, check what what node is this
                } else {
                    if (iNode != _root && iNode.getParent().getRightChild() == iNode){
                        fromRight = true;
                    } else if (iNode != _root){
                        fromRight = false;
                    }
                    iNode = iNode.getParent();
                }
            
            // If we are going up and we already check right subtree
            } else if (up && fromRight){
                // Check what node is this
                if (iNode != _root && iNode.getParent().getRightChild() == iNode){
                    fromRight = true;
                } else if (iNode != _root) {
                    fromRight = false;
                }                        
                iNode = iNode.getParent();
            }                    
        }            
    }               
    
    /**
     *
     * @param <T2>
     * @param pNode
     * @return
     */
    public static int height(Node pNode){
        if (pNode == null){
            return 0;
        }
        
        int leftCount = 0;
        int rightCount = 0;                
        
        if (pNode.getLeftChild() != null){           
            leftCount = height(pNode.getLeftChild());
        } 
        if (pNode.getRightChild() != null){            
            rightCount = height(pNode.getRightChild());
        }
        
        if (leftCount>rightCount){
            return leftCount + 1;
        } else {
            return rightCount + 1;
        }
    }                
}
