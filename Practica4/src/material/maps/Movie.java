package material.maps;

import java.util.ArrayList;

public class Movie{
	
	private String titulo;
	private int anyo;
	private ArrayList<Float> puntuacion;
	private ArrayList<String> tipo;
	
	public Movie(String titulo, int anyo, ArrayList<Float> puntuacion, ArrayList<String> tipo) {
		super();
		this.titulo = titulo;
		this.anyo = anyo;
		this.puntuacion = puntuacion;
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public ArrayList<Float> getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuation(ArrayList<Float> puntuacion) {
		this.puntuacion = puntuacion;
	}

	public ArrayList<String> getTipo() {
		return tipo;
	}

	public void setTipo(ArrayList<String> tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", año=" + anyo + ", puntuacion=" + puntuacion + ", tipo=" + tipo + "]";
	}
	
	public Float getMedia () {
		float acu = 0;
		for (Float aux: this.puntuacion) {
			acu = acu + aux;
		}
		return (acu/this.puntuacion.size());
	}
	

}
