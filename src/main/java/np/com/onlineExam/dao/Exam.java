package np.com.onlineExam.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	private boolean status;
	private String title;
	
	public Exam(){
		this.status=false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setStatus(boolean st){
		this.status=st;
	}
	
	public boolean getStatus(){
		return status;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title=title;
	}

	@Override
	public String toString() {
		String s = "ID: " + id+","+"Status"+status; 
		return s;
	}
}
