import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import np.com.onlineExam.dao.Student;

public class StudentTest {
	@Test
	public void test() {

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager eManager = emFactory.createEntityManager();
		eManager.getTransaction().begin();
		
		Student s1= new Student("Pramod", "Banjara");
		Student s2 = new Student("Nabin","Dahal");
		Student s3 = new Student("Pramod", "Banjara");

		
		System.out.println("Hello");
		
		eManager.persist(s1);
		eManager.persist(s2);
		eManager.persist(s3);
		
		
		System.out.println("Hello aFTER pERISIST");
		String name = "Pramod";
		String lastName = "Banjara";
		//List<Student> sList = sDAO.getStudentByName("Pramod","Banjara");
		//TypedQuery<Student> query = eManager.createQuery("Select s from Student s where s.firstName like " + "Pramod", Student.class);
		
		//TypedQuery<Student> query = eManager.createQuery("Select s from Student s where UPPER(s.id) = " + s1.getId(), Student.class);
		
		TypedQuery<Student> query = eManager.createQuery("Select s from Student s where s.firstName like '" + name + 
				"' and s.lastName like '" + lastName+"'", Student.class);
		List<Student> list = query.getResultList();
		for(Student s: list){
			System.out.println(s);
		}
		String name1 = "PRAMOD";
		String lastName1 = "Banjara";
		
		TypedQuery<Student> query1 = eManager.createQuery("Select s from Student s where s.firstName like '" + name1 + 
				"' and s.lastName like '" + lastName1 +"'", Student.class);
		List<Student> list1 = query.getResultList();
		for(Student s: list1){
			System.out.println(s);
		}
		
		System.out.println("Hello AFTER SELECT");
		/*
		try{
		List<Student> sList2 = sDAO.getStudentByName("Albert","Banjara");
		
		for(Student s: sList2){
			System.out.println(s);
		}
	
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
			*/
		eManager.getTransaction().commit();
		eManager.close();
		emFactory.close();
	}
	

}
