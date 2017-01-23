package practice.malioglasi;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import practice.malioglasi.model.Ad;
import practice.malioglasi.model.Author;
import practice.malioglasi.model.Category;
import practice.malioglasi.service.AdService;
import practice.malioglasi.service.AuthorService;
import practice.malioglasi.service.CategoryService;

@Component
public class TestData {

	@Autowired
	private AuthorService as;
	@Autowired
	private CategoryService cs;
	@Autowired
	private AdService ads;
	
	@PostConstruct
	public void init(){
		Author a = new Author();
		a.setUserName("admin");
		a.setAdmin(true);
		a.setPassword("admin");
		a.setEmail("nemskc@gmail.com");
		a.setPhone(5556665888L);
		a.setApproved(true);
		as.save(a);
		
		Author b = new Author();
		b.setUserName("ina");
		b.setAdmin(false);
		b.setApproved(true);
		b.setPassword("456");
		b.setEmail("tmarina89@gmail.com");
		b.setPhone(333333333L);
		as.save(b);
		
		Category c = new Category();
		c.setCategoryId(1);
		c.setDescribe("Ovo je prvi decribe1");
		c.setName("ime1");
		cs.save(c);
		
		Category w = new Category();
		w.setCategoryId(2);
		w.setDescribe("Ovo je drugi decribe1");
		w.setName("ime2");
		cs.save(w);
		
		Ad ad1 = new Ad();
		ad1.setAuthor(a);
		ad1.setCategory(c);
		ad1.setText("Tekst ogalasa je napisan!");
		ad1.setTitle("Naslov 1");
		ads.save(ad1);
		
		Ad ad2 = new Ad();
		ad2.setAuthor(b);
		ad2.setCategory(w);
		ad2.setText("Tekst ogalasa je napisan2!");
		ad2.setTitle("Naslov 2");
		ads.save(ad2);
		
	
	}
	
}
