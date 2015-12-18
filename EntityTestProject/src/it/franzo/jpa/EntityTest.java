package it.franzo.jpa;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: EntityTest
 *
 */
@Entity
public class EntityTest implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	private String testo;
	private static final long serialVersionUID = 1L;

	public EntityTest() {
		super();
	}   
	
	public EntityTest(String testo) {
		this.testo = testo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTesto() {
		return this.testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	/**
	 * main
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityTestProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();	
		
		tx.begin();
		
		System.out.println("Save prova1");
		EntityTest et1 = new EntityTest("prova1");
		em.persist(et1);
		
		System.out.println("Save prova2");
		EntityTest et2 = new EntityTest("prova2");
		em.persist(et2);
		
		tx.commit();
		
		System.out.println("prova1 id: " + et1.getId());
		System.out.println("prova2 id: " + et2.getId());
		
		tx.begin();
		
		
		
		//TODO FARE IL REMOVE QUI
		
		
		EntityTest et = em.find(EntityTest.class, et1.getId());
		System.out.println("Found " + et.getTesto());
		
		tx.commit();
		em.close();
	}
   
}
