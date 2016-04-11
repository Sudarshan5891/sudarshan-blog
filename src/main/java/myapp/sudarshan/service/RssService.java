package myapp.sudarshan.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;

import myapp.sudarshan.entity.Item;
import myapp.sudarshan.exceptions.RssException;
import myapp.sudarshan.rss.ObjectFactory;
import myapp.sudarshan.rss.TRss;
import myapp.sudarshan.rss.TRssChannel;
import myapp.sudarshan.rss.TRssItem;

@Service
public class RssService {
	
	public List<Item> getItems(File file) throws RssException {
		return getItems(new StreamSource(file));
	}
	
	public List<Item> getItems(String url) throws RssException {
		return getItems(new StreamSource(url));
	}
	
	private List<Item> getItems(Source source) throws RssException {
		
		List<Item> itemList = new ArrayList<>();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			TRss trss = unmarshaller.unmarshal(source, TRss.class).getValue();
			List<TRssChannel> channels = trss.getChannel();
			for (TRssChannel tRssChannel : channels) {
				List<TRssItem> items = tRssChannel.getItem();
				for (TRssItem tRssItem : items) {
					
					Item item = new Item();
					item.setTitle(tRssItem.getTitle());
					item.setDescription(tRssItem.getDescription());
					item.setLink(tRssItem.getLink());
					
					SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z",Locale.ENGLISH);
					item.setPublishedDate(formatter.parse(tRssItem.getPubDate()));
					
					itemList.add(item);		
				}
			}
			
			return itemList;
		} catch (JAXBException e) {
			throw new RssException(e);
		} catch (ParseException e) {
			throw new RssException(e);
		}
	}

}
