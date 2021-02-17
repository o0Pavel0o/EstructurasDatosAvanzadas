package material.tree.iterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

import material.tree.Position;
import material.tree.Tree;

/**
 * @author Pavel
 *
 */

public class PreorderIterator <E> implements Iterator<Position<E>> {

	private final  Deque<Position<E>> deque;
	private Tree<E> tree;
	
	public PreorderIterator(Tree<E> tree) {
		this.tree = tree;
		this.deque = new ArrayDeque<>();
		if (!tree.isEmpty()) {
			deque.add(tree.root());
		}
	}

    public PreorderIterator(Tree<E> tree, Position<E> root) {
    	this.tree = tree;
    	this.deque = new ArrayDeque<>();
    	deque.add(root);
    }

    @Override
    public boolean hasNext() {
        return (!deque.isEmpty());
    }

   
    @Override
    public Position<E> next() {
        Position<E> aux = deque.pop();
        Stack<Position<E>> pila = new Stack<>();
        for (Position<E> node : tree.children(aux)) {
        	pila.push(node);
        }
        while (!pila.isEmpty()) {
        	Position<E> node = pila.pop();
        	deque.add(node);
        }
        return aux;
    }
    
}