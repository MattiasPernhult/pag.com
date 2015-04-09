package pag.com.controllers;

import java.util.List;

import pag.com.beans.WineBean;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;
import pag.com.util.ApiKeys;

public class Wine {

	public static List<WineBean> getRedWines() {
		String response = HttpManager
				.getData("http://api.snooth.com/wines/?akey="
						+ ApiKeys.getWineKey()
						+ "&q=wine&color=red&t=wine&c=SE&s=&mr=3&n=10&xp=20");
		return JSONParser.wineParser(response);
	}

	public static List<WineBean> getWhiteWines() {
		String response = HttpManager
				.getData("http://api.snooth.com/wines/?akey="
						+ ApiKeys.getWineKey()
						+ "&q=wine&color=white&t=wine&c=SE&s=&mr=3&n=10&xp=20");
		return JSONParser.wineParser(response);
	}
}
