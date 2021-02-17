package material.tree;

import material.tree.LinkedTree.TreeNode;
import material.tree.iterator.BFSIteratorFactory;
import java.util.*;
import material.tree.iterator.TreeIteratorFactory;

/**
 * A linked class for a tree where nodes have an arbitrary number of children.
 *
 * @author Raul Cabido, Abraham Duarte, Jose Velez, Jesús Sánchez-Oro
 * @param <E> the elements stored in the tree
 */

/**
 * @author Pavel
 *
 */

public class LCRSTree<E> implements Tree<E> {
    
	public class TreeNode<T>  implements Position<T> {
		
		private T element;
		private TreeNode<T> parent;
		private TreeNode<T> children;
		private TreeNode<T> brother;
		private LCRSTree<E> myTree;
		
		
		
        public TreeNode(T element, LCRSTree<E>.TreeNode<T> parent, LCRSTree<E>.TreeNode<T> children,
				LCRSTree<E>.TreeNode<T> brother, LCRSTree<E> myTree) {
			super();
			this.element = element;
			this.parent = parent;
			this.children = children;
			this.brother = brother;
			this.myTree = myTree;
		}

		@Override
        public T getElement() {
           return element;
        }
		
		/**
		 * @param element el element a establecer
		 */
		public void setElement(T element) {
			this.element = element;
		}

		/**
		 * @return el children
		 */
		public TreeNode<T> getChildren() {
			return children;
		}

		/**
		 * @param children el children a establecer
		 */
		public void setChildren(TreeNode<T> children) {
			this.children = children;
		}
		
		/**
		 * @return el parent
		 */
		public TreeNode<T> getParent() {
			return parent;
		}

		/**
		 * @param parent el parent a establecer
		 */
		public void setParent(TreeNode<T> parent) {
			this.parent = parent;
		}

		/**
		 * @return el brother
		 */
		public TreeNode<T> getBrother() {
			return brother;
		}

		/**
		 * @param brother el brother a establecer
		 */
		public void setBrother(TreeNode<T> brother) {
			this.brother = brother;
		}

		/**
		 * @return el myTree
		 */
		public LCRSTree<E> getMyTree() {
			return myTree;
		}

		/**
		 * @param myTree el myTree a establecer
		 */
		public void setMyTree(LCRSTree<E> myTree) {
			this.myTree = myTree;
		}

        
    }
	
	private TreeNode<E> root; // The root of the tree
    private int size; // The number of nodes in the tree
    private TreeIteratorFactory<E> iteratorFactory; // The factory of iterators

