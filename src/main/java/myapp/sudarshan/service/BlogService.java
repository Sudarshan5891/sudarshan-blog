package myapp.sudarshan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import myapp.sudarshan.entity.Blog;
import myapp.sudarshan.entity.Item;
import myapp.sudarshan.entity.User;
import myapp.sudarshan.exceptions.RssException;
import myapp.sudarshan.repository.BlogRepository;
import myapp.sudarshan.repository.ItemRepository;
import myapp.sudarshan.repository.UserRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RssService rssService;
	
	public void saveItems(Blog blog) {
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for (Item item : items) {
				
				item.setDescription(HtmlUtils.htmlEscape(item.getDescription()));
				Item savedItem = itemRepository.findByBlogAndTitle(blog, item.getTitle());
				if(savedItem == null) {
					item.setBlog(blog);
					itemRepository.save(item);
				}	
			}
		} catch (RssException e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedDelay = 216000)
	public void reloadBlogs() {
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			saveItems(blog);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void saveBlog(Blog blog, String userName) {
		User user = userRepository.findByName(userName);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeBlog(int id) {
		blogRepository.delete(id);
		
	}

	public Blog findBlogById(int id) {
		return blogRepository.findOne(id);
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void removeBlog(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

}
