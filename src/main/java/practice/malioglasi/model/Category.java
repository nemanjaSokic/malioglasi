package practice.malioglasi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue
	@Column
	private Integer categoryId;
	
	@Column
	private String name;
	
	@Column
	private String description;

	public Category(int categoryId, String name, String describe) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.description = describe;
	}
	public Category(String name, String describe) {
		super();
		this.name = name;
		this.description = describe;
	}
	public Category(String name) {
		super();
		this.name = name;
	}
	public Category() {
		super();
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return description;
	}
	public void setDescribe(String describe) {
		this.description = describe;
	}
	
	
	
}
