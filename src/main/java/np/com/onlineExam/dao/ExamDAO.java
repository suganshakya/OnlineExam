package np.com.onlineExam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Component;

@Component
public class ExamDAO {
	private final String SELECT_EXAM = "Select e from Exam e";

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persist(Exam exam) {
		em.persist(exam);
	}

	public Exam getExam(long id) {
		Exam exam;
		try {
			exam = em.find(Exam.class, id);
		} catch (Exception e) {
			exam = null;
		}
		return exam;
	}

	public Exam getPublishedExam() {
		Exam e = null;
		try {
			TypedQuery<Exam> query1 = em.createQuery(SELECT_EXAM +" where e.status = " + true, Exam.class);
			e = query1.getSingleResult();
		} catch (NoResultException nre) {
			e = null;
		}
		return e;
	}

	public List<Exam> getAllExam() {
		List<Exam> examList;
		try {
			TypedQuery<Exam> query = em.createQuery(SELECT_EXAM, Exam.class);
			examList = query.getResultList();
		} catch (NoResultException nre) {
			examList = null;
		}
		return examList;
	}

	@Transactional
	public void update(Exam e) {
		em.merge(e);
	}

	@Transactional
	public void remove(long examId) {
		Exam exam = em.find(Exam.class, examId);
		if (exam == null) {
			System.out.println("Exam not found");
			return;

		}
		TypedQuery<Question> query = em.createQuery("SELECT q from Question q where q.exam.id = " + examId,
				Question.class);
		List<Question> list = query.getResultList();
		for (Question q : list) {
			TypedQuery<Answer> query1 = em.createQuery("SELECT a from Answer a where a.question.id = " + q.getId(),
					Answer.class);
			List<Answer> answerList = query1.getResultList();
			for (Answer a : answerList) {
				em.remove(a);
			}
			em.remove(q);
		}

		em.remove(exam);
	}

	@Transactional
	public void remove(Exam e) {
		em.remove(e);
	}

}
