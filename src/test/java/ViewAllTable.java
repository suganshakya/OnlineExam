import static org.junit.Assert.*;

import np.com.onlineExam.dao.Answer;
import np.com.onlineExam.dao.Exam;
import np.com.onlineExam.dao.Question;
import np.com.onlineExam.dao.Score;
import np.com.onlineExam.dao.Student;
import np.com.onlineExam.dao.Teacher;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewAllTable extends JFrame {

	private static EntityManagerFactory eManagerFactory;
	private static EntityManager eManager;

	private JScrollPane scroll;
	private JTextArea textArea;

	public ViewAllTable (){
		super("Data Contents");
		setSize(800, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textArea = new JTextArea();
		textArea.setEditable(true);
		scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(scroll);
		this.setVisible(true);
	}
	
	// @Test will not work,because it closed automatically
	public static void main (String [] args){
		eManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		eManager = eManagerFactory.createEntityManager();
		eManager.getTransaction().begin();
		ViewAllTable v = new ViewAllTable();
		assertNotNull(eManager);
		v.showAllTeacher();
		v.showAllStudent();
		v.showAllExam();
		v.showAllQuestion();
		v.showAllAnswer();
		v.showAllScore();
		v.setVisible(true);
		eManager.getTransaction().commit();
		eManager.close();
		eManagerFactory.close();
	}
	

	public void showAllTeacher() {
		TypedQuery<Teacher> query = eManager.createQuery("Select e from Teacher e",Teacher.class);
		List<Teacher> list = query.getResultList();
		Iterator<Teacher> itr = list.iterator();
		textArea.append("\n All Teachers in Database are: \n");
		while (itr.hasNext()) {
			textArea.append(itr.next().toString());
			textArea.append("\n");
		}
	}
	
	public void showAllStudent() {
		TypedQuery<Student> query = eManager.createQuery("Select e from Student e",Student.class);
		List<Student> list = query.getResultList();
		Iterator<Student> itr = list.iterator();
		textArea.append("\n All Students in Database are: \n");
		while (itr.hasNext()) {
			textArea.append(itr.next().toString());
			textArea.append("\n");
		}
	}
	
	public void showAllExam() {
		TypedQuery<Exam> query = eManager.createQuery("Select e from Exam e", Exam.class);
		List<Exam> list = query.getResultList();
		Iterator<Exam> itr = list.iterator();
		textArea.append("\n All Exams in Database are: \n");
		while (itr.hasNext()) {
			textArea.append(itr.next().toString());
			textArea.append("\n");
		}
	}

	public void showAllQuestion() {
		TypedQuery<Question> query = eManager.createQuery("Select q from Question q", Question.class);
		List<Question> list = query.getResultList();
		Iterator<Question> itr = list.iterator();
		textArea.append("\n All Questions in Database are: \n");
		while (itr.hasNext()) {
			textArea.append(itr.next().toString());
			textArea.append("\n");
		}
	}

	public void showAllAnswer() {
		TypedQuery<Answer> query = eManager.createQuery("Select a from Answer a", Answer.class);
		List<Answer> list = query.getResultList();
		Iterator<Answer> itr = list.iterator();
		textArea.append("\n All Answers in Database are: \n");
		while (itr.hasNext()) {
			textArea.append(itr.next().toString());
			textArea.append("\n");
		}
	}
	
	public void showAllScore() {
		TypedQuery<Score> query = eManager.createQuery("Select a from Score a", Score.class);
		List<Score> list = query.getResultList();
		Iterator<Score> itr = list.iterator();
		textArea.append("\n The Score in Database are: \n");
		while (itr.hasNext()) {
			textArea.append(itr.next().toString());
			textArea.append("\n");
		}
	}

}

