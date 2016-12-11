package np.com.onlineExam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ScoreDAO {
	final private String SELECT_SCORE = "Select s from Score s";

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persist(Score score) {
		em.persist(score);
	}

	public Score getScore(long scoreId) {
		Score score = em.find(Score.class, scoreId);
		return score;
	}

	public void setScore(long examID, long studentID, int value) {
		TypedQuery<Score> query = em.createQuery(SELECT_SCORE + " where UPPER(s.exam.id) = " + examID
				+ "and UPPER(s.student.id) = " + studentID, Score.class);
		Score score = query.getSingleResult();
		score.setValue(value);
		update(score);
	}

	public List<Score> getAllScoreByExam(long examId) {
		List<Score> scoreList;
		try {
			TypedQuery<Score> query = em.createQuery(SELECT_SCORE + " where UPPER(s.exam.id) = " + examId,
					Score.class);
			scoreList = query.getResultList();
		} catch (NoResultException nre) {
			scoreList = null;
		}
		return scoreList;
	}

	public List<Score> getAllScoreByStudent(long studentId) {
		List<Score> scoreList;
		try {
			TypedQuery<Score> query = em.createQuery(SELECT_SCORE + " where UPPER(s.student.id) = " + studentId,
					Score.class);
			scoreList = query.getResultList();
		} catch (NoResultException nre) {
			scoreList = null;
		}
		return scoreList;
	}

	public Score getStudentByeIdAndSid(long studentId, long examId) {
		Score score;
		List<Score> s = null;
		try {
			TypedQuery<Score> query = em.createQuery(SELECT_SCORE + " where UPPER(s.student.id) = "
					+ studentId + " and UPPER(s.exam.id) = " + examId, Score.class);
			s = query.getResultList();
		} catch (NoResultException nre) {
			score = null;
		} finally {
			try {
				score = s.get(0);
			} catch (Exception e) {
				score = null;
			}
		}
		return score;
	}

	@Transactional
	public void update(Score score) {
		em.merge(score);
	}

	@Transactional
	public void remove(long ScoreID) {
		Score q = em.find(Score.class, ScoreID);
		em.remove(q);
	}

	@Transactional
	public void remove(Score Score) {
		em.remove(Score);
	}

	// ScoreDAO

	public List<Score> getAllScore() {
		List<Score> scoreList;
		try {
			TypedQuery<Score> query = em.createQuery(SELECT_SCORE, Score.class);
			scoreList = query.getResultList();
		} catch (NoResultException nre) {
			scoreList = null;
		}
		return scoreList;
	}
}
