import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import np.com.onlineExam.dao.Teacher;

public class StoreTeacherTest {
	@Test
	public void testTeacher() {
		//PersistenceProvider provider= new HibernatePersistenceProvider();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager theManager = factory.createEntityManager();
		assertNotNull(theManager);

		theManager.getTransaction().begin();
		Teacher teacher = new Teacher("Sugan","12345");
		theManager.persist(teacher);
		theManager.getTransaction().commit();

		System.out.println("im running successfully...");
		Teacher teach = (Teacher) theManager.find(Teacher.class, 1);
		System.out.println(teach.toString());
		assertNotNull(teach);
	}
}
