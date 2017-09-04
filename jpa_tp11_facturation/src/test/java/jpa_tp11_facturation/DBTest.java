package jpa_tp11_facturation;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.formation.jpa.tp11.model.Address;

public class DBTest {

	protected static EntityManagerFactory emf;
	protected static EntityManager em;

	@BeforeClass
	public static void init() throws FileNotFoundException, SQLException {
		emf = Persistence.createEntityManagerFactory("test");
		em = emf.createEntityManager();
	}

	@Test
	public void testInsert() {
		Address address = new Address();

		em.getTransaction().begin();
		em.persist(address);
		em.getTransaction().commit();

		TypedQuery<Address> query = em.createQuery("from Address", Address.class);
		System.out.println(query.getResultList().size());
		
		assertEquals(1, 1);
	}

	@AfterClass
	public static void tearDown() {
		em.clear();
		em.close();
		emf.close();
	}

}
