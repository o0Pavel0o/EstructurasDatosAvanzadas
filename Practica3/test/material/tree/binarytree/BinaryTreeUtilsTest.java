package material.tree.binarytree;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import org.junit.Test;

import material.tree.Position;
import material.tree.binarytree.*;
/**
 * @author Pavel
 *
 */
public class BinaryTreeUtilsTest{


	@Test
	public void testMirror() {
		LinkedBinaryTree<String> t = new LinkedBinaryTree<>();
		LinkedBinaryTree<String> aux = new LinkedBinaryTree<>();
		BinaryTreeUtils<String> arbolespejo = new BinaryTreeUtils<String>(t);
        Position<String> a = t.addRoot("A");
        Position<String> b = t.insertLeft(a, "B");
        Position<String> c = t.insertRight(a, "C");
        Position<String> d = t.insertLeft(b, "D");
        Position<String> e = t.insertRight(b, "E");
        Position<String> f = t.insertLeft(e, "F");
        Position<String> g = t.insertRight(e, "G");
        Position<String> h = t.insertLeft(f, "H");
        aux = (LinkedBinaryTree<String>) arbolespejo.mirror();
        assertEquals(aux.root().getElement(), t.root().getElement());
        //assertEquals(aux.left(c).getElement(), t.left(b).getElement());
        Deque<Position<String>> cola = new ArrayDeque<>();
        cola.add(a);
        cola.add(c);
        cola.add(b);
        cola.add(e);
        cola.add(d);
        cola.add(g);
        cola.add(f);
        cola.add(h);
	    Iterator<Position<String>>it = aux.iterator();
    	while(it.hasNext()) {
    		assertEquals(it.next().getElement(), cola.remove().getElement());
    	}	   
        
	}

	@Test
	public void testContains() {
		LinkedBinaryTree<String> t = new LinkedBinaryTree<>();
		LinkedBinaryTree<String> aux = new LinkedBinaryTree<>();
		BinaryTreeUtils<String> arbolespejo = new BinaryTreeUtils<String>(t);
        Position<String> a = t.addRoot("A");
        Position<String> b = t.insertLeft(a, "B");
        Position<String> c = t.insertRight(a, "C");
        aux = (LinkedBinaryTree<String>) arbolespejo.mirror();
        assertEquals(arbolespejo.contains("B"), true);
	}

	@Test
	public void testLevel() {
		LinkedBinaryTree<String> t = new LinkedBinaryTree<>();
		LinkedBinaryTree<String> aux = new LinkedBinaryTree<>();
		BinaryTreeUtils<String> arbolespejo = new BinaryTreeUtils<String>(t);
		Position<String> a = t.addRoot("A");
		Position<String> b = t.insertLeft(a, "B");
        Position<String> c = t.insertRight(a, "C");
        Position<String> d = t.insertLeft(b, "D");
        Position<String> e = t.insertRight(b, "E");
        Position<String> f = t.insertLeft(e, "F");
        Position<String> g = t.insertRight(e, "G");
        Position<String> h = t.insertLeft(f, "H");
        
        aux = (LinkedBinaryTree<String>) arbolespejo.mirror();
        assertEquals(arbolespejo.level(h), 5);
	}

}
