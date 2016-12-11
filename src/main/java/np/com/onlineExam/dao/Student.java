package np.com.onlineExam.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	private int totalObtainedMark;
	private int totalAttemptedMark;



	public Student() {
	}

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getTotalObtainedMark() {
		return totalObtainedMark;
	}

	public void setTotalObtainedMark(int totalObtainedMark) {
		this.totalObtainedMark = totalObtainedMark;
	}

	public int getTotalAttemptedMark() {
		return totalAttemptedMark;
	}

	public void setTotalAttemptedMark(int totalAttemptedMark) {
		this.totalAttemptedMark = totalAttemptedMark;
	}
	@Override
	public String toString() {
		return String.format("%s %s", firstName, lastName);
	}

}
