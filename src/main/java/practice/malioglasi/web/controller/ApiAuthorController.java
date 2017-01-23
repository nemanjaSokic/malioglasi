package practice.malioglasi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import practice.malioglasi.model.Author;
import practice.malioglasi.service.AuthorService;

@RestController
@RequestMapping(value = "/api/authors")
public class ApiAuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Author>> getAuthors(){
		List<Author> autors;
		autors = authorService.findAll();
		return new ResponseEntity<List<Author>>(autors,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	ResponseEntity<Author> getAuthor(@PathVariable String id){
		Author a = authorService.findOne(id);
		if (a == null) {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Author>(a,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	ResponseEntity<Author> add (@RequestBody Author author){
		Author a = authorService.save(author);
		return new ResponseEntity<Author>(a,HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{username}")
	ResponseEntity<Author> edit (@RequestBody Author newAuthor, @PathVariable String username){
		
		Author a = authorService.save(newAuthor);
		return new ResponseEntity<Author> (a,HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	ResponseEntity<Author> delete (@PathVariable String username){
		Author a = authorService.delete(username);
		return new ResponseEntity<Author>(a,HttpStatus.OK);
	}

}
