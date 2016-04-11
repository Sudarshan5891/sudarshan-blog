package myapp.sudarshan.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import myapp.sudarshan.annotations.UniqueUserName;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min=3, message="User Name must be atleast 3 characters")
	@Column(unique=true)
	@UniqueUserName(message="User Already Exists in the system")
	private String name;

	@Email
	@Column(unique=true)
	private String email;

	@Size(min=5, message="Password must be atleast 5 characters")
	private String password;
	
	@Transient
	private String columnNotWanted;
	
	private boolean enabled;

	public boolean getEnabled() {
		return enabled;
	}

	@ManyToMany
	@JoinTable
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade=CascadeType.REMOVE)
	private List<Blog> blogs;

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
