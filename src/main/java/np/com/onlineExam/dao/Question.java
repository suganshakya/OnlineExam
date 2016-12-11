package np.com.onlineExam.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue 
	@Column(name = "id")
	private long id;

	@Column(name = "text")
	private String text;

	@Column(name = "type")
	private String type;

	@Column(name="correctAnswer")
	private String correctAnswer;
	
	@ManyToOne
	private Exam exam;

	// Constructor
	public Question() {
	}
	
	public Question(long id, String text, String type, Exam exam){
		this.id = id;
		this.text = text;
		this.type = type;
		this.exam = exam;
	}
	
	public Question(String text, String type, Exam exam){
		this.text = text;
		this.type = type;
		this.exam = exam;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString() {
		String s = "ID: " + id + ", Text: " + text + ", Type: " + type + ", Exam ID: "
				+ exam.getId();
		return s;
	}
}
