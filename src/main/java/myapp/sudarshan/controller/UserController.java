package myapp.sudarshan.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import myapp.sudarshan.entity.Blog;
import myapp.sudarshan.entity.User;
import myapp.sudarshan.repository.UserRepository;
import myapp.sudarshan.service.BlogService;
import myapp.sudarshan.service.UserService;

@Controller
@Scope("request")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@ModelAttribute("blog")
	public Blog blogConstruct() {
		return new Blog();
	}	
	
	@RequestMapping("/accounts")
	public String userAccount(Model model, Principal principal) {
		String user = principal.getName();
		model.addAttribute("user", userService.findBlogsByUser(user));
		
		return "account-detail";
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public String addBlogToUser(Model model, @Valid @ModelAttribute("blog") Blog blog, Principal principal, BindingResult result) {
		
		if(result.hasErrors())
			return userAccount(model, principal);
		
		String user = principal.getName();
		blogService.saveBlog(blog, user);
		return "redirect:/accounts.htm";
	}	
	
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id) {
		Blog blog = blogService.findBlogById(id);
		blogService.removeBlog(blog);
		return "redirect:/accounts.htm";
	}
	
}
