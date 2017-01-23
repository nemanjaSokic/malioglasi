package practice.malioglasi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import practice.malioglasi.model.Author;
import practice.malioglasi.repository.AuthorRepository;
import practice.malioglasi.service.AuthorService;

@Service
@Transactional
public class JpaAuthorService implements AuthorService {

	@Autowired
	private AuthorRepository authorRep;
	
	@Override
	public Author findOne(String username) {
		return authorRep.findOne(username);
	}

	@Override
	public List<Author> findAll() {
		return authorRep.findAll();
	}

	@Override
	public Author save(Author author) {
		return authorRep.save(author);
	}

	@Override
	public List<Author> save(List<Author> authors) {
		return authorRep.save(authors);
	}

	@Override
	public Author delete(String username) {
		
		Author a = findOne(username);
		if(a==null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant activity");
		}
		authorRep.delete(a);
		
		return a;
	}

	@Override
	public void delete(List<String> usernames) {
		
		for (String username : usernames) {
			this.delete(username);
		}
		
	}

}
