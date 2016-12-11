package np.com.onlineExam.test;

import static org.junit.Assert.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import np.com.onlineExam.dao.Student;

public class StudentTest1 {

	@Test
	public void test() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager eManager = emFactory.createEntityManager();
		eManager.getTransaction().begin();
		
		Student john = new Student("John","Cena");
		Student barrack = new Student("Barrack","Obama");
		Student albert = new Student("Albert","Einstein");
		
		eManager.persist(john);
		eManager.persist(barrack);
		eManager.persist(albert);	
		
		long johnId = john.getId();
		long barrackId = barrack.getId();
		long albertId = albert.getId();
		
		Student john1 = eManager.find(Student.class, johnId);
		Student barrack1 = eManager.find(Student.class, johnId);
		Student albert1 = eManager.find(Student.class, johnId);
		
		assertNotNull(john1);
		assertNotNull(barrack1);
		assertNotNull(albert1);
		
		eManager.getTransaction().commit();
		eManager.close();
		emFactory.close();
	}



}
