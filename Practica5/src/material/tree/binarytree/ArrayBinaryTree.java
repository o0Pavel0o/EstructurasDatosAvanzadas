package material.tree.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import material.tree.*;
import material.tree.binarytree.ArrayBinaryTree.BTPos;
import material.tree.binarytree.LinkedBinaryTree.BTNode;
import material.tree.iterator.BFSIteratorFactory;
import material.tree.iterator.TreeIteratorFactory;

/**
 * *
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro
 * @param <E> The type of the elements in the tree
 * @see BinaryTree
 */
/**
 * @author Pavel
 *
 */
public class ArrayBinaryTree<E> implements BinaryTree<E> {

    public class BTPos<T> implements Position<T> {

        private T element;
        private int index;
        private ArrayBinaryTree<T> myTree;

        public BTPos(ArrayBinaryTree<T> myTree, T element, int index) {
            this.myTree = myTree;
            this.element = element;
            this.index = index;
        }

        @Override
        public T getElement() {
            return element;
        }
        
        public int getIndex() {
            return index;
        }

        public void setElement(T element) {
            this.element = element;
        }
        
        public ArrayBinaryTree<T> getMyTree() {
            return myTree;
        }
        
        public void setMyTree(ArrayBinaryTree<T> myTree) {
            this.myTree = myTree;
        }
    }

   private class ArrayBinaryTreeIterator<T> implements Iterator<Position<T>> {
	    private int i = 0;
        @Override
        public boolean hasNext() {
        	return (i < size);
        }

        @Override
        public Position<T> next() {
        Position<T>  aux = null;
      	boolean sePuede = false;
      	int auxI = 0;
      	
      	 if(i < size) {
      	   aux =  (Position<T>) tree[i];
      	   
      	   if(aux == null) {
      		   auxI = i++;
      		   while(!sePuede) {
      			   aux = (Position<T>) tree[auxI];
      			   if(aux!= null) {
      				 sePuede = true;
      			   }
      			   auxI++;  
           	  }
      		   i = auxI;
      	   } 
      	 }
      	 i++;
         return aux;
      } 	
    }

    private BTPos<E>[] tree;
    private int size;
    private TreeIteratorFactory<E> iteratorFactory;

