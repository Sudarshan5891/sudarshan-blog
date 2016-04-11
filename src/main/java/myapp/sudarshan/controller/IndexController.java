package myapp.sudarshan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myapp.sudarshan.service.ItemService;

@Controller
@Scope("request")
public class IndexController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("items",	itemService.getItems());
		return "index";
	}

}
