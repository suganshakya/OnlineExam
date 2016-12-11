package np.com.onlineExam.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import np.com.onlineExam.dao.Exam;
import np.com.onlineExam.dao.Question;


public class QuestionTest1 {

	@Test
	public void test() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager eManager = emFactory.createEntityManager();
		eManager.getTransaction().begin();
		
		Exam e = new Exam();
		
		Question question1 = new Question("What is Java?","Multiple Choice",e);
		Question question2 = new Question("Who is Barrack Obama?", "Single Choice",e);
		Question question3 = new Question("Newton was a Politician","True/False", e);
		
		eManager.persist(e);
		eManager.persist(question1);
		eManager.persist(question2);
		eManager.persist(question3);	
		
		long question1Id = question1.getId();
		long question2Id = question2.getId();
		long question3Id = question3.getId();
		
		Question q1 = eManager.find(Question.class, question1Id);
		Question q2 = eManager.find(Question.class, question1Id);
		Question q3 = eManager.find(Question.class, question1Id);
		
		assertNotNull(q1);
		assertNotNull(q2);
		assertNotNull(q3);
		
		eManager.getTransaction().commit();
		eManager.close();
		emFactory.close();
	}

}
