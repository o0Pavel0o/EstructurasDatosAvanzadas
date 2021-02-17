package material.maps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Pavel
 *
 */
public class Netflix {
	static HashTableMapLP<String, Movie> tablaTitulo = new HashTableMapLP<String, Movie>();
	static HashTableMapLP<Integer, ArrayList<Movie>> tablaAnyo = new HashTableMapLP<Integer, ArrayList<Movie>>();
	static HashTableMapLP<String, ArrayList<Movie>> tablaTipo = new HashTableMapLP<String, ArrayList<Movie>>();

	public Netflix() {
	}

	public Netflix(HashTableMapLP<String, Movie> titulo, HashTableMapLP<Integer, ArrayList<Movie>> anyo,
			HashTableMapLP<String, ArrayList<Movie>> tipo) {
		super();
		tablaTitulo = titulo;
		tablaAnyo = anyo;
		tablaTipo = tipo;

	}

	public static void loadFile(String pathToFile) throws UnsupportedEncodingException {

		File file = new File(pathToFile);
		Scanner data = null;
		try {
			data = new Scanner(new InputStreamReader(new FileInputStream(file), "utf-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (data.hasNext()) {
			String linea = data.nextLine();
			String[] separa = linea.split(" - ");
			String genero[] = separa[3].split(", ");
			ArrayList<String> tipos = new ArrayList<String>();
			ArrayList<Float> puntua = new ArrayList<Float>();
			puntua.add(Float.parseFloat(separa[2]));
			for (int i = 0; i < genero.length; i++) {
				tipos.add(genero[i]);
			}
			int a = Integer.parseInt(separa[1]);
			// Se rellena la estructura de datos desde el archivo
			Movie peli = new Movie(separa[0], a, puntua, tipos);
			// No puede haber dos películas con el mismo titulo
			tablaTitulo.put(peli.getTitulo(), peli);
			// Por año
			ArrayList<Movie> aux = tablaAnyo.get(peli.getAnyo());
			if (aux == null) {
				aux = new ArrayList<Movie>();
				aux.add(peli);
				tablaAnyo.put(peli.getAnyo(), aux);
			} else {
				aux.add(peli);
			}

			for (String t : tipos) {
				if (tablaTipo.get(t) == null) {
					ArrayList<Movie> lista = new ArrayList<>();
					lista.add(peli);
					tablaTipo.put(t, lista);
				} else {
					ArrayList<Movie> list = tablaTipo.get(t);
					list.add(peli);
				}
			}
		}
		data.close();
	}

	public Movie findTitle(String title) {
		System.out.println(tablaTitulo.get(title));
		return tablaTitulo.get(title);
	}

	public Iterable<Movie> findYear(int year) {
		System.out.println(tablaAnyo.get(year));
		return (ArrayList<Movie>) tablaAnyo.get(year);
	}

	public Iterable<Movie> findScore(float score) {
		Set<Movie> pelis = new HashSet<Movie>();
		Iterable<Entry<String, Movie>> entries = tablaTitulo.entries();
		for (Entry<String, Movie> entry : entries) {
			if (entry.getValue().getMedia() >= score) {
				pelis.add(entry.getValue());
			}
		}
		System.out.println(pelis);
		return pelis;
	}

	public Iterable<Movie> findType(String type) {
		ArrayList<Movie> pelis = tablaTipo.get(type);
		if (pelis == null) {
			return new HashSet<Movie>();
		}
		System.out.println(pelis);
		return pelis;
	}

	public Iterable<Movie> findType(Set<String> type) {
		ArrayList<Movie> pelis = new ArrayList<>();
		type.forEach((t) -> {
			pelis.addAll(tablaTipo.get(t));
		});
		System.out.println(pelis);
		return pelis;
	}

	public void addScore(String title, float score) {
		Movie movie = this.findTitle(title);
		ArrayList<Float> puntuations = movie.getPuntuacion();
		puntuations.add(score);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		Netflix net = new Netflix();
		loadFile("C:\\Users\\Pablo\\Documents\\EDA\\Practica4\\src\\netflix_sin.txt");
		System.out.println("Por el titulo:");
		net.findTitle("Como en casa en ningun sitio");
		System.out.println("Por año:");
		net.findYear(2007);
		System.out.println("Por puntuacion:");
		net.findScore((float) 4.5);
		System.out.println("Por genero:");
		net.findType("accion");
		System.out.println("Por genero SET:");
		Set<String> set = new HashSet<String>();
		set.add("comedia");
		set.add("premiada");
		net.findType(set);

	}

}
