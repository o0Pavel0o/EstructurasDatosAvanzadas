package Test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import material.tree.InvalidPositionException;
import material.tree.LCRSTree;
import material.tree.LinkedTree;
import material.tree.Position;
import material.tree.iterator.BFSIterator;

/**
 * @author Pavel
 *
 */

public class LCRSTreeTest {


	@Test
	public void testSize() {
		LCRSTree<Integer> tree = new LCRSTree<>();
        assertEquals(tree.size(), 0);
        Position<Integer> a = tree.addRoot(7);
        assertEquals(tree.size(), 1);
	}

	@Test
	public void testIsEmpty() {
		LCRSTree<Integer> tree = new LCRSTree<>();
	    assertTrue(tree.isEmpty());
	    tree.addRoot(5);
	    assertFalse(tree.isEmpty());
	}

	@Test
	public void testIsInternal() {
		LCRSTree<Integer> tree = new LCRSTree<>();
		Position<Integer> a = tree.addRoot(1);
		Position<Integer> b = tree.add(2, a);
		Position<Integer> c = tree.add(3, a);
		Position<Integer> d = tree.add(4, a);
		Position<Integer> e = tree.add(5, c);
	}

	@Test
	public void testIsLeaf() {
		LCRSTree<Integer> tree = new LCRSTree<>();
		Position<Integer> a = tree.addRoot(1);
		Position<Integer> b = tree.add(2, a);
		Position<Integer> c = tree.add(3, a);
		Position<Integer> d = tree.add(4, a);
		Position<Integer> e = tree.add(5, b);
		Position<Integer> f = tree.add(6, b);
		
		assertFalse(tree.isLeaf(a));
		assertTrue(tree.isLeaf(c));
		assertFalse(tree.isLeaf(b));
		assertTrue(tree.isLeaf(d));
		assertTrue(tree.isLeaf(e));
		assertTrue(tree.isLeaf(f));
	
	}

	@Test
	public void testIsRoot() {
		LCRSTree<Integer> tree = new LCRSTree<>();
		Position<Integer> a = tree.addRoot(1);
		Position<Integer> b = tree.add(2, a);
		Position<Integer> c = tree.add(3, a);
		Position<Integer> d = tree.add(4, a);
		Position<Integer> e = tree.add(5, b);
		Position<Integer> f = tree.add(6, b);
		
		assertTrue(tree.isRoot(a));
		assertFalse(tree.isRoot(c));
		assertFalse(tree.isRoot(b));
		assertFalse(tree.isRoot(d));
		assertFalse(tree.isRoot(e));
		assertFalse(tree.isRoot(f));
	}

	@Test
	public void testRoot() {
		LCRSTree<String> tree = new LCRSTree<>();
		Position<String> a = tree.addRoot("F");	
		equals(tree.isRoot(a));
		
	}

	@Test
	public void testParent() {
		LCRSTree<Integer> tree = new LCRSTree<>();
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(15, p3);
		assertEquals(p2, tree.parent(p3));
	}

			

	@Test
	public void testChildren() {
		LCRSTree<String> tree = new LCRSTree<>();
		Position<String> p = tree.addRoot("-");
		tree.add("5", p);
		tree.add("7", p);

		String salida = "";
		for (Position<String> e : tree.children(p)) {
			salida += e.getElement();
		}
		assertEquals(salida, "57");
	}

	@Test
	public void testReplace() {
		LCRSTree<Integer> tree = new LCRSTree<>();
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);

		tree.replace(p, 7);
		tree.replace(p1, 7);
		tree.replace(p2, 7);
		tree.replace(p3, 7);

		String salida = "";
		for (Position<Integer> e : tree) {
			salida += e.getElement();
		}
		assertEquals(salida.toString(), "727457779");
	}

	@Test
	public void testAddRoot() {
		LCRSTree<Integer> tree = new LCRSTree<>();
		Position<Integer> a = tree.addRoot(7);
		assertEquals(a, tree.root());
	}

	@Test
	public void testSwapElements() {
		LCRSTree<Integer> tree = new LCRSTree<>();
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);

		tree.swapElements(p, p1);
		tree.swapElements(p2, p3);

		String salida = "";
		for (Position<Integer> e : tree) {
			salida += e.getElement();
		}
		assertEquals(salida.toString(), "323458789");
	
	}

	@Test
	public void testAdd() {
		LCRSTree<String> tree = new LCRSTree<>();
		Position<String> a = tree.addRoot("A");
		Position<String> b = tree.add("B", a);
		Position<String> c = tree.add("C", a);
		Position<String> d = tree.add("F", b);

		assertEquals(a, tree.parent(b));
		assertEquals(a, tree.parent(c));
		assertEquals(b, tree.parent(d));
	}

	@Test
	public void testRemove() {
		LCRSTree<String> tree = new LCRSTree<>();
		tree.addRoot("A");
		tree.remove(tree.root());
		assertEquals(tree.size(), 0);
        
	}


	@Test
	public void testIterator() {
		LCRSTree<String> tree = new LCRSTree<>();
		Position<String> a = tree.addRoot("A");
		Position<String> b = tree.add("B", a);
		Position<String> c = tree.add("C", a);
		Position<String> d = tree.add("F", b);

		StringBuilder salida = new StringBuilder();
		for (Position<String> pos : tree) {
			salida.append(pos.getElement());
		}

		assertEquals(salida.toString(), "ABCF");
	}

	@Test
	public void testMoveSubtree() {
		LCRSTree<String> tree = new LCRSTree<>();
		Position<String> a = tree.addRoot("A");
        Position<String> b = tree.add("B", a);
        Position<String> c = tree.add("C", a);
        tree.add("D", c);
        tree.add("F", c);
        Position<String> pOrig = tree.parent(c);
        Position<String> pDest = tree.parent(b);
        assertEquals(pOrig, pDest);
        Position<String> newOrig = null;
		try {
			newOrig = tree.moveSubtree(b, c);
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
        Position<String> newDest = tree.parent(newOrig);
        assertEquals(c, newDest);
        assertNotEquals(a, tree.parent(newOrig));
	}

}