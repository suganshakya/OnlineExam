package np.com.onlineExam.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "text")
	private String text; // "C is object Oriented"

	@ManyToOne
	private Question question;

	public Answer() {

	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
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

	@Override
	public String toString() {
		String s = "ID: " + id + ", Text: " + text + ", Question ID: " + question.getId();
		return s;
	}

}
