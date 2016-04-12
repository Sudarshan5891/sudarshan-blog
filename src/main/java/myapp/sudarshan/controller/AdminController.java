package myapp.sudarshan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import myapp.sudarshan.service.UserService;

@Controller
@Scope("session")
@RequestMapping("/users")
public class AdminController {
	
	@Autowired
	private UserService userService;	
	
	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	@RequestMapping("/{id}")
	public String details(Model model, @PathVariable int id) {		
		model.addAttribute("user", userService.findDetailsByIdWithBlogs(id));
		return "user-detail";
	}	
	
	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.removeUser(id);
		return "redirect:/users.htm";
	}	

}
