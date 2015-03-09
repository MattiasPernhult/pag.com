package pag.com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import pag.com.beans.BeerBean;
import pag.com.beans.SnacksBean;
import pag.com.beans.WineBean;
import pag.com.models.Beer;
import pag.com.models.Snack;
import pag.com.models.Wine;

public class Helper {
	
	public static String replaceSpaces(String content) {
		return content.replace(' ', '+');
	}
	
	public static SnacksBean getRandomSnacksFromList(List<SnacksBean> snacks) {
		Random random = new Random();
		int randomNumber = random.nextInt(snacks.size());
		return snacks.get(randomNumber);
	}
	
	public static Object getDrinksForMovie(List<String> genres) {
		Random random = new Random();
		List<?> genericList;
		if (genres.contains("Romance")) {
			genericList = findWinesForMovie(genres, random.nextInt(2));
		} else {
			int number = random.nextInt(2);
			if (number == 0) {
				genericList = findBeersForMovie(genres);
			} else {
				genericList = findWinesForMovie(genres, random.nextInt(2));
			}
		}
		return genericList.get(random.nextInt(genericList.size()));
	}
	
	public static List<?> findBeersForMovie(List<String> genres) {
		return Beer.getBeers();
	}
	
	public static List<?> findWinesForMovie(List<String> genres, int wineIndex) {
		if (wineIndex == 0) {
			return Wine.getRedWines();
		} else {
			return Wine.getWhiteWines();
		}
	}
	
	public static List<SnacksBean> findSnacksForMovie(List<String> genres) {
		List<SnacksBean> snacks = new ArrayList<SnacksBean>();
		if (genres.contains("Romance")) {
			loopList(snacks, Snack.getSnacks(12, 0));
		} else if (genres.contains("Western")) {
			loopList(snacks, Snack.searchSnacks("wild"));
			loopList(snacks, Snack.searchSnacks("west"));
			loopList(snacks, Snack.searchSnacks("ranch"));
		} else {
			loopList(snacks, Snack.getSnacks(1, 1));
			loopList(snacks, Snack.getSnacks(1, 2));
		}
		return snacks;
	}
	
	private static <T> void loopList(List<T> origList, List<T> tempList) {
		for (T object : tempList) {
			origList.add(object);
		}
	}
	
	public static String fixWineRegion(String originalRegion) {
		String[] regionParts = originalRegion.split(">");
		List<String> list = Arrays.asList(regionParts);
		
		Collections.reverse(list);
		
		regionParts = (String[]) list.toArray();
		
		String region = "";
		
		for (int i = 1; i <= regionParts.length; i++) {
			region += regionParts[i - 1];
			if (!(i == regionParts.length)) {
				region += ", ";
			}
		}
		return region.trim();
	}
}
