package practice.malioglasi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import practice.malioglasi.model.Ad;
import practice.malioglasi.model.Author;
import practice.malioglasi.model.Category;
import practice.malioglasi.service.AdService;
import practice.malioglasi.service.AuthorService;
import practice.malioglasi.service.CategoryService;


@RestController
@RequestMapping(value = "/api/{username}/ads")
public class ApiAuthorAdController {

	@Autowired
	private AdService adService;
	
	@Autowired
	private AuthorService as;
	@Autowired
	private CategoryService cs;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Ad>> getUserAds(@PathVariable(value="username") String username){
		List<Ad> ads;		
		
		Author user = as.findOne(username);
		ads = adService.findAllByUser(user);
		
		return new ResponseEntity<List<Ad>>(ads,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	ResponseEntity<Ad> add (@RequestBody Ad a,
			@PathVariable(value="username") String username,
			@RequestParam(value="category", required=false) String category){
		
		Category c = cs.findByName(category);
		Author user = as.findOne(username);
		
		a.setAuthor(user);
		a.setCategory(c);
		
		a = adService.save(a);
		return new ResponseEntity<Ad>(a,HttpStatus.CREATED);
	}
}
