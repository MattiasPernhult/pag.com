package pag.com.models;

import java.util.List;

import pag.com.beans.SnacksBean;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;

public class Snack {

	public static List<SnacksBean> getSnacks(int kind, int brand) {
		String response = HttpManager.getData("http://localhost:8080/snacks.com/api/snacks/discover?kind=" + kind +"&brand=" + brand);
		return JSONParser.parseSnacks(response);
	}
	
	public static List<SnacksBean> searchSnacks(String searchTerm) {
		String response = HttpManager.getData("http://localhost:8080/snacks.com/api/snacks/search?name=" + searchTerm);
		return JSONParser.parseSnacks(response);
	}
	
}
