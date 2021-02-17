package material.tree.binarytree;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import material.tree.Position;
/**
 * @author Pavel
 *
 */
public class ArrayBinaryTreeTest {

	@Test
	public void testSize() {
		ArrayBinaryTree<Integer> arbol = new ArrayBinaryTree<Integer>(4);
		arbol.addRoot(1);
		assertEquals(arbol.size(), 1);
	}

	@Test
	public void testIsEmpty() {
		ArrayBinaryTree<Integer> arbol = new ArrayBinaryTree<Integer>(4);
		assertTrue(arbol.isEmpty());
	}

	@Test
	public void testIsInternal() {
		ArrayBinaryTree<Integer> arbol = new ArrayBinaryTree<Integer>(4);
		Position<Integer> p = arbol.addRoot(1);
		arbol.insertRight(p, 3);
		assertTrue(arbol.isInternal(p));
	}

	@Test
	public void testIsLeaf() {
		ArrayBinaryTree<Integer> arbol = new ArrayBinaryTree<Integer>(4);
		Position<Integer> p = arbol.addRoot(1);
		assertTrue(arbol.isLeaf(p));
	}

	@Test
	public void testIsRoot() {
		ArrayBinaryTree<Integer> arbol = new ArrayBinaryTree<Integer>(4);
		Position<Integer> p = arbol.addRoot(1);
		arbol.insertRight(p, 3);
		assertEquals(arbol.isRoot(p), true);
	}

	@Test
	public void testHasLeft() {
		ArrayBinaryTree<Integer> arbol = new ArrayBinaryTree<Integer>(4);
		Position<Integer> p = arbol.addRoot(1);
		arbol.insertLeft(p, 2);
		assertTrue(arbol.hasLeft(p));
	}

	@Test
	public void testHasRight() {
		ArrayBinaryTree<Integer> arbol = new ArrayBinaryTree<Integer>(4);
		Position<Integer> p = arbol.addRoot(1);
		arbol.insertRight(p, 3);
		assertTrue(arbol.hasRight(p));
	}

	@Test
	public void testRoot() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(4);
	    Position<String> p = t.addRoot("+");
	    Position<String> h = t.insertRight(p, "*");
	    assertEquals(t.isRoot(h), false);
	}

	@Test
	public void testLeft() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(4);
		Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        assertEquals(t.left(p), n2);
	}

	@Test
	public void testRight() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(8);
		Position<String> p = t.addRoot("+");
        Position<String> h = t.insertRight(p, "*");
        assertTrue(t.hasRight(p));
	}

	@Test
	public void testParent() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(6);
		Position<String> p = t.addRoot("+");
	    Position<String> n2 = t.insertLeft(p, "2");
	    Position<String> n3 = t.insertRight(p, "3");
	    //si los 2 hermanos tienen el mismo padre
	    t.parent(n2).equals(t.parent(n3)); 
	    //t.parent(n2).equals(t.parent(p)); 
	}

	@Test
	public void testChildren() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(12);
		Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        List<Position<String>> myChildren = new ArrayList<>();
        myChildren.add(n2);
        myChildren.add(h);
        Iterator<Position<String>> myIt = myChildren.iterator();
        for (Position<String> child : t.children(p)) {
            Position<String> next = myIt.next();
            assertEquals(child, next);
        }
	}

	@Test
	public void testIterator() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(12);
		Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        t.insertRight(p, "3");
        String salida = "";
        for (Position<String> e : t) {
            salida += e.getElement();
        }
        assertEquals(salida, "+23");
	}

	@Test
	public void testReplace() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(4);
		Position<String> p = t.addRoot("+");
        t.replace(p, "-");
        assertEquals(p.getElement(), "-");
	}

	@Test
	public void testSibling() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(50);
		Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        assertEquals(t.sibling(n2),h);
	}

	@Test
	public void testAddRoot() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(50);
		Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        equals(t.isRoot(p));
        //assertNotSame(t.root(), h);
	}

	@Test
	public void testInsertLeft() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(4);
		Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        assertEquals(t.left(p), n2);
	}

	@Test
	public void testInsertRight() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(4);
		Position<String> p = t.addRoot("+");
        Position<String> h = t.insertRight(p, "*");
        equals(t.hasRight(p));
	}

	@Test
	public void testRemove() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(4);
		Position<String> p = t.addRoot("+");
        Position<String> q = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.remove(q);
        assertEquals(t.size(), 2);
	}

	@Test
	public void testSwapElements() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(12);
		Position<String> p = t.addRoot("+");
        Position<String> p1 = t.insertLeft(p, "2");
        Position<String> p2 = t.insertRight(p, "3");
        t.swapElements(p1, p2);
        String salida = "";
        salida = p.getElement() + p1.getElement() + p2.getElement();
        assertEquals("+32", salida);
	}

	@Test
	public void testToString() {
		ArrayBinaryTree<Integer> t = new ArrayBinaryTree<Integer>(8);
		Position<Integer> p = t.addRoot(1);
	    Position<Integer> n2 = t.insertLeft(p, 2);
	    Position<Integer> h = t.insertRight(p, 3);
	    Position<Integer> n3 = t.insertLeft(h, 4);
	    Position<Integer> n5 = t.insertRight(h, 5);
	    equals(n3.toString());	        
	}

	@Test
	public void testRemoveSubtree() {
		ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(24);
		Position<String> a = t.addRoot("A");
        Position<String> b = t.insertLeft(a, "B");
        Position<String> c = t.insertRight(a, "C");
        Position<String> d = t.insertLeft(b, "D");
        Position<String> e = t.insertRight(b, "E");
        Position<String> f = t.insertLeft(e, "F");
        Position<String> g = t.insertRight(e, "G");
        Position<String> h = t.insertLeft(f, "H");
        t.removeSubtree(g);

        assertEquals(t.size(), 7);
        assertEquals(t.hasRight(d), false);
        assertEquals(t.hasRight(d), false);
	}

}
