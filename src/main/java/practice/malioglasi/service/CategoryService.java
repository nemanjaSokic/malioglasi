package practice.malioglasi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import practice.malioglasi.model.Category;


public interface CategoryService {

	Category findOne(Integer id);
	Page<Category> findAll(int page, Direction dir, String property);
	Category save(Category cat);
	List<Category> save(List<Category> categories);
	Category delete(Integer id);
	void delete(List<Integer> ids);
	Category findByName(String category);
	
}
