package pag.com.models;

import java.util.List;

import pag.com.beans.WineBean;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;

public class Wine {
	
	public static List<WineBean> getRedWines() {
		String response = HttpManager.getData("http://api.snooth.com/wines/?akey=hepr7g1ie179vnororwa7maofkymji7tqj38ne9qmpzenari&q=wine&color=red&t=wine&c=SE&s=&mr=3&n=10&xp=20");
		return JSONParser.wineParser(response);
	}
	
	public static List<WineBean> getWhiteWines() {
		String response = HttpManager.getData("http://api.snooth.com/wines/?akey=hepr7g1ie179vnororwa7maofkymji7tqj38ne9qmpzenari&q=wine&color=white&t=wine&c=SE&s=&mr=3&n=10&xp=20");
		return JSONParser.wineParser(response);
	}

}
