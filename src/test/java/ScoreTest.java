import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import np.com.onlineExam.dao.Exam;
import np.com.onlineExam.dao.Score;
import np.com.onlineExam.dao.Student;
import np.com.onlineExam.dao.Teacher;

public class ScoreTest {

	@Test
	public void scoreTest() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager eManager = emFactory.createEntityManager();
		eManager.getTransaction().begin();
		
		Teacher teacher = new Teacher("Alex","Furgusan");
		
		Student john = new Student("John","Cena");
		Student barrack = new Student("Barrack","Obama");
		Student albert = new Student("Albert","Einstein");
		
		Exam exam1 = new Exam();
				
		Score barrackScoreInExam1 = new Score(50, barrack, exam1);
		Score johnScoreInExam1 = new Score(70, john, exam1);
		
		Exam exam2 = new Exam();
		
		Score barrackScoreInExam2 = new Score(80, barrack, exam2);
		Score johnScoreInExam2 = new Score(77, john, exam2);
		Score albertScoreInExam2 = new Score(40, albert, exam2);
		
		Exam exam3 = new Exam();
		Score barrackScoreInExam3 = new Score(20, barrack, exam3);
		Score johnScoreInExam3 = new Score(79, john, exam3);
		Score albertScoreInExam3 = new Score(30, albert, exam3);
		
		eManager.persist(teacher);
		
		eManager.persist(john);
		eManager.persist(barrack);
		eManager.persist(albert);	

		eManager.persist(exam1);
		eManager.persist(exam2);
		eManager.persist(exam3);
		
		
		eManager.persist(johnScoreInExam1);
		eManager.persist(barrackScoreInExam1);
		
		eManager.persist(johnScoreInExam2);
		eManager.persist(barrackScoreInExam2);
		eManager.persist(albertScoreInExam2);
		
		eManager.persist(johnScoreInExam3);
		eManager.persist(barrackScoreInExam3);
		eManager.persist(albertScoreInExam3);
		
		eManager.getTransaction().commit();
		eManager.close();
		emFactory.close();
	}

}