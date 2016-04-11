package myapp.sudarshan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import myapp.sudarshan.entity.Blog;
import myapp.sudarshan.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	List<Blog> findByUser(User user);

}
