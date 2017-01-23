package practice.malioglasi.service;

import java.util.List;

import practice.malioglasi.model.Author;

public interface AuthorService {

	Author findOne(String username);
	List<Author> findAll();
	Author save(Author author);
	List<Author> save(List<Author> authors);
	Author delete(String username);
	void delete(List<String> usernames);
	
}
