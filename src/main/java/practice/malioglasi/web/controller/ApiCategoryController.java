package practice.malioglasi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import practice.malioglasi.model.Category;
import practice.malioglasi.service.CategoryService;

@RestController
@RequestMapping(value = "/api/categories")
public class ApiCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Category>> getCategories(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="property",defaultValue="name", required=false) String property,
			@RequestParam(value="direction",defaultValue="asc", required=false) String direction){
		List<Category> categories;
		Direction dir = Direction.fromString(direction);
		
		Page<Category> pageCategories = categoryService.findAll(page, dir, property);
		categories = pageCategories.getContent();
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	ResponseEntity<Category> getCategory(@PathVariable Integer id){
		Category a = categoryService.findOne(id);
		if (a == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Category>(a,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	ResponseEntity<Category> add (@RequestBody Category Category){
		Category a = categoryService.save(Category);
		return new ResponseEntity<Category>(a,HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	ResponseEntity<Category> edit (@RequestBody Category newCategory, @PathVariable Integer id){
		
		Category a = categoryService.save(newCategory);
		return new ResponseEntity<Category> (a,HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	ResponseEntity<Category> delete (@PathVariable Integer id){
		Category a = categoryService.delete(id);
		return new ResponseEntity<Category>(a,HttpStatus.OK);
	}
	
}
