package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import co.edu.unbosque.controller.Controller;
import javassist.SerialVersionUID;

// Se usa para poder ser llamado en vista
@ManagedBean(name="Usuario")
// Se utiliza para desplegar los datos en la pagina
@RequestScoped
// Sirve para tener los mismos datos de registro
@SessionScoped
// Persistir los datos para la tabla de la base de datos
@Entity
// El nombre de la tabla de la base de datos
@Table(name="Usuario")

public class Usuario implements Serializable{
	private static final long SerialVersionUID=1L;
	// El ID es para poder identificar a el usuario
	@Id
	//la auto generacion que se va a utilizar
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	//se crean las columnas de la tabla
	@Column(name="id_usuario")
	private int id_usuario;
	@Column(name="nombre")
	private String nombre;
	@Column(name="contrase�a")
	private String contrase�a;
	@Column(name="confirmar")
	private String confirmar;
	@Column(name="direccion")
	private String direccion;
	@Column(name="tarjeta")
	private int tarjeta;
	
	//Se crea el constructor
	public Usuario(String nombre, String contrase�a, String confirmar,String direccion, int tarjeta) {
		this.nombre= nombre;
		this.contrase�a= contrase�a;
		this.confirmar= confirmar;
		this.direccion= direccion;
		this.tarjeta= tarjeta;
	}
	//Se crea un constructor vacio
	public Usuario() {
		
		nombre= "";
		contrase�a="";
		confirmar="";
		direccion="";
		tarjeta= 0;
	}
	// Se crea este metodo para poder conectar y guardar los datos
	public void crearUsuario() {
		
		Controller.crearUsuario(this.nombre, this.contrase�a, this.confirmar, this.direccion, this.tarjeta);
		
	}
	
	/**Se creo un m�todo lista para guardar los datos en la lista y poder mostrarlos en la tabla 
	   de la vista **/
	public List<Usuario> mostrarUsuario() {
		//permite a el bean el acceso
		FacesContext contex = FacesContext.getCurrentInstance();
		//almacena y accede a la informaci�n del servlet
		HttpSession hsession = (javax.servlet.http.HttpSession) contex.getExternalContext().getSession(true);
		// instanciamos la clase usuario para poder identificar en la base de datos
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Usuario.class).buildSessionFactory();
		//Almacemanos la informacion
		Session session = factory.getCurrentSession();
		try {
			// Se crea una transacci�n
			session.beginTransaction();
			// Se crea la lista donde esta conectada con la base de datos
			List<Usuario> usuario = session.createQuery("from Usuario").list();
			// creamos este recorrido para saber si esta funcionando 
			for (int i = 0; i < usuario.size(); i++) {
				System.out.println(usuario.get(i).getNombre());
			}
			// Verifica la transacci�n
			session.getTransaction().commit();
			System.out.println("a");
			// poder mostrar los datos
			hsession.setAttribute("datos_usuario", usuario);
			return usuario;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			factory.close();
		}
		return null;
	}

	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(String confirmar) {
		this.confirmar = confirmar;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(int tarjeta) {
		this.tarjeta = tarjeta;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", contrase�a=" + contrase�a + ", confirmar=" + confirmar + ", direccion="
				+ direccion + ", tarjeta=" + tarjeta+ "]";
	}
}
