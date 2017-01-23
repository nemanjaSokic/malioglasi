package practice.malioglasi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import practice.malioglasi.model.Ad;
import practice.malioglasi.model.Author;
import practice.malioglasi.repository.AdRepository;
import practice.malioglasi.service.AdService;

@Service
@Transactional
public class JpaAdService implements AdService {

	@Autowired
	private AdRepository adRep;
	
	@Override
	public Ad findOne(Integer id) {
		return adRep.findOne(id);
	}

	@Override
	public Page<Ad> findAll(int page, Direction dir, String prop) {
		return adRep.findAll(new PageRequest(page, 5, dir, prop));
	}

	@Override
	public Ad save(Ad Ad) {
		return adRep.save(Ad);
	}

	@Override
	public List<Ad> save(List<Ad> Ads) {
		return adRep.save(Ads);
	}

	@Override
	public Ad delete(Integer id) {
		
		Ad a = adRep.findOne(id);
		if(a==null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant activity");
		}
		adRep.delete(a);
		
		return a;
	}

	@Override
	public void delete(List<Integer> ids) {
		for (Integer id : ids) {
			this.delete(id);
		}

}

	@Override
	public Page<Ad> findAllByCategory(int page, Direction dir, String property, String filtCategory) {
		return adRep.findAllByCategory(new PageRequest(page, 5, dir,property), filtCategory);
	}

	@Override
	public Page<Ad> findByExpire(int page, Direction dir, String property, Date filtDate) {
		return adRep.findByExpireAfter(new PageRequest(page, 5, dir, property), filtDate);
	}

	@Override
	public List<Ad> findAllByUser(Author user) {
		List<Ad> a = adRep.findByAuthor(user);
		return a;
	}}
