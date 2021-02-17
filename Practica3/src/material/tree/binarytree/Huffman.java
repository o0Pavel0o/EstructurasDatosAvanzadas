package material.tree.binarytree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import material.tree.Position;
/**
 * @author Pavel
 *
 */
public class Huffman {
	
	private ArrayList<Caracter> almacen =  new ArrayList<Caracter> ();
	private List<LinkedBinaryTree<Caracter>> bosque = new ArrayList<LinkedBinaryTree<Caracter>>();
	
	public class Caracter{
		
		private char caracter;
		private int frecuencia;
		private String cadena;
		
		
		public Caracter(char caracter) {
			super();
			this.caracter = caracter;
		}
		public Caracter(char caracter, int frecuencia) {
			super();
			this.caracter = caracter;
			this.frecuencia = frecuencia;
		}
		public Caracter(char caracter, int frecuencia, String cadena) {
			super();
			this.caracter = caracter;
			this.frecuencia = frecuencia;
			this.cadena = cadena;
		}
		/**
		 * @return el caracter
		 */
		public char getCaracter() {
			return caracter;
		}
		/**
		 * @param caracter el caracter a establecer
		 */
		public void setCaracter(char caracter) {
			this.caracter = caracter;
		}
		/**
		 * @return el frecuencia
		 */
		public int getFrecuencia() {
			return frecuencia;
		}
		/**
		 * @param frecuencia el frecuencia a establecer
		 */
		public void setFrecuencia(int frecuencia) {
			this.frecuencia = frecuencia;
		}
		/**
		 * @return el cadena
		 */
		public String getCadena() {
			return cadena;
		}
		/**
		 * @param cadena el cadena a establecer
		 */
		public void setCadena(String cadena) {
			this.cadena = cadena;
		}
		
	}
	
	//constructor
	public Huffman(String frase) {
		for (int index=0; index<frase.length(); index++){
			char c = frase.charAt(index);
			Caracter caracter= buscarCaracter(c);
			if (caracter==null){
				this.almacen.add(new Caracter(c));
			}else{
				caracter.setFrecuencia(caracter.getFrecuencia()+1);
			}
		}
		
		for(Caracter c:almacen){
			System.out.println("El caracter '" + c.getCaracter() + "' tiene frecuencia " + c.getFrecuencia());
			LinkedBinaryTree<Caracter> arbol = new LinkedBinaryTree<Huffman.Caracter>();
			arbol.addRoot(c);
			this.bosque.add(arbol);
		}
		
		while(bosque.size()>1){
			LinkedBinaryTree<Caracter> arbol1 = dameMenor();
			LinkedBinaryTree<Caracter> arbol2 = dameMenor();
			LinkedBinaryTree<Caracter> nuevo = new LinkedBinaryTree<Caracter>();
			Caracter a = new Caracter ('♠');
			nuevo.addRoot(a);
			int f1 = arbol1.root().getElement().getFrecuencia();
			int f2 = arbol2.root().getElement().getFrecuencia();
			a.setFrecuencia(f1+f2);
			Position<Caracter> raizi = nuevo.insertLeft(nuevo.root(), arbol1.root().getElement());
			Position<Caracter> raizd = nuevo.insertRight(nuevo.root(), arbol2.root().getElement());		
			copiarArbol(nuevo, raizi, arbol1, arbol1.root());
			copiarArbol(nuevo, raizd, arbol2, arbol2.root());
			bosque.add(nuevo);
		}
		analisis();
	}
	
	private void analisis() {
		analisis(bosque.get(0), bosque.get(0).root(), "");
	}
	
	private void analisis (LinkedBinaryTree<Caracter> arbol, Position<Caracter> p, String cadena){
		if (arbol.hasLeft(p)){
			analisis(arbol, arbol.left(p), cadena + "0");
		}
		if (arbol.hasRight(p)){
			analisis(arbol, arbol.right(p), cadena + "1");
		}
		if (arbol.isLeaf(p)){
			p.getElement().setCadena(cadena);
		}
		
	}
	
	private Caracter buscarCaracter(char c){
		for (Caracter car : this.almacen){
			if (car.getCaracter() == c){
				return car;
			}
		}
		return null;
	}
	
	
	public String codificar(char c) {
		ArrayList<Position<Caracter>> hojas = dameHojas(this.bosque.get(0));
		for(Position<Caracter> p : hojas){
			Caracter caracter = p.getElement();
			if(caracter.getCaracter()==c){
				return caracter.getCadena();
			}
		}
		return null;
	}
	
	private ArrayList<Position<Caracter>> dameHojas(LinkedBinaryTree<Caracter> arbol){
		ArrayList<Position<Caracter>> lista = new ArrayList<Position<Caracter>>();
		Iterator<Position<Caracter>> it = arbol.iterator();
		while(it.hasNext()){
			Position<Caracter> p = it.next();
			if (arbol.isLeaf(p)){
				lista.add(p);
			}
		}
		return lista;
	}
	
	private LinkedBinaryTree<Caracter> dameMenor(){
		LinkedBinaryTree<Caracter> arbol = this.bosque.get(0);
		int i = 0;
		for (int index=1; index<this.bosque.size(); index++){
			if (this.bosque.get(index).root().getElement().getFrecuencia() < arbol.root().getElement().getFrecuencia()){
				arbol = this.bosque.get(index);
				i = index;
			}
		}
		return this.bosque.remove(i);
	}
	
	private void copiarArbol(LinkedBinaryTree<Caracter> nuevo, Position<Caracter> pInsertar, LinkedBinaryTree<Caracter> arbol, Position<Caracter> pOrigen){
		if (arbol.hasLeft(pOrigen)){
			Position<Caracter> raizi = nuevo.insertLeft(pInsertar, arbol.left(pOrigen).getElement());
			copiarArbol(nuevo, raizi, arbol, arbol.left(pOrigen));
		}
		if (arbol.hasRight(pOrigen)){
			Position<Caracter> raizd = nuevo.insertRight(pInsertar, arbol.right(pOrigen).getElement());
			copiarArbol(nuevo, raizd, arbol, arbol.right(pOrigen));
		}
	}

	public static void main(String[] args) {
		Huffman h = new Huffman("SUSIE SAYS IT IS EASY");
		System.out.println(h.codificar('S') + "-> S");
		System.out.println(h.codificar('U') + "-> U");
		System.out.println(h.codificar('I') + "-> I");
		System.out.println(h.codificar('E') + "-> E");
		System.out.println(h.codificar('A') + "-> A");
		System.out.println(h.codificar('Y') + "-> Y");
		System.out.println(h.codificar('T') + "-> T");
	}

}
