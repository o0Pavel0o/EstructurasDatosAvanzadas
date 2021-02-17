package material.tree.iterator;
import java.util.Iterator;
import java.util.LinkedList;
import material.tree.*;

/**
 * @author Pavel
 *
 */

public class FrontIterator <T> implements Iterator<T>{
	private Tree<T> tree;
	private LinkedList<Position<T>> list = new LinkedList<>();
	
	public FrontIterator(Tree<T> tree) {
		this (tree, tree.root());
	}
	
	public FrontIterator(Tree<T> tree, Position<T> pStart) {
		this.tree = tree;
		this.list.add(pStart);
	}
	
	@Override
	public boolean hasNext() {
		return (!list.isEmpty());
	}

	@Override
	public T next() {
		Position<T> aux = null;
		boolean encontradaHoja = false;
        
		while ((!encontradaHoja) && (!list.isEmpty())) {
			aux = list.remove(0);
			if (tree.isLeaf(aux)) {
				encontradaHoja = true;
			}else {
				for (Position<T> hijo : tree.children(aux)) {
					list.add(hijo);
				}
			}
		}
		
        return aux.getElement();
	}

}
