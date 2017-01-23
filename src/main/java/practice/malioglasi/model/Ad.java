package practice.malioglasi.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ads")
public class Ad {
	
	@Id
	@Column
	@GeneratedValue
	private Integer id;
	@Column
	private String title;
	@Column
	private String text;
	@Column
	private Date created;
	@Column
	private Date expire;
	@OneToOne(fetch=FetchType.EAGER)
	private Author author;
	@OneToOne(fetch=FetchType.EAGER)
	private Category category;
	public Ad(Integer id, String title, String text, Author author, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.created = new Date();
		this.expire = new Date(this.created.getTime()+TimeUnit.DAYS.toMillis(10));
		this.author = author;
		this.category = category;
	}
	public Ad() {
		super();
		this.created = new Date();
		this.expire = new Date(this.created.getTime()+TimeUnit.DAYS.toMillis(10));
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
