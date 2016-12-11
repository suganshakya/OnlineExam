package np.com.onlineExam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Component;

@Component
public class TeacherDAO {
	final private String SELECT_TEACHER = "SELECT t FROM Teacher t";

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persist(Teacher teacher) {
		em.persist(teacher);

	}

	public List<Teacher> getAllTeachers() {
		TypedQuery<Teacher> query = em.createQuery(SELECT_TEACHER, Teacher.class);
		return query.getResultList();
	}

	public boolean isExist(String username) {
		Teacher teacher;
		try {
			TypedQuery<Teacher> query = em.createQuery(SELECT_TEACHER + " where t.username = '" + username + "'",
					Teacher.class);
			teacher = query.getSingleResult();
		} catch (NoResultException nre) {
			return false;
		}
		return true;
	}

	public boolean validate(String username, String password) {
		Teacher teacher;
		try {
			TypedQuery<Teacher> query = em.createQuery(SELECT_TEACHER +
					" where t.username = '" + username + "' and t.password = '" + password + "'",
					Teacher.class);
			teacher = query.getSingleResult();
		} catch (NoResultException nre) {
			return false;
		}
		return true;
	}

	@Transactional
	public void remove(Teacher t) {
		em.remove(t);
	}

}
