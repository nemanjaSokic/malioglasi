package practice.malioglasi.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import practice.malioglasi.model.Ad;
import practice.malioglasi.model.Author;


public interface AdService {

	Ad findOne(Integer id);
	Page<Ad> findAll(int page, Direction dir, String property);
	Ad save(Ad Ad);
	List<Ad> save(List<Ad> Ads);
	Ad delete(Integer id);
	void delete(List<Integer> ids);
	Page<Ad> findAllByCategory(int page, Direction dir, String property, String filtCategory);
	Page<Ad> findByExpire(int page, Direction dir, String property, Date filtDate);
	List<Ad> findAllByUser(Author user);
}
