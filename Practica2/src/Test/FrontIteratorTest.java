package Test;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import material.tree.LinkedTree;
import material.tree.Position;
import material.tree.iterator.FrontIterator;

class FrontIteratorTest {
	
	// El iterador es generico y puede recibir arboles binarios
	// LinkedBinaryTree<Integer> arbolbinario = new LinkedBinaryTree<>();
	
	
	@Test
	void testFrontIterator() {
		LinkedTree<Integer> arbol = new LinkedTree<>();
		Position<Integer> p = arbol.addRoot(5);
		
		arbol.add(10, p);
		Position<Integer> p4 = arbol.add(15, p);
		arbol.add(20, p);
		
		FrontIterator<Integer> iterador = new FrontIterator<> (arbol);
		while (iterador.hasNext()) {
			Integer element = iterador.next();
			assertEquals(element, arbol.root().getElement());
		}
	}

}
