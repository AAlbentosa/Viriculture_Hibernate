package manager;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import exceptions.ExecutionException;
import exceptions.LogicExceptions;
import model.Bodega;
import model.Campo;
import model.Entrada;
import model.TipoVid;
import model.Vid;

public class Hibernate {
	private static Session session;
	private Transaction tx; 
	private Bodega bodega;
	private Campo campo;
	private List<Entrada> entrada;
	
	public void init() {
		
		try {
			initSession();
			getEntrada();
			processEntrada();
			endSession();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	private void endSession() {
		session.close();
	}

	private void initSession() throws ExecutionException {
		try {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new ExecutionException(ExecutionException.CONNECTION_ERROR);
		}
	}
	
	private void getEntrada() throws ExecutionException{
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Entrada");
			entrada = q.list();

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ExecutionException(ExecutionException.ERROR_QUERY);
		}
	}
	
	private void processEntrada(){
		for(Entrada e:entrada) {
			try {
				System.out.println(e.toString());
				String instruccion=e.getInstruccion();
				switch(instruccion.charAt(0)) {
				case 'B':
					createNewBodega(instruccion.split(" ")[1]);
					break;
				case 'C':
					createNewCampo(bodega.getId_bodega());
					break;
				case 'V':
					createNewVid(instruccion.split(" ")[1], instruccion.split(" ")[2]);
					break;
				case '#':
					vendimia();
					break;
				default:
					throw new LogicExceptions(LogicExceptions.WRONG_ARGUMENT);
				}
			
			} catch (LogicExceptions le) {
				le.printStackTrace();
			}catch (ExecutionException ee) {
				ee.printStackTrace();
			}
		}
	}

	private void vendimia() throws ExecutionException {
		try {
			tx = session.beginTransaction();
			
			for(Vid v: campo.getVids()) 
				bodega.getVids().add(v);
			
			session.save(campo);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			throw new ExecutionException(ExecutionException.ERROR_QUERY);
		}
	}

	private void createNewVid(String tipo, String cantidad) throws ExecutionException {
		TipoVid tipov=TipoVid.valueOf(tipo.toUpperCase());
		Vid vid=new Vid(tipov, Integer.parseInt(cantidad));
		
		try {
			tx = session.beginTransaction();
			int id = (Integer) session.save(vid);

			vid.setId(id);
			campo.getVids().add(vid);
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			throw new ExecutionException(ExecutionException.ERROR_QUERY);
		}
	}

	private void createNewBodega(String nombre) throws ExecutionException {
		bodega = new Bodega(nombre);

		try {
			tx = session.beginTransaction();
			int id = (Integer) session.save(bodega);
 
			tx.commit();

			bodega.setId_bodega(id);

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			throw new ExecutionException(ExecutionException.ERROR_QUERY);
		}
	}
	
	private void createNewCampo(int bodega_id) throws ExecutionException {
		campo = new Campo(bodega);

		try {
			tx = session.beginTransaction();
			int id = (Integer) session.save(campo);

			tx.commit();

			campo.setId_campo(id);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			throw new ExecutionException(ExecutionException.ERROR_QUERY);
		}
	}
}
