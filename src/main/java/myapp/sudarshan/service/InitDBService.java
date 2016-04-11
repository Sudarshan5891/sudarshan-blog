package myapp.sudarshan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myapp.sudarshan.entity.Blog;
import myapp.sudarshan.entity.Item;
import myapp.sudarshan.entity.Role;
import myapp.sudarshan.entity.User;
import myapp.sudarshan.repository.BlogRepository;
import myapp.sudarshan.repository.ItemRepository;
import myapp.sudarshan.repository.RoleRepository;
import myapp.sudarshan.repository.UserRepository;

@Transactional
@Service
public class InitDBService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		
		if(roleRepository.findByName("ROLE_ADMIN") == null) {

		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setName("admin");
		userAdmin.setEnabled(true);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));

		List<Role> roles = new ArrayList<>();
		roles.add(roleUser);
		roles.add(roleAdmin);

		userAdmin.setRoles(roles);

		userRepository.save(userAdmin);

//		Blog blogVid = new Blog();
//		blogVid.setName("JavaVideo");
//		blogVid.setUrl("http://java.video.net");
//		blogVid.setUser(userAdmin);
//		blogRepository.save(blogVid);
//
//		Item item1 = new Item();
//		item1.setBlog(blogVid);
//		item1.setTitle("first");
//		item1.setLink("http://java.videos.net");
//		item1.setPublishedDate(new Date());
//
//		Item item2 = new Item();
//		item2.setBlog(blogVid);
//		item2.setTitle("second");
//		item2.setLink("http://java1.videos.net");
//		item2.setPublishedDate(new Date());
//
//		Item item3 = new Item();
//		item3.setBlog(blogVid);
//		item3.setTitle("ftsecond");
//		item3.setLink("http://java1.videos.net");
//		item3.setPublishedDate(new Date());	
//
//		Item item4 = new Item();
//		item4.setBlog(blogVid);
//		item4.setTitle("ssec");
//		item4.setLink("http://java1.videos.net");
//		item4.setPublishedDate(new Date());	
//		
//		itemRepository.save(item1);
//		itemRepository.save(item2);
//		itemRepository.save(item3);
//		itemRepository.save(item4);
		
		}

	}
}
