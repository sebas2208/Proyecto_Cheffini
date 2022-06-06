package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import co.edu.unbosque.controller.Controller;

//Se usa para poder ser llamado en vista
@ManagedBean(name="Reservas")
//Se utiliza para desplegar los datos en la pagina
@RequestScoped
//Sirve para tener los mismos datos de registro
@SessionScoped
//Persistir los datos para la tabla de la base de datos
@Entity
//El nombre de la tabla de la base de datos
@Table(name="Reservas")

public class Reservas implements Serializable{
	private static final long serialVersionUID = 1L;
	// El ID es para poder identificar la reserva
	@Id
	//la auto generacion que se va a utilizar
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	//se crean las columnas de la tabla
	@Column(name="id_reserva")
	private int id_reserva;
	@Column(name="id_chef")
	private int id_chef;
	@Column(name="id_usuario")
	private int id_usuario;
	@Column(name="metododepago")
	private String metododepago;
	@Column(name="fecha")
	private String fecha;

	//Se crea el constructor
	public Reservas(int id_chef, int id_usuario, String metododepago, String fecha) {
		this.id_chef = id_chef;
		this.id_usuario = id_usuario;
		this.metododepago = metododepago;
		this.fecha = fecha;

	}

	//Se crea un constructor vacio
	public Reservas() {
		id_chef = 0;
		id_usuario = 0;
		metododepago = "";
		fecha = "";

	}

	// Se crea este metodo para poder conectar y guardar los datos
	public void crearReserva() {

		Controller.crearReserva(id_chef, id_usuario, metododepago, fecha);

	}

	/**Se creo un método lista para guardar los datos en la lista y poder mostrarlos en la tabla 
	   de la vista **/
	public List<Reservas> mostrarReservas() {
		//permite a el bean el acceso
		FacesContext contex = FacesContext.getCurrentInstance();
		//almacena y accede a la información del servlet
		HttpSession hsession = (javax.servlet.http.HttpSession) contex.getExternalContext().getSession(true);
		// instanciamos la clase usuario para poder identificar en la base de datos
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Reservas.class).buildSessionFactory();
		//Almacemanos la informacion
		Session session = factory.getCurrentSession();
		try {
			// Se crea una transacción
			session.beginTransaction();
			// Se crea la lista donde esta conectada con la base de datos
			List<Reservas> reserva = session.createQuery("from Reservas").list();
			// creamos este recorrido para saber si esta funcionando 
			for (int i = 0; i < reserva.size(); i++) {
				System.out.println(reserva.get(i).getFecha());
			}
			// Verifica la transacción
			session.getTransaction().commit();
			System.out.println("a");
			// poder mostrar los datos
			hsession.setAttribute("Reservas", reserva);
			return reserva;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			factory.close();
		}
		return null;
	}
	
	//Getters y Setters
	public int getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}

	public int getId_chef() {
		return id_chef;
	}

	public void setId_chef(int id_chef) {
		this.id_chef = id_chef;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getMetododepago() {
		return metododepago;
	}

	public void setMetododepago(String metododepago) {
		this.metododepago = metododepago;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Reserva [id_chef=" + id_chef + ", id_usuario=" + id_usuario + ", metododepago=" + metododepago + "fecha="+ fecha +"]";
	}


}
