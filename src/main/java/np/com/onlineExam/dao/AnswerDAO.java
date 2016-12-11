package np.com.onlineExam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Component;

@Component
public class AnswerDAO {
	final private String SELECT_ANSWER = "Select a from Answer a";

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persist(Answer answer) {
		em.persist(answer);
	}

	public List<Answer> getAllAnswer(int eNumber, int qNumber) {
		List<Answer> aList;
		try {
			TypedQuery<Answer> query = em.createQuery(SELECT_ANSWER + " where UPPER(a.question.id) = "
					+ qNumber + ", UPPER(a.question.exam.id) =" + eNumber, Answer.class);
			aList = query.getResultList();
		} catch (NoResultException nre) {
			aList = null;
		}
		return aList;

	}

	public List<Answer> getAllAnswer(long qId) {
		List<Answer> answers;
		try {
			TypedQuery<Answer> query = em.createQuery(SELECT_ANSWER + " where a.question.id = " + qId,
					Answer.class);
			answers = query.getResultList();
		} catch (NoResultException nre) {
			answers = null;
		}
		return answers;

	}

	public List<Answer> getAllAnswer() {
		List<Answer> answers;

		try {
			TypedQuery<Answer> query = em.createQuery(SELECT_ANSWER, Answer.class);
			answers = query.getResultList();
		} catch (NoResultException nre) {
			answers = null;
		}
		return answers;
	}

	public void update(Answer a) {
		em.merge(a);
	}

	@Transactional
	public void remove(long questionID) {
		List<Answer> answers;
		try {
			TypedQuery<Answer> query = em.createQuery(SELECT_ANSWER + " where a.question.id =" + questionID,
					Answer.class);
			answers = query.getResultList();

			for (Answer answer : answers) {
				em.remove(answer);
			}
		} catch (NoResultException nre) {
			
		}

	}

	@Transactional
	public void remove(Answer answer) {
		em.remove(answer);
	}

}
