package pag.com.models;

import java.util.ArrayList;
import java.util.List;

import pag.com.beans.BeerBean;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;

public class Beer {
	
	public static List<BeerBean> getBeers() {
		List<BeerBean> beers = new ArrayList<BeerBean>();
		int iterator = 0;
		
		while (iterator < 1) {
			String response = HttpManager.getData("http://api.brewerydb.com/v2/beer/random/?key=8116acd427ca82e9b5ac71c5e7d617bb&hasLabels=Y");
			
			BeerBean beer = JSONParser.parseBeer(response);
			if (beer != null) {
				beers.add(beer);
				iterator++;
			}
		}
		return beers;
	}
}
