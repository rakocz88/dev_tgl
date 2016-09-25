package pl.pilaf.inz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.pilaf.inz.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByFirstName(String firstName);

	List<User> findByLastName(String lastName);

	List<User> findByLogin(String login);

	@Query("Select u from User u where u.login like %:login% and u.firstName like %:firstName% and u.lastName like %:lastName%")
	List<User> filter(@Param(value = "login") String login, @Param(value = "firstName") String firstName,
			@Param(value = "lastName") String lastName);

	List<User> findByFirstNameLikeAndLastNameLikeAndLoginLike(String firstName, String lastName, String login);

}
