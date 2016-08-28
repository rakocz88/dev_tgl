package pl.pilaf.inz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.User;

@Repository
public interface BandRepository extends CrudRepository<Band, Long> {

	List<Band> findByName(String name);

	List<Band> findByGengre(String name);

	List<Band> findByMembers(List<User> users);

	@Query("Select u from Band u where u.name like %:name% and u.gengre like %:gengre%")
	List<Band> filter(@Param(value = "name") String name, @Param(value = "gengre") String gengre);

}
