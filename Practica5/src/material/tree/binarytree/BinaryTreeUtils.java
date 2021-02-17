package material.tree.binarytree;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import material.tree.Position;
import material.tree.binarytree.LinkedBinaryTree.BTNode;
/**
 * @author Pavel
 *
 */
public class BinaryTreeUtils<E> {

    private BinaryTree<E> binTree;
    
    public BinaryTreeUtils(BinaryTree<E> binTree) {
        this.binTree = binTree;
    }

    /**
     * Given a tree the method returns a new tree where all left children become
     * right children and vice versa
     * @return the mirror of the tree
     */
    public BinaryTree<E> mirror() {
    	BinaryTree<E> arbol = null;
    	BinaryTree<E> espejo = null;
    	Position<E> raiz = this.binTree.root();
        if (this.binTree instanceof LinkedBinaryTree) {
            espejo = new LinkedBinaryTree<>();         
        }else if(this.binTree instanceof ArrayBinaryTree) {
            espejo = new ArrayBinaryTree<>(binTree.size());   
        }
        espejo.addRoot(raiz.getElement());
        mirrorAux(espejo, raiz, espejo.root());
        arbol = (BinaryTree<E>) espejo;
        return arbol;
    }

	private void mirrorAux(BinaryTree<E> espejo, Position<E> raiz, Position<E> raizespejo){
	    if(this.binTree.hasLeft(raiz)){
	    	espejo.insertRight(raizespejo, this.binTree.left(raiz).getElement());
	    }
	    if(this.binTree.hasRight(raiz)){
	    	espejo.insertLeft(raizespejo, this.binTree.right(raiz).getElement());
	    }
	    if(!this.binTree.isLeaf(raiz)){
	        if(this.binTree.hasLeft(raiz))
	            mirrorAux(espejo, this.binTree.left(raiz), espejo.right(raizespejo));
	        if(this.binTree.hasRight(raiz))
	            mirrorAux(espejo, this.binTree.right(raiz), espejo.left(raizespejo));
	    }
	}


    /**
     * Determines whether the element e is the tree or not
     * @param e the element to check
     * @return TRUE if e is in the tree, FALSE otherwise
     */
    public boolean contains(E e) {
        boolean pertenece = false;
        Iterator<Position<E>> it = binTree.iterator();
        while(it.hasNext()) {
        	Position<E> nodo = it.next();
        	if(nodo.getElement().equals(e)) {
        		pertenece = true;
        	}
        }
        return pertenece;
    }

    /**
     * Determines the level of a node in the tree
     * @param p the position to check
     * @return the level of the position in the tree
     */
    public int level(Position<E> p) {
       if(binTree.isRoot(p)) {
    	   return 1;
       }
       return 1 + level(binTree.parent(p));
    }
}
