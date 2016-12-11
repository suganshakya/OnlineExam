package np.com.onlineExam.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teacher")
public class Teacher{
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;

	public Teacher() {

	}

	public Teacher(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return String.format("Teacher: %s %s", username, password);
	}
}
