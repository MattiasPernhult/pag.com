package pag.com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import pag.com.beans.BeerBean;
import pag.com.models.Links;
import pag.com.beans.MovieBean;
import pag.com.beans.SnacksBean;
import pag.com.beans.WineBean;
import pag.com.controllers.Beer;
import pag.com.controllers.Movie;
import pag.com.controllers.Snack;
import pag.com.controllers.Wine;

public class Helper {

	public static void addLinkToMovie(MovieBean movie) {
		int movieId = movie.getId();
		Links link = new Links();
		link.setName("actors");
		link.setLink("localhost:8080/pag.com/api/movies/" + movieId + "/actors");
		movie.setLinks(link);
	}

	public static MovieBean getRandomMovie() {
		return Movie.randomMovie();
	}

	public static SnacksBean getRandomSnacksFromList(List<SnacksBean> snacks) {
		Random random = new Random();
		int randomNumber = random.nextInt(snacks.size());
		return snacks.get(randomNumber);
	}

	public static Object getDrinks() {
		Random random = new Random();
		List<?> genericList;
		int number = random.nextInt(2);
		if (number == 0) {
			genericList = findBeersForMovie(null);
		} else {
			genericList = findWinesForMovie(null, random.nextInt(2));
		}
		return genericList.get(random.nextInt(genericList.size()));
	}

	public static Object getDrinksForMovie(List<String> genres) {
		Random random = new Random();
		List<?> genericList;
		if (genres.contains("Animation")) {
			genericList = Snack.getSnacks(17, 0);
		} else if (genres.contains("Romance")) {
			genericList = findWinesForMovie(genres, random.nextInt(2));
		} else {
			int number = random.nextInt(3);
			if (number == 0) {
				genericList = findBeersForMovie(genres);
			} else if (number == 1) {
				genericList = findWinesForMovie(genres, random.nextInt(2));
			} else {
				genericList = Snack.getSnacks(17, 0);
			}
		}
		return genericList.get(random.nextInt(genericList.size()));
	}

	public static Object getDrinksForMovie(List<String> genres, int drinkId) {
		Random random = new Random();
		List<?> genericList;

		if (drinkId != 0) {
			if (drinkId == 1) {
				genericList = findWinesForMovie(genres, random.nextInt(2));
				return genericList.get(random.nextInt(genericList.size()));
			} else if (drinkId == 2) {
				genericList = findBeersForMovie(genres);
				return genericList.get(random.nextInt(genericList.size()));
			} else if (drinkId == 3) {
				genericList = Snack.getSnacks(17, 0);
				return genericList.get(random.nextInt(genericList.size()));
			}
		}

		if (genres.contains("Animation")) {
			genericList = Snack.getSnacks(17, 0);
		} else if (genres.contains("Romance")) {
			genericList = findWinesForMovie(genres, random.nextInt(2));
		} else {
			int number = random.nextInt(3);
			if (number == 0) {
				genericList = findBeersForMovie(genres);
			} else if (number == 1) {
				genericList = findWinesForMovie(genres, random.nextInt(2));
			} else {
				genericList = Snack.getSnacks(17, 0);
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
			loopList(snacks, Snack.getSnacks(13, 0));
			return snacks;
		}
		if (genres.contains("Western")) {
			loopList(snacks, Snack.searchSnacksTitleAndDescription("wild"));
			loopList(snacks, Snack.searchSnacksTitleAndDescription("west"));
			loopList(snacks, Snack.searchSnacksTitleAndDescription("ranch"));
			return snacks;
		}

		if (genres.contains("Animation") || genres.contains("Family")) {
			loopList(snacks, Snack.getSnacks(2, 0));
			loopList(snacks, Snack.getSnacks(3, 0));
			loopList(snacks, Snack.getSnacks(1, 0));
		}
		if (snacks.isEmpty()) {
			loopList(snacks, Snack.getSnacks(0, 0));
		}
		return snacks;
	}

	public static List<SnacksBean> findSnacksForMovie(List<String> genres,
			int kind) {
		List<SnacksBean> snacks = new ArrayList<SnacksBean>();

		if (kind != 0) {
			loopList(snacks, Snack.getSnacks(kind, 0));
			return snacks;
		}
		if (genres.contains("Romance")) {
			loopList(snacks, Snack.getSnacks(12, 0));
			return snacks;
		}
		if (genres.contains("Western")) {
			loopList(snacks, Snack.searchSnacksTitleAndDescription("wild"));
			loopList(snacks, Snack.searchSnacksTitleAndDescription("west"));
			loopList(snacks, Snack.searchSnacksTitleAndDescription("ranch"));
			return snacks;
		}
		if (genres.contains("Animation") || genres.contains("Family")) {
			loopList(snacks, Snack.getSnacks(2, 0));
			loopList(snacks, Snack.getSnacks(3, 0));
			loopList(snacks, Snack.getSnacks(1, 0));
		}
		if (snacks.isEmpty()) {
			loopList(snacks, Snack.getSnacks(0, 0));
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
	
	public static String getSnackKind() {
		return Snack.getSnackKind();
	}
}
