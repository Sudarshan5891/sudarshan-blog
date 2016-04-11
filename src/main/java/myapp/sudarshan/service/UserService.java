package myapp.sudarshan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import myapp.sudarshan.entity.*;
import myapp.sudarshan.repository.BlogRepository;
import myapp.sudarshan.repository.ItemRepository;
import myapp.sudarshan.repository.RoleRepository;
import myapp.sudarshan.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findDetailsById(int id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public User findDetailsByIdWithBlogs(int id) {

		User user = findDetailsById(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 3, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}

		user.setBlogs(blogs);
		return user;
	}

	@Transactional
	public void save(User user) {
		user.setEnabled(true);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		userRepository.save(user);
	}

	@Transactional
	public User findBlogsByUser(String user) {
		User usr = userRepository.findByName(user);
		return findDetailsByIdWithBlogs(usr.getId());
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeUser(int id) {
		userRepository.delete(id);
	}

	public User findOne(String userName) {
		return userRepository.findByName(userName);
	}
}
