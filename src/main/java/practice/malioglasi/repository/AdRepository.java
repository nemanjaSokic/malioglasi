package practice.malioglasi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import practice.malioglasi.model.Ad;
import practice.malioglasi.model.Author;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {

	@Query("select ad from Ad ad where ad.category.name = :filtCategory")
	Page<Ad> findAllByCategory(Pageable page, @Param("filtCategory") String filtCategory);
	
//	@Query(value="select a from Ad a where a.expire = :filtDate")
	Page<Ad> findByExpireAfter(Pageable page,Date filtDate);

	List<Ad> findByAuthor(Author user);

}
