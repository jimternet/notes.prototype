package us.hennepin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.tapestry5.beaneditor.Validate;

@Entity
public class PersistableNote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Validate("required")
	public String contents;
	
	public String title;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	 public String toString() {
		   return ReflectionToStringBuilder.toString(this);
		 }

}
