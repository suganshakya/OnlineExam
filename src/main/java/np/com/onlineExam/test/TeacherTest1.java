package np.com.onlineExam.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import np.com.onlineExam.dao.Teacher;


public class TeacherTest1 {

	@Test
	public void test() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager eManager = emFactory.createEntityManager();
		eManager.getTransaction().begin();
		
		Teacher john = new Teacher("John","Cena");
		Teacher barrack = new Teacher("Barrack","Obama");
		Teacher albert = new Teacher("Albert","Einstein");
		
		eManager.persist(john);
		eManager.persist(barrack);
		eManager.persist(albert);	
		
		long johnId = john.getId();
		long barrackId = barrack.getId();
		long albertId = albert.getId();
		
		Teacher john1 = eManager.find(Teacher.class, johnId);
		Teacher barrack1 = eManager.find(Teacher.class, johnId);
		Teacher albert1 = eManager.find(Teacher.class, johnId);
		
		assertNotNull(john1);
		assertNotNull(barrack1);
		assertNotNull(albert1);
		
		eManager.getTransaction().commit();
		eManager.close();
		emFactory.close();
	}

}
