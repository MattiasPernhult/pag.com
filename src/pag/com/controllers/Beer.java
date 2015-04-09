package pag.com.controllers;

import java.util.ArrayList;
import java.util.List;

import pag.com.beans.BeerBean;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;
import pag.com.util.ApiKeys;

public class Beer {

	public static List<BeerBean> getBeers() {
		List<BeerBean> beers = new ArrayList<BeerBean>();
		int iterator = 0;

		while (iterator < 1) {
			String response = HttpManager
					.getData("http://api.brewerydb.com/v2/beer/random/?key="
							+ ApiKeys.getBeerKey() + "&hasLabels=Y");

			BeerBean beer = JSONParser.parseBeer(response);
			if (beer != null) {
				beers.add(beer);
				iterator++;
			}
		}
		return beers;
	}
}
