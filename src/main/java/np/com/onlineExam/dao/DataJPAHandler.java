package np.com.onlineExam.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DataJPAHandler {
	EntityManagerFactory emFactory;
	EntityManager eManager;
	
	public DataJPAHandler(){
		emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		eManager = emFactory.createEntityManager();
		eManager.getTransaction().begin();
	}
	
	public List<Question> getAllQuestion(){
		TypedQuery<Question> query = eManager.createQuery("Select q from Question q", Question.class);
		List<Question> questionList = query.getResultList();
		return questionList;
		
	}
	public List<Question> getAllQuestion(long examID){
		TypedQuery<Question> query = 
				eManager.createQuery("Select q from Question q "
						+ "where UPPER(q.exam.id)="+ examID, Question.class);
		List<Question> questionList = query.getResultList();
		return questionList;		
	}

	public List<Answer> getAnswers(long questionID){		
		TypedQuery<Answer> query = eManager.createQuery("Select a from Answer a where UPPER(a.question.qId) = "+questionID, Answer.class);;
		List<Answer> questionList = query.getResultList();
		return questionList;	
	}
	
	
	public void commit(){
		eManager.getTransaction().commit();
	}
	
	public void close(){
		eManager.close();
		emFactory.close();
	}
}
