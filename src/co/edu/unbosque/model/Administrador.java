package co.edu.unbosque.model;

public class Administrador {

	private String nombre;
	private char contraseña;
	
	public Administrador(String nombre, char contraseña) {
		this.nombre= nombre;
		this.contraseña= contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getContraseña() {
		return contraseña;
	}

	public void setContraseña(char contraseña) {
		this.contraseña = contraseña;
	}
}
