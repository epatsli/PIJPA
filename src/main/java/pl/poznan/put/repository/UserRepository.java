package pl.poznan.put.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.poznan.put.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//User findByLogin (String login);
	
	//@Query("select login from user where upper(user.login) like concat(upper(:login), '%')")
	User findByLogin(String login);
	
}
