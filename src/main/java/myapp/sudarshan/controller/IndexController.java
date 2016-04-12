package myapp.sudarshan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import myapp.sudarshan.entity.Item;
import myapp.sudarshan.service.ItemService;

@Controller
@Scope("session")
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping
	public String index(Model model) {
		model.addAttribute("items",	itemService.getItems());
		return "index";
	}
	
	@RequestMapping(value="/paginate", produces = {"application/json"})
	//, produces = {"text/plain", "application/*"}, 
	//		headers = "content-type=text/plain")
	public @ResponseBody String indexPage(@RequestParam String page) {
		Item item = new Item();
		item.setId(2);
		return "jhgj";
	}

}
