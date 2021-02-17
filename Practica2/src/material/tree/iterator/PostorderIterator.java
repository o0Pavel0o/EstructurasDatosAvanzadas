package material.tree.iterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import material.tree.Position;
import material.tree.Tree;

/**
 * @author Pavel
 *
 */

public class PostorderIterator<E> implements Iterator<Position<E>> {

	private final  Deque<Position<E>> deque;
	private final Tree<E> tree ;
	
	public PostorderIterator(Tree<E> tree, Position<E> start) {	
		this.deque = new ArrayDeque<>();
		this.tree = tree;
		auxPostOrder(start);
	}

	public PostorderIterator(Tree<E> tree) {
		this.deque = new ArrayDeque<>();
		this.tree = tree;
		auxPostOrder(tree.root());	
	}
	
	@Override
	public boolean hasNext() {
		return (!deque.isEmpty());
	}
	
	@Override
	public Position<E> next() {
		Position<E> aux = deque.removeFirst();
		return aux;
	}

	private void auxPostOrder(Position<E> p) {
		for(Position<E> node: tree.children(p)) {
			if(tree.isLeaf(node)) {	
				deque.add(node);
			}else {
				auxPostOrder(node);	
			}
		 }
	 deque.add(p);
		
	}
}