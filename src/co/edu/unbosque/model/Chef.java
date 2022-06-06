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

//Se usa para poder ser llamado en vista
@ManagedBean(name="Chef")
//Se utiliza para desplegar los datos en la pagina
@RequestScoped
//Sirve para tener los mismos datos de registro
@SessionScoped
//Persistir los datos para la tabla de la base de datos
@Entity
//El nombre de la tabla de la base de datos
@Table(name="Chef")

public class Chef implements Serializable{
	private static final long SerialVersionUID=1L;
	// El ID es para poder identificar a el chef

	@Id
	//la auto generacion que se va a utilizar
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	//se crean las columnas de la tabla
	@Column(name="id_chef")
	private int id_chef;
	@Column(name="nombre")
	private String nombre;
	@Column(name="contraseña")
	private String contraseña;
	@Column(name="confirmar")
	private String confirmar;
	@Column(name="servicio")
	private String servicio;
	@Column(name="horario")
	private String horario;
	@Column(name="tarifa")
	private int tarifa;

	//Se crea el constructor
	public Chef(String nombre, String contraseña, String confirmar,String servicio, String horario, int tarifa) {
		this.nombre= nombre;
		this.contraseña= contraseña;
		this.confirmar= confirmar;
		this.servicio = servicio;
		this.horario = horario;
		this.tarifa = tarifa;
	}
	
	//Se crea un constructor vacio
	public Chef() {
		
		nombre= "";
		contraseña="";
		confirmar="";
		servicio="";
		horario= "";
		tarifa=0;
	}
	
	// Se crea este metodo para poder conectar y guardar los datos
	public void crearChef() {
		
		Controller.crearChef(this.nombre, this.contraseña, this.confirmar, this.servicio, this.horario, this.tarifa);
		
	}
	
	/**Se creo un método lista para guardar los datos en la lista y poder mostrarlos en la tabla 
	   de la vista **/
	public List<Chef> mostrarChef() {
		//permite a el bean el acceso
		FacesContext contex = FacesContext.getCurrentInstance();
		//almacena y accede a la información del servlet
		HttpSession hsession = (javax.servlet.http.HttpSession) contex.getExternalContext().getSession(true);
		// instanciamos la clase usuario para poder identificar en la base de datos
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Chef.class).buildSessionFactory();
		//Almacemanos la informacion
		Session session = factory.getCurrentSession();
		try {
			// Se crea una transacción
			session.beginTransaction();
			// Se crea la lista donde esta conectada con la base de datos
			List<Chef> chef = session.createQuery("from Chef").list();
			// creamos este recorrido para saber si esta funcionando 
			for (int i = 0; i < chef.size(); i++) {
				System.out.println(chef.get(i).getNombre());
			}
			// Verifica la transacción
			session.getTransaction().commit();
			System.out.println("a");
			// poder mostrar los datos
			hsession.setAttribute("datos_chef", chef);
			return chef;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			factory.close();
		}
		return null;
	}

	
	//Getters y Setters
	public int getId_chef() {
		return id_chef;
	}

	public void setId_chef(int id_chef) {
		this.id_chef = id_chef;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(String confirmar) {
		this.confirmar = confirmar;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public int getTarifa() {
		return tarifa;
	}

	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}



	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", contraseña=" + contraseña + ", confirmar=" + confirmar + ", servicio="
				+ servicio + ", horario=" + horario + ", tarifa=" + tarifa + "]";
	}
}