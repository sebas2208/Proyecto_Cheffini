package co.edu.unbosque.model;

public class Administrador {

	private String nombre;
	private char contrase�a;
	
	public Administrador(String nombre, char contrase�a) {
		this.nombre= nombre;
		this.contrase�a= contrase�a;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(char contrase�a) {
		this.contrase�a = contrase�a;
	}
}