    /**
     * Constructor of the class.
     */
    public ArrayBinaryTree(int capacity) {
        this.size = 0;
        this.iteratorFactory = new BFSIteratorFactory<>();
        this.tree = (BTPos<E>[]) new BTPos[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
    	return (size == 0);
    }

    @Override
    public boolean isInternal(Position<E> p) throws IllegalStateException {
    	 checkPosition(p);
         return (hasLeft(p) || hasRight(p));
    }

    @Override
    public boolean isLeaf(Position<E> p) throws IllegalStateException {
    	 return !isInternal(p);
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalStateException {
    	 checkPosition(p);
         return (p == root());
    }

    @Override
    public boolean hasLeft(Position<E> p) throws IllegalStateException {
    	BTPos<E> nodo = checkPosition(p);
    	return (tree[nodo.getIndex()*2] != null); //&& 2*nodo.getIndex()<this.tree.length
    }

    @Override
    public boolean hasRight(Position<E> p) throws IllegalStateException {
    	BTPos<E> nodo = checkPosition(p);
    	return (tree[nodo.getIndex()*2+1] != null); //&& 2*nodo.getIndex()+1<this.tree.length
    }

    @Override
    public Position<E> root() throws IllegalStateException {
    	if (isEmpty()) {
            throw new IllegalStateException("The tree is empty");
        } else {
            return tree[1];
        }
    }    	

    @Override
    public Position<E> left(Position<E> p) throws IllegalStateException, IndexOutOfBoundsException {
        BTPos<E> nodo = checkPosition(p);
        if(hasLeft(nodo)) {
        	return this.tree[nodo.getIndex()*2];
        }        
        throw new IllegalStateException("No left child");    	
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalStateException, IndexOutOfBoundsException {
    	BTPos<E> nodo = checkPosition(p);
        if(hasLeft(nodo)) {
        	return this.tree[nodo.getIndex()*2+1];
        }        
        throw new IllegalStateException("No right child");   
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalStateException, IndexOutOfBoundsException {
    	BTPos<E> nodo = checkPosition(p);
    	if (isRoot(p)){
    		throw new IllegalStateException("The root dont have father");
    	}
    	int i = (nodo.getIndex()) / 2;
    	return this.tree[i];
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalStateException {
    	List<Position<E>> children = new ArrayList<>();
        
        if (hasLeft(p)) {
            children.add(left(p));
        }
        if (hasRight(p)) {
            children.add(right(p));
        }
        return children;
    }

    @Override
    public Iterator<Position<E>> iterator() {
    	 return this.iteratorFactory.createIterator(this);
    }

    @Override
    public E replace(Position<E> p, E e) throws IllegalStateException {
    	BTPos<E> nodo = checkPosition(p);
        E anterioElemento = p.getElement();
        nodo.setElement(e);
        return anterioElemento;
    }

    // Additional Methods
    @Override
    public Position<E> sibling(Position<E> v) throws IllegalStateException,
            IndexOutOfBoundsException {
    	BTPos<E> p = checkPosition(v);
        BTPos<E> parent = (BTPos<E>) parent(p);
        if(right(parent)==p) {
        	return left(parent);
        }else if(left(parent)==p) {
        	return right(parent);
        }else {
        	throw new IllegalStateException("No sibling");
        }
   
    }

    @Override
    public Position<E> addRoot(E e) throws IllegalStateException {
    	if (!isEmpty()) {
            throw new IllegalStateException("Already have a root");
        }
        size = 1;
        BTPos<E> node = new BTPos<E>(this, e, 1);
        this.tree[1] = node;
        return node;
        
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) throws IllegalStateException {
        BTPos<E> nodo = checkPosition(p);
        int iparent = nodo.getIndex();
        int ileft = iparent*2;
        if (!hasLeft(nodo)){
            if(tree.length < (ileft*2)+1){
                BTPos<E>[] arbol =  new BTPos[tree.length*2];
                arbol = this.tree;
                this.tree = new BTPos[ileft*2];
                int cont = 0;
                for(BTPos<E> nodo2:arbol){
                    tree[cont] = nodo2;
                    cont++;
                }
            }
            BTPos<E> left2 = new BTPos<>(this, e, ileft);
            tree[ileft] = left2;
            size++;
            return left2;
        }
        throw new IllegalStateException("Have left already");
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) throws IllegalStateException {
        BTPos<E> nodo = checkPosition(p);
        int iparent = nodo.getIndex();
        int iright = iparent*2+1;
        if (!hasRight(nodo)){
            if(tree.length < (iright*2)+1){
                BTPos<E>[] arbol =  new BTPos[tree.length*2];
                arbol = this.tree; 
                this.tree = new BTPos[iright*2];
                int cont = 0;

                for(BTPos<E> nodo2:arbol){
                    tree[cont] = nodo2;
                    cont++;
                }
            }
            BTPos<E> right2 = new BTPos<>(this, e, iright);
            tree[iright] = right2;
            size++;
            return right2;
        }
        throw new IllegalStateException("Have right already");
    }

    @Override
    public E remove(Position<E> p) throws IllegalStateException {
    	 BTPos<E> nodo = checkPosition(p);
         BTPos<E> left = tree[nodo.index*2];
         BTPos<E> right = tree[nodo.index*2+1];

         if ((right != null) && (left != null)) {
             throw new IllegalStateException("Can't remove a node with two children");
         }
         if (parent(nodo) == null) {
        	 nodo.setElement(null);
        	 nodo.setMyTree(null);
         } else { 
             if ((left != null) && (right != null) 
            	|| ((left != null) && (right == null)) 
            	|| (left == null) && (right != null)) { 
                 Iterator<Position<E>> it = this.iteratorFactory.createIterator(this, nodo);
                 while (it.hasNext()) {
                     BTPos<E> aux = (BTPos<E>) it.next();
                     aux.setMyTree(null);
                     it.next();
                 }
             } 
             //Si no tiene hijos
             nodo.setMyTree(null);  
             tree[nodo.getIndex()] = null;
         }
         size--;
         return nodo.getElement();

    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2)
            throws IllegalStateException {
        
    	BTPos<E> nodo1 = checkPosition(p1);
        BTPos<E> nodo2 = checkPosition(p2);
        int ip1 = nodo1.getIndex();
        int ip2 = nodo2.getIndex();
        E e1 = nodo1.getElement();
        E e2 = nodo2.getElement();
        tree[ip1].setElement(e2);
        tree[ip2].setElement(e1);
    	
    }

    /**
     * Determines whether the given position is a valid node.
     *
     * @param v the position to be checked
     * @return the position casted to BTPos
     */
    private BTPos<E> checkPosition(Position<E> v) throws IllegalStateException {
    	 BTPos<E> pos = (BTPos<E>) v;
         if (pos.getMyTree() != this){
             throw new IllegalStateException("This position not begin to the same tree");
         }
         if(pos == null || !(v instanceof BTPos)){
             throw new IllegalStateException("The position is incorrect");
         }
         return pos;
    }

    @Override
	public String toString() {
		return "ArrayBinaryTree [tree=" + Arrays.toString(tree) + ", size=" + size + ", iteratorFactory="
				+ iteratorFactory + ", toString()=" + super.toString() + "]";
	}
    
    /** Removes the subtree rooted at pOrig */
    public Position<E> removeSubtree(Position<E> p) 
    		throws IllegalStateException{
    	BTPos<E> nodo = checkPosition(p);
    	tree[nodo.getIndex()]=null;
    	size--;
    	removeSubTreeAux(nodo.getIndex()*2);
    	removeSubTreeAux(nodo.getIndex()*2+1);
    	
		return p;
    	
    }

	private void removeSubTreeAux(int i) {
		if(i<tree.length && tree[i]!=null) {
			size--;
			BTPos<E> nodo = tree[i];
			tree[nodo.getIndex()]=null;
			removeSubTreeAux(nodo.getIndex()*2);
			removeSubTreeAux(nodo.getIndex()*2+1);
		}
		
	}

}
