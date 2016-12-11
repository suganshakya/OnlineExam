package np.com.onlineExam.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import np.com.onlineExam.dao.Question;
import np.com.onlineExam.dao.Answer;

public class AnswerTest1 {

	@Test
	public void test() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager eManager = emFactory.createEntityManager();
		eManager.getTransaction().begin();

		Question q = new Question();

		Answer answer1 = new Answer();
		answer1.setText("Object Oriented Language");
		Answer answer2 = new Answer();
		answer2.setText("President");
		Answer answer3 = new Answer();
		answer3.setText("True/");

		eManager.persist(q);
		eManager.persist(answer1);
		eManager.persist(answer2);
		eManager.persist(answer3);

		long answer1Id = answer1.getId();
		long answer2Id = answer2.getId();
		long answer3Id = answer3.getId();

		Answer ans1 = eManager.find(Answer.class, answer1Id);
		Answer ans2 = eManager.find(Answer.class, answer2Id);
		Answer ans3 = eManager.find(Answer.class, answer3Id);

		assertNotNull(ans1);
		assertNotNull(ans2);
		assertNotNull(ans3);

		eManager.getTransaction().commit();
		eManager.close();
		emFactory.close();
	}

}
