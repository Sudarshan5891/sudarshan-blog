package myapp.sudarshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myapp.sudarshan.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String user);

}
