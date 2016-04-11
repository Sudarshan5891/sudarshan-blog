package myapp.sudarshan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(length=1000)
	private String title;
	
	@Lob
	@Type(type = "org.hibernate.type.StringType")// new version 5 <="org.hibernate.type.StringClobType")=> old hib version 4
	private String description; //@Embedded annotation will embed the address attributes in same table.

	@Column(name = "published_date")
	//@Temporal(TemporalType.TIME)
	private Date publishedDate;
	
	@Column(length=1000)
	private String link;

	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		
		
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date date) {
		this.publishedDate = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	

}
