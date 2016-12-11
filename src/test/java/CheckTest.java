//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import np.com.onlineExam.dao.Answer;
//import np.com.onlineExam.dao.Exam;
//import np.com.onlineExam.dao.Question;
//
//public class CheckTest {
//	public static void main(String [] args){
//		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
//		EntityManager eManager = emFactory.createEntityManager();
//		eManager.getTransaction().begin();
//	
//		Exam exam = new Exam();
////		exam.setNumber(50);
//		eManager.persist(exam);
//
//		Question q1 = new Question();
//		q1.setqNumber(1);
//		q1.setqText("Which is the smallest Animal?");
//		q1.setType("MultipleChoice");
//		q1.setExam(exam);
//		
//		Question q2 = new Question();//2,"Who will win the league?","MultipleChoice",kc);
//		q2.setqNumber(2);
//		q2.setqText("Where is Nepal?");
//		q2.setType("MultipleChoice");
//		q2.setExam(exam);
//		
////		eManager.persist(exam);
//		eManager.persist(q1);
//		eManager.persist(q2);
//		//eManager.persist(exam);
//		
//		Answer a11=new Answer(1, "Rat","true",q1);		//Java is most boring
//		Answer a12=new Answer(2, "Python","false",q1);	
//		Answer a13=new Answer(3, "Elephant","false",q1);		
//		Answer a14=new Answer(4, "Whale","false",q1);	
//		
//		Answer a21=new Answer(1, "Europe","false",q2);
//		Answer a22=new Answer(2, "Africa","false",q2);
//		Answer a23=new Answer(3, "Asia","true",q2);
//		Answer a24=new Answer(4, "America","false",q2);
//		
//		eManager.persist(a11);
//		eManager.persist(a12);
//		eManager.persist(a13);
//		eManager.persist(a14);
//		
//		eManager.persist(a21);
//		eManager.persist(a22);
//		eManager.persist(a23);
//		eManager.persist(a24);
//		
//		// Update of Database Content
//		/*
//		// The first question should be "most powerful" not "most boring"
//		System.out.println("Question 1 was mistake.!!!");
//		Question q = eManager.find(Question.class, q1.getQid());
//		q.setqText("Which is the most powerful language?");
//		System.out.println("Question 2 edited.");
//		
//		System.out.println("Mark C as my powerful language");
//		Answer a = eManager.find(Answer.class, a11.getId());
//		a.setValue("false");							// Java is not powerful
//		eManager.persist(a);
//		Answer a1 = eManager.find(Answer.class, a13.getId());
//		a1.setValue("true");	 						// C is powerful
//		eManager.persist(a1);
//		
//		//Delete Data
//		// Dont put ManU as the option, no one would believe they will win
//		Answer manu = eManager.find(Answer.class, a21.getId());
//		eManager.remove(manu);
//		*/
//		//Actual write to database
//		eManager.getTransaction().commit();
//		
//		eManager.close();		
//		emFactory.close();		
//	}
//}
//
