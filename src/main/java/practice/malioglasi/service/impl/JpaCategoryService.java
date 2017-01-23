package practice.malioglasi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import practice.malioglasi.model.Category;
import practice.malioglasi.repository.CategoryRepository;
import practice.malioglasi.service.CategoryService;

@Service
@Transactional
public class JpaCategoryService implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Category findOne(Integer id) {
		return categoryRepo.findOne(id);
	}

	@Override
	public Page<Category> findAll(int page, Direction dir, String prop) {
		Page<Category> p = categoryRepo.findAll(new PageRequest(page,5, dir, prop));
		return p;
	}

	@Override
	public Category save(Category cat) {
		return categoryRepo.save(cat);
	}

	@Override
	public List<Category> save(List<Category> categories) {
		return categoryRepo.save(categories);
	}

	@Override
	public Category delete(Integer id) {
		
		Category c = categoryRepo.findOne(id);
		
		if(c==null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant activity");
		}
		
		categoryRepo.delete(id);
		return c;
	}

	@Override
	public void delete(List<Integer> ids) {
		
		for (Integer id : ids) {
			this.delete(id);
		}
		
	}

	@Override
	public Category findByName(String category) {
		return categoryRepo.findByName(category);
	}

}
