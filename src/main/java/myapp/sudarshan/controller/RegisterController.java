package myapp.sudarshan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import myapp.sudarshan.entity.User;
import myapp.sudarshan.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User userConstruct() {
		return new User();
	}	

	@RequestMapping
	public String userRegister() {
		return "user-register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return "user-register";

		userService.save(user);
		
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/register.htm";
	}
	
	@RequestMapping("/available") 
	@ResponseBody
	public String isUserAlreadyAvailable(@RequestParam String userName) {
		Boolean userAvail = userService.findOne(userName) == null;
		return userAvail.toString();
	}	

}
