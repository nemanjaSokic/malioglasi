package practice.malioglasi.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import practice.malioglasi.model.Ad;
import practice.malioglasi.service.AdService;

@RestController
@RequestMapping(value = "/api/ads")
public class ApiAdController {

	@Autowired
	private AdService adService;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Ad>> getAds(@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="property",defaultValue="category.name", required=false) String property,
			@RequestParam(value="direction",defaultValue="asc", required=false) String direction,
			@RequestParam(value="filtCategory", required=false) String filtCategory,
			@RequestParam(value="filtDate", required=false) @DateTimeFormat(iso=ISO.DATE) Date filtDate,
			@RequestParam(value="user", required=false) String username){
		List<Ad> ads;
		Page<Ad> adPage;
		Direction dir = Direction.fromString(direction);
		
		if(filtCategory != null){
			adPage = adService.findAllByCategory(page, dir, property, filtCategory);
			ads = adPage.getContent();
		}else if(filtDate !=null){
			adPage = adService.findByExpire(page,dir,property,filtDate);
			ads = adPage.getContent();
		}else{
			adPage = adService.findAll(page, dir, property);
			ads = adPage.getContent();
		}
		
		return new ResponseEntity<List<Ad>>(ads,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	ResponseEntity<Ad> getAd(@PathVariable Integer id){
		Ad a = adService.findOne(id);
		if (a == null) {
			return new ResponseEntity<Ad>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Ad>(a,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	ResponseEntity<Ad> add (@RequestBody Ad Ad){
		Ad a = adService.save(Ad);
		return new ResponseEntity<Ad>(a,HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	ResponseEntity<Ad> edit (@RequestBody Ad newAd, @PathVariable Integer id){
		
		Ad a = adService.save(newAd);
		return new ResponseEntity<Ad> (a,HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	ResponseEntity<Ad> delete (@PathVariable Integer id){
		Ad a = adService.delete(id);
		return new ResponseEntity<Ad>(a,HttpStatus.OK);
	}
}
