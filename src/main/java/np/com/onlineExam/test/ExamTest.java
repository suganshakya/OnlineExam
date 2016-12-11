package np.com.onlineExam.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import np.com.onlineExam.dao.Exam;


public class ExamTest {

	@Test
	public void test() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager eManager = emFactory.createEntityManager();
		eManager.getTransaction().begin();
		
		Exam e1 = new Exam();
		Exam e2 = new Exam();
		Exam e3 = new Exam();
		
		eManager.persist(e1);
		eManager.persist(e2);
		eManager.persist(e3);	
		
		long Id1 = e1.getId();
		long Id2 = e2.getId();
		long Id3 = e3.getId();
		
		Exam exam1 = eManager.find(Exam.class, Id1);
		Exam exam2 = eManager.find(Exam.class, Id2);
		Exam exam3 = eManager.find(Exam.class, Id3);
		
		assertNotNull(exam1);
		assertNotNull(exam2);
		assertNotNull(exam3);
		
		eManager.getTransaction().commit();
		eManager.close();
		emFactory.close();
	}

}
