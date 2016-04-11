package myapp.sudarshan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import myapp.sudarshan.entity.Item;
import myapp.sudarshan.repository.ItemRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getItems() {
		return itemRepository.findAll(new PageRequest(0, 10, Direction.DESC, "publishedDate")).getContent();
	}

}
