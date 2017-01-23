package practice.malioglasi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authors")
public class Author {

	@Column
	@Id
	private String username;
	@Column
	private String password;
	@Column
	private Boolean admin;
	@Column
	private String email;
	@Column
	private Long phone;
	@Column
	private boolean approved;
	
	public Author(String username, String password, String email, Long phone) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.admin = false;
		this.approved = false;
	}
	public Author() {
		super();
		this.approved = false;
		this.admin=false;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
}
