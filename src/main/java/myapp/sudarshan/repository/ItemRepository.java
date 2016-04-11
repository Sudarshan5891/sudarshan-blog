package myapp.sudarshan.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myapp.sudarshan.entity.Blog;
import myapp.sudarshan.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	List<Item> findByBlog(Blog blog, Pageable pageable);

	Item findByBlogAndTitle(Blog blog, String title);

}
