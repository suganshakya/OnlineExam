package np.com.onlineExam.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="score")
public class Score {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	private int value;


	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@ManyToOne
	private Student student;

	@ManyToOne
	private Exam exam;

	public Score(){}
	
	public Score(int value, Student student, Exam exam){
		this.value = value;
		this.student = student;
		this.exam = exam;
		
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
