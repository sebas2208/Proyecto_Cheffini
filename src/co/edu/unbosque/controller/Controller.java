package co.edu.unbosque.controller;

import javax.servlet.http.HttpServlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.Reservas;
import co.edu.unbosque.model.Chef;

public class Controller extends HttpServlet{
	
	/** Tabajo realizado por:
	 * Laura Daniela Chiquillo Leon
	 * Sebastian David Cubillos Medina
	 * Yuli Tatiana Currea Pino
	 * Juan Camilo Poveda Pinilla
	 * Daniela Alejandra Victoria Lemus**/

	public Controller() {


	}

	public static void crearUsuario(String nombre, String contraseña, String confirmar, String direccion, int tarjeta) {
		// instanciamos la clase usuario para poder identificar en la base de datos
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Usuario.class).buildSessionFactory();
		//Almacemanos la informacion
		Session session = factory.getCurrentSession();
		System.out.println("si funciona");
		try {
			// Creamos una variable para guardar los datos ingresados
			Usuario us = new Usuario(nombre,contraseña,confirmar,direccion, tarjeta);

			// Se crea una nueva transacción
			session.beginTransaction();
			System.out.println("si funciona");
			// Se guarda a la base de datos
			session.save(us);
			System.out.println("si funciona");
			// Verifica la transacción
			session.getTransaction().commit();
			System.out.println("si funciona");
			

		} finally {
			//finaliza
			factory.close();
		}

	}
	
	public static void crearChef(String nombre, String contraseña, String confirmar,String servicio, String horario, int tarifa) {
		// instanciamos la clase usuario para poder identificar en la base de datos
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Chef.class).buildSessionFactory();
		//Almacemanos la informacion
		Session session = factory.getCurrentSession();
		System.out.println("si");
		
		try {
			// Creamos una variable para guardar los datos ingresados
			Chef chef = new Chef(nombre, contraseña, confirmar, servicio, horario, tarifa);

			// Se crea una nueva transacción
			session.beginTransaction();
			System.out.println("si");
			// Se guarda a la base de datos
			session.save(chef);
			System.out.println("si");
			// Verifica la transacción
			session.getTransaction().commit();
			System.out.println("si");

		} finally {
			//finaliza
			factory.close();
		}

	}

	public static void crearReserva(int id_chef, int id_usuario, String metododepago, String fecha) {
		// instanciamos la clase usuario para poder identificar en la base de datos
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Reservas.class).buildSessionFactory();
		//Almacemanos la informacion
		Session session = factory.getCurrentSession();
		System.out.println("si");
		try {
			// Creamos una variable para guardar los datos ingresados
			Reservas re = new Reservas(id_chef, id_usuario, metododepago, fecha);

			// Se crea una nueva transacción
			session.beginTransaction();
			System.out.println("si");
			// Se guarda a la base de datos
			session.save(re);
			System.out.println("si");
			// Verifica la transacción
			session.getTransaction().commit();
			System.out.println("si");

		} finally {
			//finaliza
			factory.close();
		}

		System.out.println("SI LO HACE GG");

	}
	
	
}
