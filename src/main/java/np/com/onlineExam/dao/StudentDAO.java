package np.com.onlineExam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StudentDAO {
	final private String SELECT_STUDENT = "Select s from Student s";

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persist(Student student) {
		em.persist(student);
	}

	public Student getStudent(long studentID) {
		Student s = em.find(Student.class, studentID);
		return s;
	}

	public List<Student> getStudentId(String firstName, String lastName) {
		List<Student> s;
		try {
			TypedQuery<Student> query = em.createQuery(SELECT_STUDENT + " where s.firstName ='" + firstName
					+ "' and s.lastName = '" + lastName + "'", Student.class);
			s = query.getResultList();
		} catch (NoResultException nre) {
			s = null;
		}

		return s;
	}

	public List<Student> getAllStudent() {
		List<Student> studentList;
		try{
		TypedQuery<Student> query = em.createQuery(SELECT_STUDENT, Student.class);
		studentList = query.getResultList();
		} catch (NoResultException nre) {
			studentList = null;
		}

		return studentList;
	}

	@Transactional
	public void remove(long studentID) {
		Student student = em.find(Student.class, studentID);
		em.remove(student);
	}

	@Transactional
	public void remove(Student student) {
		em.remove(student);
	}
}
