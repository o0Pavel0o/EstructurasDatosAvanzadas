package test;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import material.tree.Position;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

public class LinkedBinarySearchTreeTest {

	@Test
	public void testFindRange() {
		  LinkedBinarySearchTree<Integer> bin = new LinkedBinarySearchTree<>();
		  ArrayList<Integer> lista = new ArrayList<Integer>();
		  bin.insert(1);
		  bin.insert(2);
		  bin.insert(3);
		  bin.insert(4);
		  bin.insert(5);
		  bin.insert(6);
		  bin.insert(7);
		  bin.insert(8);
		  bin.insert(9);
		  Iterable<Position<Integer>> p = bin.findRange(1, 5);
		  for(Position<Integer> e:p) {
			lista.add(e.getElement());
		  }
		  //System.out.println(lista);
		  assertEquals(lista.toString(), "[1, 2, 3, 4, 5]");
	}

	@Test
	public void testFirst() {
		LinkedBinarySearchTree<Integer> bin = new LinkedBinarySearchTree<>();
		 bin.insert(1);
		 bin.insert(2);
		 bin.insert(3);
		 bin.insert(4);
		 bin.insert(5);
		 bin.insert(6);
		 bin.insert(7);
		 bin.insert(8);
		 bin.insert(9);
		 assertEquals(bin.first().getElement().toString(), "1");
	}

	@Test
	public void testLast() {
		LinkedBinarySearchTree<Integer> bin = new LinkedBinarySearchTree<>();
		 bin.insert(1);
		 bin.insert(2);
		 bin.insert(3);
		 bin.insert(4);
		 bin.insert(5);
		 bin.insert(6);
		 bin.insert(7);
		 bin.insert(8);
		 bin.insert(9);
		 assertEquals(bin.last().getElement().toString(), "9");
	}

	@Test
	public void testSuccessors() {
		LinkedBinarySearchTree<Integer> bin = new LinkedBinarySearchTree<>();
		  ArrayList<Integer> lista = new ArrayList<Integer>();
		  Position<Integer> pos = bin.insert(5);
		  bin.insert(1);
		  bin.insert(2);
		  bin.insert(3);
		  bin.insert(4);
		  bin.insert(5);
		  bin.insert(6);
		  bin.insert(7);
		  bin.insert(8);
		  bin.insert(9);
		  Iterable<Position<Integer>> p = bin.successors(pos);
		  for(Position<Integer> e:p) {
			lista.add(e.getElement());
		  }
		  //System.out.println(lista);
		  assertEquals(lista.toString(), "[5, 6, 7, 8, 9]");
	}

	@Test
	public void testPredecessors() {
		LinkedBinarySearchTree<Integer> bin = new LinkedBinarySearchTree<>();
		  ArrayList<Integer> lista = new ArrayList<Integer>();
		  Position<Integer> pos = bin.insert(5);
		  bin.insert(1);
		  bin.insert(2);
		  bin.insert(3);
		  bin.insert(4);
		  bin.insert(5);
		  bin.insert(6);
		  bin.insert(7);
		  bin.insert(8);
		  bin.insert(9);
		  Iterable<Position<Integer>> p = bin.predecessors(pos);
		  for(Position<Integer> e:p) {
			lista.add(e.getElement());
		  }
		  //System.out.println(lista);
		  assertEquals(lista.toString(), "[1, 2, 3, 4]");
	}

}
