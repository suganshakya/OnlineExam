package np.com.onlineExam.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import np.com.onlineExam.dao.Exam;
import np.com.onlineExam.dao.Score;
import np.com.onlineExam.dao.Student;

public class ScoreTest1 {

	@Test
	public void scoreTest() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager eManager = emFactory.createEntityManager();
		eManager.getTransaction().begin();
		
	
		Student john = new Student("John","Cena");
		Student barrack = new Student("Barrack","Obama");
		Student albert = new Student("Albert","Einstein");
		
		Exam exam1 = new Exam();
				
		Score barrackScoreInExam1 = new Score(50, barrack, exam1);
		Score johnScoreInExam1 = new Score(70, john, exam1);
		Score albertScoreInExam1 = new Score(40, albert, exam1);
		
		Exam exam2 = new Exam();
		Score barrackScoreInExam2 = new Score(20, barrack, exam2);
		Score johnScoreInExam2 = new Score(79, john, exam2);
		Score albertScoreInExam2 = new Score(20, albert, exam2);
		
	
		eManager.persist(john);
		eManager.persist(barrack);
		eManager.persist(albert);	

		eManager.persist(exam1);
		eManager.persist(exam2);
	
		eManager.persist(johnScoreInExam1);
		eManager.persist(barrackScoreInExam1);
		eManager.persist(albertScoreInExam1);
		
		eManager.persist(johnScoreInExam2);
		eManager.persist(barrackScoreInExam2);
		eManager.persist(albertScoreInExam2);
		
		
		long id11 = johnScoreInExam1.getId();
		long id21 = barrackScoreInExam1.getId();
		long id31 = albertScoreInExam1.getId();
		
		long id12 = johnScoreInExam2.getId();
		long id22 = barrackScoreInExam2.getId();
		long id32 = albertScoreInExam2.getId();
		
		
		Score s11 = eManager.find(Score.class, id11);
		Score s21 = eManager.find(Score.class, id21);
		Score s31 = eManager.find(Score.class, id31);
		
		Score s12 = eManager.find(Score.class, id12);
		Score s22 = eManager.find(Score.class, id22);
		Score s32 = eManager.find(Score.class, id32);
		
		assertNotNull(s11);
		assertNotNull(s21);
		assertNotNull(s31);
		
		assertNotNull(s12);
		assertNotNull(s22);
		assertNotNull(s32);
		
		
		eManager.getTransaction().commit();
		eManager.close();
		emFactory.close();
	}

}