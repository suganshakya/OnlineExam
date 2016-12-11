package np.com.onlineExam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

@Component
public class QuestionDAO {
	final private String SELECT_QUESTION = "Select q from Question q";

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persist(Question question) {
		em.persist(question);
	}

	public Question getQuestion(long qId) {
		Question q = em.find(Question.class, qId);
		return q;
	}

	public List<Question> getAllQuestion(long examId) {
		List<Question> qList;
		try {
			TypedQuery<Question> query = em
					.createQuery(SELECT_QUESTION + " where UPPER(q.exam.id) = " + examId, Question.class);
			qList = query.getResultList();
		} catch (NoResultException nre) {
			qList = null;
		}
		return qList;
	}

	public List<String> getAllQuestionText(long examId) {

		List<String> qTextList;
		try {
			TypedQuery<String> query = em.createQuery(
					"Select q.qText from Question q where UPPER(q.exam.id) = " + examId, String.class);
			qTextList = query.getResultList();
		} catch (NoResultException nre) {
			qTextList = null;
		}
		return qTextList;
	}

	public List<Long> getAllIds(long examId) {
		List<Long> qIdList;
		try {
			TypedQuery<Long> query = em
					.createQuery("Select q.qId from Question q where UPPER(q.exam.id) = " + examId, Long.class);
			qIdList = query.getResultList();
		} catch (NoResultException nre) {
			qIdList = null;
		}
		return qIdList;
	}

	@Transactional
	public void update(Question q) {
		em.merge(q);
	}

	@Transactional
	public void remove(long questionID) {
		Question q = em.find(Question.class, questionID);
		em.remove(q);
	}

	@Transactional
	public void remove(Question question) {
		em.remove(question);
	}

}