    /**
     * Creates an empty tree.
     */
    public LCRSTree() {
        root = null;
        size = 0;
        this.iteratorFactory = new BFSIteratorFactory<>();
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
    public boolean isInternal(Position<E> v) throws IllegalStateException {
    	 return !isLeaf(v);
    }

    @Override
    public boolean isLeaf(Position<E> p) throws IllegalStateException {
    	TreeNode<E> node = checkPosition(p);
        return (node.getChildren() == null);
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalStateException {
    	TreeNode<E> node = checkPosition(p);
        return (node == this.root());
    }

    @Override
    public Position<E> root() throws IllegalStateException {
    	if (root == null) {
            throw new IllegalStateException("The tree is empty");
        }
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IndexOutOfBoundsException,
            IllegalStateException {
    	TreeNode<E> node = checkPosition(p);
    	
    	if(this.isRoot(p)) {	
    		throw new IllegalStateException("The node has not parent");
    	}
    	return (Position<E>) node.getParent();
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> p) {
       TreeNode<E> node = checkPosition(p);
       List<Position<E>> lista = new ArrayList<Position<E>>();
       node = node.getChildren();
       while(node!=null) {
    	   lista.add(node);
    	   node = node.getBrother();
       }
       return lista;
    }

    public E replace(Position<E> p, E e) throws IllegalStateException {
    	TreeNode<E>node = checkPosition(p);
        E elemt = node.getElement();
        node.setElement(e);
        return elemt;
    }
    
    @Override
    public Position<E> addRoot(E e) throws IllegalStateException {
        if(!isEmpty()) {
        	throw new IllegalStateException("Tree already has a root");
        }
        size = 1;
        root = new TreeNode<E>(e, null, null, null, this);
        return root;
    }

    
    public void swapElements(Position<E> p1, Position<E> p2)
            throws IllegalStateException {
    	 TreeNode<E> node1 = checkPosition(p1);
         TreeNode<E> node2 = checkPosition(p2);
         E elemt = p2.getElement();
         node2.setElement(node2.getElement());
         node1.setElement(elemt);
    }

    
    private TreeNode<E> checkPosition(Position<E> p)
            throws IllegalStateException {
    	if (p == null || !(p instanceof TreeNode)) {
            throw new IllegalStateException("The position is invalid");
        }
        TreeNode<E> aux = (TreeNode<E>) p;

        if (aux.getMyTree() != this) {
            throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
    }


    public Position<E> add(E element, Position<E> p) {
    	TreeNode<E> node1 = checkPosition(p);
        TreeNode<E> child = new TreeNode<>(element, node1, null, null, this);
        if(isLeaf(p)) {
        	node1.setChildren(child);
        }else {
        	TreeNode<E> node2 = node1.getChildren();
        	while(node2.getBrother()!=null) {
        		node2=node2.getBrother();
        	}
        	node2.setBrother(child);
        }
        size++;
        return child;
    }

    public void remove(Position<E> p) throws IllegalStateException {
    	TreeNode<E> node = checkPosition(p);
    	Iterator<Position<E>> it = this.iteratorFactory.createIterator(this, node);
       
        int cont = 0;
        while (it.hasNext()) {
            it.next();
            cont++;
        }
            size = size - cont;
        if (isRoot(node)) {    
        	this.root = null;
            this.size = 0;
        } else {
            if(node.getParent().getChildren() == node) {
            	node.getParent().setChildren(node.getBrother());
            }else {
            	TreeNode<E> aux = node.getParent().getChildren();
            	while(aux.getBrother()!=node) {
            		aux = aux.getBrother();
            	}
            }
        }
        node.setMyTree(null);
    }

    public void setIterator(TreeIteratorFactory<E> iteratorFactory) {
    	this.iteratorFactory = iteratorFactory; 
    }

    @Override
    public Iterator<Position<E>> iterator() {
    	return this.iteratorFactory.createIterator(this);
    }
    
    /** Moves a node and its corresponding subtree (rooted at pOrig) to make
    it as a new children of pDest */
    public Position<E> moveSubtree(Position<E> pOrig, Position<E> pDest) 
    		throws InvalidPositionException{

    	TreeNode<E> origen = checkPosition(pOrig);
		TreeNode<E> destino = checkPosition(pDest);
		if((origen == destino) || (pertenece(pOrig, pDest))) {
			throw new IllegalStateException("The same tree");
		}
		if(origen.getParent().getChildren()==origen) {
			origen.getParent().setChildren(origen.getBrother());
		}else {
			TreeNode<E> paux = origen.getParent().getChildren();
			TreeNode<E> pant = null;
			while (paux!=origen) {
				pant = paux;
				paux = paux.getBrother();
			}
			pant.setBrother(paux.getBrother());			
		}
    	origen.setParent(destino);
    	origen.setBrother(null);
    	return (Position<E>) origen;
    	
    }

	private boolean pertenece(Position<E> pOrig, Position<E> pDest) {
		TreeNode<E> origen = checkPosition(pOrig);
		TreeNode<E> destino = checkPosition(pDest);
		Iterator<Position<E>> it = iteratorFactory.createIterator(this, origen);
		while(it.hasNext()) {
			TreeNode<E> aux = (TreeNode<E>) it.next();
			if(aux == destino) {
				return true;
			}
		}
		return false;
	}
}
